package xyz.shardion.badaccess.mixin.plethora;

import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.squiddev.plethora.integration.vanilla.transfer.TransferEntityPlayerInventory;
import xyz.shardion.badaccess.compat.ReadOnlyEmptyInventory;
import xyz.shardion.badaccess.core.IPlayerInterfaceManager;
import xyz.shardion.badaccess.core.InvalidPlayerException;
import xyz.shardion.badaccess.compat.PlayerInterfaceManagerUtils;

import java.util.Collections;
import java.util.Set;

@Mixin(TransferEntityPlayerInventory.class)
public abstract class PlethoraTransferInventoryMixin {
    @Inject(
            method = "getTransferLocation(Lnet/minecraft/entity/player/EntityPlayer;Ljava/lang/String;)Ljava/lang/Object;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private void returnDummyIfRestricted(EntityPlayer object, String key, CallbackInfoReturnable<Object> cir) {
        IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManagerUtils.getPlayerInterfaceManager(object.getServer());
        try {
            if (playerInterfaceManager.isInterfaceAccessDisallowedForPlayer(object.getUniqueID())) {
                @Nullable ReadOnlyEmptyInventory dummyInventory;
                if (key.equals("inventory")) {
                    dummyInventory = new ReadOnlyEmptyInventory(36);
                } else if (key.equals("ender_chest")) {
                    dummyInventory = new ReadOnlyEmptyInventory(27);
                } else {
                    dummyInventory = null;
                }
                cir.setReturnValue(dummyInventory);
            }
        } catch (InvalidPlayerException ignored) {
        }
    }
}
