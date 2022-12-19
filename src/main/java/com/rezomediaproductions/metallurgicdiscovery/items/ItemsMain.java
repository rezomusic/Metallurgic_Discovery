package com.rezomediaproductions.metallurgicdiscovery.items;

import com.rezomediaproductions.metallurgicdiscovery.util.CreativeModeTabs;
import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import com.rezomediaproductions.metallurgicdiscovery.util.MDToolTiers;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsMain {

    // TITANIUM ORE ITEMS
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MetallurgicDiscovery.MOD_ID);

    // Titanium Items
    public static final RegistryObject<Item> RAW_TITANIUM =
            ITEMS.register("raw_titanium", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> TITANIUM_INGOT =
            ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    // TITANIUM TOOLS
    public static final RegistryObject<Item> TITANIUM_SWORD =
            ITEMS.register("titanium_sword", () -> new SwordItem(MDToolTiers.TITANIUM_TIER, 3, 1f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_PICKAXE =
            ITEMS.register("titanium_pickaxe", () -> new PickaxeItem(MDToolTiers.TITANIUM_TIER, 2, 1f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_AXE =
            ITEMS.register("titanium_axe", () -> new AxeItem(MDToolTiers.TITANIUM_TIER, 3, 1f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_SHOVEL =
            ITEMS.register("titanium_shovel", () -> new ShovelItem(MDToolTiers.TITANIUM_TIER, 1, 1f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_HOE =
            ITEMS.register("titanium_hoe", () -> new HoeItem(MDToolTiers.TITANIUM_TIER, 1, 1f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
