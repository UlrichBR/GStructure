package me.ulrich.structure.nbt.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import me.ulrich.structure.nbt.tag.Tag;
import me.ulrich.structure.net.querz.io.Serializer;

public class NBTSerializer implements Serializer<NamedTag> {

	private boolean compressed;

	public NBTSerializer() {
		this(true);
	}

	public NBTSerializer(boolean compressed) {
		this.compressed = compressed;
	}

	@Override
	public void toStream(NamedTag object, OutputStream out) throws IOException {
		NBTOutputStream nbtOut;
		if (compressed) {
			nbtOut = new NBTOutputStream(new GZIPOutputStream(out));
		} else {
			nbtOut = new NBTOutputStream(out);
		}
		nbtOut.writeTag(object, Tag.DEFAULT_MAX_DEPTH);
	}
}
