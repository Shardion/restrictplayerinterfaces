package xyz.shardion.badaccess.mixin.randomthings;

import lumien.randomthings.tileentity.TileEntityPlayerInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.shardion.badaccess.compat.PlayerInterfaceManagerUtils;
import xyz.shardion.badaccess.core.IPlayerInterfaceManager;
import xyz.shardion.badaccess.core.InvalidPlayerException;

import java.util.UUID;

@Mixin(TileEntityPlayerInterface.class)
public abstract class TileEntityPlayerInterfaceMixin {
    @Shadow(remap = false) public abstract UUID getPlayerUUID();

    @Inject(
            method = "getPlayerInventory()Llumien/randomthings/tileentity/TileEntityPlayerInterface$PlayerInventoryWrapper;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private void returnNullIfRestricted(CallbackInfoReturnable<Object> cir) {
        UUID uuid = getPlayerUUID();
        IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManagerUtils.getPlayerInterfaceManager();
        try {
            if (playerInterfaceManager.isInterfaceAccessDisallowedForPlayer(uuid)) {
                cir.setReturnValue(null);
            }
        } catch (InvalidPlayerException ignored) {
        }
    }

}
