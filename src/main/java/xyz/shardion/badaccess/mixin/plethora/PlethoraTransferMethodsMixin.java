package xyz.shardion.badaccess.mixin.plethora;

import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.squiddev.plethora.integration.vanilla.method.MethodsInventoryTransfer;
import xyz.shardion.badaccess.compat.PlayerInterfaceManagerUtils;

@Mixin(MethodsInventoryTransfer.class)
public abstract class PlethoraTransferMethodsMixin {
    @Inject(
            method = "moveItem(Lnet/minecraftforge/items/IItemHandler;ILnet/minecraftforge/items/IItemHandler;II)I",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private static void returnZeroIfRestricted(IItemHandler from, int fromSlot, IItemHandler _to, int toSlot, int limit, CallbackInfoReturnable<Integer> cir) {
        boolean fromRestricted = PlayerInterfaceManagerUtils.isItemHandlerOwnedByRestrictedPlayer(from);
        boolean toRestricted = PlayerInterfaceManagerUtils.isItemHandlerOwnedByRestrictedPlayer(_to);
        if (fromRestricted || toRestricted) {
            cir.setReturnValue(0);
        }
    }
}
