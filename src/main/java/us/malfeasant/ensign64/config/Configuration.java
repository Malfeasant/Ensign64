package us.malfeasant.ensign64.config;

/**
 * Represents all of a machine's configurable bits- powerline & clock frequency, chip revisions, etc.
 * ToD modes- sim time or realtime, can be set independently for each CIA
 *
 * @author Malfeasant
 */
public class Configuration {
	public final Crystal crystal;
	public final Power power;
	public final RTCMode rtc1Mode;
	public final RTCMode rtc2Mode;
	// TODO chip revisions, anything else?
	
	private Configuration(Crystal c, Power p, RTCMode m1, RTCMode m2) {
		crystal = c;
		power = p;
		rtc1Mode = m1;
		rtc2Mode = m2;
	}
	
	public static Configuration getDefault() {
		return getNTSC();
	}
	public static Configuration getNTSC() {
		return new Configuration(Crystal.NTSC, Power.NA, RTCMode.SYNC, RTCMode.REAL);
	}
	public static Configuration getPAL() {
		return new Configuration(Crystal.PAL, Power.EU, RTCMode.SYNC, RTCMode.REAL);
	}
}
