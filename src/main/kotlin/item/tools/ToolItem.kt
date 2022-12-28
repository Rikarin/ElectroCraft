package com.rikarin.electrocraft.item.tools

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

abstract class ToolItem(settings: Settings) : Item(settings.maxCount(1).maxDamage(100)) {
    override fun hasRecipeRemainder() = true

    override fun getRecipeRemainder(stack: ItemStack): ItemStack {
        val newStack = stack.copy()
        newStack.damage++

        return if (newStack.damage < newStack.maxDamage) newStack else ItemStack.EMPTY
    }
}