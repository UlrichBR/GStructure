package me.ulrich.structure.ItemTag;

import java.util.ArrayList;
import java.util.List;

import me.ulrich.structure.nbt.tag.CompoundTag;
import me.ulrich.structure.nbt.tag.ListTag;
import me.ulrich.structure.nbt.tag.StringTag;

public class BookAndQuillTag extends ItemDataTag {
	protected List<String> pages;
	
	public BookAndQuillTag() {
		pages = new ArrayList<String>();
		type = 2;
	}
	
	public List<String> getPages() {
		return pages;
	}
	
	public void read(CompoundTag tag) {
		ListTag<StringTag> pages = tag.getListTag("pages").asStringTagList();
		pages.forEach((page) -> {
			this.pages.add(page.getValue());
		});
	}
}
