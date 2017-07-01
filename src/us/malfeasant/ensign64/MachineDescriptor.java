package us.malfeasant.ensign64;

import us.malfeasant.ensign64.timing.Crystal;
import us.malfeasant.ensign64.timing.Powerline;

public class MachineDescriptor {
	private final Crystal crystal;
	private final Powerline power;
	
	MachineDescriptor(Crystal c, Powerline p) {
		crystal = c;
		power = p;
	}
	
	@Override
	public String toString() {
		return crystal + ", " + power;
	}
	// TODO more...
}
