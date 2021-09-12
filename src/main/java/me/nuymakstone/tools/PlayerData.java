package me.nuymakstone.tools;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {
    private static UUID uuid;
    public static Player getBukkitPlayerFromUUID() {
        return Bukkit.getPlayer(uuid);
    }
    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }
}
