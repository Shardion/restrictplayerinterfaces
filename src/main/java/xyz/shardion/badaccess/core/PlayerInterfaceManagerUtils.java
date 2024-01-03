package xyz.shardion.badaccess.core;

import dan200.computercraft.api.lua.LuaException;
import net.minecraft.server.MinecraftServer;
import org.squiddev.plethora.integration.EntityIdentifier;

public class PlayerInterfaceManagerUtils {
    public static IPlayerInterfaceManager getPlayerInterfaceManager(MinecraftServer server) {
        IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManager.getInstance();
        if (playerInterfaceManager == null) {
            PlayerInterfaceManager.setInstance(new PlayerInterfaceManager(server));
            playerInterfaceManager = PlayerInterfaceManager.getInstance();
        }
        return playerInterfaceManager;
    }
    public static void throwLuaExceptionIfRestricted(EntityIdentifier.Player player) throws LuaException {
        IPlayerInterfaceManager playerInterfaceManager = getPlayerInterfaceManager(player.getPlayer().getServer());
        try {
            if (playerInterfaceManager.isInterfaceAccessDisallowedForPlayer(player.getId())) {
                throw new LuaException("Player interface access is restricted");
            }
        } catch (InvalidPlayerException ignored) {
        }
    }
}
