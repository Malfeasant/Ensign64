package us.malfeasant.ensign64.config;

/**
 * Represents all of a machine's configurable bits- powerline & clock frequency, chip revisions, etc.
 *
 * @author Malfeasant
 */
public class Configuration {
	public final Crystal crystal;
	public final Power power;
	// TODO chip revisions, anything else?
	
	private Configuration(Crystal c, Power p) {
		crystal = c;
		power = p;
	}
	
	public static Configuration getDefault() {
		return getNTSC();
	}
	public static Configuration getNTSC() {
		return new Configuration(Crystal.NTSC, Power.NA);
	}
	public static Configuration getPAL() {
		return new Configuration(Crystal.PAL, Power.EU);
	}
}
