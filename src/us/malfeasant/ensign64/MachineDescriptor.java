package us.malfeasant.ensign64;

public class MachineDescriptor {
	private String name;
	
	public MachineDescriptor(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	// TODO more...
}
