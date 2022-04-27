package me.ulrich.structure.ItemTag;

import java.util.ArrayList;
import java.util.List;

import me.ulrich.structure.GEffect;
import me.ulrich.structure.nbt.tag.CompoundTag;
import me.ulrich.structure.nbt.tag.ListTag;

public class SuspiciousStewTag extends ItemDataTag {
	private List<GEffect> effects;
	
	public SuspiciousStewTag() {
		type = 14;
		effects = new ArrayList<GEffect>();
	}
	
	public List<GEffect> getEffects() {
		return effects;
	}
	
	public void read(CompoundTag tag) {
		if (tag.containsKey("Effects")) {
			ListTag<CompoundTag> effects = tag.getListTag("Effects").asCompoundTagList();
			effects.forEach((effect) -> {
				this.effects.add(GEffect.readNewEffect(effect));
			});
		}
	}
}
