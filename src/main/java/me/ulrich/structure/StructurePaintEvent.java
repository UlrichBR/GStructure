package me.ulrich.structure;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.ulrich.structure.BlockEntityTag.BlockEntityTag;


public class StructurePaintEvent extends Event {
	
	private boolean cancelled = false;
	private Location location;
	private GPalette palette;
	private BlockEntityTag blockEntityTag;
	private int buildKey;
	private Block block;
	
	public StructurePaintEvent(Location location, GPalette palette, BlockEntityTag blockEntityTag, int buildKey, Block block) {
		this.location = location;
		this.palette = palette;
		this.blockEntityTag = blockEntityTag;
		this.buildKey = buildKey;
		this.block = block;
	}
	
	public int getBuildKey() {
		return buildKey;
	}
	
    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

	public boolean getCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public GPalette getPalette() {
		return palette;
	}
	
	public BlockEntityTag getBlockEntityTag() {
		return blockEntityTag;
	}

	public Block getBlock() {
		return block;
	}


}