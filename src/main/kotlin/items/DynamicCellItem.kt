package com.rikarin.electrocraft.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.FluidDrainable
import net.minecraft.block.FluidFillable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.Fluids
import net.minecraft.item.*
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.RaycastContext
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.event.GameEvent

enum class Cell(fluid: Fluid) : ItemConvertible {
    EMPTY(Fluids.EMPTY), WATER(Fluids.WATER), LAVA(Fluids.LAVA);

    val item = DynamicCellItem(fluid)
    val itemName = this.toString().lowercase()
//    val identifier = Identifier(MOD_ID, "cell/$itemName")

    val defaultStack get() = item.defaultStack
    override fun asItem() = item
}

class DynamicCellItem(val fluid: Fluid) : Item(FabricItemSettings()) {
    fun placeFluid(player: PlayerEntity, world: World, pos: BlockPos, hitResult: BlockHitResult?, filledCell: ItemStack): Boolean {
        val fluid = filledCell.fluid
        if (fluid == Fluids.EMPTY) {
            return false
        }

        val blockState = world.getBlockState(pos)
        val material = blockState.material
        val canPlace = blockState.canBucketPlace(fluid)

        if (!blockState.isAir && !canPlace && (!(blockState.block is FluidFillable) || !(blockState.block as FluidFillable).canFillWithFluid(world, pos, blockState, fluid))) {
            return hitResult != null && placeFluid(player, world, hitResult.blockPos.offset(hitResult.side), null, filledCell)
        }

        if (world.dimension.ultrawarm && fluid.isIn(FluidTags.WATER)) {
            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, .5f, 2.6f + (world.random.nextFloat() - world.random.nextFloat()) * .8f)

            for (l in 0..7) {
                world.addParticle(ParticleTypes.LARGE_SMOKE, pos.x + Math.random(), pos.y + Math.random(), pos.z + Math.random(), 0.0, 0.0, 0.0)
            }
        } else if (blockState.block is FluidFillable && fluid == Fluids.WATER) {
            if ((blockState.block as FluidFillable).tryFillWithFluid(world, pos, blockState, (fluid as FlowableFluid).getStill(false))) {
                playEmptyingSound(player, world, pos, fluid)
            }
        } else {
            if (!world.isClient && canPlace && !material.isLiquid) {
                world.breakBlock(pos, true)
            }

            playEmptyingSound(player, world, pos, fluid)
            world.setBlockState(pos, fluid.defaultState.blockState, 11)
        }

        return true
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        var stack = user.getStackInHand(hand)
        val fluid = stack.fluid

        val hit = raycast(world, user, if (fluid == Fluids.EMPTY) RaycastContext.FluidHandling.SOURCE_ONLY else RaycastContext.FluidHandling.NONE)

        if (hit.type == HitResult.Type.MISS || !(fluid is FlowableFluid || fluid == Fluids.EMPTY)) {
            return TypedActionResult.pass(stack)
        }

        if (hit.type != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(stack)
        }

        val hitPos = hit.blockPos
        val placePos = hitPos.offset(hit.side)

        if (!world.canPlayerModifyAt(user, hitPos)) {
            return TypedActionResult.fail(stack)
        }

        if (!user.canPlaceOn(placePos, hit.side, stack)) {
            return TypedActionResult.fail(stack)
        }

        if (fluid == Fluids.EMPTY) { // Empty cell item
            val hitState = world.getBlockState(hitPos)
            val fluidDrainable = hitState.block

            if (fluidDrainable !is FluidDrainable) {
                return TypedActionResult.fail(stack)
            }

            var itemStack = fluidDrainable.tryDrainFluid(world, hitPos, hitState)
            if (!itemStack.isEmpty) {
                // TODO
                val drainFluid = if(itemStack.item == Items.LAVA_BUCKET) Fluids.LAVA else Fluids.WATER

                fluidDrainable.bucketFillSound.ifPresent { user.playSound(it, 1f, 1f) }
                world.emitGameEvent(user, GameEvent.FLUID_PICKUP, hitPos)

                // Replace bucket item with cell item
//                itemStack = DynamicCellItem.getCellWithFluid(drainFluid, 1)
                itemStack = if (drainFluid == Fluids.LAVA) Cell.LAVA.defaultStack else Cell.WATER.defaultStack
                val resultStack = ItemUsage.exchangeStack(stack, user, itemStack, false)
                return TypedActionResult.success(resultStack, world.isClient())
            }
        } else {
            val placeState = world.getBlockState(placePos)
            if (placeState.canBucketPlace(fluid)) {
                placeFluid(user, world, placePos, hit, stack)

                if (user.abilities.creativeMode) {
                    return TypedActionResult.pass(stack)
                }

                if (stack.count == 1) {
                    stack = Cell.EMPTY.defaultStack
                } else {
                    stack.decrement(1)

                    val empty = Cell.EMPTY.defaultStack
                    if (!user.inventory.insertStack(empty)) {
                        user.dropStack(empty)
                    }
                }

                return TypedActionResult.pass(stack)
            }
        }

        return TypedActionResult.fail(stack)
    }

    private fun playEmptyingSound(playerEntity: PlayerEntity?, world: WorldAccess, blockPos: BlockPos, fluid: Fluid) {
        val soundEvent =
            if (fluid.isIn(FluidTags.LAVA)) SoundEvents.ITEM_BUCKET_EMPTY_LAVA else SoundEvents.ITEM_BUCKET_EMPTY
        world.playSound(playerEntity, blockPos, soundEvent, SoundCategory.BLOCKS, 1.0f, 1.0f)
    }
}


private val ItemStack.fluid: Fluid
    get() {
        val item = item
        if (item is DynamicCellItem) {
            return item.fluid
        }

        return Fluids.EMPTY
    }
