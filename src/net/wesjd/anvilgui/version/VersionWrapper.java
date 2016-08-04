package net.wesjd.anvilgui.version;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class VersionWrapper {

	public abstract int getNextContainerId(Player player);

	public abstract void handleInventoryCloseEvent(Player player);

	public abstract void sendPacketOpenWindow(Player player, int containerId);

	public abstract void sendPacketCloseWindow(Player player, int containerId);

	public abstract void setActiveContainerDefault(Player player);

	public abstract void setActiveContainer(Player player, Object container);

	public abstract void setActiveContainerId(Object container, int containerId);

	public abstract void addActiveContainerSlotListener(Object container, Player player);

	public abstract Inventory toBukkitInventory(Object container);

	public abstract Object newContainerAnvil(Player player);

}
