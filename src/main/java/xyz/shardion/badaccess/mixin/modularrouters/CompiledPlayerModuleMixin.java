package xyz.shardion.badaccess.mixin.modularrouters;

import me.desht.modularrouters.block.tile.TileEntityItemRouter;
import me.desht.modularrouters.logic.compiled.CompiledPlayerModule;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.shardion.badaccess.core.IPlayerInterfaceManager;
import xyz.shardion.badaccess.core.InvalidPlayerException;
import xyz.shardion.badaccess.compat.PlayerInterfaceManagerUtils;

@Mixin(CompiledPlayerModule.class)
public abstract class CompiledPlayerModuleMixin {
    @Shadow(remap = false) public abstract EntityPlayer getPlayer();

    @Inject(
            method = "execute(Lme/desht/modularrouters/block/tile/TileEntityItemRouter;)Z",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private void failIfRestricted(TileEntityItemRouter router, CallbackInfoReturnable<Boolean> cir) {
        EntityPlayer player = this.getPlayer();
        IPlayerInterfaceManager playerInterfaceManager = PlayerInterfaceManagerUtils.getPlayerInterfaceManager(player.getServer());
        try {
            if (playerInterfaceManager.isInterfaceAccessDisallowedForPlayer(player.getUniqueID())) {
                cir.setReturnValue(false);
            }
        } catch (InvalidPlayerException ignored) {
        }
    }
}
