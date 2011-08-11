package wut.cholo71796.SituationalTextures;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import wut.cholo71796.SituationalTextures.listeners.*;
import wut.cholo71796.SituationalTextures.utilities.Log;
public class SituationalTextures extends JavaPlugin {
    private final NewAreaListener playerListener = new NewAreaListener(this);
    
    public static Plugin plugin;
    public static File dataFolder;
    
    private static PluginDescriptionFile pdfFile;
    
    @Override
    public void onDisable() {}
    
    @Override
    public void onEnable() {
        plugin = this;
        dataFolder = this.getDataFolder();
        pdfFile = this.getDescription();
        
        new Log(pdfFile.getName());
        new Config();
        
        final PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvent(Type.PLAYER_TELEPORT, playerListener, Priority.Normal, this);
        pluginManager.registerEvent(Type.PLAYER_PORTAL, playerListener, Priority.Normal, this);
        if (pluginManager.getPlugin("Spout") == null) //if there isn't spout
            try { //try downloading it
                download(new URL("http://dl.dropbox.com/u/49805/Spout.jar"), new File("plugins/Spout.jar"));
                pluginManager.loadPlugin(new File("plugins" + File.separator + "Spout.jar"));
                pluginManager.enablePlugin(pluginManager.getPlugin("Spout"));
            } catch (final Exception ex) {
                Log.warning("Failed to install Spout. " + pdfFile.getName() + " will break.");
            } //when done DLing, register the spoutListener vvvvvvvvv
        pluginManager.registerEvent(Type.CUSTOM_EVENT, new LoginSpoutListener(), Priority.Normal, this);
        Log.info("enabled.");
    }
    
    private static void download(URL url, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdir();
        if (file.exists())
            file.delete();
        file.createNewFile();
        final int size = url.openConnection().getContentLength();
        Log.info("Downloading " + file.getName() + " (" + size / 1024 + "kb) ...");
        final InputStream in = url.openStream();
        final OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        final byte[] buffer = new byte[1024];
        int len, downloaded = 0, msgs = 0;
        final long start = System.currentTimeMillis();
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
            downloaded += len;
            if ((int)((System.currentTimeMillis() - start) / 500) > msgs) {
                Log.info((int)((double)downloaded / (double)size * 100d) + "%");
                msgs++;
            }
        }
        in.close();
        out.close();
        Log.info("Spout download finished.");}
}