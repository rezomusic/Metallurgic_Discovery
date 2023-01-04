package com.rezomediaproductions.metallurgicdiscovery.blocks.entity;

import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import com.rezomediaproductions.metallurgicdiscovery.blocks.BlocksMain;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MDBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MetallurgicDiscovery.MOD_ID);

    public static final RegistryObject<BlockEntityType<BasicMetallurgyStationBlockEntity>> BASIC_METALLURGY_STATION =
            BLOCK_ENTITIES.register("basic_metallurgy_station",
                    () -> BlockEntityType.Builder.of(BasicMetallurgyStationBlockEntity::new,
                    BlocksMain.BASIC_METALLURGY_STATION.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
