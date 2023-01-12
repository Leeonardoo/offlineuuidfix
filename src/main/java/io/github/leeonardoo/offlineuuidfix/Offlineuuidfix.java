package io.github.leeonardoo.offlineuuidfix;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("offlineuuidfix")
public class Offlineuuidfix {

    public Offlineuuidfix() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
