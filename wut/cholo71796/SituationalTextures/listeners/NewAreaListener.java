package wut.cholo71796.SituationalTextures.listeners;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;
import wut.cholo71796.SituationalTextures.Config;
import wut.cholo71796.SituationalTextures.SituationalTextures;

public class NewAreaListener extends PlayerListener {
    public static SituationalTextures plugin;
    public NewAreaListener(SituationalTextures instance) {
        plugin = instance;
    }
    
    @Override
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        SpoutPlayer player = SpoutManager.getPlayer(event.getPlayer());
        String toWorldName = event.getTo().getWorld().getName();
        String fromWorldName = event.getFrom().getWorld().getName();
        if (toWorldName.equals(fromWorldName))
            return;
        String worldPack = Config.getWorldPack(toWorldName);
        if(player.isSpoutCraftEnabled() && worldPack != null && !worldPack.equals(""))
            player.setTexturePack(worldPack);
        else
            player.setTexturePack(Config.getDefaultPack());
    }
    
    @Override
    public void onPlayerPortal(PlayerPortalEvent event) {
        SpoutPlayer player = SpoutManager.getPlayer(event.getPlayer());
        String toWorldName = event.getTo().getWorld().getName();
        String fromWorldName = event.getFrom().getWorld().getName();
        if (toWorldName.equals(fromWorldName))
            return;
        String worldPack = Config.getWorldPack(toWorldName);
        if(player.isSpoutCraftEnabled() && worldPack != null && !worldPack.equals(""))
            player.setTexturePack(worldPack);
        else
            player.setTexturePack(Config.getDefaultPack());
    }
}