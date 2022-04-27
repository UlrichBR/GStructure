package me.ulrich.structure.BlockEntityTag;

import java.util.ArrayList;
import java.util.List;

import me.ulrich.structure.GItem;
import me.ulrich.structure.nbt.tag.CompoundTag;
import me.ulrich.structure.nbt.tag.ListTag;

public class BrewingStandTag extends BlockEntityTag {
	private String customName;
	private String lock;
	private List<GItem> items;
	private short brewTime;
	private byte fuel;
	
	public BrewingStandTag() {
		items = new ArrayList<GItem>();
	}
	
	public String getCustomName() {
		return customName;
	}
	
	public String getLock() {
		return lock;
	}
	
	public List<GItem> getItems() {
		return items;
	}
	
	public short getBrewTime() {
		return brewTime;
	}
	
	public byte getFuel() {
		return fuel;
	}
	
	public void read(CompoundTag tag) {
		super.read(tag);
		customName = tag.getString("CustomName");
		lock = tag.getString("Lock");
		
		ListTag<CompoundTag> items = tag.getListTag("Items").asCompoundTagList();
		items.forEach((item) -> {
			this.items.add(GItem.readNewItem(item));
		});
		
		brewTime = tag.getShort("BrewTime");
		fuel = tag.getByte("Fuel");
	}
}
