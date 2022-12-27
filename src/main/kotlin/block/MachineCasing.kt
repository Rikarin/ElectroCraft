package com.rikarin.electrocraft.block

import com.rikarin.electrocraft.items.registerGroup
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material




// TODO: handle tool
class MachineCasing : Block(FabricBlockSettings.of(Material.METAL).strength(4f))

//fun registerMachineCasing() {
//    val machineCasing = Identifier(MOD_ID, "machine_casing")
//    Registry.register(Registries.BLOCK, machineCasing, MACHINE_CASING)
//    Registry.register(Registries.ITEM, machineCasing, MACHINE_CASING_ITEM)
//
//    val advancedMachineCasing = Identifier(MOD_ID, "advanced_machine_casing")
//    Registry.register(Registries.BLOCK, advancedMachineCasing, ADVANCED_MACHINE_CASING)
//    Registry.register(Registries.ITEM, advancedMachineCasing, ADVANCED_MACHINE_CASING_ITEM)
//
//    ItemGroupEvents.modifyEntriesEvent(MACHINES_GROUP)
//        .register { x ->
//        x.add(MACHINE_CASING_ITEM)
//        x.add(ADVANCED_MACHINE_CASING_ITEM)
//    }
//}