package de.christianolms.emperiorParser;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;


import org.junit.Before;
import org.junit.Test;

public class ExperimentTest {
	private Experiment e;
	private PauseTimestamp pt1;

	
	@Before
	public void init(){
		this.e = new Experiment();
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(0);
		this.e.setStartTimestamp(c.getTime());
		c.add(Calendar.MINUTE, 5);
		this.e.setEndTimestamp(c.getTime());
		this.pt1 = computePause(45);
	}
	@Test
	public void simpleTest() {
		testTimeWithoutPauses();
	}
	
	@Test
	public void testTimestampObject(){
		Calendar c = Calendar.getInstance();
		Date start = c.getTime();
		c.add(Calendar.SECOND, 5);
		Date stop = c.getTime();
		TimestampObject to = new PauseTimestamp(start,stop);
		assertEquals(start, to.getStartTimestamp());
		assertEquals(stop, to.getEndTimestamp());

	}
	
	
	private PauseTimestamp computePause(int durationInSec){
		PauseTimestamp p = new PauseTimestamp();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(0);
		p.setStartTimestamp(c.getTime());
		c.add(Calendar.SECOND, durationInSec);
		p.setEndTimestamp(c.getTime());
		return p;
	}
	@Test
	public void pauseTest(){
		this.e.add(this.pt1);
		this.e.add(computePause(15));
		assertEquals("The computed time is wrong. Check if the pauses are concerned.",4*60*1000, this.e.computeDurationInMilliSecs());
		assertFalse(e.isEmpty());
		this.e.clear();
		assertTrue(e.isEmpty());
		assertFalse(this.e.contains(pt1));
		this.e.add(this.pt1);
		assertTrue(this.e.contains(pt1));
		this.e.remove(this.pt1);
		assertFalse(this.e.contains(this.pt1));
		testTimeWithoutPauses();
		}
	private void testTimeWithoutPauses(){
		assertEquals("The computed time is wrong.",5*60*1000, this.e.computeDurationInMilliSecs());
	}
}
