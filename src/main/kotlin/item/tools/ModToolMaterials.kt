package com.rikarin.electrocraft.item.tools

import net.minecraft.item.ToolMaterial

enum class ModToolMaterials(
    private val miningLevel: Int,
    private val itemDurability: Int,
    private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int
) : ToolMaterial {
    // Used for Hammer, Cutter and Wrench
    BASIC(0, 0, 0f, 0f, 0),
    NANO(4, 0, 20f, 9.0f, 0);

    override fun getDurability() = itemDurability
    override fun getMiningSpeedMultiplier() = miningSpeed
    override fun getAttackDamage() = attackDamage
    override fun getMiningLevel() = miningLevel
    override fun getEnchantability() = enchantability
    override fun getRepairIngredient() = null
}
