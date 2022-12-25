package com.rikarin.electrocraft.items

import com.rikarin.electrocraft.*
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
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import java.util.function.Consumer


private val PLATE_GROUP = FabricItemGroup.builder(Identifier(MOD_ID, "plates"))
    .icon{ ItemStack(Plate.IRON) }
    .build()

enum class Plate : ItemConvertible {
    COPPER, BRONZE, IRON, GOLD, LAPIS, LEAD, OBSIDIAN, STEEL, TIN,
    DENSE_COPPER, DENSE_BRONZE, DENSE_IRON, DENSE_GOLD, DENSE_LAPIS, DENSE_LEAD, DENSE_OBSIDIAN, DENSE_STEEL, DENSE_TIN;

    val item = Item(FabricItemSettings())
    val itemName = this.toString().lowercase()
    val identifier = Identifier(MOD_ID, "plate/$itemName")

    override fun asItem() = item
}

fun registerPlateItems() {
    val group = ItemGroupEvents.modifyEntriesEvent(PLATE_GROUP)

    Plate.values().forEach {
        Registry.register(Registries.ITEM, it.identifier, it.item)
        group.register { x -> x.add(it) }
    }
}

class PlateItemGenerator(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun getName() = "Plate Item Generator"

    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) { }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        Plate.values().forEach {
            itemModelGenerator.register(it.item, Models.GENERATED)
        }
    }
}

class PlateRecipeGenerator(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun getName() = "Plate Recipe Generator"

    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        fun craft(plate: Plate, item: ItemConvertible) {
            ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, plate)
                .criterion(RecipeProvider.hasItem(plate), RecipeProvider.conditionsFromItem(plate))
                .input(item)
                .input(HAMMER_TOOL)
                .offerTo(exporter, Identifier(MOD_ID, "${item.asItem()}_${plate.itemName}"))
        }

        craft(Plate.COPPER, Items.COPPER_INGOT)
        // bronze
        craft(Plate.IRON, Items.IRON_INGOT)
        craft(Plate.GOLD, Items.GOLD_INGOT)
        // TODO

        craft(Plate.DENSE_COPPER, Plate.COPPER)
        craft(Plate.DENSE_BRONZE, Plate.BRONZE)
        craft(Plate.DENSE_IRON, Plate.IRON)
        craft(Plate.DENSE_GOLD, Plate.GOLD)
        craft(Plate.DENSE_LAPIS, Plate.LAPIS)
        craft(Plate.DENSE_LEAD, Plate.LEAD)
        craft(Plate.DENSE_OBSIDIAN, Plate.OBSIDIAN)
        craft(Plate.DENSE_STEEL, Plate.STEEL)
        craft(Plate.DENSE_TIN, Plate.TIN)
    }
}
