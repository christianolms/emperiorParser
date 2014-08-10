package de.christianolms.emperiorParser;

import static org.junit.Assert.*;

import org.junit.Test;

import de.christianolms.emperiorParser.Console.Starter;

public class ParseFileTest {

	private String getTestContent(){
		String text = "2012.333 17:33:53.773 experiment  ---started---\n" + 
				"2012.333 17:33:54.707 task started\n" + 
				"2012.333 18:33:53.773 experiment  ---started---\n" + 
				"2012.333 18:33:54.707 task started\n" + 
				"2012.333 19:20:18.553 experiment  ---pause---\n" + 
				"2012.333 19:20:23.333 experiment  ---continue---\n" + 
				"2012.333 19:23:54.690 task stopped\n" + 
				"2012.333 19:23:55.066 experiment  ---closed---\n";
				
			return text;
		
	}
	private long getTestDuration(){
		return 2995;
	}
	
	@Test
	public void testParser() {
		long actual = ParserStarter.parseTime(getTestContent());
		assertEquals("The returned duration is not the expected duration.", getTestDuration(), actual);
	}
	@Test
	public void wrongSubjectTest(){
		String input = "2012.333 17:33:53.773 foo  ---started---\n";
		try {
			ParserStarter.parseTime(input);
			fail();
		}
		catch(IllegalStateException e){
			//should be thrown
		}
		
	}
	@Test
	public void wrongExperimentActionTest(){
		String input = "2012.333 17:33:53.773 experiment  ---remain---\n";
		try {
			ParserStarter.parseTime(input);
			fail();
		}
		catch(IllegalStateException e){
			//should be thrown
		}	
	}

	
	@Test
	public void wrongTaskActionTest(){
		String input = "2012.333 17:33:53.773 task  foo \n";
		try {
			ParserStarter.parseTime(input);
			fail();
		}
		catch(IllegalStateException e){
			//should be thrown
		}	
	}
	@Test public void parseFileContentTest(){
		String actual = Starter.parseFileContent("task1", getTestContent()); 
		String expected = "task:task1 duration:" + getTestDuration() + "\n";
		assertEquals(expected, actual);
	}
}
