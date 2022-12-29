package com.rikarin.electrocraft

import com.rikarin.electrocraft.generator.CableItemGenerator
import com.rikarin.electrocraft.generator.CableRecipeGenerator
import com.rikarin.electrocraft.generator.PlateItemGenerator
import com.rikarin.electrocraft.generator.PlateRecipeGenerator
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

class ElectroCraftDataGeneration : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()

        pack.addProvider(::PlateItemGenerator)
        pack.addProvider(::PlateRecipeGenerator)
        pack.addProvider(::CableItemGenerator)
        pack.addProvider(::CableRecipeGenerator)
    }
}
