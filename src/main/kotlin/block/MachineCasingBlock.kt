package com.rikarin.electrocraft.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material

// TODO: handle tool
class MachineCasingBlock : Block(FabricBlockSettings.of(Material.METAL).strength(4f))
