package de.christianolms.emperiorParser;

import main.java.de.christianolms.emperiorParser.ExperimentParserLexer;
import main.java.de.christianolms.emperiorParser.ExperimentParserParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ParserStarter {
	public static long parseTime(String input){
        ANTLRInputStream inputStream = new ANTLRInputStream(input);
        ExperimentParserLexer lexer = new ExperimentParserLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExperimentParserParser parser = new ExperimentParserParser(tokens);
        
        TimeComputationListener l = new TimeComputationListener();
        
        ParseTreeWalker.DEFAULT.walk(l, parser.file());
        return Math.round(l.getExperiment().computeDurationInMilliSecs()/1000);
	}


}
