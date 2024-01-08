# Restrict Player Interfaces

Allows restricting automated remote access to player inventories ("player interfaces").

The mod supports restricting these player interfaces:

- Introspection module (Plethora)
- Player Chest (Extra Utilities 2)
- Player Module (Modular Routers)
- Player Interface (Actually Additions)
- Player Interface (Random Things)
- Creative Player Interface (Random Things)

The mod supports these restrictions:

- Access to players in a given dimension

## Introspection module notes

If the Introspection module is targeting a player which is being restricted by Restrict Player Interfaces, all player
inventories, such as the main inventory, Ender Chest, and Baubles, will be empty when read. Item transfers involving
these player inventories will always transfer 0 items.

## History and other notes

This mod requires [MixinBooter](https://www.curseforge.com/minecraft/mc-mods/mixin-booter).

This mod was made for the MeatballCraft modpack, and exists to prevent the use of player
interfaces to transfer items to players in the Vethea dimension.

The mod is [open-source](https://github.com/shardion/restrictplayerinterfaces/) and licensed under the MIT license (yes, you can use it in your modpack).
