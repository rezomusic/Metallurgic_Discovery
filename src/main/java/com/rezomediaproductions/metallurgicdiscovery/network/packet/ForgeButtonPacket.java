package com.rezomediaproductions.metallurgicdiscovery.network.packet;


import com.rezomediaproductions.metallurgicdiscovery.blocks.entity.BasicAssemblyTableBlockEntity;
import com.rezomediaproductions.metallurgicdiscovery.blocks.entity.BasicMetallurgyStationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ForgeButtonPacket {
    private final BlockPos pos;

    public ForgeButtonPacket(BlockPos pos) {
        this.pos = pos;
    }

    public ForgeButtonPacket(FriendlyByteBuf buffer) {
        this.pos = buffer.readBlockPos();
    }


    public void write(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(pos);
    }


    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerLevel level = context.getSender().getLevel();
            BasicMetallurgyStationBlockEntity entity = (BasicMetallurgyStationBlockEntity) level.getBlockEntity(pos);
            assert entity != null;
            entity.setShouldCraft();
        });
        return true;
    }
}
