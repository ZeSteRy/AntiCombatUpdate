package net.zestery.combat.events;

import net.zestery.combat.Core;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EatAPPLE implements Listener {

    Core c = Core.getOurInstance();

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();

        ItemStack enchantedApple = new ItemStack(Material.GOLDEN_APPLE, 1, (short)1);
        ItemStack inHand = e.getItem();

        if ((inHand.getType().equals(Material.GOLDEN_APPLE)) && (inHand.getDurability() == enchantedApple.getDurability())){
            if(c.getConfig().getBoolean("previousGodApple")){
                p.removePotionEffect(PotionEffectType.ABSORPTION);
                p.removePotionEffect(PotionEffectType.REGENERATION);
                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);

                // ADD PREVIOUS EFFECTS !

                p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 120*20, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30*20, 4));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300*20, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300*20, 0));
            }
        }

    }

}
