package xyz.shardion.badaccess.mixin.plethora;

import dan200.computercraft.api.lua.LuaException;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.squiddev.plethora.api.method.IContext;
import org.squiddev.plethora.api.method.TypedLuaObject;
import org.squiddev.plethora.api.module.IModuleContainer;
import org.squiddev.plethora.integration.EntityIdentifier;
import org.squiddev.plethora.integration.vanilla.method.MethodsIntrospectionEntity;
import xyz.shardion.badaccess.core.PlayerInterfaceManagerUtils;

@Mixin(MethodsIntrospectionEntity.class)
public abstract class PlethoraIntrospectionInventoryMixin {
    @Inject(
            method = "getInventory(Lorg/squiddev/plethora/api/method/IContext;Lorg/squiddev/plethora/integration/EntityIdentifier$Player;)Lorg/squiddev/plethora/api/method/TypedLuaObject;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            remap = false
    )
    private static void inventoryThrowIfRestricted(IContext<IModuleContainer> context, EntityIdentifier.Player player, CallbackInfoReturnable<TypedLuaObject<IInventory>> cir) throws LuaException {
        PlayerInterfaceManagerUtils.throwLuaExceptionIfRestricted(player);
    }

    @Inject(
            method = "getEquipment(Lorg/squiddev/plethora/api/method/IContext;Lorg/squiddev/plethora/integration/EntityIdentifier;)Lorg/squiddev/plethora/api/method/TypedLuaObject;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            remap = false
    )
    private static void equipmentThrowIfRestricted(IContext<IModuleContainer> context, EntityIdentifier entity, CallbackInfoReturnable<TypedLuaObject<IInventory>> cir) throws LuaException {
        if (entity instanceof EntityIdentifier.Player) {
            PlayerInterfaceManagerUtils.throwLuaExceptionIfRestricted((EntityIdentifier.Player)entity);
        }
    }

    @Inject(
            method = "getEnder(Lorg/squiddev/plethora/api/method/IContext;Lorg/squiddev/plethora/integration/EntityIdentifier$Player;)Lorg/squiddev/plethora/api/method/TypedLuaObject;",
            at = @At(
                    value = "HEAD",
                    remap = false
            ),
            remap = false
    )
    private static void enderThrowIfRestricted(IContext<IModuleContainer> context, EntityIdentifier.Player player, CallbackInfoReturnable<TypedLuaObject<IInventory>> cir) throws LuaException {
        PlayerInterfaceManagerUtils.throwLuaExceptionIfRestricted(player);
    }
}
