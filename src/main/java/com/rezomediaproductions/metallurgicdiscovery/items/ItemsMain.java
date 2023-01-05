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
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MetallurgicDiscovery.MOD_ID);

    // Overworld Alloy Flakes
    public static final RegistryObject<Item> MYTHRIL_FLAKES =
            ITEMS.register("mythril_flakes", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CHROMIUM_FLAKES =
            ITEMS.register("chromium_flakes", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> VANADIUM_FLAKES =
            ITEMS.register("vanadium_flakes", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CELESTITE_FLAKES =
            ITEMS.register("celestite_flakes", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    // Basic Stencil Items
    public static final RegistryObject<Item> BASIC_SWORD_STENCIL =
            ITEMS.register("basic_sword_stencil", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> BASIC_PICKAXE_STENCIL =
            ITEMS.register("basic_pickaxe_stencil", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> BASIC_AXE_STENCIL =
            ITEMS.register("basic_axe_stencil", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> BASIC_SHOVEL_STENCIL =
            ITEMS.register("basic_shovel_stencil", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> BASIC_HOE_STENCIL =
            ITEMS.register("basic_hoe_stencil", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    // Overworld Tool Heads
    public static final RegistryObject<Item> IRON_SWORD_HEAD =
            ITEMS.register("iron_sword_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> IRON_PICKAXE_HEAD =
            ITEMS.register("iron_pickaxe_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> IRON_AXE_HEAD =
            ITEMS.register("iron_axe_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> IRON_SHOVEL_HEAD =
            ITEMS.register("iron_shovel_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> IRON_HOE_HEAD =
            ITEMS.register("iron_hoe_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> DIAMOND_SWORD_HEAD =
            ITEMS.register("diamond_sword_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> DIAMOND_PICKAXE_HEAD =
            ITEMS.register("diamond_pickaxe_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> DIAMOND_AXE_HEAD =
            ITEMS.register("diamond_axe_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> DIAMOND_SHOVEL_HEAD =
            ITEMS.register("diamond_shovel_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> DIAMOND_HOE_HEAD =
            ITEMS.register("diamond_hoe_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> TITANIUM_SWORD_HEAD =
            ITEMS.register("titanium_sword_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_PICKAXE_HEAD =
            ITEMS.register("titanium_pickaxe_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_AXE_HEAD =
            ITEMS.register("titanium_axe_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_SHOVEL_HEAD =
            ITEMS.register("titanium_shovel_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_HOE_HEAD =
            ITEMS.register("titanium_hoe_head", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    // Titanium Ore Items
    public static final RegistryObject<Item> RAW_TITANIUM =
            ITEMS.register("raw_titanium", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_INGOT =
            ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));

    // Titanium Tools
    public static final RegistryObject<Item> TITANIUM_SWORD =
            ITEMS.register("titanium_sword", () -> new SwordItem(MDToolTiers.TITANIUM_TIER, 3, 1f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_PICKAXE =
            ITEMS.register("titanium_pickaxe", () -> new PickaxeItem(MDToolTiers.TITANIUM_TIER, 0, 1f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_AXE =
            ITEMS.register("titanium_axe", () -> new AxeItem(MDToolTiers.TITANIUM_TIER, 2, 0f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_SHOVEL =
            ITEMS.register("titanium_shovel", () -> new ShovelItem(MDToolTiers.TITANIUM_TIER, 0, 1f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TITANIUM_HOE =
            ITEMS.register("titanium_hoe", () -> new HoeItem(MDToolTiers.TITANIUM_TIER, 0, 1f, new Item.Properties().tab(CreativeModeTabs.CREATIVE_MODE_TAB)));



    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
