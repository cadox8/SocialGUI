package net.wesjd.anvilgui.version.impl;

import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_10_R1.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.Blocks;
import net.minecraft.server.v1_10_R1.ChatMessage;
import net.minecraft.server.v1_10_R1.Container;
import net.minecraft.server.v1_10_R1.ContainerAnvil;
import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.PacketPlayOutCloseWindow;
import net.minecraft.server.v1_10_R1.PacketPlayOutOpenWindow;
import net.wesjd.anvilgui.version.VersionWrapper;

public class Wrapper1_10_R1 extends VersionWrapper {

	@Override
	public int getNextContainerId(Player player){
		return toNMS(player).nextContainerCounter();
	}

	@Override
	public void handleInventoryCloseEvent(Player player){
		CraftEventFactory.handleInventoryCloseEvent(toNMS(player));
	}

	@Override
	public void sendPacketOpenWindow(Player player, int containerId){
		toNMS(player).playerConnection.sendPacket(new PacketPlayOutOpenWindow(containerId, "minecraft:anvil", new ChatMessage(Blocks.ANVIL.a() + ".name")));
	}

	@Override
	public void sendPacketCloseWindow(Player player, int containerId){
		toNMS(player).playerConnection.sendPacket(new PacketPlayOutCloseWindow(containerId));
	}

	@Override
	public void setActiveContainerDefault(Player player){
		toNMS(player).activeContainer = toNMS(player).defaultContainer;
	}

	@Override
	public void setActiveContainer(Player player, Object container){
		toNMS(player).activeContainer = (Container) container;
	}

	@Override
	public void setActiveContainerId(Object container, int containerId){
		((Container) container).windowId = containerId;
	}

	@Override
	public void addActiveContainerSlotListener(Object container, Player player){
		((Container) container).addSlotListener(toNMS(player));
	}

	@Override
	public Inventory toBukkitInventory(Object container){
		return ((Container) container).getBukkitView().getTopInventory();
	}

	@Override
	public Object newContainerAnvil(Player player){
		return new Wrapper1_10_R1.AnvilContainer(toNMS(player));
	}

	private EntityPlayer toNMS(Player player){
		return ((CraftPlayer) player).getHandle();
	}

	private class AnvilContainer extends ContainerAnvil {

		public AnvilContainer(EntityHuman entityhuman){
			super(entityhuman.inventory, entityhuman.world, new BlockPosition(0, 0, 0), entityhuman);
		}

		@Override
		public boolean a(EntityHuman entityhuman){
			return true;
		}

	}

}
