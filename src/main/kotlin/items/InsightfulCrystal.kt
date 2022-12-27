package com.rikarin.electrocraft.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class InsightfulCrystal : Item(FabricItemSettings().maxDamage(500)) {
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)

        if (user.isSneaking) {
            if (stack.storedLevel > 0) {
                stack.storedLevel--
                user.addExperienceLevels(1)
            }
        } else {
            if (user.experienceLevel > 0 && stack.storedLevel < 500) {
                stack.storedLevel++
                user.addExperienceLevels(-1)
            }
        }

        return TypedActionResult(ActionResult.PASS, stack)
    }

    override fun getItemBarColor(stack: ItemStack) = 0x00FF00
}

private var ItemStack.storedLevel: Int
    get() = (500 - damage) % 500
    set(value) {
        damage = (500 - value) % 500
    }

//fun getStoredLevel(stack: ItemStack): Int? {
//    if (!stack.isOf(INSIGHTFUL_CRYSTAL_ITEM)) {
//        throw Exception("error")
//    }
//
//    return stack.nbt?.getInt("level")
//}
//
//fun storeLevel(stack: ItemStack, value: Int) {
//    if (!stack.isOf(INSIGHTFUL_CRYSTAL_ITEM)) {
//        throw Exception("error")
//    }
//
//    stack.getOrCreateNbt().putInt("level", value)
//}