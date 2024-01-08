package xyz.shardion.badaccess.compat;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;
import xyz.shardion.badaccess.core.IPlayerInterfaceManager;
import xyz.shardion.badaccess.core.InvalidPlayerException;
import xyz.shardion.badaccess.core.PlayerInterfaceManager;

import java.util.UUID;

public class PlayerInterfaceManagerUtils {
    public static IPlayerInterfaceManager getPlayerInterfaceManager() {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManager.getInstance();
        if (playerInterfaceManager == null) {
            PlayerInterfaceManager.setInstance(new PlayerInterfaceManager(server));
            playerInterfaceManager = PlayerInterfaceManager.getInstance();
        }
        return playerInterfaceManager;
    }

    public static boolean isItemHandlerOwnedByRestrictedPlayer(IItemHandler itemHandler) {
        Object checkableObject;
        if (itemHandler instanceof InvWrapper) {
            // unwrap inventory wrappers
            // fixes enderchests and anything else that starts as an inventory instead of an item handler
            checkableObject = ((InvWrapper)itemHandler).getInv();
        } else {
            checkableObject = itemHandler;
        }

        @Nullable UUID ownerUuid = PlayerInventoryTracker.INVENTORIES.get(checkableObject);
        IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManagerUtils.getPlayerInterfaceManager();
        if (ownerUuid != null) {
            try {
                if (playerInterfaceManager.isInterfaceAccessDisallowedForPlayer(ownerUuid)) {
                    return true;
                }
            } catch (InvalidPlayerException ignored) {
            }
        }
        return false;
    }
}
