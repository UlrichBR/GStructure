package me.ulrich.structure.nbt.io;

import java.io.IOException;

import me.ulrich.structure.nbt.tag.Tag;

public class SNBTUtil {

	public static String toSNBT(Tag<?> tag) throws IOException {
		return new SNBTSerializer().toString(tag);
	}

	public static Tag<?> fromSNBT(String string) throws IOException {
		return new SNBTDeserializer().fromString(string);
	}
}
