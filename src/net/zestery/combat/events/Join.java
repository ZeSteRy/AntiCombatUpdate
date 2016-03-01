package net.zestery.combat.events;

import net.zestery.combat.Core;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    Core c = Core.getOurInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();

        if(c.getConfig().getBoolean("informPlayers")){
            p.sendMessage(c.getConfig().getString("msgPlayers").replace("&", "§"));
        }

        if(!c.getConfig().getBoolean("saveCooldown")) {
            p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0D);
        }else{
            p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(c.getConfig().getDouble("cooldownPerSeconds") * 16.0D);
        }
    }

}
