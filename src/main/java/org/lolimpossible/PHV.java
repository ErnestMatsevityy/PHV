package org.lolimpossible;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class PHV extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        getLogger().info("-----Player`s head drop like in Vanilla---by-TheImpossible-----");
        getLogger().info("-----PHV---Enable-----");
    }

    @Override
    public void onDisable() {
        getLogger().info("-----PHV---Disable-----");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        if (isChargedCreeper(event)) {

            ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta_skull = (SkullMeta) playerSkull.getItemMeta();
            Player player = event.getEntity().getPlayer();

            meta_skull.setOwningPlayer(player);
            meta_skull.setDisplayName(player.getDisplayName() + "`s " + "head");

            playerSkull.setItemMeta(meta_skull);

            event.getDrops().add(playerSkull);
        }
    }

    public boolean isChargedCreeper(PlayerDeathEvent event) {

        if (event.getDamageSource().getCausingEntity() instanceof Creeper) {

            Creeper creeper = (Creeper) event.getDamageSource().getCausingEntity();
            return creeper.isPowered();
        }
        return false;
    }
}
