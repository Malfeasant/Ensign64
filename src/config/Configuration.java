package config;

/**
 * Encompasses all (non-gui) static configuration of a given machine- if it doesn't make sense to change it
 * after power up, it's in here.  Chip revisions, ROMs, maybe someday customizations...
 * 
 * @author Malfeasant
 */
public class Configuration {
	private final String name;
	private final VicFlavor flavor;
	// TODO: more stuff to modify
	
	public String getName() {
		return name;
	}
	public VicFlavor getFlavor() {
		return flavor;
	}
	private Configuration(String name, VicFlavor flavor) {
		// Ensure no nulls
		if (name == null)
			throw new NullPointerException("Name must not be null.");
		this.name = name;
		if (flavor == null)
			throw new NullPointerException("VicFlavor must not be null.");
		this.flavor = flavor;
	}
	
	public static Configuration makeNew(String name) {
		// TODO: Better way of specifying defaults- for now, hard code:
		return new Configuration(name, VicFlavor.NEWNTSC);
	}
	
	public Configuration modify(String name) {
		return new Configuration(name, flavor);
	}
	public Configuration modify(VicFlavor flavor) {
		return new Configuration(name, flavor);
	}
}
