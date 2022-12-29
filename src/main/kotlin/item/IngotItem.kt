package com.rikarin.electrocraft.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible

enum class IngotItem : ItemConvertible {
    TIN;

    val item = Item(FabricItemSettings())
    val itemName = "resource/${toString().lowercase()}_ingot"

    override fun asItem() = item
}
