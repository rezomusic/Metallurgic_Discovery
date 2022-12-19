package com.rezomediaproductions.metallurgicdiscovery.util;

import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class MDTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_TITANIUM_TOOL
                = tag("needs_titanium_tool");


        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(MetallurgicDiscovery.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name)
        {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

    }

}
