package xyz.shardion.badaccess.mixin.plethora;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.squiddev.plethora.integration.baubles.TransferBaublesInventory;
import xyz.shardion.badaccess.core.IPlayerInterfaceManager;
import xyz.shardion.badaccess.core.InvalidPlayerException;
import xyz.shardion.badaccess.core.PlayerInterfaceManagerUtils;

import java.util.Collections;
import java.util.Set;

@Mixin(TransferBaublesInventory.class)
public abstract class PlethoraTransferBaublesMixin {
    @Inject(
            method = "getTransferLocation(Lnet/minecraft/entity/player/EntityPlayer;Ljava/lang/String;)Ljava/lang/Object;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private void returnNullIfRestricted(EntityPlayer object, String key, CallbackInfoReturnable<Object> cir) {
        IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManagerUtils.getPlayerInterfaceManager(object.getServer());
        try {
            if (!playerInterfaceManager.isInterfaceAccessAllowedForPlayer(object.getUniqueID())) {
                cir.setReturnValue(null);
            }
        } catch (InvalidPlayerException ignored) {
        }
    }

    @Inject(
            method = "getTransferLocations(Lnet/minecraft/entity/player/EntityPlayer;)Ljava/util/Set;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private void returnEmptyIfRestricted(EntityPlayer object, CallbackInfoReturnable<Set<String>> cir) {
        IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManagerUtils.getPlayerInterfaceManager(object.getServer());
        try {
            if (!playerInterfaceManager.isInterfaceAccessAllowedForPlayer(object.getUniqueID())) {
                cir.setReturnValue(Collections.emptySet());
            }
        } catch (InvalidPlayerException ignored) {
        }
    }
}
