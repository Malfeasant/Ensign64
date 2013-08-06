package us.malfeasant.ensign64.config;

/**
 * The VIC-II chip had a few variations- for one, there was PAL vs NTSC, but also, early NTSC VICs
 * had 262 lines of 512 pixel periods, leading to a refresh rate of 61Hz, which some TVs had
 * trouble syncing to.  This was later changed to 263x520, i.e. 59.8Hz.  There were also color
 * differences, but there will be an editable palette so I won't worry about it here.
 */
public enum VicRev {
	NTSCr56a, NTSCr8, PAL;
}
