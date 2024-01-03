package xyz.shardion.badaccess.mixin.extrautilities2;

import com.rwtema.extrautils2.tile.TilePlayerChest;
import net.minecraft.entity.player.EntityPlayerMP;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.shardion.badaccess.core.IPlayerInterfaceManager;
import xyz.shardion.badaccess.core.InvalidPlayerException;
import xyz.shardion.badaccess.core.PlayerInterfaceManagerUtils;

@Mixin(TilePlayerChest.class)
public abstract class TilePlayerChestMixin {
    @Inject(
            method = "getOwnerPlayer()Lnet/minecraft/entity/player/EntityPlayerMP;",
            at = @At(
                    value = "RETURN",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private void noOwnerIfRestricted(CallbackInfoReturnable<EntityPlayerMP> cir) {
        @Nullable EntityPlayerMP player = cir.getReturnValue();
        if (player != null) {
            IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManagerUtils.getPlayerInterfaceManager(player.getServer());
            try {
                if (!playerInterfaceManager.isInterfaceAccessAllowedForPlayer(player.getUniqueID())) {
                    cir.setReturnValue(null);
                }
            } catch (InvalidPlayerException ignored) {
            }
        }
    }
}
