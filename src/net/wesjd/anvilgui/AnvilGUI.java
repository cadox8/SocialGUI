package net.wesjd.anvilgui;

import java.util.function.BiFunction;

import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.wesjd.anvilgui.version.Version;
import net.wesjd.anvilgui.version.VersionWrapper;

//Code by Wesley Smith

public class AnvilGUI implements Listener {

	private final Player holder;
	private final ItemStack insert;
	private final BiFunction<Player, String, String> biFunction;

	private final VersionWrapper wrapper;
	private final int containerId;
	private final Inventory inventory;

	private boolean open = false;

	@Deprecated
	public AnvilGUI(Plugin plugin, Player holder, String insert, ClickHandler clickHandler){
		this(plugin, holder, insert, clickHandler::onClick);
	}

	public AnvilGUI(Plugin plugin, Player holder, String insert, BiFunction<Player, String, String> biFunction){
		this.holder = holder;
		this.biFunction = biFunction;

		final ItemStack paper = new ItemStack(Material.PAPER);
		final ItemMeta paperMeta = paper.getItemMeta();
		paperMeta.setDisplayName(insert);
		paper.setItemMeta(paperMeta);
		this.insert = paper;

		final Version version = Version.of(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]);
		Validate.notNull(version, "Your server version isn't supported in AnvilGUI!");
		wrapper = version.getWrapper();

		wrapper.handleInventoryCloseEvent(holder);
		wrapper.setActiveContainerDefault(holder);

		Bukkit.getPluginManager().registerEvents(this, plugin);

		final Object container = wrapper.newContainerAnvil(holder);

		inventory = wrapper.toBukkitInventory(container);
		inventory.setItem(Slot.INPUT_LEFT, this.insert);

		containerId = wrapper.getNextContainerId(holder);
		wrapper.sendPacketOpenWindow(holder, containerId);
		wrapper.setActiveContainer(holder, container);
		wrapper.setActiveContainerId(container, containerId);
		wrapper.addActiveContainerSlotListener(container, holder);

		open = true;
	}

	public void closeInventory(){
		Validate.isTrue(open, "You can't close an inventory that isn't open!");
		open = false;

		wrapper.handleInventoryCloseEvent(holder);
		wrapper.setActiveContainerDefault(holder);
		wrapper.sendPacketCloseWindow(holder, containerId);

		HandlerList.unregisterAll(this);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if (e.getInventory().equals(inventory)) {
			e.setCancelled(true);
			final Player clicker = (Player) e.getWhoClicked();
			if (e.getRawSlot() == Slot.OUTPUT) {
				final ItemStack clicked = inventory.getItem(e.getRawSlot());
				final String ret = biFunction.apply(clicker, clicked.hasItemMeta() ? clicked.getItemMeta().getDisplayName() : clicked.getType().toString());
				if (ret != null) {
					final ItemMeta meta = clicked.getItemMeta();
					meta.setDisplayName(ret);
					clicked.setItemMeta(meta);
					inventory.setItem(e.getRawSlot(), clicked);
				} else
					closeInventory();
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e){
		if (open && e.getInventory().equals(inventory))
			closeInventory();
	}

	@Deprecated
	public static abstract class ClickHandler {

		public abstract String onClick(Player clicker, String input);

	}

	public static class Slot {

		public static final int INPUT_LEFT = 0;
		public static final int INPUT_RIGHT = 1;
		public static final int OUTPUT = 2;

	}

}
