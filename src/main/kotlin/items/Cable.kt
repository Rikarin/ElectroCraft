package com.rikarin.electrocraft.items

import com.rikarin.electrocraft.CUTTER_TOOL
import com.rikarin.electrocraft.MOD_ID
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.RecipeProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import java.util.function.Consumer


private val CABLE_GROUP = FabricItemGroup.builder(Identifier(MOD_ID, "cables"))
    .icon{ ItemStack(Cable.IRON_3) }
    .build()


val RUBBER = Item(FabricItemSettings())

enum class Cable : ItemConvertible {
    COPPER_0, COPPER_1,
    TIN_0, TIN_1,
    GOLD_0, GOLD_1, GOLD_2,
    IRON_0, IRON_1, IRON_2, IRON_3;

    val item = Item(FabricItemSettings())
    val itemName = this.toString().lowercase()
    val identifier = Identifier(MOD_ID, "cable/$itemName")

    override fun asItem() = item
}

fun registerCableItems() {
    val group = ItemGroupEvents.modifyEntriesEvent(CABLE_GROUP)

    Registry.register(Registries.ITEM, Identifier(MOD_ID, "crafting/rubber"), RUBBER)
    group.register { x -> x.add(RUBBER) }

    Cable.values().forEach {
        Registry.register(Registries.ITEM, it.identifier, it.item)
        group.register { x -> x.add(it) }
    }
}

class CableItemGenerator(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun getName() = "Cable Item Generator"
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) { }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        Cable.values().forEach {
            itemModelGenerator.register(it.item, Models.GENERATED)
        }
    }
}

class CableRecipeGenerator(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun getName() = "Cable Recipe Generator"

    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        fun craft(cable: Cable, item: ItemConvertible) {
            ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, cable, 3)
                .criterion(RecipeProvider.hasItem(cable), RecipeProvider.conditionsFromItem(cable))
                .input(item)
                .input(CUTTER_TOOL)
                .offerTo(exporter, Identifier(MOD_ID, "${item.asItem()}_${cable.itemName}"))
        }

        craft(Cable.COPPER_0, Plate.COPPER)
        craft(Cable.TIN_0, Plate.TIN)
        craft(Cable.GOLD_0, Plate.GOLD)
        craft(Cable.IRON_0, Plate.IRON)

        fun insulated(cable: Cable, source: Cable) {
            ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, cable)
                .criterion(RecipeProvider.hasItem(cable), RecipeProvider.conditionsFromItem(cable))
                .input(source)
                .input(RUBBER)
                .offerTo(exporter, Identifier(MOD_ID, "${source.asItem()}_${cable.itemName}"))
        }

        insulated(Cable.COPPER_1, Cable.COPPER_0)
        insulated(Cable.TIN_1, Cable.TIN_0)
        insulated(Cable.GOLD_1, Cable.GOLD_0)
        insulated(Cable.GOLD_2, Cable.GOLD_1)
        insulated(Cable.IRON_1, Cable.IRON_0)
        insulated(Cable.IRON_2, Cable.IRON_1)
        insulated(Cable.IRON_3, Cable.IRON_2)
    }
}
