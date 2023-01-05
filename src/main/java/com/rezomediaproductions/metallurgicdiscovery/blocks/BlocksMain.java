package com.rezomediaproductions.metallurgicdiscovery.blocks;

import com.rezomediaproductions.metallurgicdiscovery.blocks.custom.BasicMetallurgyStation;
import com.rezomediaproductions.metallurgicdiscovery.util.CreativeModeTabs;
import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import com.rezomediaproductions.metallurgicdiscovery.items.ItemsMain;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlocksMain {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MetallurgicDiscovery.MOD_ID);

    /* BLOCK ENTITIES */
    public static final RegistryObject<Block> BASIC_METALLURGY_STATION =
            registerBlock("basic_metallurgy_station",
                    () -> new BasicMetallurgyStation(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops().noOcclusion()),
                    CreativeModeTabs.CREATIVE_MODE_TAB);


    /* -----OVERWORLD ORES----- */

    // Titanium Blocks
    public static final RegistryObject<Block> TITANIUM_ORE =
            registerBlock("titanium_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                        CreativeModeTabs.CREATIVE_MODE_TAB);

    public static final RegistryObject<Block> DEEPSLATE_TITANIUM_ORE =
            registerBlock("deepslate_titanium_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                        CreativeModeTabs.CREATIVE_MODE_TAB);

    public static final RegistryObject<Block> TITANIUM_BLOCK =
            registerBlock("titanium_block",
                        () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()),
                        CreativeModeTabs.CREATIVE_MODE_TAB);

    // Mythril Ore Blocks
    public static final RegistryObject<Block> MYTHRIL_ORE =
            registerBlock("mythril_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                    CreativeModeTabs.CREATIVE_MODE_TAB);

    public static final RegistryObject<Block> DEEPSLATE_MYTHRIL_ORE =
            registerBlock("deepslate_mythril_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                    CreativeModeTabs.CREATIVE_MODE_TAB);

    // Chromium Ore Blocks
    public static final RegistryObject<Block> CHROMIUM_ORE =
            registerBlock("chromium_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                    CreativeModeTabs.CREATIVE_MODE_TAB);

    public static final RegistryObject<Block> DEEPSLATE_CHROMIUM_ORE =
            registerBlock("deepslate_chromium_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                    CreativeModeTabs.CREATIVE_MODE_TAB);

    // Vanadium Ore Blocks
    public static final RegistryObject<Block> VANADIUM_ORE =
            registerBlock("vanadium_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                    CreativeModeTabs.CREATIVE_MODE_TAB);

    public static final RegistryObject<Block> DEEPSLATE_VANADIUM_ORE =
            registerBlock("deepslate_vanadium_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                    CreativeModeTabs.CREATIVE_MODE_TAB);

    // Celestite Ore Blocks
    public static final RegistryObject<Block> CELESTITE_ORE =
            registerBlock("celestite_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                    CreativeModeTabs.CREATIVE_MODE_TAB);

    public static final RegistryObject<Block> DEEPSLATE_CELESTITE_ORE =
            registerBlock("deepslate_celestite_ore",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3,7)),
                    CreativeModeTabs.CREATIVE_MODE_TAB);



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item>  registerBlockItem(String name, RegistryObject<T> block,
                                                                             CreativeModeTab tab)
    {
        return ItemsMain.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
