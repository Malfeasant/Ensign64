package us.malfeasant.ensign64.video;

import java.awt.image.BufferedImage;

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
	private final BufferedImage image;
	private final ColorWrangler palette;
	
	public VIC(Revision r) {
		if (r == null) throw new NullPointerException();
		rev = r;
		palette = new ColorWrangler();
		image = new BufferedImage(rev.cycles * 8, rev.lines, BufferedImage.TYPE_BYTE_BINARY, palette.makeModel());
	}
	
	public BufferedImage getImage() {
		return image;
	}
}
