package de.christianolms.emperiorParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.de.christianolms.emperiorParser.ExperimentParserBaseListener;
import main.java.de.christianolms.emperiorParser.ExperimentParserParser.LineContext;
import main.java.de.christianolms.emperiorParser.ExperimentParserParser.TimestampContext;

public class TimeComputationListener extends ExperimentParserBaseListener{
	private Experiment exp;
	private Date lastSeenTimestamp;
	private PauseTimestamp p;
	
	
@Override
	public void exitTimestamp(TimestampContext ctx) {
		super.exitTimestamp(ctx);
	}


	@Override
	public void exitLine(LineContext ctx) {
		super.enterLine(ctx);
		String subject = ctx.subject.getText();
		String action = ctx.action.getText();
 		if(subject.equals("task")){
			switch(action){
			case "started": 
				this.exp = new Experiment();
				this.exp.setStartTimestamp(lastSeenTimestamp);
				break;
			case "stopped": 
				this.exp.setEndTimestamp(lastSeenTimestamp);
				
				break;
			default: 
				throw new IllegalStateException("Not supported Action occured: " + action);

			}
		}
		else if(subject.equals("experiment")){
			switch(action){
			case "---pause---": 
				this.p = new PauseTimestamp();
				this.p.setStartTimestamp(lastSeenTimestamp);
				break;
			case "---continue---":
				p.setEndTimestamp(lastSeenTimestamp);
				this.exp.add(p);
			case "---started---":break;
			case "---closed---":break;

			default: 
				throw new IllegalStateException("Not supported Action occured: " + action);

			}
			
		}
		else throw new IllegalStateException("Not supported Subject occured");
	}
	@Override
	public void enterTimestamp(TimestampContext ctx) {
		super.exitTimestamp(ctx);
		String timestamp = ctx.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.DDDkk:mm:ss.SSS");
		try {
			Date d = sdf.parse(timestamp);
			this.lastSeenTimestamp = d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Experiment getExperiment(){ return this.exp;}

	
}
