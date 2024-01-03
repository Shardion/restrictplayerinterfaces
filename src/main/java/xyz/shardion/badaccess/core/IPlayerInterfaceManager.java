package xyz.shardion.badaccess.core;

import java.util.UUID;

public interface IPlayerInterfaceManager {
    /**
     * Checks if player interfaces should be allowed to access the inventory of the player with the given UUID.
     * @param uuid The UUID which corresponds to the player which should be checked.
     * @return {@literal true} if player interfaces should be allowed to access the inventory of the player with the
     *         given entity UUID, or {@literal false} if not.
     * @throws InvalidPlayerException If there is no data for the player with the UUID specified.
     */
    boolean isInterfaceAccessAllowedForPlayer(UUID uuid) throws InvalidPlayerException;
}
