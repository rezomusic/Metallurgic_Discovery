package com.rezomediaproductions.metallurgicdiscovery.world.feature;

import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class PlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MetallurgicDiscovery.MOD_ID);

    // ----- OVERWORLD ORE PLACEMENTS ----- //

    public static final RegistryObject<PlacedFeature> TITANIUM_ORE_PLACED = PLACED_FEATURES.register("titanium_ore_placed",
            () -> new PlacedFeature(ConfiguredFeatures.TITANIUM_ORE_FEATURE.getHolder().get(),
                    commonOrePlacement(9, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> MYTHRIL_ORE_PLACED = PLACED_FEATURES.register("mythril_ore_placed",
            () -> new PlacedFeature(ConfiguredFeatures.MYTHRIL_ORE_FEATURE.getHolder().get(),
                    commonOrePlacement(20, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> CHROMIUM_ORE_PLACED = PLACED_FEATURES.register("chromium_ore_placed",
            () -> new PlacedFeature(ConfiguredFeatures.CHROMIUM_ORE_FEATURE.getHolder().get(),
                    commonOrePlacement(20, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> VANADIUM_ORE_PLACED = PLACED_FEATURES.register("vanadium_ore_placed",
            () -> new PlacedFeature(ConfiguredFeatures.VANADIUM_ORE_FEATURE.getHolder().get(),
                    commonOrePlacement(20, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> CELESTITE_ORE_PLACED = PLACED_FEATURES.register("celestite_ore_placed",
            () -> new PlacedFeature(ConfiguredFeatures.CELESTITE_ORE_FEATURE.getHolder().get(),
                    commonOrePlacement(20, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));




    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }

    public static void register(IEventBus eventBus)
    {
        PLACED_FEATURES.register(eventBus);
    }

}
