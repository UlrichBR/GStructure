package me.ulrich.structure.nbt.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import me.ulrich.structure.nbt.tag.Tag;
import me.ulrich.structure.net.querz.io.Deserializer;

public class NBTDeserializer implements Deserializer<NamedTag> {

	private boolean compressed;

	public NBTDeserializer() {
		this(true);
	}

	public NBTDeserializer(boolean compressed) {
		this.compressed = compressed;
	}

	@Override
	public NamedTag fromStream(InputStream stream) throws IOException {
		NBTInputStream nbtIn;
		if (compressed) {
			nbtIn = new NBTInputStream(new GZIPInputStream(stream));
		} else {
			nbtIn = new NBTInputStream(stream);
		}
		return nbtIn.readTag(Tag.DEFAULT_MAX_DEPTH);
	}
}
