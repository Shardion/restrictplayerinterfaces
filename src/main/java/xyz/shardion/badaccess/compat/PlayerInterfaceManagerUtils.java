package xyz.shardion.badaccess.compat;

import dan200.computercraft.api.lua.LuaException;
import net.minecraft.server.MinecraftServer;
import org.squiddev.plethora.integration.EntityIdentifier;
import xyz.shardion.badaccess.core.IPlayerInterfaceManager;
import xyz.shardion.badaccess.core.InvalidPlayerException;
import xyz.shardion.badaccess.core.PlayerInterfaceManager;

public class PlayerInterfaceManagerUtils {
    public static IPlayerInterfaceManager getPlayerInterfaceManager(MinecraftServer server) {
        IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManager.getInstance();
        if (playerInterfaceManager == null) {
            PlayerInterfaceManager.setInstance(new PlayerInterfaceManager(server));
            playerInterfaceManager = PlayerInterfaceManager.getInstance();
        }
        return playerInterfaceManager;
    }
}
