package me.ulrich.structure.BlockEntityTag;

import me.ulrich.structure.nbt.tag.CompoundTag;

public class ComparatorTag extends BlockEntityTag {
	private int outputSignal;
	
	public int getOutputSignal() {
		return outputSignal;
	}
	
	public void read(CompoundTag tag) {
		super.read(tag);
		outputSignal = tag.getInt("OutputSignal");
	}
}
