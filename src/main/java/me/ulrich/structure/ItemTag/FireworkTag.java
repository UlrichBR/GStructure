package me.ulrich.structure.ItemTag;

import java.util.ArrayList;
import java.util.List;

import me.ulrich.structure.GExplosion;
import me.ulrich.structure.nbt.tag.CompoundTag;
import me.ulrich.structure.nbt.tag.ListTag;

public class FireworkTag extends ItemDataTag {
	private byte flight;
	private List<GExplosion> explosions;
	
	public FireworkTag() {
		type = 9;
		explosions = new ArrayList<GExplosion>();
	}
	
	public byte getFlight() {
		return flight;
	}
	
	public List<GExplosion> getExplosions() {
		return explosions;
	}
	
	public void read(CompoundTag tag) {
		CompoundTag t = tag.getCompoundTag("Fireworks");
		flight = t.getByte("Flight");
		ListTag<CompoundTag> explosions = t.getListTag("Explosions").asCompoundTagList();
		explosions.forEach((explosion) -> {
			this.explosions.add(GExplosion.readNewExplosion(explosion));
		});
	}
}
