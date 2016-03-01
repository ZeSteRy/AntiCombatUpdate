package net.zestery.combat.commands;

import net.zestery.combat.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandExecutor implements org.bukkit.command.CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(label.equalsIgnoreCase("acu")) {

            if (!(sender instanceof Player)) {
                Bukkit.getConsoleSender().sendMessage("§cThis command can't execute in console ! | Cette commande ne peux pas etre executee dans la console :o");
            }

            Player p = (Player) sender;

            if(args.length <= 0){
                if(Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("FR")) {
                    p.sendMessage("§7§m------------ §8[§aAntiCombatUpdate§8] §7§m------------");
                    p.sendMessage("§e/acu info §7| §aPermet de voir les modules actifs du plugin de la configuration !");
                    p.sendMessage("§e/acu set §atrue§7 | §cfalse §7| §aActiver le mode PVP 1.8 ou non !");
                    p.sendMessage("§e/acu version §7| §3Obtenir la version du plugin !");
                    p.sendMessage("§e/acu reload §7| §5Redémarrer la configuration du plugin !");
                    p.sendMessage("              §ePlugin par §aZeSteRy");
                    p.sendMessage("§7§m-----------------------------------------------------");
                    return true;
                }

                if(Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("EN")) {
                    p.sendMessage("§7§m------------ §8[§aAntiCombatUpdate§8] §7§m------------");
                    p.sendMessage("§e/acu info §7| §aGet modules actives in configuration");
                    p.sendMessage("§e/acu set §atrue§7 | §cfalse §7| §aSet on or not the default PVP 1.8");
                    p.sendMessage("§e/acu version §7| §3Get version of plugin !");
                    p.sendMessage("§e/acu reload §7| §5Reload config of plugin !");
                    p.sendMessage("              §ePlugin par §aZeSteRy");
                    p.sendMessage("§7§m-----------------------------------------------------");
                    return true;
                }
                return true;
            }


            if(args[0].equalsIgnoreCase("reload")){
                if(p.hasPermission("acu.reload")){
                    if (Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("FR")) {
                        Core.getOurInstance().reloadConfig();
                        p.sendMessage("§8[§aAntiCombatUpdate§8] §aLa configuration du plugin a bien été rechargée !");
                    }

                    if (Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("EN")) {
                        Core.getOurInstance().reloadConfig();
                        p.sendMessage("§8[§aAntiCombatUpdate§8] §aReload config successful");
                    }
                }
                return true;
            }

            if(args[0].equalsIgnoreCase("version")){
                if (Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("FR")) {
                    p.sendMessage("§8[§aAntiCombatUpdate§8] §aVersion du plugin : §21.0.1");
                    return true;
                }

                if (Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("EN")) {
                    p.sendMessage("§8[§aAntiCombatUpdate§8] §aVersion : §21.0.1");
                    return true;
                }
            }

            if(args[0].equalsIgnoreCase("set")){
                if(p.hasPermission("acu.set")) {
                    boolean actif = Boolean.parseBoolean(args[1].toString());
                    FileConfiguration c = Core.getOurInstance().getConfig();
                    if (Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("FR")) {
                        if (actif) {
                            p.sendMessage("§8[§aAntiCombatUpdate§8] §aPVP 1.8 : §2Activé");
                            c.set("previousGodApple", "true");
                            c.set("saveCooldown", "false");
                            c.set("changeIncreaseDamage", "true");
                            Core.getOurInstance().saveConfig();
                        } else {
                            p.sendMessage("§8[§aAntiCombatUpdate§8] §aPVP 1.8 : §cDésactivé");
                            c.set("previousGodApple", "false");
                            c.set("saveCooldown", "true");
                            c.set("changeIncreaseDamage", "false");
                            Core.getOurInstance().saveConfig();
                        }
                        return true;
                    }

                    if (Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("EN")) {
                        if (actif) {
                            p.sendMessage("§8[§aAntiCombatUpdate§8] §aPVP 1.8 : §2Enable");
                            c.set("previousGodApple", "true");
                            c.set("saveCooldown", "false");
                            c.set("changeIncreaseDamage", "true");
                            Core.getOurInstance().saveConfig();
                        } else {
                            p.sendMessage("§8[§aAntiCombatUpdate§8] §aPVP 1.8 : §cDisable");
                            c.set("previousGodApple", "false");
                            c.set("saveCooldown", "true");
                            c.set("changeIncreaseDamage", "false");
                            Core.getOurInstance().saveConfig();
                        }
                        return true;
                    }
                }
            }

            if(args[0].equalsIgnoreCase("info")) {
                if (p.hasPermission("acu.info")) {
                    Core c = Core.getOurInstance();

                    if (Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("FR")) {
                        p.sendMessage("§7§m------------ §8[§aAntiCombatUpdate§8] §7§m------------");
                        p.sendMessage("                §eInformations des modules actifs !");
                        if (c.getConfig().getBoolean("informPlayers")) {
                            p.sendMessage("§eInformer les joueurs des modifications des combats : §aActif");
                        } else {
                            p.sendMessage("§eInformer les joueurs des modifications des combats : §cDésactivé");
                        }
                        p.sendMessage(" ");
                        if (c.getConfig().getBoolean("autoUpdate")) {
                            p.sendMessage("§eMise à jour automatique du plugin : §aActif");
                        } else {
                            p.sendMessage("§eMise à jour automatique du plugin : §cDésactivé");
                        }
                        p.sendMessage("§eMessage d'information : §a" + c.getConfig().getString("msgPlayers"));
                        p.sendMessage(" ");
                        if (c.getConfig().getBoolean("previousGodApple")) {
                            p.sendMessage("§eRevenir aux anciens effets de la 1.8 pour la pomme de notch : §aActif");
                        } else {
                            p.sendMessage("§eRevenir aux anciens effets de la 1.8 pour la pomme de notch : §cDésactivé");
                        }
                        p.sendMessage(" ");
                        if (c.getConfig().getBoolean("saveCooldown")) {
                            p.sendMessage("§eGarder le cooldown en 1.9, juste changer les valeurs du cooldown : §aActif");
                        } else {
                            p.sendMessage("§eGarder le cooldown en 1.9, juste changer les valeurs du cooldown : §cDésactivé");
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§eCooldown en seconde / par coups : §a" + c.getConfig().getDouble("cooldownPerSeconds"));
                        p.sendMessage(" ");
                        if (c.getConfig().getBoolean("changeIncreaseDamage")) {
                            p.sendMessage("§eDégats infligés par items (no enchants) : §aActif");
                        } else {
                            p.sendMessage("§eDégats infligés par items (no enchants) : §cDésactivé");
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§7§m-----------------------------------------------------");
                        return true;
                    }

                    if (Core.getOurInstance().getConfig().getString("defaultLanguage").equalsIgnoreCase("EN")) {
                        p.sendMessage("§7§m------------ §8[§aAntiCombatUpdate§8] §7§m------------");
                        p.sendMessage("                §eInformations of actives modules !");
                        if (c.getConfig().getBoolean("informPlayers")) {
                            p.sendMessage("§eInform player cooldown is modified on your server : §aEnable");
                        } else {
                            p.sendMessage("§eInform player cooldown is modified on your server : §cDisable");
                        }
                        p.sendMessage(" ");
                        if (c.getConfig().getBoolean("autoUpdate")) {
                            p.sendMessage("§eActive auto update plugin : §aEnable");
                        } else {
                            p.sendMessage("§eActive auto update plugin : §cDisable");
                        }
                        p.sendMessage("§eCustom message inform players : §a" + c.getConfig().getString("msgPlayers"));
                        p.sendMessage(" ");
                        if (c.getConfig().getBoolean("previousGodApple")) {
                            p.sendMessage("§eBack to previous effects in 1.8 for god apple : §aEnable");
                        } else {
                            p.sendMessage("§eBack to previous effects in 1.8 for god apple : §cDisable");
                        }
                        p.sendMessage(" ");
                        if (c.getConfig().getBoolean("saveCooldown")) {
                            p.sendMessage("§eSave cooldown in 1.9, just change cooldown value  : §aEnable");
                        } else {
                            p.sendMessage("§eSave cooldown in 1.9, just change cooldown value  : §cDisable");
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§eCooldown per seconde / damage : §a" + c.getConfig().getDouble("cooldownPerSeconds"));
                        p.sendMessage(" ");
                        if (c.getConfig().getBoolean("changeIncreaseDamage")) {
                            p.sendMessage("§eIncrease damage by items (no enchants) : §aEnable");
                        } else {
                            p.sendMessage("§eIncrease damage by items (no enchants) : §cDisable");
                        }
                        p.sendMessage(" ");
                        p.sendMessage("§7§m-----------------------------------------------------");
                        return true;
                    }
                }
            }

        }


        return true;
    }

}
