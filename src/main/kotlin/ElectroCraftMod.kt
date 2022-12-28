package com.rikarin.electrocraft

import com.rikarin.electrocraft.block.ModBlocks
import com.rikarin.electrocraft.item.*
import net.fabricmc.api.ModInitializer

const val MOD_ID = "electrocraft"

@Suppress("UNUSED")
object ElectroCraftMod : ModInitializer {
    override fun onInitialize() {
        ModItems.registerItems()
        ModBlocks.registerBlocks()
    }
}
