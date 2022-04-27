package me.ulrich.structure.ItemTag;

import me.ulrich.structure.GEntityData;
import me.ulrich.structure.nbt.tag.CompoundTag;

public class EntityTag extends ItemDataTag {
	protected GEntityData entityTag;
	
	public EntityTag() {
		type = 8;
	}
	
	public GEntityData getEntityTag() {
		return entityTag;
	}
	
	public void read(CompoundTag tag) {
		entityTag = GEntityData.readNewEntity(tag.getCompoundTag("EntityTag"));
	}
}
