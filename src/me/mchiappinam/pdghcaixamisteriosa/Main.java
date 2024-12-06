package me.mchiappinam.pdghcaixamisteriosa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.mchiappinam.pdghcaixamisteriosa.Listeners;
import me.mchiappinam.pdghcaixamisteriosa.Itens;

public class Main extends JavaPlugin {
	int task;
	public static List<ItemStack> listados = new ArrayList<ItemStack>();
	public static ItemStack openGUI = null;
	Itens itens = new Itens(this);
	public boolean emUso=false;
	//private static itens itns;
	
	@Override
	public void onEnable() {
		File file = new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				saveResource("config_template.yml",false);
				File file2 = new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			}
			catch(Exception e) {}
		}
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		getServer().getPluginCommand("caixa").setExecutor(new Comando(this));
		itens.getItemOpenGUI();
		itens.getAllItens();
		getServer().getConsoleSender().sendMessage("§3[PDGHCaixaMisteriosa] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHCaixaMisteriosa] §2Acesse: http://pdgh.com.br/");
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHCaixaMisteriosa] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHCaixaMisteriosa] §2Acesse: http://pdgh.com.br/");
	}
	
	public void getItens() {
		itens.getAllItens();
	}
	
	public void setInv(final Player p) {
		emUso=true;
		final Inventory menu = Bukkit.createInventory(null, getConfig().getInt("tamanho"), getConfig().getString("nome").replaceAll("&", "§"));;
        p.openInventory(menu);
    	for(int i=0; i<=26; i++) {
    		ItemStack a0 = new ItemStack(Material.STAINED_GLASS_PANE, 1);
    	    ItemMeta b0 = a0.getItemMeta();
    		List<String> l0 = new ArrayList<String>();
    	    b0.setDisplayName("§a.............");
    	    l0.add("§a.............");
    	    b0.setLore(l0);
    	    a0.setItemMeta(b0);
    	    menu.setItem(i, a0);
    	}
    	final List<ItemStack> aleatorios = new ArrayList<ItemStack>();
    	/**for(int i=0; i<=listados.size(); i++) {    	
        	Random r = new Random();
        	int ganhador = r.nextInt(listados.size());
        	ItemStack vencedor = listados.get(ganhador);
        	aleatorios.add(vencedor);
    	}*/
    	for(int i=0; i<=getConfig().getInt("itensPorRodadaRandom"); i++) {
        	Random r = new Random();
        	int ganhador = r.nextInt(listados.size());
        	ItemStack vencedor = listados.get(ganhador);
        	aleatorios.add(vencedor);
    	}
    	/**int itemMenu = 11;
    	for(int i=aleatorios.size(); i>=0; i--) {
        	if(itemMenu>=11 §§ itemMenu<=15)
        		menu.setItem(itemMenu, aleatorios.get(i));
        	itemMenu++;
    	}*/
		ItemStack a0 = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
	    ItemMeta b0 = a0.getItemMeta();
		List<String> l0 = new ArrayList<String>();
	    b0.setDisplayName("§a↑");
	    l0.add("§a↑");
	    l0.add("§a↑");
	    l0.add("§a↑");
	    b0.setLore(l0);
	    a0.setItemMeta(b0);
	    menu.setItem(22, a0);
		//menu.setItem(4, new ItemStack(Material.REDSTONE_TORCH_ON, 1));
		ItemStack a1 = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
	    ItemMeta b1 = a1.getItemMeta();
		List<String> l1 = new ArrayList<String>();
	    b1.setDisplayName("§a↓");
	    l1.add("§a↓");
	    l1.add("§a↓");
	    l1.add("§a↓");
	    b1.setLore(l1);
	    a1.setItemMeta(b1);
	    menu.setItem(4, a1);
	    p.updateInventory();
		//menu.setItem(22, new ItemStack(Material.REDSTONE_TORCH_ON, 1)); //↑↓
		task = getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	  		int timer = getConfig().getInt("timer2Ticks");
	  		int item = 0;
	  		public void run() {
	  			/**if(timer ==0) {
	  				getServer().getScheduler().cancelTask(task);
	  				getServer().getScheduler().cancelTask(task);
	  				getServer().getScheduler().cancelTask(task);
	  				getServer().getScheduler().cancelTask(task);
	  				getServer().getScheduler().cancelTask(task);
	  				getServer().getScheduler().cancelTask(task);
	  				getServer().getScheduler().cancelTask(task);
	  				getServer().getScheduler().cancelTask(task);
	  				getServer().getScheduler().cancelTask(task);
	  				getServer().getScheduler().cancelTask(task);
	  			}*/
	  			if(timer>=60) {
  		  			if(item==aleatorios.size()-7)
  		  				item=0;
	  				if(item<=aleatorios.size()-7) {
	  					menu.setItem(10, aleatorios.get(item));
		  			    menu.setItem(11, aleatorios.get(item+1));
		  			    menu.setItem(12, aleatorios.get(item+2));
		  			    menu.setItem(13, aleatorios.get(item+3));
		  			    menu.setItem(14, aleatorios.get(item+4));
		  			    menu.setItem(15, aleatorios.get(item+5));
		  			    menu.setItem(16, aleatorios.get(item+6));
			  			item++;
	  				}
	            	p.playSound(p.getLocation(), Sound.SHOOT_ARROW, 1.0F, 1.0F);
	  			}else 
		  			if(timer==58||
		  			timer==56||
		  			timer==54||
		  			timer==52||
		  			timer==50||
		  			timer==48||
				  	timer==46||
				  	timer==44||
				  	timer==42||
				  	timer==40||
		  			timer==38||
				  	timer==36||
				  	timer==34||
				  	timer==32||
				  	timer==30||
		  			timer==25||
		  			timer==20||
		  			timer==15||
		  			timer==10||
		  			timer==5||
		  			timer==0
				  	) {
	  		  			if(item==aleatorios.size()-7)
	  		  				item=0;
		  				if(item<=aleatorios.size()-7) {
		  					menu.setItem(10, aleatorios.get(item));
			  			    menu.setItem(11, aleatorios.get(item+1));
			  			    menu.setItem(12, aleatorios.get(item+2));
			  			    menu.setItem(13, aleatorios.get(item+3));
			  			    menu.setItem(14, aleatorios.get(item+4));
			  			    menu.setItem(15, aleatorios.get(item+5));
			  			    menu.setItem(16, aleatorios.get(item+6));
		  				}
		            	if(timer!=0) {
			            	p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
		            	}else{
			            	p.playSound(p.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
			            	p.getInventory().addItem(aleatorios.get(item+3));
			            	Map<Enchantment, Integer> enchantments = aleatorios.get(item+3).getEnchantments();
			            	//if(aleatorios.get(item+3).hasItemMeta())
			            	//	getServer().broadcastMessage("§5[Caixa MISTERIOSA] §e"+p.getName()+" recebeu: "+aleatorios.get(item+3).getItemMeta().getDisplayName());
			            	//else
			            	
			            	String traducaoItens;
			            	traducaoItens=aleatorios.get(item+3).getType().toString();
			            	for(String trans : getConfig().getStringList("traducaoItens")) {
			            		if(traducaoItens.contains(trans.split(",")[0]))
			            			traducaoItens=trans.split(",")[1];
			            	}
			            	String traducaoEncantamentos=null;
			            	if(isEnchantments(aleatorios.get(item+3))) {
				            	traducaoEncantamentos=enchantments.toString()
				            			.replace("[4", "")
				            			.replace("]", "")
				            			.replace("{", "")
				            			.replace("}", "")
				            			.replace("Enchantment", "")
				            			.replace("=", " ");
				            	for(String trans : getConfig().getStringList("traducaoEncantamentos")) {
				            		if(traducaoEncantamentos.contains(trans.split(",")[0]))
				            			traducaoEncantamentos=trans.split(",")[1];
				            	}
			            	}
			            	getServer().broadcastMessage("§5[Caixa MISTERIOSA] §e"+p.getName()+" recebeu "+aleatorios.get(item+3).getAmount()+" "+traducaoItens+
				            			(isEnchantments(aleatorios.get(item+3))?" encantado com:"+traducaoEncantamentos:""));
			        		emUso=false;
			  				getServer().getScheduler().cancelTask(task);
		            	}
			  			item++;
		  			}
	    		timer--;
	    		
	  		}
	  	  }, 0, 2);
	}

	
	public boolean isEnchantments(ItemStack item) {
		if(item.getEnchantments().isEmpty())
			return false;
		return true;
	}

	
	/**public List stringFormatadaEnchantments(ItemStack item) {
	}
	
	public List<String> a = new ArrayList<String>() {
		return item.getEnchantments().keySet();
	}*/
}