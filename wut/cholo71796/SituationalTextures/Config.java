package wut.cholo71796.SituationalTextures;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.World;
import org.bukkit.util.config.Configuration;

/**
 *
 * @author Cole Erickson
 */
public class Config {
    private static Configuration config;
    private static File configfile;
    
    private static Map<String, Object> nodes = new HashMap<String, Object>();
    private static String defaultPack;
    
    public Config() {
        configfile = new File(SituationalTextures.dataFolder, "config.yml");
        if (!configfile.exists()) {// create the folders for the config
            SituationalTextures.dataFolder.mkdirs();
        }
        
        config = new Configuration(configfile);
        config.load();
        for (World world : SituationalTextures.plugin.getServer().getWorlds()) {
            String worldName = world.getName();
            if (config.getString("worlds." + worldName, null) == null)// add all worlds not currently in config
                config.setProperty("worlds." + worldName, null);
        }
        if (config.getString("default") == null)
            config.setProperty("default", "http://evilmousestudios.com/tronic/tronic.zip");
        config.save();//make sure all the properties are put in config
        defaultPack = config.getString("default", "http://evilmousestudios.com/tronic/tronic.zip");
        nodes = config.getAll(); //get them
    }
    
    public static Map<String, Object> getNodes() {
        return nodes;
    }
    
    public static String getDefaultPack() {
        return defaultPack;
    }
    
    public static String getWorldPack(String worldName) {
        return (String) Config.getNodes().get("worlds." + worldName);
    }
}
