package ucsd.cs110.splurge.model;

import java.util.Calendar;

/**
 * Immutable class representing a period of time. Its primary functionality is
 * to determine whether other times fall within or interfere with this Timeslot.
 */
public class Timeslot {
	private final Calendar startTime;
	private final Calendar endTime;

	/**
	 * Create a Timeslot with the given start and end times.
	 */
	public Timeslot(Calendar startTime, Calendar endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Retrieve the beginning of this Timeslot.
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * Retrive the ending time of this Timeslot.
	 */
	public Calendar getEndTime() {
		return endTime;
	}

	/**
	 * Determine if the specified moment in time falls within this Timeslot.
	 */
	public boolean withinTimeslot(Calendar time) {
		return startTime.before(time) && endTime.after(time);
	}

	/**
	 * Determine whether this Timeslot has any overlap with another Timeslot.
	 */
	public boolean collides(Timeslot t) {
		return startTime.after(t.startTime) && startTime.before(t.endTime)
				|| endTime.after(t.startTime) && endTime.before(t.endTime);
	}

	/**
	 * Determine whether the argument Timeslot falls entirely within this
	 * Timeslot.
	 */
	public boolean contains(Timeslot t) {
		return startTime.before(t.startTime) && endTime.after(t.endTime);
	}
}