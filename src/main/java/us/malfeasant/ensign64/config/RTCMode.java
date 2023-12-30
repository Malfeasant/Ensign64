package us.malfeasant.ensign64.config;

/**
 * Mode for the CIA's RealTime Clock.  SYNC locks to Oscillator, REAL bypasses internal
 * RTC function and returns current time.  Reccomend using SYNC for CIA1, since BASIC makes use
 * of it for RND function, it should have expected behavior (that is, it will be uninitialized
 * and therefore zeros).  CIA2's RTC is not used by anything, so it can have non-standard behavior.
 */
public enum RTCMode {
    SYNC, REAL;
}
