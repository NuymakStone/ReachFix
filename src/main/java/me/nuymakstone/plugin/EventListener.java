package me.nuymakstone.plugin;

import me.nuymakstone.tools.*;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.bukkit.Bukkit.getServer;

public class EventListener implements Listener {
    
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity entity = event.getEntity();

        if (entity instanceof Player && damager instanceof Player) {
            //第四种检查
             Player p = ((Player) damager).getPlayer();
            final Entity d = event.getEntity();
            final double YawDifference = Math.abs(180 - Math.abs(d.getLocation().getYaw() - p.getLocation().getYaw()));
            double Difference = PlayerUtil.getEyeLocation(p).distance(d.getLocation()) - 0.35 -0.38 - 0.03;
            final int Ping = ((CraftPlayer) damager).getHandle().ping;
            double maxReach = 3.0 + d.getVelocity().length();
            if (p.getAllowFlight()) {
                maxReach += p.getFlySpeed();
            }
            if (p.isSprinting()) {
                maxReach += 0.2;
            }
            if (!(d instanceof Player)) {
                maxReach += 1.0;
            }
            if (d instanceof Player) {
                final Player player = (Player) d;
                if (player.getAllowFlight()) {
                    maxReach += 2.0;
                }
            }
            if (PlayerUtil.isNearSlime(p.getLocation())
                    || PlayerUtil.isNearSlime(d.getLocation())) {
                maxReach += 1.0;
            }
            if (d instanceof Spider || d instanceof Giant) {
                maxReach += 1.0;
            }
            if (d instanceof Slime) {
                final Slime slime = (Slime) d;
                maxReach += slime.getSize()/4;
            }
            if (d instanceof MagmaCube) {
                final MagmaCube MagmaCube = (MagmaCube) d;
                maxReach += MagmaCube.getSize()/4;
            }
            if (p.getGameMode().equals(GameMode.CREATIVE)) {
                maxReach += 1.0;
            }
            if (p.getLocation().getY() > d.getLocation().getY()) {
                Difference = p.getLocation().getY() - p.getLocation().getY();
                maxReach += Difference / 2.5;
            } else if (p.getLocation().getY() > p.getLocation().getY()) {
                Difference = p.getLocation().getY() - p.getLocation().getY();
                maxReach += Difference / 2.5;
            }
            for (final PotionEffect effect : p.getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.SPEED)) {
                    maxReach += 0.2D * (effect.getAmplifier() + 1);
                }
            }
            if (19.99 <20) {
                final double TPSMultiplier = 19.99 / 20;
                final double tmp = maxReach / TPSMultiplier;
                maxReach = tmp;
            }
            final float velocity = (float)((Ping*0.0025) + Math.abs(d.getVelocity().length()) * 0.8);
            maxReach += velocity;
            maxReach += Ping < 250 ? Ping * 0.01262 : Ping * 0.0415;
            maxReach += YawDifference * 0.008;
            final double x = Math.abs(Math.abs(p.getLocation().getX()) - Math.abs(d.getLocation().getX()));
            final double y = Math.abs(Math.abs(p.getLocation().getY()) - Math.abs(d.getLocation().getY()));
            final double z = Math.abs(Math.abs(p.getLocation().getZ()) - Math.abs(d.getLocation().getZ()));
            final double distance = x+y+z;
            final double Reach1 = Difference - maxReach;
            double Reach = Reach1;
            if (p.getGameMode().equals(GameMode.CREATIVE)) {
                Reach += 4;
            }
            else {
                Reach += 3;
            }
            final String en = d.getName().toString();
            if (maxReach < Difference) {
                final double ChanceVal = Math.round(Math.abs((Difference - maxReach) * 100));
                damager.sendMessage("" + ChatColor.AQUA + "[ReachChecker] " + ChatColor.RESET + damager.getName() + " : " + Difference);
            }
            //第一种检查
            final int ping = ((CraftPlayer) damager).getHandle().ping;

            List<Cuboid> cuboidList = getPastEntitiesLocationsInRange(ping)
                    .stream().map(PlayerLocation::hitbox).collect(Collectors.toList());

            Vector attacker = event.getDamager().getLocation().toVector();

            float distance_1 = (float) cuboidList.stream().mapToDouble(cuboid ->
                    cuboid.distanceXZ(attacker.getX(), attacker.getZ()) - 0.1f).min().orElse(0);

            if (((Player) damager).getGameMode() != GameMode.CREATIVE) {
                // If attacker is a player
                // Fire local event, this returns the best-case reach of the player
                float f = onPlayerHitPlayer((Player) event.getDamager(), event.getEntity());
                //第二种
                double damagerreach = damager.getLocation().distance(entity.getLocation());
                double damager_reach = damagerreach-0.34;
                // Get the player's record
                //第三种
                PlayerRecord record = Main.main.records.get(event.getDamager().getUniqueId().toString());
                Player targetPlayer = (Player) entity;//被攻击者
                Player fromPlayer = (Player) damager;//检查者
                if (Main.Config.showRecordOnHotbar)
                    Utils.displayActionTitle(((Player) event.getDamager()), "你的攻击距离为: " + f + " / Bukkit算法: " + damager_reach + " / Yaw算法: " + Difference + " / Cuboid算法: " + distance_1 + " / 你的VL值: " + record.offenses);
                    if (record.playerReach>3.0) {
                     damager.sendMessage("" + ChatColor.AQUA + "[ReachChecker] " + ChatColor.RESET + fromPlayer.getName() + " : " + f + " §6§l(" + record.offenses + ")§r " + "ping: " + ((CraftPlayer) fromPlayer).getHandle().ping + "\n§bdebug:" + targetPlayer.getVelocity().getY() + " | " + targetPlayer.getVelocity());
                    }    // Punish if required
                if (record.offenses >= Main.Config.stopAt) {
                    event.setDamage(0);
                }
                if (record.offenses >= Main.Config.kickAt) {
                    ((Player) event.getDamager()).kickPlayer("Reach/Hitbox Hack");
                    record.offenses = (float) (Main.Config.stopAt - 0.2);
                }
            }
        }
    }

    private List<PlayerLocation> getPastEntitiesLocationsInRange(int ping) {
        List<PlayerLocation> toReturn = new ArrayList<>();
        int range = 0;
        int pingInTicks = ping / 50;
        return toReturn;
    }

    public float onPlayerHitPlayer(Player source, Entity victim) {
        
        // Record
        PlayerRecord record;
        
        // Get the record
        if(!Main.main.records.keys().contains(source.getUniqueId().toString())) {
            record = new PlayerRecord();
            Main.main.records.set(source.getUniqueId().toString(), record);
        }
        else {
            record = Main.main.records.get(source.getUniqueId().toString());
        }
        
        // Calculate reach and add it to the record
        float f;
        record.recordReach(f = calculateReach(source, victim));
        
        return f;
    }
    
    public float calculateReach(Player source, Entity victim) {
        float d = Float.MAX_VALUE;
    
        EntityLiving entityVictim = (EntityLiving) Utils.getActualEntity(victim);
        Vector eyes = source.getEyeLocation().toVector();
        AxisAlignedBB hitBox = entityVictim.getBoundingBox();
        
        // All corners of the hitbox
        Vector posLLL = new Vector(hitBox.a, hitBox.b, hitBox.c); // x0 y0 z0
        Vector posLLH = new Vector(hitBox.a, hitBox.b, hitBox.f); // x0 y0 z1
        Vector posLHL = new Vector(hitBox.a, hitBox.e, hitBox.c); // x0 y1 z0
        Vector posLHH = new Vector(hitBox.a, hitBox.e, hitBox.f); // x0 y1 z1
        Vector posHLL = new Vector(hitBox.d, hitBox.b, hitBox.c); // x1 y0 z0
        Vector posHLH = new Vector(hitBox.d, hitBox.b, hitBox.f); // x1 y0 z1
        Vector posHHL = new Vector(hitBox.d, hitBox.e, hitBox.c); // x1 y1 z0
        Vector posHHH = new Vector(hitBox.d, hitBox.e, hitBox.f); // x1 y1 z1
        
        // Get distance to each corner, the smallest distance is then stored
        d = getDistance(d, eyes, posLLL);
        d = getDistance(d, eyes, posLLH);
        d = getDistance(d, eyes, posLHL);
        d = getDistance(d, eyes, posLHH);
        d = getDistance(d, eyes, posHLL);
        d = getDistance(d, eyes, posHLH);
        d = getDistance(d, eyes, posHHL);
        d = getDistance(d, eyes, posHHH);
        
        // Return smallest distance
        return d;
    }
    
    public float getDistance(float d, Vector location, Vector vector) {
        // Get smallest value, this distance, or d (prev distance)
        return (float) Math.min(d, location.distance(vector));
    }
}
