package xyz.shardion.badaccess.mixin.plethora;

import dan200.computercraft.api.lua.LuaException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
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
import org.squiddev.plethora.integration.vanilla.method.MethodsIntrospectionEntity;
import xyz.shardion.badaccess.compat.PlayerInventoryTracker;

@Mixin(MethodsIntrospectionEntity.class)
public abstract class PlethoraIntrospectionMethodsMixin {
    @Inject(
            method = "getInventory(Lorg/squiddev/plethora/api/method/IContext;Lorg/squiddev/plethora/integration/EntityIdentifier$Player;)Lorg/squiddev/plethora/api/method/TypedLuaObject;",
            at = @At(
                    value = "RETURN",
                    remap = false
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void trackInventory(IContext<IModuleContainer> context, EntityIdentifier.Player player, CallbackInfoReturnable<TypedLuaObject<IItemHandler>> cir, IItemHandler inventory) {
        PlayerInventoryTracker.INVENTORIES.put(inventory, player.getId());
    }

    @Inject(
            method = "getEquipment(Lorg/squiddev/plethora/api/method/IContext;Lorg/squiddev/plethora/integration/EntityIdentifier;)Lorg/squiddev/plethora/api/method/TypedLuaObject;",
            at = @At(
                    value = "RETURN",
                    remap = false
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void trackEquipment(IContext<IModuleContainer> context, EntityIdentifier entity, CallbackInfoReturnable<TypedLuaObject<IItemHandler>> cir, IItemHandler inventory) {
        try {
            if (entity.getEntity() instanceof EntityPlayer) {
                PlayerInventoryTracker.INVENTORIES.put(inventory, entity.getId());
            }
        } catch (LuaException ignored) {
        }
    }

    @Inject(
            method = "getEnder(Lorg/squiddev/plethora/api/method/IContext;Lorg/squiddev/plethora/integration/EntityIdentifier$Player;)Lorg/squiddev/plethora/api/method/TypedLuaObject;",
            at = @At(
                    value = "RETURN",
                    remap = false
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void trackEnderChest(IContext<IModuleContainer> context, EntityIdentifier.Player player, CallbackInfoReturnable<TypedLuaObject<IInventory>> cir, IInventory inventory) {
        PlayerInventoryTracker.INVENTORIES.put(inventory, player.getId());
    }
}
