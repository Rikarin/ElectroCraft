package com.rikarin.electrocraft.block

import com.rikarin.electrocraft.MOD_ID
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


val MACHINE_CASING = MachineCasing()
val MACHINE_CASING_ITEM = BlockItem(MACHINE_CASING, FabricItemSettings())

val ADVANCED_MACHINE_CASING = MachineCasing()
val ADVANCED_MACHINE_CASING_ITEM = BlockItem(ADVANCED_MACHINE_CASING, FabricItemSettings())

val MACHINES_GROUP = FabricItemGroup.builder(Identifier(MOD_ID, "machines"))
    .icon{ ItemStack(MACHINE_CASING) }
    .build()

// TODO: handle tool
class MachineCasing : Block(FabricBlockSettings.of(Material.METAL).strength(4f))

fun registerMachineCasing() {
    val machineCasing = Identifier(MOD_ID, "machine_casing")
    Registry.register(Registries.BLOCK, machineCasing, MACHINE_CASING)
    Registry.register(Registries.ITEM, machineCasing, MACHINE_CASING_ITEM)

    val advancedMachineCasing = Identifier(MOD_ID, "advanced_machine_casing")
    Registry.register(Registries.BLOCK, advancedMachineCasing, ADVANCED_MACHINE_CASING)
    Registry.register(Registries.ITEM, advancedMachineCasing, ADVANCED_MACHINE_CASING_ITEM)

    ItemGroupEvents.modifyEntriesEvent(MACHINES_GROUP)
        .register { x ->
        x.add(MACHINE_CASING_ITEM)
        x.add(ADVANCED_MACHINE_CASING_ITEM)
    }
}