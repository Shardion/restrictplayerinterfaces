package xyz.shardion.badaccess.mixin.plethora;

import dan200.computercraft.api.lua.LuaException;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.squiddev.plethora.api.method.IContext;
import org.squiddev.plethora.api.method.TypedLuaObject;
import org.squiddev.plethora.api.module.IModuleContainer;
import org.squiddev.plethora.integration.EntityIdentifier;
import org.squiddev.plethora.integration.baubles.MethodIntrospectionBaublesInventory;
import xyz.shardion.badaccess.core.PlayerInterfaceManagerUtils;

@Mixin(MethodIntrospectionBaublesInventory.class)
public abstract class PlethoraIntrospectionBaublesMixin {
    @Inject(
            method = "getBaubles(Lorg/squiddev/plethora/api/method/IContext;Lorg/squiddev/plethora/integration/EntityIdentifier$Player;)Lorg/squiddev/plethora/api/method/TypedLuaObject;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            remap = false
    )
    private static void throwIfRestricted(IContext<IModuleContainer> context, EntityIdentifier.Player player, CallbackInfoReturnable<TypedLuaObject<IItemHandler>> cir) throws LuaException {
        PlayerInterfaceManagerUtils.throwLuaExceptionIfRestricted(player);
    }
}
