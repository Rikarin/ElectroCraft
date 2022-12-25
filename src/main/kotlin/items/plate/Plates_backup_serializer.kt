package com.rikarin.electrocraft.items.plate


//val PLATE_RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier(MOD_ID, "crafting_plates"), DynamicPlateRecipeSerializer())

//        Registry.register(Registries.RECIPE_SERIALIZER, PlateRecipeSerializer.ID, PlateRecipeSerializer.INSTANCE)

//class DynamicPlateRecipe(val tool: Ingredient, val ingot: Ingredient, private val outputStack: ItemStack, private val id: Identifier) : CraftingRecipe {
//    override fun matches(inventory: CraftingInventory, world: World) =
//        inventory.containsAny(tool) && inventory.containsAny(ingot)
//
//    override fun craft(inventory: CraftingInventory) = output.copy()
//    override fun fits(width: Int, height: Int) = false
//    override fun getOutput() = outputStack
//    override fun getId() = id
//    override fun getSerializer() = PLATE_RECIPE_SERIALIZER
//    override fun getCategory() = CraftingRecipeCategory.MISC
//
//    override fun getRemainder(inventory: CraftingInventory): DefaultedList<ItemStack> {
//        val defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
//
//        for (i in 0 .. defaultedList.size) {
//            val itemStack = inventory.getStack(i)
//            if (!itemStack.isEmpty && itemStack.item is ToolItem) {
//                val itemStack2 = itemStack.copy()
//                itemStack2.count = 1
//                itemStack2.damage++
//
//                if (itemStack2.damage < itemStack2.maxDamage) {
//                    defaultedList[i] = itemStack2
//                }
//            }
//        }
//
//        return defaultedList;
//    }
//}

//
//private data class PlateRecipeOutput(val item: String, val amount: Int?)
//private data class PlateRecipeFormat(val tool: JsonObject, val ingot: JsonObject, val output: PlateRecipeOutput)
//
//class DynamicPlateRecipeSerializer : RecipeSerializer<DynamicPlateRecipe> {
//    override fun read(id: Identifier, json: JsonObject): DynamicPlateRecipe {
//        val recipe = Gson().fromJson(json, PlateRecipeFormat::class.java)
//        val tool = Ingredient.fromJson(recipe.tool)
//        val ingot = Ingredient.fromJson(recipe.ingot)
//        val outputItem = Registries.ITEM.getOrEmpty(Identifier(recipe.output.item)).get()
//        val output = ItemStack(outputItem, recipe.output.amount ?: 1)
//
//        return DynamicPlateRecipe(tool, ingot, output, id)
//    }
//
//    override fun read(id: Identifier, buf: PacketByteBuf): DynamicPlateRecipe {
//        val tool = Ingredient.fromPacket(buf)
//        val ingot = Ingredient.fromPacket(buf)
//        val output = buf.readItemStack()
//
//        return DynamicPlateRecipe(tool, ingot, output, id)
//    }
//
//    override fun write(buf: PacketByteBuf, recipe: DynamicPlateRecipe) {
//        recipe.tool.write(buf)
//        recipe.ingot.write(buf)
//        buf.writeItemStack(recipe.output)
//    }
//}
//
//








//
//
//
//class PlateRecipe(val tool: Ingredient, val ingot: Ingredient, private val outputStack: ItemStack, private val id: Identifier) : Recipe<CraftingInventory> {
//    override fun matches(inventory: CraftingInventory, world: World): Boolean {
//
//        val teset = inventory.containsAny(tool) && inventory.containsAny(ingot)
//        println("matched $tool and $ingot test = $teset")
////        return inventory.containsAny(tool) && inventory.containsAny(ingot)
//        return true
//    }
//
//    override fun craft(inventory: CraftingInventory): ItemStack {
//        return ItemStack.EMPTY
//    }
//
//    override fun fits(width: Int, height: Int): Boolean {
//        return false
//    }
//
//    override fun getOutput(): ItemStack {
//        return outputStack
//    }
//
//    override fun getId(): Identifier {
//        return id
//    }
//
//    override fun getSerializer(): RecipeSerializer<*> {
//        return PlateRecipeType.INSTANCE
//    }
//
//    override fun getType(): RecipeType<*> {
//        return PlateRecipeType.INSTANCE
//    }
//}
//
//
//data class PlateRecipeOutput(val item: String, val amount: Int?)
//data class PlateRecipeFormat(val tool: JsonObject, val ingot: JsonObject, val output: PlateRecipeOutput)
//
//class PlateRecipeType : RecipeType<PlateRecipe>, RecipeSerializer<PlateRecipe> {
//    companion object {
//        val ID = Identifier(MOD_ID, "crafting_plates")
//        val INSTANCE = PlateRecipeType()
//    }
//
//    override fun read(id: Identifier, json: JsonObject): PlateRecipe {
//        val recipe = Gson().fromJson(json, PlateRecipeFormat::class.java)
//        val tool = Ingredient.fromJson(recipe.tool)
//        val ingot = Ingredient.fromJson(recipe.ingot)
//        val outputItem = Registries.ITEM.getOrEmpty(Identifier(recipe.output.item)).get()
//        val output = ItemStack(outputItem, recipe.output.amount ?: 1)
//
//        println("Read crafting recipe id $id and ${tool.matchingStacks} and ${ingot.matchingStacks} produce $output")
//
//        return PlateRecipe(tool, ingot, output, id)
//    }
//
//    override fun read(id: Identifier, buf: PacketByteBuf): PlateRecipe {
//        val tool = Ingredient.fromPacket(buf)
//        val ingot = Ingredient.fromPacket(buf)
//        val output = buf.readItemStack()
//
//        return PlateRecipe(tool, ingot, output, id)
//    }
//
//    override fun write(buf: PacketByteBuf, recipe: PlateRecipe) {
//        recipe.tool.write(buf)
//        recipe.ingot.write(buf)
//        buf.writeItemStack(recipe.output)
//    }
//}