package net.zestery.combat.events;

import net.zestery.combat.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Damager implements Listener {

    // CHANGE DAMAGE BY ITEMS :O

    Core c = Core.getOurInstance();

    @EventHandler
    public void onDamage (EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){

            if(c.getConfig().getBoolean("changeIncreaseDamage")){
                e.setDamage(c.getConfig().getDouble(((Player) e.getEntity()).getItemInHand().toString()));
            }

        }
    }

}
