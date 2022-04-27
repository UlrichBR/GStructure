package me.ulrich.structure.ItemTag;

import me.ulrich.structure.nbt.tag.CompoundTag;

public class BucketOfFishTag extends EntityTag {
	private int bucketVariantTag;
	
	public BucketOfFishTag() {
		type = 3;
	}
	
	public int getBucketVariant() {
		return bucketVariantTag;
	}
	
	public void read(CompoundTag tag) {
		super.read(tag);
		bucketVariantTag = tag.getInt("BucketVariantTag");
	}
}
