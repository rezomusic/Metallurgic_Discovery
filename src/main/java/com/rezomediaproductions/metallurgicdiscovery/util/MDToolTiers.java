package com.rezomediaproductions.metallurgicdiscovery.util;

import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import com.rezomediaproductions.metallurgicdiscovery.items.ItemsMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class MDToolTiers {
    public static Tier TITANIUM_TIER;

    static
    {
        TITANIUM_TIER = TierSortingRegistry.registerTier(
                new ForgeTier(4, 1853, 9.0f, 4.0f, 13, MDTags.Blocks.NEEDS_TITANIUM_TOOL, () -> Ingredient.of(ItemsMain.TITANIUM_INGOT.get())),
                new ResourceLocation(MetallurgicDiscovery.MOD_ID, "titanium"), List.of(Tiers.DIAMOND), List.of());
    }
}
