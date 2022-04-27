package me.ulrich.structure.BlockEntityTag;

import me.ulrich.structure.GItem;
import me.ulrich.structure.nbt.tag.CompoundTag;

public class JukeboxTag extends BlockEntityTag {
	private GItem recordItem;
	
	public GItem getRecordItem() {
		return recordItem;
	}
	
	public void read(CompoundTag tag) {
		super.read(tag);
		
		if (tag.containsKey("RecordItem")) {
			recordItem = GItem.readNewItem(tag.getCompoundTag("RecordItem"));
		}
	}
}
