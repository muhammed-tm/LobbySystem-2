package eu.hypetime.spigot.hypelobby.listener;

import eu.hypetime.spigot.hypelobby.HypeLobby;
import eu.hypetime.spigot.hypelobby.utils.CoinsAPI;
import eu.hypetime.spigot.hypelobby.utils.ItemBuilder;
import eu.hypetime.spigot.hypelobby.utils.MySQL;
import eu.hypetime.spigot.hypelobby.utils.ScoreAPI;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Lottery {

    public static HashMap<Player, Integer> chests = new HashMap<>();
    public static HashMap<Player, Integer> currentCoins = new HashMap<>();

    @EventHandler
    public void JoinEvent(PlayerJoinEvent event){
        Player p = event.getPlayer();
        chests.put(p, 0);
        currentCoins.put(p, 0);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getType() == Material.CHEST){
                //if(!.contains(p)){
                    e.setCancelled(true);
                    int tickets = new MySQL().getFreeTickets(p.getUniqueId());
                    if(tickets <= 9*3){
                        Inventory inv = Bukkit.createInventory(null, 9  *  4, "§aLotto");
                        ArrayList<String> lore1 = new ArrayList<>();
                        lore1.add("§7§m--------");
                        lore1.add("");
                        lore1.add("§e" + new MySQL().getFreeTickets(p.getUniqueId()) + " §6§lLose");
                        lore1.add("");
                        lore1.add("§7§m--------");

                        inv.setItem(13, utils.createlore(Material.PAPER, 1, 0, "§6Los", lore1));
                        // §7§m------ \n \n §e500 §7Coins \n \n §7§m------
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add("§7§m--------");
                        lore.add("");
                        lore.add("§e1000 §7Coins");
                        lore.add("");
                        lore.add("§7§m--------");

                        inv.setItem(9*3, utils.createlore(Material.BARRIER, 1, 0, "§aLos kaufen", lore));


                        p.openInventory(inv);
                    }else{
                        new MySQL().removeFreeickets(p.getUniqueId(), 2);
                    }
               // }
            }
        }
    }

    @EventHandler
    public void on(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        try{
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aLos kaufen") && e.getClickedInventory().getTitle().equalsIgnoreCase("§aLotto")){
                if(CoinsAPI.getCoins(p) > 999){
                    new MySQL().addFreeTickets(p.getUniqueId(), 1);
                    CoinsAPI.removeCoins(p, 1000);
                }else{
                    p.sendMessage(HypeLobby.getInstance().getConstants().getPrefix() + "Du hast nicht genügend Coins um dir ein Los zu kaufen");
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Los") && new MySQL().getFreeTickets(p.getUniqueId()) > 0){
                Inventory inv = Bukkit.createInventory(null, 9*1, "§c§lLos einlösen");
                inv.setItem(0, new ItemBuilder(Material.LEGACY_STAINED_CLAY).setDyeColor(DyeColor.RED).setName("§a§lLos einlösen").toItemStack());
                inv.setItem(4, new ItemBuilder(Material.PAPER).setName("§6Los").toItemStack());
                inv.setItem(8, new ItemBuilder(Material.LEGACY_STAINED_CLAY).setName("§c§lKauf abbrechen").toItemStack());
                p.openInventory(inv);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lLos einl§sen")){
                new MySQL().removeFreeickets(p.getUniqueId(), 1);
                p.closeInventory();

                // Reward
                p.openInventory(getInv());

            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lKauf abbrechen")){
                p.closeInventory();
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aLos kaufen") && p.getOpenInventory().getTitle().equalsIgnoreCase("§c§lLos einlösen")){
                e.setCancelled(true);
            }else if(e.getCurrentItem().getType() == Material.ENDER_CHEST){
                if(chests.get(p) < 6){
                    chests.put(p, chests.get(p) + 1);
                    List<Integer> coins = getList();
                    currentCoins.put(p, currentCoins.get(p) + coins.get(e.getSlot()));
                    e.getCurrentItem().setType(Material.BARRIER);
                }
                if(chests.get(p) == 5){
                    chests.put(p, 0);
                    p.closeInventory();
                    CoinsAPI.addCoins(p, currentCoins.get(p));
                    ScoreAPI.setScoreboard(p);
                    p.sendTitle("§6Lottery", "§c+ §7 " + currentCoins.get(p) + " Coins", 2, 2, 2);

                    currentCoins.put(p, 0);
                    Bukkit.getScheduler().scheduleSyncDelayedTask( HypeLobby.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            Firework fire = p.getWorld().spawn( p.getLocation(), Firework.class );

                            FireworkMeta meta = fire.getFireworkMeta();
                            meta.addEffect( FireworkEffect.builder().withFade( Color.BLUE ).flicker( true ).withColor( Color.GREEN ).with( FireworkEffect.Type.BALL_LARGE ).flicker( true ).build() );
                            meta.setPower( 2 );

                            fire.setFireworkMeta( meta );

                        }
                    }, 0 );
                }
            }
        }catch(Exception ex){}
    }

	/*
	 * Random r = new Random();
				int random  = r.nextInt(3) + 0;
				if(random == 0){
					API.sendTitle(p, "§6Lottery", "§c+ §750 Coins", 2, 2, 2);
					Coins.addCoins(p, 50);
					Scoreboard.setScoreboard(p);
					p.closeInventory();
					return;
				}
				if(random == 1){
					API.sendTitle(p, "§6Lottery", "§c+ §750 Coins", 2, 2, 2);
					Coins.addCoins(p, 50);
					Scoreboard.setScoreboard(p);
					p.closeInventory();
					return;
				}else if(random == 2){
					API.sendTitle(p, "§6Lottery", "§c+ §7100 Coins", 2, 2, 2);
					Coins.addCoins(p, 100);
					Scoreboard.setScoreboard(p);
					p.closeInventory();
					return;
				}else if(random == 3){
					API.sendTitle(p, "§6Lottery", "§c+ §7200 Coins", 2, 2, 2);
					Coins.addCoins(p, 200);
					Scoreboard.setScoreboard(p);
					p.closeInventory();
					return;
				}
	 */

    public Inventory getInv(){
        Inventory inv = Bukkit.createInventory(null, 54, "§6§lLottery");

        ItemStack i = new ItemStack(Material.ENDER_CHEST);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName("§6§lKlick mich");
        i.setItemMeta(m);

        for(int i1 = 0; i1 < inv.getSize(); i1++){
            inv.setItem(i1, i);
        }
        return inv;
    }

    public List<Integer> getList(){
        List<Integer> list = new LinkedList<>();

        for(int i = 0; i < 54; i++){
            list.add(randomCoins());
        }

        Collections.shuffle(list);
        return list;
    }

    public Integer randomCoins(){
        HashMap<Integer, Integer> randoms = new HashMap<>();

        randoms.put((int) Math.ceil(Math.random()*100), 100);
        randoms.put((int) Math.ceil(Math.random()*1000), 1);

        List<Integer> arrayList = new ArrayList<>();

        for(int coins : randoms.keySet()){
            for(int i = 0; i < (randoms.get(coins)*10); i++){
                arrayList.add(coins);
            }
        }
        Collections.shuffle(arrayList);
        int r = new Random().nextInt(arrayList.size());
        return arrayList.get(r);
    }
}
