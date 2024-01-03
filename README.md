# Restrict Player Interfaces

Allows restricting automated remote access to player inventories ("player interfaces").

The mod supports restricting these player interfaces:

- Introspection module (Plethora)
- Player Chest (Extra Utilities 2)
- Player Module (Modular Routers)

The mod supports these restrictions:

- Access to players in a given dimension

## Introspection module notes

These methods will fail with the error `Player interface access is restricted` if access
to the player's inventory is disallowed by the mod:

- `getInventory`
- `getEquipment`
- `getEnder`
- `getBaubles`

Additionally, all inventories returned by the above methods will have no transfer locations.

## History and other notes

This mod requires [MixinBooter](https://www.curseforge.com/minecraft/mc-mods/mixin-booter).

This mod was made for the Meatballcraft modpack, and exists to prevent the use of player
interfaces to transfer items to players in the Vethea dimension (from DivineRPG).

The mod is [open-source](https://github.com/shardion/restrictplayerinterfaces/) and licensed under the MIT license (yes, you can use it in your modpack).
