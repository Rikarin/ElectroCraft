package com.rikarin.electrocraft.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible

enum class CableItem : ItemConvertible {
    COPPER_0, COPPER_1,
    TIN_0, TIN_1,
    GOLD_0, GOLD_1, GOLD_2,
    IRON_0, IRON_1, IRON_2, IRON_3;

    val item = Item(FabricItemSettings())
    val itemName = "cable/${toString().lowercase()}"

    override fun asItem() = item
}
