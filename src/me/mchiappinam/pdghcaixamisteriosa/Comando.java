package me.mchiappinam.pdghcaixamisteriosa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comando implements CommandExecutor {
  //private Main plugin;

  public Comando(Main main) {
    //plugin = main;
  }

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (cmd.getName().equalsIgnoreCase("caixa")) {
			if(sender.hasPermission("pdgh.op")) {
				Player p = (Player) sender;
				p.getInventory().addItem(Main.openGUI);
			}
				
		}
		return true;
	}
}