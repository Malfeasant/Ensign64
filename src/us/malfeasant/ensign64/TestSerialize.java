package us.malfeasant.ensign64;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import us.malfeasant.ensign64.config.PixelClockRate;
import us.malfeasant.ensign64.config.PowerRate;

public class TestSerialize {
	private static final String FILENAME = "machine.ser";
	private static final Path path = Paths.get(System.getProperty("user.home")).resolve(FILENAME);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(path);
		Machine mac = new Machine();
//		Machine mac = new Machine(PixelClockRate.PAL, PowerRate.EU);
		System.out.println("Before: " + mac.toString());
		try {
			mac.write(path);
//			mac = Machine.buildFrom(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("After: " + mac.toString());
	}
}
