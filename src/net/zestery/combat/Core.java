package net.zestery.combat;

import net.zestery.combat.commands.CommandExecutor;
import net.zestery.combat.events.Damager;
import net.zestery.combat.events.EatAPPLE;
import net.zestery.combat.events.Join;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private static Core ourInstance;

    private static Plugin plugin;

    public static Core getOurInstance(){
        return ourInstance;
    }

    public static Plugin getInstance(){
        return plugin;
    }

    @Override
    public void onLoad(){
        //TODO
    }

    @Override
    public void onEnable(){
        ourInstance = this;
        plugin = this;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0D);
        }

        if(getConfig().getBoolean("previousGodApple")){
            ItemStack enchantedApple = new ItemStack(Material.GOLDEN_APPLE, 1, (short)1);
            ShapedRecipe god_apple = new ShapedRecipe(enchantedApple).
                    shape("***", "*#*", "***").setIngredient('*', Material.GOLD_BLOCK).setIngredient('#', Material.APPLE);

            Bukkit.addRecipe(god_apple);
        }

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Damager(), this);
        pm.registerEvents(new EatAPPLE(), this);
        pm.registerEvents(new Join(), this);
        saveDefaultConfig();
        getCommand("acu").setExecutor(new CommandExecutor());
    }

    @Override
    public void onDisable(){
        //TODO
    }


}
