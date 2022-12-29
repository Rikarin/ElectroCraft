package com.rikarin.electrocraft.generator

import com.rikarin.electrocraft.MOD_ID
import com.rikarin.electrocraft.item.HAMMER_TOOL
import com.rikarin.electrocraft.item.PlateItem
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.util.Identifier
import java.util.function.Consumer

class PlateRecipeGenerator(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun getName() = "PlateItem Recipe Generator"

    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        fun craft(plate: PlateItem, item: ItemConvertible) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, plate)
                .criterion(hasItem(plate), conditionsFromItem(plate))
                .input(item)
                .input(HAMMER_TOOL)
                .offerTo(exporter, Identifier(MOD_ID, "${item.asItem()}_${plate.itemName}"))
        }

        craft(PlateItem.COPPER, Items.COPPER_INGOT)
        // bronze
        craft(PlateItem.IRON, Items.IRON_INGOT)
        craft(PlateItem.GOLD, Items.GOLD_INGOT)
        // TODO

        craft(PlateItem.DENSE_COPPER, PlateItem.COPPER)
        craft(PlateItem.DENSE_BRONZE, PlateItem.BRONZE)
        craft(PlateItem.DENSE_IRON, PlateItem.IRON)
        craft(PlateItem.DENSE_GOLD, PlateItem.GOLD)
        craft(PlateItem.DENSE_LAPIS, PlateItem.LAPIS)
        craft(PlateItem.DENSE_LEAD, PlateItem.LEAD)
        craft(PlateItem.DENSE_OBSIDIAN, PlateItem.OBSIDIAN)
        craft(PlateItem.DENSE_STEEL, PlateItem.STEEL)
        craft(PlateItem.DENSE_TIN, PlateItem.TIN)
    }
}