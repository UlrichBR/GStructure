package me.ulrich.structure.nbt.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import me.ulrich.structure.nbt.tag.ByteArrayTag;
import me.ulrich.structure.nbt.tag.ByteTag;
import me.ulrich.structure.nbt.tag.CompoundTag;
import me.ulrich.structure.nbt.tag.DoubleTag;
import me.ulrich.structure.nbt.tag.EndTag;
import me.ulrich.structure.nbt.tag.FloatTag;
import me.ulrich.structure.nbt.tag.IntArrayTag;
import me.ulrich.structure.nbt.tag.IntTag;
import me.ulrich.structure.nbt.tag.ListTag;
import me.ulrich.structure.nbt.tag.LongArrayTag;
import me.ulrich.structure.nbt.tag.LongTag;
import me.ulrich.structure.nbt.tag.ShortTag;
import me.ulrich.structure.nbt.tag.StringTag;
import me.ulrich.structure.nbt.tag.Tag;
import me.ulrich.structure.net.querz.io.ExceptionBiFunction;
import me.ulrich.structure.net.querz.io.MaxDepthIO;

public class NBTInputStream extends DataInputStream implements MaxDepthIO {

	private static Map<Byte, ExceptionBiFunction<NBTInputStream, Integer, ? extends Tag<?>, IOException>> readers = new HashMap<>();
	private static Map<Byte, Class<?>> idClassMapping = new HashMap<>();

	static {
		put(EndTag.ID, (i, d) -> EndTag.INSTANCE, EndTag.class);
		put(ByteTag.ID, (i, d) -> readByte(i), ByteTag.class);
		put(ShortTag.ID, (i, d) -> readShort(i), ShortTag.class);
		put(IntTag.ID, (i, d) -> readInt(i), IntTag.class);
		put(LongTag.ID, (i, d) -> readLong(i), LongTag.class);
		put(FloatTag.ID, (i, d) -> readFloat(i), FloatTag.class);
		put(DoubleTag.ID, (i, d) -> readDouble(i), DoubleTag.class);
		put(ByteArrayTag.ID, (i, d) -> readByteArray(i), ByteArrayTag.class);
		put(StringTag.ID, (i, d) -> readString(i), StringTag.class);
		put(ListTag.ID, NBTInputStream::readListTag, ListTag.class);
		put(CompoundTag.ID, NBTInputStream::readCompound, CompoundTag.class);
		put(IntArrayTag.ID, (i, d) -> readIntArray(i), IntArrayTag.class);
		put(LongArrayTag.ID, (i, d) -> readLongArray(i), LongArrayTag.class);
	}

	private static void put(byte id, ExceptionBiFunction<NBTInputStream, Integer, ? extends Tag<?>, IOException> reader, Class<?> clazz) {
		readers.put(id, reader);
		idClassMapping.put(id, clazz);
	}

	public NBTInputStream(InputStream in) {
		super(in);
	}

	public NamedTag readTag(int maxDepth) throws IOException {
		byte id = readByte();
		return new NamedTag(readUTF(), readTag(id, maxDepth));
	}

	public Tag<?> readRawTag(int maxDepth) throws IOException {
		byte id = readByte();
		return readTag(id, maxDepth);
	}

	private Tag<?> readTag(byte type, int maxDepth) throws IOException {
		ExceptionBiFunction<NBTInputStream, Integer, ? extends Tag<?>, IOException> f;
		if ((f = readers.get(type)) == null) {
			throw new IOException("invalid tag id \"" + type + "\"");
		}
		return f.accept(this, maxDepth);
	}

	private static ByteTag readByte(NBTInputStream in) throws IOException {
		return new ByteTag(in.readByte());
	}

	private static ShortTag readShort(NBTInputStream in) throws IOException {
		return new ShortTag(in.readShort());
	}

	private static IntTag readInt(NBTInputStream in) throws IOException {
		return new IntTag(in.readInt());
	}

	private static LongTag readLong(NBTInputStream in) throws IOException {
		return new LongTag(in.readLong());
	}

	private static FloatTag readFloat(NBTInputStream in) throws IOException {
		return new FloatTag(in.readFloat());
	}

	private static DoubleTag readDouble(NBTInputStream in) throws IOException {
		return new DoubleTag(in.readDouble());
	}

	private static StringTag readString(NBTInputStream in) throws IOException {
		return new StringTag(in.readUTF());
	}

	private static ByteArrayTag readByteArray(NBTInputStream in) throws IOException {
		ByteArrayTag bat = new ByteArrayTag(new byte[in.readInt()]);
		in.readFully(bat.getValue());
		return bat;
	}

	private static IntArrayTag readIntArray(NBTInputStream in) throws IOException {
		IntArrayTag iat = new IntArrayTag(new int[in.readInt()]);
		for (int i = 0; i < iat.length(); i++) {
			iat.getValue()[i] = in.readInt();
		}
		return iat;
	}

	private static LongArrayTag readLongArray(NBTInputStream in) throws IOException {
		LongArrayTag iat = new LongArrayTag(new long[in.readInt()]);
		for (int i = 0; i < iat.length(); i++) {
			iat.getValue()[i] = in.readLong();
		}
		return iat;
	}

	private static ListTag<?> readListTag(NBTInputStream in, int maxDepth) throws IOException {
		byte listType = in.readByte();
		ListTag<?> list = ListTag.createUnchecked(idClassMapping.get(listType));
		int length = in.readInt();
		if (length < 0) {
			length = 0;
		}
		for (int i = 0; i < length; i++) {
			list.addUnchecked(in.readTag(listType, in.decrementMaxDepth(maxDepth)));
		}
		return list;
	}

	private static CompoundTag readCompound(NBTInputStream in, int maxDepth) throws IOException {
		CompoundTag comp = new CompoundTag();
		for (int id = in.readByte() & 0xFF; id != 0; id = in.readByte() & 0xFF) {
			String key = in.readUTF();
			Tag<?> element = in.readTag((byte) id, in.decrementMaxDepth(maxDepth));
			comp.put(key, element);
		}
		return comp;
	}
}
