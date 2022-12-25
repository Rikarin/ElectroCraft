package com.rikarin.electrocraft

import com.rikarin.electrocraft.block.registerMachineCasing
import com.rikarin.electrocraft.items.*
import com.rikarin.electrocraft.items.tools.CutterTool
import com.rikarin.electrocraft.items.tools.HammerTool
import com.rikarin.electrocraft.items.tools.WrenchTool
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


const val MOD_ID = "electrocraft"

val WRENCH_TOOL = WrenchTool(FabricItemSettings())
val CUTTER_TOOL = CutterTool(FabricItemSettings())
val HAMMER_TOOL = HammerTool(FabricItemSettings())

val TOOLS_GROUP = FabricItemGroup.builder(Identifier(MOD_ID, "tools"))
    .icon{ ItemStack(WRENCH_TOOL) }
    .build()


@Suppress("UNUSED")
object Main : ModInitializer {
    override fun onInitialize() {
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "tool/wrench"), WRENCH_TOOL)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "tool/cutter"), CUTTER_TOOL)
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "tool/hammer"), HAMMER_TOOL)

        registerPlateItems()
        registerCableItems()
        registerMachineCasing()
        registerCrystal()

        ItemGroupEvents.modifyEntriesEvent(TOOLS_GROUP)
            .register {
                it.add(WRENCH_TOOL)
                it.add(CUTTER_TOOL)
                it.add(HAMMER_TOOL)
            }

        // This is used to create dynamic cell
//        val fluids = Registries.FLUID.stream().collect(Collectors.toList())
//        for (fluid in fluids) {
//            if (fluid.isStill(fluid.defaultState)) {
//                println("foo bar $fluid")
//            }
//        }
    }
}

class DataGeneration : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()

        pack.addProvider(::PlateItemGenerator)
        pack.addProvider(::PlateRecipeGenerator)
        pack.addProvider(::CableItemGenerator)
        pack.addProvider(::CableRecipeGenerator)
    }
}
