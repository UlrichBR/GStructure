package me.ulrich.structure.BlockEntityTag;

import java.util.ArrayList;
import java.util.List;

import me.ulrich.structure.GItem;
import me.ulrich.structure.nbt.tag.CompoundTag;
import me.ulrich.structure.nbt.tag.ListTag;

public class ChestTag extends BlockEntityTag {
	private String customName;
	private String lock;
	private List<GItem> items;
	private String lootTable;
	private long lootTableSeed;
	
	public ChestTag() {
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
	
	public String getLootTable() {
		return lootTable;
	}
	
	public long getLootTableSeed() {
		return lootTableSeed;
	}
	
	public void read(CompoundTag tag) {
		super.read(tag);
		this.customName = tag.getString("CustomName");
		this.lock = tag.getString("Lock");
		
		ListTag<CompoundTag> items = tag.getListTag("Items").asCompoundTagList();
		items.forEach((item) -> {
			GItem i = GItem.readNewItem(item);
			this.items.add(i);
		});
		
		this.lootTable = tag.getString("LootTable");
		this.lootTableSeed = tag.getLong("LootTableSeed");
	}
}
