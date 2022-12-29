package com.rikarin.electrocraft.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible

enum class PlateItem : ItemConvertible {
    COPPER, BRONZE, IRON, GOLD, LAPIS, LEAD, OBSIDIAN, STEEL, TIN,
    DENSE_COPPER, DENSE_BRONZE, DENSE_IRON, DENSE_GOLD, DENSE_LAPIS, DENSE_LEAD, DENSE_OBSIDIAN, DENSE_STEEL, DENSE_TIN;

    val item = Item(FabricItemSettings())
    val itemName = "plate/${toString().lowercase()}"

    override fun asItem() = item
}
