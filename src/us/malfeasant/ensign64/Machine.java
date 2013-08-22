package us.malfeasant.ensign64;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import us.malfeasant.ensign64.config.PixelClockRate;
import us.malfeasant.ensign64.config.PowerRate;
import us.malfeasant.ensign64.config.Stop;

public class Machine {
	private final PixelClockRate pixelRate;
	private final PowerRate powerRate;
	
	private int cycleCount;
	private long totalCycles;
	
	public Machine() {
		this(null, null);
	}
	public Machine(PixelClockRate pixRate, PowerRate power) {
		pixelRate = pixRate == null ? PixelClockRate.NTSC : pixRate;
		powerRate = power == null ? PowerRate.NA : power;
	}
	
	/**
	 * makes the machine run some amount of time
	 * @param stop - when to stop running
	 * @return how much time elapsed (in seconds)
	 */
	public double runTo(Stop stop) {
		// do stuff
		return 0;
	}
	@Override
	public String toString() {
		String s = super.toString();
		return s + "{PixelRate: " + pixelRate + "}{PowerRate: " + powerRate + "}";
	}
	public static Machine buildFrom(Path file) throws IOException {
		ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file));
		try {
			PixelClockRate pix = (PixelClockRate) in.readObject();
			PowerRate pow = (PowerRate) in.readObject();
			return new Machine(pix, pow);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void write(Path file) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(file))) {
			out.writeObject(pixelRate);
			out.writeObject(powerRate);
		}
	}
}
