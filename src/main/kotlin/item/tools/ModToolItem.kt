package com.rikarin.electrocraft.item.tools

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterial

abstract class ModToolItem(material: ToolMaterial, settings: Settings) : ToolItem(material, settings.maxCount(1).maxDamage(100)) {
    private val attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier>

    init {
        val builder = ImmutableMultimap.builder<EntityAttribute, EntityAttributeModifier>()
        builder.put(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            EntityAttributeModifier(
                ATTACK_DAMAGE_MODIFIER_ID,
                "Tool modifier",
                material.attackDamage.toDouble(),
                EntityAttributeModifier.Operation.ADDITION
            )
        )
        builder.put(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            EntityAttributeModifier(
                ATTACK_SPEED_MODIFIER_ID,
                "Tool modifier",
                1.0,
//                attackSpeed.toDouble(),
                EntityAttributeModifier.Operation.ADDITION
            )
        )

        this.attributeModifiers = builder.build()
    }
    override fun hasRecipeRemainder() = true
    override fun canRepair(stack: ItemStack, ingredient: ItemStack) = false

    override fun getRecipeRemainder(stack: ItemStack): ItemStack {
        val newStack = stack.copy()
        newStack.damage++

        return if (newStack.damage < newStack.maxDamage) newStack else ItemStack.EMPTY
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute, EntityAttributeModifier> {
        return if (slot == EquipmentSlot.MAINHAND) attributeModifiers else super.getAttributeModifiers(slot)
    }
}
