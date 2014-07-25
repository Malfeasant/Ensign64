package us.malfeasant.ensign64;

import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.util.Arrays;

public class ColorWrangler {
	private static final int[] DEFAULTS = {
		0x000000, 0xFFFFFF, 0x894036, 0x7ABFC7,
		0x8A46AE, 0x68A941, 0x3E31A2, 0xD0DC71,
		0x905F25, 0x5C4700, 0xBB776D, 0x555555,
		0x808080, 0xACEA88, 0x7C70DA, 0xABABAB
	};
	
	private int[] colors;
	
	public ColorWrangler() {
		colors = Arrays.copyOf(DEFAULTS, DEFAULTS.length);
	}
	
	public IndexColorModel makeModel() {
		return new IndexColorModel(4, 16, colors, 0, false, -1, DataBuffer.TYPE_BYTE);
	}
}
