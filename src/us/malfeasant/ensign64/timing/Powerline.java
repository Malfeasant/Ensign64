package us.malfeasant.ensign64.timing;

public enum Powerline {
	NA(60), EU(50);
	
	private final int frequency;
	
	Powerline(int f) {
		frequency = f;
	}
	
	public int getHz() {
		return frequency;
	}
	
	@Override
	public String toString() {
		return getHz() + "Hz";
	}
}
