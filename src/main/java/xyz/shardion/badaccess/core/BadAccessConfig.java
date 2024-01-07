package xyz.shardion.badaccess.core;

import net.minecraftforge.common.config.Config;

@Config(modid = "restrictplayerinterfaces", name = "RestrictPlayerInterfaces", category = "RestrictPlayerInterfaces")
public class BadAccessConfig {
    @Config.Name("Disallowed dimension IDs")
    @Config.Comment({"A list of numbers corresponding to dimension IDs that player interfaces cannot transfer to.",
                     "If the target player of a player interface is in a dimension listed here, the player interface",
                     "will cease to detect the player, transfer items, or otherwise not function."})
    public static int[] disallowedDimensionIds = new int[] { };
}
