package com.rezomediaproductions.metallurgicdiscovery;

import com.rezomediaproductions.metallurgicdiscovery.items.ItemsMain;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeModeTabs {
    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab("maintab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemsMain.TITANIUM_INGOT.get());
        }
    };
}
