package us.malfeasant.ensign64.video;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import us.malfeasant.ensign64.ColorWrangler;

public class VIC {
	public enum Revision {
		NTSCr56a(64, 262),
		NTSCr8(65, 263),
		PAL(63, 312) {
			@Override
			protected boolean vBlank(int line) {
				return !((line < 15) && (line > 300));
			}
		};
		private final int cycles;
		private final int lines;
		Revision(int c, int l) {
			cycles = c;
			lines = l;
		}
		protected boolean vBlank(int line) {
			return !((line < 13) || (line > 40));
		}
	}
	
	private final Revision rev;
	private final WritableRaster raster;
	
	public VIC(Revision r) {
		if (r == null) throw new NullPointerException();
		rev = r;
		// seems weird to create a buffered image then throw it away- but if i want an editable palette (and i do)
		// it's easier to do it this way than to create all the internal bits that are required.
		raster = new BufferedImage(rev.cycles * 8, rev.lines,
				BufferedImage.TYPE_BYTE_BINARY, new ColorWrangler().makeModel()).getRaster();
		// Test pattern
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				raster.setSample(x, y, 0, (x / 8 + y / 8) & 0xf);
			}
		}
	}
	
	public WritableRaster getRaster() {
		return raster;
	}
}
