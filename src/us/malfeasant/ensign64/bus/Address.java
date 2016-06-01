package us.malfeasant.ensign64.bus;

public class Address {
	private int cpu;	// 16 bits from the cpu
	private int vic;	// 14 bits from the vic
	private int cia;	// 2 bits from cia to supplement vic
	private int ext;	// 16 bits from expansion port
	
	public class SetFromCPU {
		public void set(int a) {
			cpu = a & 0xffff;
		}
	}
	public class SetFromVic {
		public void set(int a) {
			vic = a & 0x3fff;
		}
	}
	public class SetFromExt {
		public void set(int a) {
			ext = a & 0xffff;
		}
	}
	public class SetFromCIA {
		public void set(int a) {
			cia = a & 3;
		}
	}
	
	public int get() {
		return ((cia << 12) | vic) & cpu & ext;
	}
}
