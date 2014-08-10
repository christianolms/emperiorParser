package de.christianolms.emperiorParser;

import java.util.Date;

public class PauseTimestamp extends TimestampObject {

	public PauseTimestamp(Date start, Date stop) {
		super(start,stop);
	}

	public PauseTimestamp() {
		super();
	}

	@Override
	public long computeDurationInMilliSecs() {
		return this.endTimestamp.getTime() - this.startTimestamp.getTime();
	}

}
