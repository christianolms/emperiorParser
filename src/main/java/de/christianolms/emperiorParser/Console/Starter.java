package de.christianolms.emperiorParser.Console;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import de.christianolms.emperiorParser.ParserStarter;

public class Starter {

	public static void main(String[] args) {
		if(args.length < 1){
			System.out.println("You must provide a dir with emperior results");
			System.exit(1);
		}
		File in = new File(args[0]);
		if(!(in.exists())){
			throw new IllegalArgumentException("First parameter must point to an existing file/directory.");
			
		}
		else if(in.isDirectory()){
			try {
				System.out.print(handleDir(in));
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		}
		else{
			try {
				String content = FileUtils.readFileToString(in);
				System.out.println(parseFileContent(in.getName(), content));

			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		}
	
	
	public static String parseFileContent(String taskName, String content){
		Formatter fmt = new Formatter(new StringBuffer());
		long time = -1;
		time = ParserStarter.parseTime(content);
		fmt.format("task:%s duration:%s\n", taskName,time);
		
		String retVal = fmt.toString();
		fmt.close();
		return retVal;
	}
	
	public static String handleDir(File dir) throws IOException{
		StringBuffer b = new StringBuffer();
		Iterator<File> iter = FileUtils.iterateFiles(dir, new String[]{"log"},true);
		while(iter.hasNext()){
			File f= iter.next();
			if(f.getName().equals("action.log") && f.getParentFile().getName().equals("logs")){
				String taskName = f.getParentFile().getParentFile().getName();
				String content = FileUtils.readFileToString(f);
				b.append(parseFileContent(taskName, content));
			}
		}
		return b.toString();
		
	}
	

}
