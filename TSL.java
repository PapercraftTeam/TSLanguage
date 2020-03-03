import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.*;

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
		System.out.println("time (int varindex)");
		System.out.println("plus (int varindex) (int varindex) -- Plus two variable together");
		System.out.println("mnus (int varindex) (int varindex) -- minus two variable together");
		System.out.println("mtpl (int varindex) (int varindex) -- Multiply two variable together");
		System.out.println("dvid (int varindex) (int varindex)) -- Divide two variable");
		System.out.println("all these operations will save the result in the first variable index.");
	}

	public static void init(){
		System.out.println("Welcome to TSL(T Script Language)! 0.6.1");
		System.out.println("Made by GrieferPig and written in Java!");
		System.out.println("To see all commands aviliable, type help.");
	}

	public static void create(int varindex, String data){
		var.ensureCapacity(32767);
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

	public static void main(String[] args) throws IOException{
		init();
		try{
		while(true){
			BufferedReader cmdbr = new BufferedReader(new InputStreamReader(System.in));
			cmd = cmdbr.readLine();
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
					create(Integer.parseInt(cmdArr[1]), cmdArr[2]);
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
					text(cmdArr[1]);
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
			}else{
			    	System.err.println("Command Not Exist!");
			    }
			}
		}catch(Exception e){
			System.err.println("An error has happened, log is below.");
			e.printStackTrace();
			try {
				e.wait(10000);
			} catch (InterruptedException e1) {}
		}
	}
}
