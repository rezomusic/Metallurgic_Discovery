package com.rezomediaproductions.metallurgicdiscovery.world.feature;

import com.google.common.base.Suppliers;
import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import com.rezomediaproductions.metallurgicdiscovery.blocks.BlocksMain;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MetallurgicDiscovery.MOD_ID);

    // ----- OVERWORLD ORE GENERATION ----- //
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TITANIUM_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlocksMain.TITANIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlocksMain.DEEPSLATE_TITANIUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_MYTHRIL_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlocksMain.MYTHRIL_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlocksMain.DEEPSLATE_MYTHRIL_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CHROMIUM_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlocksMain.CHROMIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlocksMain.DEEPSLATE_CHROMIUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_VANADIUM_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlocksMain.VANADIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlocksMain.DEEPSLATE_VANADIUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_CELESTITE_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlocksMain.CELESTITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlocksMain.DEEPSLATE_CELESTITE_ORE.get().defaultBlockState())));


    public static final RegistryObject<ConfiguredFeature<?, ?>> TITANIUM_ORE_FEATURE = CONFIGURED_FEATURES.register("titanium_ore_feature",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_TITANIUM_ORE.get(), 6)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> MYTHRIL_ORE_FEATURE = CONFIGURED_FEATURES.register("mythril_ore_feature",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_MYTHRIL_ORE.get(), 3)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CHROMIUM_ORE_FEATURE = CONFIGURED_FEATURES.register("chromium_ore_feature",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_CHROMIUM_ORE.get(), 3)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> VANADIUM_ORE_FEATURE = CONFIGURED_FEATURES.register("vanadium_ore_feature",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_VANADIUM_ORE.get(), 3)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CELESTITE_ORE_FEATURE = CONFIGURED_FEATURES.register("celestite_ore_feature",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_CELESTITE_ORE.get(), 3)));

    public static void register(IEventBus eventBus)
    {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
