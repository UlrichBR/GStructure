package me.ulrich.structure.ItemTag;

import java.util.ArrayList;
import java.util.List;

import me.ulrich.structure.GAttributeModifier;
import me.ulrich.structure.nbt.tag.CompoundTag;
import me.ulrich.structure.nbt.tag.ListTag;

public class AttributeModifiersTag extends ItemDataTag {
	private List<GAttributeModifier> attributeModifiers;

	public AttributeModifiersTag() {
		type = 0;
		attributeModifiers = new ArrayList<GAttributeModifier>();
	}
	
	public List<GAttributeModifier> getAttributeModifiers() {
		return attributeModifiers;
	}
	
	public void read(CompoundTag tag) {
		ListTag<CompoundTag> attributeModifiers = tag.getListTag("AttributeModifiers").asCompoundTagList();
		attributeModifiers.forEach((attributeModifier) -> {
			this.attributeModifiers.add(GAttributeModifier.readNewModifier(attributeModifier));
		});
	}
}
