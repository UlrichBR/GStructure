package me.ulrich.structure.BlockEntityTag;

import me.ulrich.structure.GItem;
import me.ulrich.structure.nbt.tag.CompoundTag;

public class LecternTag extends BlockEntityTag {
	private GItem book;
	private int page;
	
	public GItem getBook() {
		return book;
	}
	
	public int getPage() {
		return page;
	}
	
	public void read(CompoundTag tag) {
		super.read(tag);
		book = GItem.readNewItem(tag.getCompoundTag("Book"));
		page = tag.getInt("Page");
	}
}
