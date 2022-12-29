package com.rikarin.electrocraft.generator

import com.rikarin.electrocraft.item.CableItem
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models

class CableItemGenerator(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun getName() = "CableItem Item Generator"
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) { }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        CableItem.values().forEach {
            itemModelGenerator.register(it.item, Models.GENERATED)
        }
    }
}