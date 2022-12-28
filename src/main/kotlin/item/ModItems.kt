package com.rikarin.electrocraft.item

import com.rikarin.electrocraft.MOD_ID
import com.rikarin.electrocraft.item.tools.CutterTool
import com.rikarin.electrocraft.item.tools.HammerTool
import com.rikarin.electrocraft.item.tools.WrenchTool
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import java.util.function.Supplier

val TOOLS_GROUP: ItemGroup = registerGroup("tools") { WRENCH_TOOL }
val CABLE_GROUP: ItemGroup = registerGroup("cables") { Cable.IRON_3 }
val PLATE_GROUP: ItemGroup = registerGroup("plates") { Plate.IRON }

val WRENCH_TOOL = registerItem(WrenchTool(FabricItemSettings()), "tool/wrench", TOOLS_GROUP)
val CUTTER_TOOL = registerItem(CutterTool(FabricItemSettings()), "tool/cutter", TOOLS_GROUP)
val HAMMER_TOOL = registerItem(HammerTool(FabricItemSettings()), "tool/hammer", TOOLS_GROUP)

val INSIGHTFUL_CRYSTAL = registerItem(InsightfulCrystal(), "insightful_crystal", TOOLS_GROUP)
val RUBBER = registerItem(Item(FabricItemSettings()), "crafting/rubber", CABLE_GROUP)

class ModItems {
    companion object {
        fun registerItems() {
            Plate.values().forEach {
                registerItem(it.item, "plate/${it.itemName}", PLATE_GROUP)
            }

            Cable.values().forEach {
                registerItem(it.item, "cable/${it.itemName}", CABLE_GROUP)
            }

            Cell.values().forEach {
                registerItem(it.item, "cell/${it.itemName}", CABLE_GROUP)
            }
        }
    }
}

fun registerItem(item: Item, name: String, group: ItemGroup): Item {
    println("register item $name")

    ItemGroupEvents.modifyEntriesEvent(group).register { it.add(item) }
    return Registry.register(Registries.ITEM, Identifier(MOD_ID, name), item)
}

fun registerGroup(name: String, item: Supplier<ItemConvertible>): ItemGroup {
    println("register group $name")

    return FabricItemGroup.builder(Identifier(MOD_ID, name))
        .icon { ItemStack(item.get()) }
        .build()
}