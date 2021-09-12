package me.nuymakstone.plugin;

import me.nuymakstone.tools.FileRW;
import me.nuymakstone.obj.Save;
import me.nuymakstone.obj.TLMap;
import me.nuymakstone.tools.ConfigSaverTCN;
import me.nuymakstone.tools.ObjectSerializerTCN;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.nuymakstone.parsing.TCN;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
    
    public static Main main;
    
    {
        main = this;
    }
    
    @Save
    TLMap<String, PlayerRecord> records = new TLMap<>();
    
    @Override
    public void onEnable() {
        try {
            TCN tcn;
            if(new File("plugins/ReachFix").mkdirs()) {
                //noinspection InstantiationOfUtilityClass
                tcn = new ObjectSerializerTCN(new Config()).convertAll().done();
                assert tcn != null;
                tcn.set("$type", null);
                tcn.set("$isArray", null);
                new FileRW("plugins/ReachFix/config.tcn").setContent(tcn.toString());
            }
            tcn = TCN.read(new FileRW("plugins/ReachFix/config.tcn").getContent().join("\n"));
            tcn.set("$isArray", false);
            tcn.set("$type", Config.class.getName());
            new ObjectSerializerTCN(tcn);
            
            ConfigSaverTCN.loadConfig(this, TCN.read(new FileRW("plugins/ReachFix/record.tcn").getContent().join("\n")));
        }
        catch (Exception ignored) { }
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        getLogger().info("Enabled ReachFix.MAIN");
    }
    
    @Override
    public void onDisable() {
        try {
            new FileRW("plugins/ReachFix/record.tcn").setContent(ConfigSaverTCN.saveConfig(this).toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class Config {
        public static int kickAt = 6;
        public static int stopAt = 3;
        public static float maxReach = 3;
        public static boolean showRecordOnHotbar = true;
    }
}
