package us.malfeasant.ensign64.plumbing;

public class Port {
	public final BusReader reader;
	public final BusWriter writer;
	
	public Port(BusReader r) {
		this(r, null);
	}
	public Port(BusWriter w) {
		this(null, w);
	}
	public Port(BusReader r, BusWriter w) {
		if (r == null && w == null) {
			throw new NullPointerException("At least one of reader/writer must be set.");
		}
		reader = r;
		writer = w;
	}
}
