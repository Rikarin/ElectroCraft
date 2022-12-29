package com.rikarin.electrocraft.block

import com.rikarin.electrocraft.MOD_ID
import com.rikarin.electrocraft.item.*
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.ExperienceDroppingBlock
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

val MACHINES_GROUP: ItemGroup = registerGroup("machines") { MACHINE_CASING }

val MACHINE_CASING = registerBlock("machine_casing", MachineCasingBlock(), MACHINES_GROUP)
val ADVANCED_MACHINE_CASING = registerBlock("advanced_machine_casing", MachineCasingBlock(), MACHINES_GROUP)

// TODO: move this to enum class in the same way as Ingots
val TIN_BLOCK = registerBlock("resource/tin_block", Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), RESOURCE_GROUP)
val TIN_ORE = registerBlock("resource/tin_ore", ExperienceDroppingBlock(FabricBlockSettings.copy(Blocks.IRON_ORE)), RESOURCE_GROUP)

class ModBlocks {
    companion object {
        fun registerBlocks() {
            // We need to reference global variables to initialize them
            MACHINE_CASING
        }
    }
}

fun registerBlock(name: String, block: Block, group: ItemGroup): Block {
    registerBlockItem(name, block, group)
    println("register block $name")
    return Registry.register(Registries.BLOCK, Identifier(MOD_ID, name), block)
}

fun registerBlockItem(name: String, block: Block, group: ItemGroup): Item {
    println("register block item $name")
    return registerItem(name, BlockItem(block, FabricItemSettings()), group)
}
