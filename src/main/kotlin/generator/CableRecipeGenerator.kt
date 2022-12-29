package com.rikarin.electrocraft.generator

import com.rikarin.electrocraft.MOD_ID
import com.rikarin.electrocraft.item.CUTTER_TOOL
import com.rikarin.electrocraft.item.CableItem
import com.rikarin.electrocraft.item.PlateItem
import com.rikarin.electrocraft.item.RUBBER
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.ItemConvertible
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.util.Identifier
import java.util.function.Consumer

class CableRecipeGenerator(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun getName() = "CableItem Recipe Generator"

    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        fun craft(cable: CableItem, item: ItemConvertible) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, cable, 3)
                .criterion(hasItem(cable), conditionsFromItem(cable))
                .input(item)
                .input(CUTTER_TOOL)
                .offerTo(exporter, Identifier(MOD_ID, "${item.asItem()}_${cable.itemName}"))
        }

        craft(CableItem.COPPER_0, PlateItem.COPPER)
        craft(CableItem.TIN_0, PlateItem.TIN)
        craft(CableItem.GOLD_0, PlateItem.GOLD)
        craft(CableItem.IRON_0, PlateItem.IRON)

        fun insulated(cable: CableItem, source: CableItem) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, cable)
                .criterion(hasItem(cable), conditionsFromItem(cable))
                .input(source)
                .input(RUBBER)
                .offerTo(exporter, Identifier(MOD_ID, "${source.asItem()}_${cable.itemName}"))
        }

        insulated(CableItem.COPPER_1, CableItem.COPPER_0)
        insulated(CableItem.TIN_1, CableItem.TIN_0)
        insulated(CableItem.GOLD_1, CableItem.GOLD_0)
        insulated(CableItem.GOLD_2, CableItem.GOLD_1)
        insulated(CableItem.IRON_1, CableItem.IRON_0)
        insulated(CableItem.IRON_2, CableItem.IRON_1)
        insulated(CableItem.IRON_3, CableItem.IRON_2)
    }
}