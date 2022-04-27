package me.ulrich.structure.nbt.io;

import java.io.IOException;
import java.io.Writer;

import me.ulrich.structure.nbt.tag.Tag;
import me.ulrich.structure.net.querz.io.StringSerializer;

public class SNBTSerializer implements StringSerializer<Tag<?>> {

	@Override
	public void toWriter(Tag<?> tag, Writer writer) throws IOException {
		SNBTWriter.write(tag, writer);
	}

	public void toWriter(Tag<?> tag, Writer writer, int maxDepth) throws IOException {
		SNBTWriter.write(tag, writer, maxDepth);
	}
}
