package us.malfeasant.ensign64.config;

public enum Power {
	NA(60), EU(50);
	
	Power(int c) {
		cycles = c;
	}
	public final int cycles;
}
