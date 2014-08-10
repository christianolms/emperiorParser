package de.christianolms.emperiorParser;

import java.util.Collection;
import java.util.HashSet;

public class Experiment extends TimestampObject {
	Collection<PauseTimestamp> pauses;
	
	public Experiment(){
		this.pauses = new HashSet<PauseTimestamp>();
	}
	public boolean add(PauseTimestamp arg0) {
		return pauses.add(arg0);
	}
	public void clear() {
		pauses.clear();
	}
	public boolean contains(Object arg0) {
		return pauses.contains(arg0);
	}
	public boolean isEmpty() {
		return pauses.isEmpty();
	}
	public boolean remove(Object arg0) {
		return pauses.remove(arg0);
	}


	@Override
	public long computeDurationInMilliSecs() {
		long exTime =  this.endTimestamp.getTime() - this.startTimestamp.getTime();
		
		for(PauseTimestamp pt : this.pauses){
			exTime -= pt.computeDurationInMilliSecs();
		}
		return exTime;
	}
	
}
