package me.nuymakstone.tools;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerUtil {
    private static ImmutableSet<Material> ground = Sets.immutableEnumSet(Material.SUGAR_CANE, Material.SUGAR_CANE_BLOCK,
            Material.TORCH, Material.ACTIVATOR_RAIL, Material.AIR, Material.CARROT, Material.CROPS, Material.DEAD_BUSH,
            Material.DETECTOR_RAIL, Material.DIODE_BLOCK_OFF, Material.DIODE_BLOCK_ON, Material.DOUBLE_PLANT,
            Material.FIRE, Material.GOLD_PLATE, Material.IRON_PLATE, Material.LAVA, Material.LEVER, Material.LONG_GRASS,
            Material.MELON_STEM, Material.NETHER_WARTS, Material.PORTAL, Material.POTATO, Material.POWERED_RAIL,
            Material.PUMPKIN_STEM, Material.RAILS, Material.RED_ROSE, Material.REDSTONE_COMPARATOR_OFF,
            Material.REDSTONE_COMPARATOR_ON, Material.REDSTONE_TORCH_OFF, Material.REDSTONE_TORCH_ON,
            Material.REDSTONE_WIRE, Material.SAPLING, Material.SEEDS, Material.SIGN, Material.SIGN_POST,
            Material.STATIONARY_LAVA, Material.STATIONARY_WATER, Material.STONE_BUTTON, Material.STONE_PLATE,
            Material.SUGAR_CANE_BLOCK, Material.TORCH, Material.TRIPWIRE, Material.TRIPWIRE_HOOK, Material.WALL_SIGN,
            Material.WATER, Material.WEB, Material.WOOD_BUTTON, Material.WOOD_PLATE, Material.YELLOW_FLOWER);

    @SuppressWarnings("unused")
    public static int getEff(Player p){
        int enchantmentLevel = 0;
        final ItemStack[] inv = p.getInventory().getContents();
        for(final ItemStack item:inv){
            if (item != null) {
                if(item.getType() != null){
                    if(item.getEnchantments().containsKey(Enchantment.DIG_SPEED)){
                        return enchantmentLevel = item.getEnchantmentLevel(Enchantment.DIG_SPEED);
                    }
                }
            }
        }
        return 0;
    }
    public static boolean isFlying2(BlockPlaceEvent e, Player p)
    {
        final Location loc = p.getLocation();
        loc.setY(loc.getY() -1);

        final Block block = loc.getWorld().getBlockAt(loc);
        if (block.getType().equals(Material.AIR))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean isFlying2(PlayerMoveEvent e, Player p)
    {
        final Location loc = p.getLocation();
        loc.setY(loc.getY() -1);

        final Block block = loc.getWorld().getBlockAt(loc);
        if (block.getType().equals(Material.AIR))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean isOnGround(PlayerMoveEvent e, Player p)
    {
        final Location loc = p.getLocation();
        loc.setY(loc.getY());

        final Block block = loc.getWorld().getBlockAt(loc);
        if (!block.getType().equals(Material.AIR))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean isOnGround(BlockPlaceEvent e, Player p)
    {
        final Location loc = p.getLocation();
        loc.setY(loc.getY());

        final Block block = loc.getWorld().getBlockAt(loc);
        if (!block.getType().equals(Material.AIR))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean isFlying(PlayerMoveEvent e, Player p)
    {
        final Location loc = p.getLocation();
        loc.setY(loc.getY() -2);

        final Block block = loc.getWorld().getBlockAt(loc);
        if (block.getType().equals(Material.AIR))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean isFlying(BlockPlaceEvent e, Player p)
    {
        final Location loc = p.getLocation();
        loc.setY(loc.getY() -2);

        final Block block = loc.getWorld().getBlockAt(loc);
        if (block.getType().equals(Material.AIR))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean isNearChest(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isChest(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNear1_13(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.Block_1_13(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearBar(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isBar(b)) {
                out = true;
            }
        }
        return out;
    }

    public static boolean isNearSign(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isSign(b)) {
                out = true;
            }
        }
        return out;
    }

    public static boolean isNearWeb(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isWeb(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearSolid(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isSolid(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearAllowedPhase(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.allowedPhase(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearGrass(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isGrass(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearLog(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isLog(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearAllowed(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isAllowed(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearLessThanABlock(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isLessThanBlock(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearPiston(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isPiston(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearPressure(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isPressure(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearSlab(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isSlab(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearAir(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isAir(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNotNearAir(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (!BlockUtil.isAir(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearB_1_13(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (!BlockUtil.B_1_13(b)) {
                out = true;
            }
        }
        return out;
    }

    public static boolean isNearHalfBlock(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isHalfBlock(b)) {
                out = true;
            }
        }
        return out;
    }

    public static boolean isNearIce(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isIce(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearPiston(Location loc) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(loc, 3)) {
            if (BlockUtil.isPiston(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearSlime(Location loc) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(loc, 3)) {
            if (BlockUtil.isSlime(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearSlime(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isSlime(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean isNearClimable(Player p) {
        boolean out = false;
        for (final Block b : BlockUtil.getNearbyBlocks(p.getLocation(), 1)) {
            if (BlockUtil.isClimbableBlock(b)) {
                out = true;
            }
        }
        return out;
    }
    public static boolean onGround2(Player p) {
        if (p.getLocation().getBlock().getType() == Material.AIR) {
            return false;
        } else {
            return true;
        }
    }
    public static int hasDepthStrider(Player player) {
        if(player.getInventory().getBoots() != null && player.getInventory().getBoots().containsEnchantment(Enchantment.getByName("DEPTH_STRIDER"))) {
            return player.getInventory().getBoots().getEnchantments().get(Enchantment.getByName("DEPTH_STRIDER"));
        }
        return 0;
    }

    public static boolean isOnGround4(Player player) {
        if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            return true;
        }
        Location a = player.getLocation().clone();
        a.setY(a.getY() - 0.5);
        if (a.getBlock().getType() != Material.AIR) {
            return true;
        }
        a = player.getLocation().clone();
        a.setY(a.getY() + 0.5);
        return a.getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR || isBlock(player.getLocation().getBlock().getRelative(BlockFace.DOWN),
                new Material[]{Material.FENCE, Material.FENCE_GATE, Material.COBBLE_WALL, Material.LADDER});
    }

    public static int getDistanceToGround(Player p){
        final Location loc = p.getLocation().clone();
        final double y = loc.getBlockY();
        int distance = 0;
        for (double i = y; i >= 0; i--){
            loc.setY(i);
            if(loc.getBlock().getType().isSolid()) {
                break;
            }
            distance++;
        }
        return distance;
    }

    private static boolean isGround(Material material) {
        return ground.contains(material);
    }

    public static boolean isOnGround(Location loc) {
        final double diff = .3;

        return !isGround(loc.clone().add(0, -.1, 0).getBlock().getType())
                || !isGround(loc.clone().add(diff, -.1, 0).getBlock().getType())
                || !isGround(loc.clone().add(-diff, -.1, 0).getBlock().getType())
                || !isGround(loc.clone().add(0, -.1, diff).getBlock().getType())
                || !isGround(loc.clone().add(0, -.1, -diff).getBlock().getType())
                || !isGround(loc.clone().add(diff, -.1, diff).getBlock().getType())
                || !isGround(loc.clone().add(diff, -.1, -diff).getBlock().getType())
                || !isGround(loc.clone().add(-diff, -.1, diff).getBlock().getType())
                || !isGround(loc.clone().add(-diff, -.1, -diff).getBlock().getType())
                || (BlockUtil.getBlockHeight(loc.clone().subtract(0.0D, 0.5D, 0.0D).getBlock()) != 0.0D &&
                (!isGround(loc.clone().add(diff, BlockUtil.getBlockHeight(loc.getBlock()) - 0.1, 0).getBlock().getType())
                        || !isGround(loc.clone().add(-diff, BlockUtil.getBlockHeight(loc.getBlock()) - 0.1, 0).getBlock().getType())
                        || !isGround(loc.clone().add(0, BlockUtil.getBlockHeight(loc.getBlock()) - 0.1, diff).getBlock().getType())
                        || !isGround(loc.clone().add(0, BlockUtil.getBlockHeight(loc.getBlock()) - 0.1, -diff).getBlock().getType())
                        || !isGround(loc.clone().add(diff, BlockUtil.getBlockHeight(loc.getBlock()) - 0.1, diff).getBlock().getType())
                        || !isGround(loc.clone().add(diff, BlockUtil.getBlockHeight(loc.getBlock()) - 0.1, -diff).getBlock().getType())
                        || !isGround(loc.clone().add(-diff, BlockUtil.getBlockHeight(loc.getBlock()) - 0.1, diff).getBlock().getType())
                        || !isGround(loc.clone().add(-diff, BlockUtil.getBlockHeight(loc.getBlock()) - 0.1, -diff).getBlock().getType())));
    }




    public static boolean isInWater(Location loc) {
        final double diff = .3;
        return BlockUtil.isLiquid(loc.clone().add(0, 0D, 0).getBlock())
                || BlockUtil.isLiquid(loc.clone().add(diff, 0D, 0).getBlock())
                || BlockUtil.isLiquid(loc.clone().add(-diff, 0D, 0).getBlock())
                || BlockUtil.isLiquid(loc.clone().add(0, 0D, diff).getBlock())
                || BlockUtil.isLiquid(loc.clone().add(0, 0D, -diff).getBlock())
                || BlockUtil.isLiquid(loc.clone().add(diff, 0D, diff).getBlock())
                || BlockUtil.isLiquid(loc.clone().add(diff, 0D, -diff).getBlock())
                || BlockUtil.isLiquid(loc.clone().add(-diff, 0D, diff).getBlock())
                || BlockUtil.isLiquid(loc.clone().add(-diff, 0D, -diff).getBlock())
                || (BlockUtil.getBlockHeight(loc.clone().subtract(0.0D, 0.5D, 0.0D).getBlock()) != 0.0D &&
                (BlockUtil.isLiquid(loc.clone().add(diff,  0D, 0).getBlock())
                        || BlockUtil.isLiquid(loc.clone().add(-diff,  0D, 0).getBlock())
                        || BlockUtil.isLiquid(loc.clone().add(0,  0D, diff).getBlock())
                        || BlockUtil.isLiquid(loc.clone().add(0,  0D, -diff).getBlock())
                        || BlockUtil.isLiquid(loc.clone().add(diff,  0D, diff).getBlock())
                        || BlockUtil.isLiquid(loc.clone().add(diff,  0D, -diff).getBlock())
                        || BlockUtil.isLiquid(loc.clone().add(-diff,  0D, diff).getBlock())
                        || BlockUtil.isLiquid(loc.clone().add(-diff,  0D, -diff).getBlock())));
    }
    public static boolean isOnSlime(Location loc) {
        final double diff = .3;
        return BlockUtil.isSlime(loc.clone().add(0, 0D, 0).getBlock())
                || BlockUtil.isSlime(loc.clone().add(diff, 0D, 0).getBlock())
                || BlockUtil.isSlime(loc.clone().add(-diff, 0D, 0).getBlock())
                || BlockUtil.isSlime(loc.clone().add(0, 0D, diff).getBlock())
                || BlockUtil.isSlime(loc.clone().add(0, 0D, -diff).getBlock())
                || BlockUtil.isSlime(loc.clone().add(diff, 0D, diff).getBlock())
                || BlockUtil.isSlime(loc.clone().add(diff, 0D, -diff).getBlock())
                || BlockUtil.isSlime(loc.clone().add(-diff, 0D, diff).getBlock())
                || BlockUtil.isSlime(loc.clone().add(-diff, 0D, -diff).getBlock());
    }


    public static boolean isOnSlab(Location loc) {
        final double diff = .3;
        return BlockUtil.isSlab(loc.clone().add(0, 0D, 0).getBlock())
                || BlockUtil.isSlab(loc.clone().add(diff, 0D, 0).getBlock())
                || BlockUtil.isSlab(loc.clone().add(-diff, 0D, 0).getBlock())
                || BlockUtil.isSlab(loc.clone().add(0, 0D, diff).getBlock())
                || BlockUtil.isSlab(loc.clone().add(0, 0D, -diff).getBlock())
                || BlockUtil.isSlab(loc.clone().add(diff, 0D, diff).getBlock())
                || BlockUtil.isSlab(loc.clone().add(diff, 0D, -diff).getBlock())
                || BlockUtil.isSlab(loc.clone().add(-diff, 0D, diff).getBlock())
                || BlockUtil.isSlab(loc.clone().add(-diff, 0D, -diff).getBlock());
    }

    public static boolean isOnStair(Location loc) {
        final double diff = 0.3;
        return 	BlockUtil.isStair(loc.clone().add(0, 0D, 0).getBlock())
                || BlockUtil.isStair(loc.clone().add(diff, 0D, 0).getBlock())
                || BlockUtil.isStair(loc.clone().add(-diff, 0D, 0).getBlock())
                || BlockUtil.isStair(loc.clone().add(0, 0D, diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(0, 0D, -diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(diff, 0D, diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(diff, 0D, -diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(-diff, 0D, diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(-diff, 0D, -diff).getBlock());
    }
    public static boolean isOnFence(Location loc) {
        final double diff = 0.3;
        return 	BlockUtil.isStair(loc.clone().add(0, 0D, 0).getBlock())
                || BlockUtil.isFence(loc.clone().add(diff, 0D, 0).getBlock())
                || BlockUtil.isFence(loc.clone().add(-diff, 0D, 0).getBlock())
                || BlockUtil.isFence(loc.clone().add(0, 0D, diff).getBlock())
                || BlockUtil.isFence(loc.clone().add(0, 0D, -diff).getBlock())
                || BlockUtil.isFence(loc.clone().add(diff, 0D, diff).getBlock())
                || BlockUtil.isFence(loc.clone().add(diff, 0D, -diff).getBlock())
                || BlockUtil.isFence(loc.clone().add(-diff, 0D, diff).getBlock())
                || BlockUtil.isFence(loc.clone().add(-diff, 0D, -diff).getBlock());
    }
    public static boolean isOnPressure(Location loc) {
        final double diff = 0.3;
        return 	BlockUtil.isStair(loc.clone().add(0, 0D, 0).getBlock())
                || BlockUtil.isStair(loc.clone().add(diff, 0D, 0).getBlock())
                || BlockUtil.isStair(loc.clone().add(-diff, 0D, 0).getBlock())
                || BlockUtil.isStair(loc.clone().add(0, 0D, diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(0, 0D, -diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(diff, 0D, diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(diff, 0D, -diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(-diff, 0D, diff).getBlock())
                || BlockUtil.isStair(loc.clone().add(-diff, 0D, -diff).getBlock());
    }

    public static boolean hasSlabsNear(Location location) {
        for(final Block block : BlockUtil.getSurroundingXZ(location.getBlock(), true)) {
            if(BlockUtil.isSlab(block)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isOnClimbable(Player player, int blocks) {
        if (blocks == 0) {
            for (final Block block : getSurrounding(player.getLocation().getBlock(), false)) {
                if (block.getType() == Material.LADDER || block.getType() == Material.VINE) {
                    return true;
                }
            }
        } else {
            for (final Block block : getSurrounding(player.getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock(),
                    false)) {
                if (block.getType() == Material.LADDER || block.getType() == Material.VINE) {
                    return true;
                }
            }
        }
        return player.getLocation().getBlock().getType() == Material.LADDER
                || player.getLocation().getBlock().getType() == Material.VINE;
    }

    public static boolean isInWeb(Player player) {
        if (player.getLocation().getBlock().getType() != Material.WEB && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.WEB && player.getLocation().getBlock().getRelative(BlockFace.UP).getType() != Material.WEB) {
            return false;
        }
        return true;
    }

    public static ArrayList<Block> getSurrounding(Block block, boolean diagonals) {
        final ArrayList<Block> blocks = new ArrayList<>();
        if (diagonals) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if ((x != 0) || (y != 0) || (z != 0)) {
                            blocks.add(block.getRelative(x, y, z));
                        }
                    }
                }
            }
        } else {
            blocks.add(block.getRelative(BlockFace.UP));
            blocks.add(block.getRelative(BlockFace.DOWN));
            blocks.add(block.getRelative(BlockFace.NORTH));
            blocks.add(block.getRelative(BlockFace.SOUTH));
            blocks.add(block.getRelative(BlockFace.EAST));
            blocks.add(block.getRelative(BlockFace.WEST));
        }
        return blocks;
    }


    public static Location getEyeLocation(Player player) {
        final Location eye = player.getLocation();
        eye.setY(eye.getY() + player.getEyeHeight());
        return eye;
    }

    public static boolean isBlock(Block block, Material[] materials) {
        final Material type = block.getType();
        Material[] arrayOfMaterial;
        final int j = (arrayOfMaterial = materials).length;
        for (int i = 0; i < j; i++) {
            final Material m = arrayOfMaterial[i];
            if (m == type) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAir(Player player) {
        final Block b = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        return b.getType().equals(Material.AIR)
                && b.getRelative(BlockFace.WEST).getType().equals(Material.AIR)
                && b.getRelative(BlockFace.NORTH).getType().equals(Material.AIR)
                && b.getRelative(BlockFace.EAST).getType().equals(Material.AIR)
                && b.getRelative(BlockFace.SOUTH).getType().equals(Material.AIR);
    }


    public static int getPotionEffectLevel(Player player, PotionEffectType pet) {
        for (final PotionEffect pe : player.getActivePotionEffects()) {
            if (pe.getType().getName().equals(pet.getName())) {
                return pe.getAmplifier() + 1;
            }
        }
        return 0;
    }


    public static boolean isOnTheGround(Player player) {
        return isOnTheGround(player, 0.25);
    }
    public static boolean isOnTheGround(Player player, double yExpanded) {
        final Object box = ReflectionUtil.modifyBoundingBox(ReflectionUtil.getBoundingBox(player), 0, -yExpanded, 0,0,0,0);

        return ReflectionUtil.getCollidingBlocks(player, box).size() > 0;
    }

    public static boolean isNotSpider(Player player) {
        return isOnTheGround(player, 1.25);
    }
    public static boolean isInSlime(Player player) {
        final Object box = ReflectionUtil.getBoundingBox(player);

        final double minX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "a"), box);
        final double minY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "b"), box);
        final double minZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "c"), box);
        final double maxX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "d"), box);
        final double maxY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "e"), box);
        final double maxZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "f"), box);

        for(double x = minX ; x < maxX ; x++) {
            for(double y = minY ; y < maxY ; y++) {
                for(double z = minZ ; z < maxZ ; z++) {
                    final Block block = new Location(player.getWorld(), x, y, z).getBlock();

                    if(BlockUtil.isSlime(block)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isInLiquid(Player player) {
        final Object box = ReflectionUtil.getBoundingBox(player);

        final double minX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "a"), box);
        final double minY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "b"), box);
        final double minZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "c"), box);
        final double maxX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "d"), box);
        final double maxY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "e"), box);
        final double maxZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "f"), box);

        for(double x = minX ; x < maxX ; x++) {
            for(double y = minY ; y < maxY ; y++) {
                for(double z = minZ ; z < maxZ ; z++) {
                    final Block block = new Location(player.getWorld(), x, y, z).getBlock();

                    if(BlockUtil.isLiquid(block)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isInStairs(Player player) {
        final Object box = ReflectionUtil.modifyBoundingBox(ReflectionUtil.getBoundingBox(player), 0, -0.5,0,0,0,0);

        final double minX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "a"), box);
        final double minY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "b"), box);
        final double minZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "c"), box);
        final double maxX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "d"), box);
        final double maxY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "e"), box);
        final double maxZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "f"), box);

        for(double x = minX ; x < maxX ; x++) {
            for(double y = minY ; y < maxY ; y++) {
                for(double z = minZ ; z < maxZ ; z++) {
                    final Block block = new Location(player.getWorld(), x, y, z).getBlock();

                    if(BlockUtil.isStair(block)
                            || BlockUtil.isSlab(block)
                            || block.getType().equals(Material.SKULL)
                            || block.getType().equals(Material.CAKE_BLOCK)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isOnClimbable(Player player) {
        return BlockUtil.isClimbableBlock(player.getLocation().getBlock())
                || BlockUtil.isClimbableBlock(player.getLocation().add(0, 1,0).getBlock());
    }



    public static boolean inUnderBlock(Player player) {
        final Object box = ReflectionUtil.modifyBoundingBox(ReflectionUtil.getBoundingBox(player), 0, 0,0,0,1,0);

        final double minX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "a"), box);
        final double minY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "b"), box);
        final double minZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "c"), box);
        final double maxX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "d"), box);
        final double maxY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "e"), box);
        final double maxZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "f"), box);

        for(double x = minX ; x < maxX ; x++) {
            for(double y = minY ; y < maxY ; y++) {
                for(double z = minZ ; z < maxZ ; z++) {
                    final Block block = new Location(player.getWorld(), x, y, z).getBlock();

                    if(block.getType().isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean isOnAllowedPhase(Player player) {
        final Object box = ReflectionUtil.modifyBoundingBox(ReflectionUtil.getBoundingBox(player), 0, -0.5,0,0,0,0);

        final double minX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "a"), box);
        final double minY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "b"), box);
        final double minZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "c"), box);
        final double maxX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "d"), box);
        final double maxY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "e"), box);
        final double maxZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "f"), box);

        for(double x = minX ; x < maxX ; x++) {
            for(double y = minY ; y < maxY ; y++) {
                for(double z = minZ ; z < maxZ ; z++) {
                    final Block block = new Location(player.getWorld(), x, y, z).getBlock();

                    if(BlockUtil.allowedPhase(block)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isOnIce(Player player) {
        final Object box = ReflectionUtil.modifyBoundingBox(ReflectionUtil.getBoundingBox(player), 0, -0.5,0,0,0,0);

        final double minX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "a"), box);
        final double minY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "b"), box);
        final double minZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "c"), box);
        final double maxX = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "d"), box);
        final double maxY = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "e"), box);
        final double maxZ = (double) ReflectionUtil.getInvokedField(ReflectionUtil.getField(box.getClass(), "f"), box);

        for(double x = minX ; x < maxX ; x++) {
            for(double y = minY ; y < maxY ; y++) {
                for(double z = minZ ; z < maxZ ; z++) {
                    final Block block = new Location(player.getWorld(), x, y, z).getBlock();

                    if(block.getType().equals(Material.ICE)
                            || block.getType().equals(Material.PACKED_ICE)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static boolean isInWater(Player player) {
        final Material m = player.getLocation().getBlock().getType();
        return m == Material.STATIONARY_WATER || m == Material.WATER;
    }


    public static boolean isPartiallyStuck(Player player) {
        if (player.getLocation().clone().getBlock() == null) {
            return false;
        }
        final Block block = player.getLocation().clone().getBlock();
        if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid()
                || player.getLocation().getBlock().getRelative(BlockFace.UP).getType().isSolid()) {
            return true;
        }
        if (player.getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock().getRelative(BlockFace.DOWN).getType()
                .isSolid()
                || player.getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock().getRelative(BlockFace.UP).getType()
                .isSolid()) {
            return true;
        }
        return block.getType().isSolid();
    }

    public static boolean isFullyStuck(Player player) {
        final Block block1 = player.getLocation().clone().getBlock();
        final Block block2 = player.getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock();
        if (block1.getType().isSolid() && block2.getType().isSolid()) {
            return true;
        }
        return block1.getRelative(BlockFace.DOWN).getType().isSolid()
                || block1.getLocation().getBlock().getRelative(BlockFace.UP).getType().isSolid()
                && block2.getRelative(BlockFace.DOWN).getType().isSolid()
                || block2.getLocation().getBlock().getRelative(BlockFace.UP).getType().isSolid();
    }

}
