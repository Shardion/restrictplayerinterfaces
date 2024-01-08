package xyz.shardion.badaccess.mixin.plethora;

import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.squiddev.plethora.api.method.IContext;
import org.squiddev.plethora.api.method.TypedLuaObject;
import org.squiddev.plethora.api.reference.ItemSlot;
import org.squiddev.plethora.integration.vanilla.method.MethodsInventory;
import xyz.shardion.badaccess.compat.PlayerInterfaceManagerUtils;

import java.util.Map;

@Mixin(MethodsInventory.class)
public abstract class PlethoraInventoryMethodsMixin {
    @Inject(
            method = "list(Lnet/minecraftforge/items/IItemHandler;)Ljava/util/Map;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private static void returnEmptyIfRestricted(IItemHandler inventory, CallbackInfoReturnable<Map<Integer, Object>> cir) {
        if (PlayerInterfaceManagerUtils.isItemHandlerOwnedByRestrictedPlayer(inventory)) {
            cir.setReturnValue(Int2ObjectMaps.emptyMap());
        }
    }

    @Inject(
            method = "getItem(Lorg/squiddev/plethora/api/method/IContext;I)Lorg/squiddev/plethora/api/method/TypedLuaObject;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private static void returnEmptyStackIfRestricted(IContext<IItemHandler> baked, int slot, CallbackInfoReturnable<TypedLuaObject<ItemSlot>> cir) {
        if (PlayerInterfaceManagerUtils.isItemHandlerOwnedByRestrictedPlayer(baked.getTarget())) {
            cir.setReturnValue(null);
        }
    }

    @Inject(
            method = "getItemMeta(Lorg/squiddev/plethora/api/method/IContext;I)Lorg/squiddev/plethora/api/meta/TypedMeta;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            cancellable = true,
            remap = false
    )
    private static void returnEmptyMetaIfRestricted(IContext<IItemHandler> baked, int slot, CallbackInfoReturnable<TypedLuaObject<ItemSlot>> cir) {
        if (PlayerInterfaceManagerUtils.isItemHandlerOwnedByRestrictedPlayer(baked.getTarget())) {
            cir.setReturnValue(null);
        }
    }
}
