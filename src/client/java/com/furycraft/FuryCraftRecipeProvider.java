package com.furycraft;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FuryCraftRecipeProvider extends FabricRecipeProvider {
    public FuryCraftRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                // Burger Recipe
                shaped(RecipeCategory.FOOD, FuryCraftItems.HAMBURGER, 1)
                        .define('B', Items.BREAD).define('S', Items.COOKED_BEEF)
                        .pattern("B").pattern("S").pattern("B")
                        .unlockedBy(getHasName(Items.COOKED_BEEF), has(Items.COOKED_BEEF)).save(output);
                // Cheeseburger Recipe
                shapeless(RecipeCategory.FOOD, FuryCraftItems.CHEESEBURGER, 1)
                        .requires(FuryCraftItems.HAMBURGER).requires(FuryCraftItems.CHEESE)
                        .unlockedBy(getHasName(FuryCraftItems.HAMBURGER), has(FuryCraftItems.HAMBURGER)).save(output);
                // Cheese Recipe
                shapeless(RecipeCategory.FOOD, FuryCraftItems.CHEESE, 4)
                        .requires(FuryCraftItems.CHEESE_BLOCK)
                        .unlockedBy(getHasName(FuryCraftItems.CHEESE_BLOCK), has(FuryCraftItems.CHEESE_BLOCK)).save(output);
                // Cheese Block Recipe
                shaped(RecipeCategory.BUILDING_BLOCKS, FuryCraftItems.CHEESE_BLOCK, 1)
                        .define('C', FuryCraftItems.CHEESE)
                        .pattern("CC").pattern("CC")
                        .unlockedBy(getHasName(FuryCraftItems.CHEESE_BLOCK), has(FuryCraftItems.CHEESE_BLOCK)).save(output);
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "QOLCraftModRecipeProvider";
    }
}