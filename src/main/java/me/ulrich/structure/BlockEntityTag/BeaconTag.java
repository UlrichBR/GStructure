package me.ulrich.structure.BlockEntityTag;

import me.ulrich.structure.nbt.tag.CompoundTag;

public class BeaconTag extends BlockEntityTag {
	String lock;
	int levels;
	int primary;
	int secondary;
	
	public String getLock() {
		return lock;
	}
	
	public int getLevels() {
		return levels;
	}
	
	public int getPrimary() {
		return primary;
	}
	
	public int getSecondary() {
		return secondary;
	}
	
	public void read(CompoundTag tag) {
		super.read(tag);
		lock = tag.getString("Lock");
		levels = tag.getInt("Levels");
		primary = tag.getInt("Primary");
		secondary = tag.getInt("Secondary");
	}
}
