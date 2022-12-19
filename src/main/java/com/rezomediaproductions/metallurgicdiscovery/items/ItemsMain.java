package com.rezomediaproductions.metallurgicdiscovery.items;

import com.rezomediaproductions.metallurgicdiscovery.util.CreativeModeTabs;
import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsMain {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MetallurgicDiscovery.MOD_ID);

    // Titanium Items
    public static final RegistryObject<Item> RAW_TITANIUM =
            ITEMS.register("raw_titanium", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> TITANIUM_INGOT =
            ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
