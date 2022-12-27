package com.rikarin.electrocraft.block

import com.rikarin.electrocraft.MOD_ID
import com.rikarin.electrocraft.items.InsightfulCrystal
import com.rikarin.electrocraft.items.TOOLS_GROUP
import com.rikarin.electrocraft.items.registerGroup
import com.rikarin.electrocraft.items.registerItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

val MACHINES_GROUP = registerGroup("machines") { MACHINE_CASING }

val MACHINE_CASING = registerBlock(MachineCasing(), "machine_casing")
val MACHINE_CASING_ITEM = registerBlockItem(MACHINE_CASING, "machine_casing", MACHINES_GROUP)

val ADVANCED_MACHINE_CASING = registerBlock(MachineCasing(), "advanced_machine_casing")
val ADVANCED_MACHINE_CASING_ITEM = registerBlockItem(ADVANCED_MACHINE_CASING, "advanced_machine_casing", MACHINES_GROUP)

val INSIGHTFUL_CRYSTALtest = registerItem(InsightfulCrystal(), "insightful_crystal_test", TOOLS_GROUP)

class ModBlocks {
    companion object {
        fun registerBlocks() {
            // We need to reference global variables to initialize them
            MACHINE_CASING_ITEM
        }
    }
}

fun registerBlock(block: Block, name: String): Block {
    println("register block $name")
    return Registry.register(Registries.BLOCK, Identifier(MOD_ID, name), block)
}

fun registerBlockItem(block: Block, name: String, group: ItemGroup): Item {
    println("register block item $name")
    return registerItem(BlockItem(block, FabricItemSettings()), name, group)
}