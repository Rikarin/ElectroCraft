package com.rikarin.electrocraft.items.tools

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

class WrenchTool(settings: Settings) : ToolItem(settings), ToolHandler {
    override fun handleTool(
        stack: ItemStack,
        pos: BlockPos,
        world: World,
        player: PlayerEntity,
        side: Direction,
        damage: Boolean
    ): Boolean {
        if (!player.world.isClient && damage) {
            stack.damage(1, player.world.random, player as ServerPlayerEntity);
        }

        return true;
    }
}
