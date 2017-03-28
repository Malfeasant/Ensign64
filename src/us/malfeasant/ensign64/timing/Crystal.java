package us.malfeasant.ensign64.timing;

public enum Crystal {
	// Represents a fraction, the result being a frequency in MHz
	NTSC(1125,1100), PAL(709379,720000);
	
	private final int NUMER;
	private final int DENOM;
	Crystal(int n, int d) {
		NUMER = n;
		DENOM = d;
	}
	
	// TODO expose private fields in some useful way
}
