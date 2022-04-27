package me.ulrich.structure.ItemTag;

import java.util.ArrayList;
import java.util.List;

import me.ulrich.structure.GItem;
import me.ulrich.structure.nbt.tag.CompoundTag;
import me.ulrich.structure.nbt.tag.ListTag;

public class CrossbowsTag extends ItemDataTag {
	private List<GItem> chargedProjectiles;
	private byte charged;
	
	public CrossbowsTag() {
		type = 4;
		chargedProjectiles = new ArrayList<GItem>();
	}
	
	public List<GItem> getChargedProjectiles() {
		return chargedProjectiles;
	}
	
	public byte getCharged() {
		return charged;
	}
	
	public void read(CompoundTag tag) {
		ListTag<CompoundTag> chargedProjectiles = tag.getListTag("ChargedProjectiles").asCompoundTagList();
		chargedProjectiles.forEach((projectile) -> {
			this.chargedProjectiles.add(GItem.readNewItem(projectile));
		});
		charged = tag.getByte("Charged");
	}
}
