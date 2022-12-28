package com.rikarin.electrocraft

import com.rikarin.electrocraft.item.*
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
