package com.rezomediaproductions.metallurgicdiscovery.recipe;

import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MDRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MetallurgicDiscovery.MOD_ID);

    public static final RegistryObject<RecipeSerializer<BasicMetallurgyStationRecipe>> BASIC_METALLURGY_SERIALIZER =
            SERIALIZERS.register("basic_metallurgy", () -> BasicMetallurgyStationRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
