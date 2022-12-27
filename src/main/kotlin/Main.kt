package com.rikarin.electrocraft

import com.rikarin.electrocraft.items.*

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

const val MOD_ID = "electrocraft"

@Suppress("UNUSED")
object Main : ModInitializer {
    override fun onInitialize() {
        ModItems.registerItems()

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
