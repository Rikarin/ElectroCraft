package com.rikarin.electrocraft.item.tools

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.BlockState
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.BlockTags

class NanoMinerItem : ModToolItem(ModToolMaterials.NANO, FabricItemSettings().fireproof()) {
    override fun getMiningSpeedMultiplier(stack: ItemStack, state: BlockState) = material.miningSpeedMultiplier

    override fun isSuitableFor(state: BlockState): Boolean {
        return state.isIn(BlockTags.PICKAXE_MINEABLE)
                || state.isIn(BlockTags.AXE_MINEABLE)
                || state.isIn(BlockTags.HOE_MINEABLE)
                || state.isIn(BlockTags.SHOVEL_MINEABLE)
    }
}
