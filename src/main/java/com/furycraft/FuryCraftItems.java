package com.furycraft;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class FuryCraftItems {
    public static final Item HAMBURGER = register("hamburger", Item::new, new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(12).saturationModifier(1.6f).build()));
    public static final Item CHEESEBURGER = register("cheeseburger", Item::new, new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(14).saturationModifier(1.8f).build()));
    public static final Item CHEESE = register("cheese", Item::new, new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.6f).build()));

    public static final Item CHEESE_BLOCK = FuryCraftBlocks.CHEESE_BLOCK.asItem();

    public static void init() {
        Event<ItemGroupEvents.ModifyEntries> FOOD_AND_DRINKS = ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS);
        Event<ItemGroupEvents.ModifyEntries> BUILDING_BLOCKS = ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS);

        FOOD_AND_DRINKS.register((itemGroup) -> itemGroup.accept(FuryCraftItems.HAMBURGER));
        FOOD_AND_DRINKS.register((itemGroup) -> itemGroup.accept(FuryCraftItems.CHEESEBURGER));
        FOOD_AND_DRINKS.register((itemGroup) -> itemGroup.accept(FuryCraftItems.CHEESE));

        BUILDING_BLOCKS.register((itemGroup) -> itemGroup.accept(CHEESE_BLOCK));
    }

    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(FuryCraft.MOD_ID, name));

        // Create the item instance.
        GenericItem item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
}
