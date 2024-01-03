package xyz.shardion.badaccess.core;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.UUID;

public class PlayerInterfaceManager implements IPlayerInterfaceManager {
    @Nullable
    private static volatile IPlayerInterfaceManager INSTANCE;
    private final MinecraftServer server;

    public PlayerInterfaceManager(MinecraftServer server) {
        this.server = server;
    }

    /**
     * Gets the global instance of {@literal PlayerInterfaceManager}.
     * If this is {@literal null}, it is perfectly OK to construct an implementation of {@literal IPlayerInterfaceManager}
     * and set the global instance to it with {@link PlayerInterfaceManager#setInstance(IPlayerInterfaceManager)}.
     * @return The global instance of {@literal PlayerInterfaceManager}.
     */
    public static @Nullable IPlayerInterfaceManager getInstance() {
        return INSTANCE;
    }

    /**
     * Sets the global instance of {@literal PlayerInterfaceManager}.
     * @param instance The {@literal IPlayerManagerInterface} to set the global instance to.
     */
    public static void setInstance(@Nullable IPlayerInterfaceManager instance) {
        INSTANCE = instance;
    }

    @SuppressWarnings("ConstantValue") // MCP annotates all methods with NotNull by default, but it's wrong here
    @Override
    public boolean isInterfaceAccessDisallowedForPlayer(UUID uuid) throws InvalidPlayerException {
        PlayerList players = server.getPlayerList();
        @Nullable EntityPlayerMP player = players.getPlayerByUUID(uuid);
        if (player == null) {
            throw new InvalidPlayerException("Player with UUID " + uuid + " is not online or does not exist!");
        }

        int dimensionId = player.getEntityWorld().provider.getDimension();
        return Arrays.stream(BadAccessConfig.disallowedDimensionIds).anyMatch((disallowedId) -> disallowedId == dimensionId);
    }
}
