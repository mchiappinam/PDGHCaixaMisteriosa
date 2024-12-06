package me.mchiappinam.pdghcaixamisteriosa;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener  {
	
	private Main plugin;
	public Listeners(Main main) {
		plugin=main;
	}
	
	@EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR)||e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
        	Player p = e.getPlayer();
    		if((p.getItemInHand() != null)) {
    			if(p.getItemInHand().hasItemMeta()) {
		            String name = p.getItemInHand().getItemMeta().getDisplayName();
		            if(name != null)
			        	if(name.contains(Main.openGUI.getItemMeta().getDisplayName())) {
			        		if(plugin.emUso) {
			        			p.sendMessage("§cO sistema de caixas está atualmente em uso.");
			        			return;
			        		}
				    		e.setCancelled(true);
				    		if(p.getItemInHand().getAmount()==1)
				    			p.setItemInHand(new ItemStack(Material.AIR));
				    		else
				    			p.getItemInHand().setAmount(p.getItemInHand().getAmount()-1);
				    		p.updateInventory();
				    		plugin.getItens();
				    		plugin.setInv(p);
			        	}
    			}
    		}
        }
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
	    Inventory inventory = e.getInventory();
	    if (inventory.getName().equals(plugin.getConfig().getString("nome").replaceAll("&", "§"))) {
	    	e.setCancelled(true);
	    }
	}
	
}