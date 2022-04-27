package me.ulrich.structure.BlockEntityTag;

import java.util.ArrayList;
import java.util.List;

import me.ulrich.structure.GItem;
import me.ulrich.structure.nbt.tag.CompoundTag;
import me.ulrich.structure.nbt.tag.ListTag;

public class CampfireTag extends BlockEntityTag {
	private List<GItem> items;
	private int[] cookingTimes = {0,0,0,0};
	private int[] cookingTotalTimes = {0,0,0,0};

	public CampfireTag() {
		items = new ArrayList<GItem>(4);
	}
	
	public List<GItem> getItems() {
		return items;
	}
	
	public int[] getCookingTimes() {
		return cookingTimes;
	}
	
	public int[] getCookingTotalTimes() {
		return cookingTotalTimes;
	}
	
	public void read(CompoundTag tag) {
		super.read(tag);
		ListTag<CompoundTag> items = tag.getListTag("Items").asCompoundTagList();
		items.forEach((item) -> {
			this.items.add(GItem.readNewItem(item));
		});
		
		cookingTimes = tag.getIntArray("CookingTimes");
		cookingTotalTimes = tag.getIntArray("CookingTotalTimes");
	}
}
