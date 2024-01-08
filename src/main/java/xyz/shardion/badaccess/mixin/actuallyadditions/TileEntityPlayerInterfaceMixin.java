package xyz.shardion.badaccess.mixin.actuallyadditions;

import de.ellpeck.actuallyadditions.mod.tile.TileEntityPlayerInterface;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.shardion.badaccess.compat.PlayerInterfaceManagerUtils;
import xyz.shardion.badaccess.core.IPlayerInterfaceManager;
import xyz.shardion.badaccess.core.InvalidPlayerException;

@Mixin(TileEntityPlayerInterface.class)
public abstract class TileEntityPlayerInterfaceMixin {
    @Inject(
            method = "getPlayer()Lnet/minecraft/entity/player/EntityPlayer;",
            at = @At(
                    value = "RETURN",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private void returnNullIfRestricted(CallbackInfoReturnable<EntityPlayer> cir) {
        @Nullable EntityPlayer returnPlayer = cir.getReturnValue();
        if (returnPlayer != null) {
            IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManagerUtils.getPlayerInterfaceManager();
            try {
                if (playerInterfaceManager.isInterfaceAccessDisallowedForPlayer(returnPlayer.getUniqueID())) {
                    cir.setReturnValue(null);
                }
            } catch (InvalidPlayerException ignored) {
            }
        }
    }
}
