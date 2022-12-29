package com.rikarin.electrocraft.item

import com.rikarin.electrocraft.MOD_ID
import com.rikarin.electrocraft.item.tools.*
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
val CABLE_GROUP: ItemGroup = registerGroup("cables") { CableItem.IRON_3 }
val RESOURCE_GROUP: ItemGroup = registerGroup("resources") { PlateItem.IRON }

val WRENCH_TOOL = registerItem("tool/wrench", WrenchToolItem(FabricItemSettings()), TOOLS_GROUP)
val CUTTER_TOOL = registerItem("tool/cutter", CutterTooItem(FabricItemSettings()), TOOLS_GROUP)
val HAMMER_TOOL = registerItem("tool/hammer", HammerToolItem(FabricItemSettings()), TOOLS_GROUP)
val NANO_MINER = registerItem("tool/nano_miner", NanoMinerItem(), TOOLS_GROUP)

val INSIGHTFUL_CRYSTAL = registerItem("insightful_crystal", InsightfulCrystalItem(), TOOLS_GROUP)
val RUBBER = registerItem("crafting/rubber", Item(FabricItemSettings()), CABLE_GROUP)

class ModItems {
    companion object {
        fun registerItems() {
            RawItem.values().forEach {
                registerItem(it.itemName, it.item, RESOURCE_GROUP)
            }

            IngotItem.values().forEach {
                registerItem(it.itemName, it.item, RESOURCE_GROUP)
            }

            PlateItem.values().forEach {
                registerItem(it.itemName, it.item, RESOURCE_GROUP)
            }

            CableItem.values().forEach {
                registerItem( it.itemName, it.item, CABLE_GROUP)
            }

            Cell.values().forEach {
                registerItem(it.itemName, it.item, CABLE_GROUP)
            }
        }
    }
}

fun registerItem(name: String, item: Item, group: ItemGroup): Item {
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


// This is used to create dynamic cell
//        val fluids = Registries.FLUID.stream().collect(Collectors.toList())
//        for (fluid in fluids) {
//            if (fluid.isStill(fluid.defaultState)) {
//                println("foo bar $fluid")
//            }
//        }