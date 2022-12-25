package com.rikarin.electrocraft.items

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class EmptyCellItem(settings: Settings) : Item(settings) {

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient) {
            return super.use(world, player, hand)
        }

        player.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1f, 1f)
        player.setOnFireFor(10)
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}