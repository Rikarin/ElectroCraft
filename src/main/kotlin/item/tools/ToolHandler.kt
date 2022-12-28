package com.rikarin.electrocraft.item.tools

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

interface ToolHandler {
    fun handleTool(stack: ItemStack, pos: BlockPos, world: World, player: PlayerEntity, side: Direction, damage: Boolean): Boolean
}
