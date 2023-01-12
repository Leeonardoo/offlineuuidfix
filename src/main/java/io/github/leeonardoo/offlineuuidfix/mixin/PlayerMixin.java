package io.github.leeonardoo.offlineuuidfix.mixin;

import io.github.leeonardoo.offlineuuidfix.PlayerLocator;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.UUID;

@Mixin(value = Player.class)
public class PlayerMixin {

    private static final Logger LOGGER = LogManager.getLogger("OfflineUUIDFix");

    /**
     * @author Leeonardoo
     * @reason Because it's all what this mod is supposed to do
     */
    @Overwrite
    public static UUID createPlayerUUID(String pUsername) {
        UUID uuid = PlayerLocator.getOnlineUUID(pUsername);
        LOGGER.info("The player " + pUsername + " will now use the online UUID: " + uuid);
        return uuid;
    }
}
