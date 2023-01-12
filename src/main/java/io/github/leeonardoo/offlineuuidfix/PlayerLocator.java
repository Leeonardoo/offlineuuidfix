package io.github.leeonardoo.offlineuuidfix;

import com.google.gson.*;
import io.github.leeonardoo.offlineuuidfix.model.PlayerProfileModel;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.UUID;

public class PlayerLocator {

    @Nullable
    public static UUID getOnlineUUID(String playerName) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + playerName;
        try {
            String response = IOUtils.toString(new URL(url), Charset.defaultCharset());
            Gson gson = new Gson();
            PlayerProfileModel playerModel = gson.fromJson(response, PlayerProfileModel.class);

            long uuidMSB = Long.parseLong(playerModel.getId().substring(0, 8), 16);
            uuidMSB <<= 32;
            uuidMSB |= Long.parseLong(playerModel.getId().substring(8, 16), 16);
            long uuidLSB = Long.parseLong(playerModel.getId().substring(16, 24), 16);
            uuidLSB <<= 32;
            uuidLSB |= Long.parseLong(playerModel.getId().substring(24, 32), 16);
            UUID uuid = new UUID(uuidMSB, uuidLSB);

            if (playerModel.getName().equalsIgnoreCase(playerName))
                return uuid;
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
