package dmillerw.menu.network;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import dmillerw.menu.data.session.ActionSessionData;
import dmillerw.menu.handler.LogHandler;
import dmillerw.menu.network.packet.client.PacketServerResponse;

/**
 * @author dmillerw
 */
public class NetworkEventHandler {

    public static void register() {
        FMLCommonHandler.instance().bus().register(new NetworkEventHandler());
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) { 
        if (event.player instanceof EntityPlayerMP) {
            LogHandler.info("SERVER: MineMenu is installed. Sending server response packet");
            PacketHandler.INSTANCE.sendTo(new PacketServerResponse(), (EntityPlayerMP) event.player);
        }
    }

    @SubscribeEvent
    public void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (!event.isLocal) {
            // Disable server specific options, which will be re-enabled if the server replies
            LogHandler.info("CLIENT: Connected to server. Disabling server-side click actions until server replies");
            ActionSessionData.activateClientValues();
        } else {
            ActionSessionData.activateAll();
        }
    }

    @SubscribeEvent
    public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        LogHandler.info("CLIENT: Disconnected from server, enabling all click actions");
        ActionSessionData.activateAll();
    }
}
