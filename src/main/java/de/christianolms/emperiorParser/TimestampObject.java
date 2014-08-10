package de.christianolms.emperiorParser;

import java.util.Date;

public abstract class TimestampObject {
	Date startTimestamp;
	Date endTimestamp;
	
	public TimestampObject(){super();}
	public TimestampObject(Date startTimestamp, Date endTimestamp){
		this();
		this.startTimestamp = startTimestamp;
		this.endTimestamp = endTimestamp;
	}
	
	public Date getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Date getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public abstract long computeDurationInMilliSecs();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTimestamp == null) ? 0 : endTimestamp.hashCode());
		result = prime * result + ((startTimestamp == null) ? 0 : startTimestamp.hashCode());
		return result;
	}

	
	
}
