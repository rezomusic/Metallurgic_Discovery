package com.rezomediaproductions.metallurgicdiscovery;

import com.mojang.logging.LogUtils;
import com.rezomediaproductions.metallurgicdiscovery.blocks.BlocksMain;
import com.rezomediaproductions.metallurgicdiscovery.blocks.entity.MDBlockEntities;
import com.rezomediaproductions.metallurgicdiscovery.items.ItemsMain;
import com.rezomediaproductions.metallurgicdiscovery.network.MDNetworkMessages;
import com.rezomediaproductions.metallurgicdiscovery.recipe.MDRecipes;
import com.rezomediaproductions.metallurgicdiscovery.screen.BasicMetallurgyStationScreen;
import com.rezomediaproductions.metallurgicdiscovery.screen.MDMenuTypes;
import com.rezomediaproductions.metallurgicdiscovery.world.feature.ConfiguredFeatures;
import com.rezomediaproductions.metallurgicdiscovery.world.feature.PlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.util.datafix.fixes.RecipesFix;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MetallurgicDiscovery.MOD_ID)
public class MetallurgicDiscovery
{
    public static final String MOD_ID = "metallurgic_discovery";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MetallurgicDiscovery()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemsMain.register(modEventBus);
        BlocksMain.register(modEventBus);

        ConfiguredFeatures.register(modEventBus);
        PlacedFeatures.register(modEventBus);

        MDBlockEntities.register(modEventBus);
        MDMenuTypes.register(modEventBus);

        MDRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            MDNetworkMessages.register();
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(MDMenuTypes.BASIC_METALLURGY_STATION_MENU.get(), BasicMetallurgyStationScreen::new);
        }
    }
}
