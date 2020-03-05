
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TSL {
	/*
	 * author grieferpig
	 * version 0.6.1
	 * build core preview
	 */

	static String cmd;
	static int varcapacity = 0;
	static ArrayList<String> var = new ArrayList<>();

	public static void help(){
		System.out.println("All the commands aviliable here.");
		System.out.println("help -- show this help");
		System.out.println("init -- show startup screen");
		System.out.println("crte (int varindex) (string data) -- Set an string variable, will rewrite the data in the dest. index if already has an var");
		System.out.println("int varindex must from 0 to 32767 and must start with 0.");
		System.out.println("read (int varindex) -- Read an variable");
		System.out.println("text (String msg) -- Print a message from the console");
		System.out.println("(deprecated) wrte (int varindex) (String overwritemsg) -- Overwrite a variable");
		System.out.println("time (int varindex) -- Save current time in a variable");
		System.out.println("plus (int varindex) (int varindex) -- Plus two variable together");
		System.out.println("mnus (int varindex) (int varindex) -- minus two variable together");
		System.out.println("mtpl (int varindex) (int varindex) -- Multiply two variable together");
		System.out.println("dvid (int varindex) (int varindex)) -- Divide two variable");
		System.out.println("all these operations will save the result in the first variable index.");
		System.out.println("inpt (int questionindex) (int varindex) -- ask a question and save the anwser in the varindex");
		System.out.println("asci (int varindex) (int destvarindex) -- change the string in the varindex to ascii code and save in the destvarindex");
		System.out.println("icsa (int varindex) (int destvarindex) -- opposite operation for asci");
		System.out.println("inpt (int quizindex) (int anwserindex) -- ask a question and receive an anwser from terminal");
		System.out.println("lnth (int varindex) (int destvarindex) -- get variable's length and write in the destvarindex");
		System.out.println("[Math]");
		System.out.println("Methods:");
		System.out.println("sqt (int index) (int anwserindex) -- calculate square root for the number in the index and save the anwser into the destindex");
		System.out.println("cbt (int index) (int anwserindex) -- cube root, usage as same as sqt");
		System.out.println("pwr (int base) (int index) -- calculate a power operation");
		System.out.println("abs (int index) (int anwserindex) -- get absolute value");
		System.out.println("rnd (int index) (int anwserindex) -- round a number");
		System.out.println("rdm (long seed) (int max) -- generate an random number");
		System.out.println("mvv (int var1) (int var2) -- copy a variable");
		System.out.println("exec (index) -- run code in the index");
		System.out.println("cler (index) -- clear index");
		System.out.println("loop (4evr/int times) (codeindex) -- loop codes");
		System.out.println("exit (value) -- exit with a value");
	}

	public static void init(){
		System.out.println("Welcome to TSL(T Script Language)! 0.6.1");
		System.out.println("Made by GrieferPig and written in Java!");
		System.out.println("To see all commands aviliable, type help.");
	}

	public static void create(int varindex, String data){
		var.ensureCapacity(128);
		var.add(varindex, data);
		System.out.println("Data at index "+varindex + " : " + var.get(varindex));
	}

	public static void read(int varindex){
		System.out.println(var.get(varindex));
	}

	public static void text(String text){
		System.out.println(text);
	}

	public static void side(int varindex){
		var.remove(varindex);
	}

	public static void write(int varindex, String overwritemsg){
		var.remove(varindex);
		var.add(varindex, overwritemsg);
	}

	public static void time(int varindex){
		Date date = new Date();
		var.add(varindex, date.toString());
	}

	public static void plus(int varindex, int varindex1){
		int process1 = Integer.parseInt(var.get(varindex)) + Integer.parseInt(var.get(varindex1));
		var.set(varindex, String.valueOf(process1));
		System.out.println("Result: " + process1);
		process1 = 0;
	}

	public static void mnus(int varindex, int varindex1){
		int process1 = Integer.parseInt(var.get(varindex)) - Integer.parseInt(var.get(varindex1));
		var.set(varindex, String.valueOf(process1));
		System.out.println("Result: " + process1);
		process1 = 0;
	}

	public static void mtpl(int varindex, int varindex1){
		int process1 = Integer.parseInt(var.get(varindex)) * Integer.parseInt(var.get(varindex1));
		var.set(varindex, String.valueOf(process1));
		System.out.println("Result: " + process1);
		process1 = 0;
	}

	public static void dvid(int varindex, int varindex1){
		int process1 = Integer.parseInt(var.get(varindex)) / Integer.parseInt(var.get(varindex1));
		var.set(varindex, String.valueOf(process1));
		System.out.println("Result: " + process1);
		process1 = 0;
	}

	public static void strt(int cmdindex){
		try{
		exec(var.get(cmdindex));
		}catch(IndexOutOfBoundsException e){
			System.err.println("Error while trying to read index. Is it exists?");
		}
	}

	public static void inpt(int questionindex, int varindex){
		System.out.println(var.get(questionindex));
		BufferedReader cmdbr = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = cmdbr.readLine();
			System.out.println("Anwser is:"+input);
			var.add(varindex, input);
		} catch (IOException e) {
			System.err.println("An error has happened, log is below");
			e.printStackTrace();
		}
	}

	public static void loop(String choice, int cmdindex){
		try{
			switch(choice){
			case "4evr" :
				while(true){
					exec(var.get(cmdindex));
				}
			default :
				for(int time = Integer.parseInt(choice) ;time>=1; time--){
					exec(var.get(cmdindex));
				}
				break;
			}
		}catch(Exception e){
			System.err.println("An error has happened, log is below");
			e.printStackTrace();
		}
	}
	
	public static void exit(int level){
		System.out.println("TSL exited.");
		System.exit(level);
	}
	
	public static void math(String switchs, int varindex, int destvarindex){
		switch(switchs){
		case "sqt":
			double forenum = Integer.parseInt(var.get(varindex));
			String result = String.valueOf(Math.sqrt(forenum));
			System.out.println("Result is " + result);
			var.add(destvarindex, result);
			forenum = 0;
			result = null;
			break;
	case "cbt":
		double forenum1 = Integer.parseInt(var.get(varindex));
		String result1 = String.valueOf(Math.cbrt(forenum1));
		System.out.println("Result is " + result1);
		var.add(destvarindex, result1);
		forenum1 = 0;
		result1 = null;
		break;
	case "pwr":
		double forenum2 = Integer.parseInt(var.get(varindex));
		String result2 = String.valueOf(Math.pow(forenum2, Integer.parseInt(var.get(destvarindex))));
		System.out.println("Result is " + result2 + " and saved to index " + destvarindex);
		var.add(destvarindex, result2);
		forenum2 = 0;
		result2 = null;
		break;
	case "abs":
		int forenum3 = Integer.parseInt(var.get(varindex));
		String result3 = String.valueOf(Math.abs(forenum3));
		System.out.println("Result is " + result3 + " and saved to index " + destvarindex);
		var.add(destvarindex, result3);
		forenum3 = 0;
		result3 = null;
		break;
	case "rnd":
		double forenum4 = Double.valueOf(var.get(varindex));
		String result4 = String.valueOf(Math.round(forenum4));
		System.out.println("Result is " + result4 + " and saved to index " + destvarindex);
		var.add(destvarindex, result4);
		forenum4 = 0;
		result4 = null;
		break;
	case "rdm":

		Random rand =new Random(varindex);
		int i;
		i=rand.nextInt(destvarindex);
		System.out.println("The generated random number is " + i + "and saved to index 0");
		var.add(0, String.valueOf(i));
		i = 0;
		break;
	case "mvv":
		var.add(destvarindex, var.get(varindex));
		System.out.println("Variable in Index " + destvarindex + "is now copied to index " + varindex);
		break;
		}
	}

	public static void asci(int varindex, int destindex){
		String string = var.get(varindex);
		StringBuilder sb = new StringBuilder();
	    char[] ch = string.toCharArray();
	    for (int i = 0; i < ch.length; i++) {
	        sb.append(Integer.valueOf(ch[i]).intValue()).append(" ");
	    }
	    System.out.println("Convented data: "+sb.toString());
	    var.add(destindex, sb.toString());
	    string = null;
	    sb = null;
	    ch = null;
	}

	public static void icsa(int varindex, int destindex){
		String value = var.get(varindex);
		StringBuffer sbu = new StringBuffer();
		String[] chars = value.split(" ");
		for (int i = 0; i < chars.length; i++) {
			sbu.append((char) Integer.parseInt(chars[i]));
		}
		System.out.println("Convented data: "+sbu.toString());
		var.add(destindex, sbu.toString());
	    value = null;
	    sbu = null;
	    chars = null;
	}
	
	public static void cler(int index){
		try{
		var.remove(index);
		}catch(Exception e){
			System.out.println("An error happened during clearing, log is below");
			e.printStackTrace();
		}
		System.out.println("Cleared index " + index);
	}

	public static void lnth(int index1, int index2){
		var.add(index2, String.valueOf(var.get(index1).length()));
		System.out.println("Length of index "+ index1 + " is " + var.get(index2) + " and saved to " + index2);
	}

	public static void caidan(){
		while(true){
		System.err.println("Wtf! You just discovered a caidan!");
		}
	}

	public static void caidan1(){
		System.out.println("Well, this is the true caidan.");
		System.out.println("Congratulations that you've found this, ");
		System.err.println("BUT THERE IS ABSOLUTELY NO SURPRISE LOL");
	}

	public static void exec(String cmd){
		String[] cmdArr = cmd.split("\\s+");
		if(cmd.equalsIgnoreCase("init")){
			init();
			cmd = "";
		}
		else if(cmd.equalsIgnoreCase("help")){
			help();
			cmd = "";
		}
		else if(cmdArr[0].equalsIgnoreCase("crte")){
			try{
				String cuttedCommand = cmd.substring(7);
				create(Integer.parseInt(cmdArr[1]), cuttedCommand);
				cmd = "";
			}catch(ArrayIndexOutOfBoundsException e){
					System.err.println("Too less arguments.");
			}
		}
		else if(cmdArr[0].equalsIgnoreCase("read")){
			try{
				read(Integer.parseInt(cmdArr[1]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("text")){
			try{
				text(cmd.substring(5));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}
		else if(cmdArr[0].equalsIgnoreCase("side")){
			try{
				side(Integer.parseInt(cmdArr[1]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}
		else if(cmdArr[0].equalsIgnoreCase("wrte")){
			try{
				write(Integer.parseInt(cmdArr[1]), cmdArr[2]);
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("time")){
			try{
				time(Integer.parseInt(cmdArr[1]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("plus")){
			try{
				plus(Integer.parseInt(cmdArr[1]), Integer.parseInt(cmdArr[2]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("mnus")){
			try{
				mnus(Integer.parseInt(cmdArr[1]), Integer.parseInt(cmdArr[2]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("mtpl")){
			try{
				mtpl(Integer.parseInt(cmdArr[1]), Integer.parseInt(cmdArr[2]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("dvid")){
			try{
				dvid(Integer.parseInt(cmdArr[1]), Integer.parseInt(cmdArr[2]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("caidan")){
			try{
				caidan();
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("caidan1")){
			try{
				caidan1();
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("asci")){
			try{
				asci(Integer.parseInt(cmdArr[1]), Integer.parseInt(cmdArr[2]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("icsa")){
			try{
				icsa(Integer.parseInt(cmdArr[1]), Integer.parseInt(cmdArr[2]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("inpt")){
			try{
				inpt(Integer.parseInt(cmdArr[1]), Integer.parseInt(cmdArr[2]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("math")){
			try{
				math(cmdArr[1], Integer.parseInt(cmdArr[2]), Integer.parseInt(cmdArr[3]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("lnth")){
			try{
				lnth(Integer.parseInt(cmdArr[1]), Integer.parseInt(cmdArr[2]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}
		else if(cmdArr[0].equalsIgnoreCase("cler")){
			try{
				cler(Integer.parseInt(cmdArr[1]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("exec")){
			try{
				exec(var.get(Integer.parseInt(cmdArr[1])));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("loop")){
			try{
				loop(cmdArr[1], Integer.parseInt(cmdArr[2]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else if(cmdArr[0].equalsIgnoreCase("exit")){
			try{
				exit(Integer.parseInt(cmdArr[1]));
			}catch(ArrayIndexOutOfBoundsException e){
				System.err.println("Too less arguments.");
			}
		}else{
		    	System.err.println("Command Not Exist!");
		    }
	}
	
	public static void main(String[] args) throws IOException{
		init();
		try{
		while(true){
			BufferedReader cmdbr = new BufferedReader(new InputStreamReader(System.in));
			cmd = cmdbr.readLine();
			exec(cmd);
		    	}
			}catch(Exception e){
		    	System.err.println("An error has happened, log is below");
		    	e.printStackTrace();
			}
		}
}
