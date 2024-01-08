package xyz.shardion.badaccess.mixin.plethora;

import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.squiddev.plethora.api.method.IContext;
import org.squiddev.plethora.api.method.TypedLuaObject;
import org.squiddev.plethora.api.module.IModuleContainer;
import org.squiddev.plethora.integration.EntityIdentifier;
import org.squiddev.plethora.integration.baubles.MethodIntrospectionBaublesInventory;
import xyz.shardion.badaccess.compat.PlayerInventoryTracker;

@Mixin(MethodIntrospectionBaublesInventory.class)
public abstract class PlethoraIntrospectionMethodsBaublesMixin {
    @Inject(
            method = "getBaubles(Lorg/squiddev/plethora/api/method/IContext;Lorg/squiddev/plethora/integration/EntityIdentifier$Player;)Lorg/squiddev/plethora/api/method/TypedLuaObject;",
            at = @At(
                    value = "RETURN",
                    remap = false
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void trackBaubles(IContext<IModuleContainer> context, EntityIdentifier.Player player, CallbackInfoReturnable<TypedLuaObject<IItemHandler>> cir, IItemHandler inventory) {
        PlayerInventoryTracker.INVENTORIES.put(inventory, player.getId());
    }
}
