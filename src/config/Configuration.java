package config;

/**
 * Encompasses all (non-gui) static configuration of a given machine- if it doesn't make sense to change it
 * after power up, it's in here.  Chip revisions, ROMs, maybe someday customizations...
 * 
 * @author Malfeasant
 */
public class Configuration {
	private final VicFlavor flavor;
	// TODO: more stuff such as oscillator & powerline frequencies, chip revisions...
	
	public VicFlavor getFlavor() {
		return flavor;
	}
	private Configuration(VicFlavor flavor) {
		// Ensure no nulls
		if (flavor == null)
			throw new NullPointerException("VicFlavor must not be null.");
		this.flavor = flavor;
	}
	
	public static Configuration getDefault() {
		// TODO: Better way of specifying defaults- for now, hard code:
		return new Configuration(VicFlavor.NEWNTSC);
	}
	
	public Configuration modify(VicFlavor flavor) {
		return new Configuration(flavor);
	}
}
