package xyz.shardion.badaccess.core;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Arrays;
import java.util.List;

@Mod(modid = "restrictplayerinterfaces", name = "Restrict Player Interfaces", version = "1.0.0", useMetadata = true)
public class BadAccessEntrypoint implements ILateMixinLoader {
    public static Logger LOGGER = LogManager.getLogger();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
    }

    @Override
    public List<String> getMixinConfigs() {
        return Arrays.asList(
                "extrautilities2.badaccess.mixin.json",
                "modularrouters.badaccess.mixin.json",
                "plethora.badaccess.mixin.json",
                "randomthings.badaccess.mixin.json",
                "actuallyadditions.badaccess.mixin.json"
        );
    }
}