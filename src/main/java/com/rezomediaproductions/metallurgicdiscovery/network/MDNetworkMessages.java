package com.rezomediaproductions.metallurgicdiscovery.network;

import com.rezomediaproductions.metallurgicdiscovery.MetallurgicDiscovery;
import com.rezomediaproductions.metallurgicdiscovery.blocks.entity.BasicMetallurgyStationBlockEntity;
import com.rezomediaproductions.metallurgicdiscovery.network.packet.ForgeButtonPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MDNetworkMessages {
    private static SimpleChannel INSTANCE;

    private static int packetID = 0;
    private static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(MetallurgicDiscovery.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s-> true)
                .simpleChannel();

        INSTANCE = net;


        net.messageBuilder(ForgeButtonPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ForgeButtonPacket::new)
                .encoder(ForgeButtonPacket::write)
                .consumerMainThread(ForgeButtonPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
}
