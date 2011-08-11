package wut.cholo71796.SituationalTextures.listeners;

import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutListener;
import org.getspout.spoutapi.player.SpoutPlayer;
import wut.cholo71796.SituationalTextures.Config;

public class LoginSpoutListener extends SpoutListener {    
    /**
     * When a player with Spoutcraft logs on, get their world and set the
     * texture pack accordingly
     */    
    @Override
    public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
        SpoutPlayer player = event.getPlayer();
        String worldName = player.getWorld().getName();
        String worldPack = Config.getWorldPack(worldName);
        if(player.isSpoutCraftEnabled() && worldPack != null && !worldPack.equals(""))
            player.setTexturePack(worldPack);
        else
            player.setTexturePack(Config.getDefaultPack());
    }
}