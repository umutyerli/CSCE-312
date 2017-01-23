import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

// VM Translator

// Umut Yerli
// CSCE 312
// 12/4/16

public class VMTranslator {
	
	private Scanner scan;
	private BufferedReader bufferedReader;
	public ArrayList<String> lines = new ArrayList<String>();
	public ArrayList<String> writeLines = new ArrayList<String>();
	public PrintWriter writer;
	public String fileName;
	public int inc=0;
	
	public String increment(){
		inc++;
		return Integer.toString(inc);
	}
	
	public void push(String[] array) {
		if(array[1].equals("constant")) {
			writeLines.add("@"+array[2]);
			writeLines.add("D=A");
			writeLines.add("@SP");
			writeLines.add("A=M");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("M=M+1");
		} else if(array[1].equals("argument")) {
			writeLines.add("@ARG");
			writeLines.add("D=M");
			writeLines.add("@"+array[2]);
			writeLines.add("A=D+A");
			writeLines.add("D=M");
			writeLines.add("@SP");
			writeLines.add("A=M");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("M=M+1");
		} else if(array[1].equals("this")) {
			writeLines.add("@THIS");
			writeLines.add("D=M");
			writeLines.add("@"+array[2]);
			writeLines.add("A=D+A");
			writeLines.add("D=M");
			writeLines.add("@SP");
			writeLines.add("A=M");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("M=M+1");
		} else if(array[1].equals("that")) {
			writeLines.add("@THAT");
			writeLines.add("D=M");
			writeLines.add("@"+array[2]);
			writeLines.add("A=D+A");
			writeLines.add("D=M");
			writeLines.add("@SP");
			writeLines.add("A=M");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("M=M+1");
		} else if(array[1].equals("temp")) {
			writeLines.add("@R5");
			writeLines.add("D=A");
			writeLines.add("@"+array[2]);
			writeLines.add("A=D+A");
			writeLines.add("D=M");
			writeLines.add("@SP");
			writeLines.add("A=M");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("M=M+1");
		} else if(array[1].equals("local")) {
			writeLines.add("@LCL");
			writeLines.add("D=M");
			writeLines.add("@"+array[2]);
			writeLines.add("A=D+A");
			writeLines.add("D=M");
			writeLines.add("@SP");
			writeLines.add("A=M");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("M=M+1");
		} else if(array[1].equals("static")) {
			writeLines.add("@"+fileName+"."+array[2]);
			writeLines.add("D=M");
			writeLines.add("@SP");
			writeLines.add("A=M");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("M=M+1");
		} else if(array[1].equals("pointer")) {
			if(array[2].equals("0")){
				writeLines.add("@THIS");
				writeLines.add("D=M");
				writeLines.add("@SP");
				writeLines.add("A=M");
				writeLines.add("M=D");
				writeLines.add("@SP");
				writeLines.add("M=M+1");
				
			} else {
				writeLines.add("@THAT");
				writeLines.add("D=M");
				writeLines.add("@SP");
				writeLines.add("A=M");
				writeLines.add("M=D");
				writeLines.add("@SP");
				writeLines.add("M=M+1");
			}
		}
	}
	
	public void pop(String[] array) {
		if(array[1].equals("local")) {
			writeLines.add("@LCL");
			writeLines.add("D=M");
			writeLines.add("@"+array[2]);
			writeLines.add("D=D+A");
			writeLines.add("@R13");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("AM=M-1");
			writeLines.add("D=M");
			writeLines.add("@R13");
			writeLines.add("A=M");
			writeLines.add("M=D");
		} else if(array[1].equals("argument")) {
			writeLines.add("@ARG");
			writeLines.add("D=M");
			writeLines.add("@"+array[2]);
			writeLines.add("D=D+A");
			writeLines.add("@R13");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("AM=M-1");
			writeLines.add("D=M");
			writeLines.add("@R13");
			writeLines.add("A=M");
			writeLines.add("M=D");
		} else if(array[1].equals("this")) {
			writeLines.add("@"+array[2]);
			writeLines.add("D=A");
			writeLines.add("@THIS");
			writeLines.add("D=D+A");
			writeLines.add("@R13");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("AM=M-1");
			writeLines.add("D=M");
			writeLines.add("@R13");
			writeLines.add("A=M");
			writeLines.add("M=D");
			
		} else if(array[1].equals("that")) {
			writeLines.add("@"+array[2]);
			writeLines.add("D=A");
			writeLines.add("@THAT");
			writeLines.add("D=D+A");
			writeLines.add("@R13");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("AM=M-1");
			writeLines.add("D=M");
			writeLines.add("@R13");
			writeLines.add("A=M");
			writeLines.add("M=D");
		} else if(array[1].equals("temp")) {
			writeLines.add("@R5");
			writeLines.add("D=A");
			writeLines.add("@"+array[2]);
			writeLines.add("D=D+A");
			writeLines.add("@R13");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("AM=M-1");
			writeLines.add("D=M");
			writeLines.add("@R13");
			writeLines.add("A=M");
			writeLines.add("M=D");
		} else if(array[1].equals("pointer")) {
			if(array[2].equals("0")){
				writeLines.add("@THIS");
				writeLines.add("D=A");
				writeLines.add("@R13");
				writeLines.add("M=D");
				writeLines.add("@SP");
				writeLines.add("AM=M-1");
				writeLines.add("D=M");
				writeLines.add("@R13");
				writeLines.add("A=M");
				writeLines.add("M=D");
				
			} else {
				writeLines.add("@THAT");
				writeLines.add("D=A");
				writeLines.add("@R13");
				writeLines.add("M=D");
				writeLines.add("@SP");
				writeLines.add("AM=M-1");
				writeLines.add("D=M");
				writeLines.add("@R13");
				writeLines.add("A=M");
				writeLines.add("M=D");
			}
		} else if(array[1].equals("static")) {
			writeLines.add("@"+fileName+"."+array[2]);
			writeLines.add("D=A");
			writeLines.add("@R13");
			writeLines.add("M=D");
			writeLines.add("@SP");
			writeLines.add("AM=M-1");
			writeLines.add("D=M");
			writeLines.add("@R13");
			writeLines.add("A=M");
			writeLines.add("M=D");
		}
	}
	
	public void add(String[] array) {
		writeLines.add("@SP");
		writeLines.add("AM=M-1");
		writeLines.add("D=M");
		writeLines.add("A=A-1");
		writeLines.add("M=M+D");
	}
	
	public void sub(String[] array) {
		writeLines.add("@SP");
		writeLines.add("AM=M-1");
		writeLines.add("D=M");
		writeLines.add("A=A-1");
		writeLines.add("M=M-D");
	}
	
	public void label(String[] array) {
		writeLines.add("("+array[1]+")");
	}
	
	public void ifgoto(String[] array) {
		writeLines.add("@SP");
		writeLines.add("AM=M-1");
		writeLines.add("D=M");
		writeLines.add("@"+array[1]);
		writeLines.add("D;JNE");
	}
	
	public void thisgoto(String[] array) {
		writeLines.add("@"+array[1]);
		writeLines.add("0;JMP");
	}
	
	public void eq(String[] array) {
		writeLines.add("@SP");
		writeLines.add("AM=M-1");
		writeLines.add("D=M");
		writeLines.add("A=A-1");
		writeLines.add("D=M-D");
		writeLines.add("@EQ.true."+increment());
		writeLines.add("D;JEQ");
		writeLines.add("@SP");
		writeLines.add("A=M-1");
		writeLines.add("M=0");
		writeLines.add("@EQ.after."+inc);
		writeLines.add("0;JMP");
		writeLines.add("(EQ.true."+inc+")");
		writeLines.add("@SP");
		writeLines.add("A=M-1");
		writeLines.add("M=-1");
		writeLines.add("(EQ.after."+inc+")");
	}
	
	public void lt(String[] array) {
		writeLines.add("@SP");
		writeLines.add("AM=M-1");
		writeLines.add("D=M");
		writeLines.add("A=A-1");
		writeLines.add("D=M-D");
		writeLines.add("@LT.true."+increment());
		writeLines.add("D;JLT");
		writeLines.add("@SP");
		writeLines.add("A=M-1");
		writeLines.add("M=0");
		writeLines.add("@LT.after."+inc);
		writeLines.add("0;JMP");
		writeLines.add("(LT.true."+inc+")");
		writeLines.add("@SP");
		writeLines.add("A=M-1");
		writeLines.add("M=-1");
		writeLines.add("(LT.after."+inc+")");
	}
	
	public void gt(String[] array) {
		writeLines.add("@SP");
		writeLines.add("AM=M-1");
		writeLines.add("D=M");
		writeLines.add("A=A-1");
		writeLines.add("D=M-D");
		writeLines.add("@GT.true."+increment());
		writeLines.add("D;JGT");
		writeLines.add("@SP");
		writeLines.add("A=M-1");
		writeLines.add("M=0");
		writeLines.add("@GT.after."+inc);
		writeLines.add("0;JMP");
		writeLines.add("(GT.true."+inc+")");
		writeLines.add("@SP");
		writeLines.add("A=M-1");
		writeLines.add("M=-1");
		writeLines.add("(GT.after."+inc+")");
	}
	
	public void neg(String[] array) {
		writeLines.add("@SP");
		writeLines.add("A=M-1");
		writeLines.add("M=-M");
	}
	
	public void and(String[] array) {
		writeLines.add("@SP");
		writeLines.add("AM=M-1");
		writeLines.add("D=M");
		writeLines.add("A=A-1");
		writeLines.add("M=D&M");
	}
	
	public void or(String[] array) {
		writeLines.add("@SP");
		writeLines.add("AM=M-1");
		writeLines.add("D=M");
		writeLines.add("A=A-1");
		writeLines.add("M=D|M");
	}
	
	public void not(String[] array) {
		writeLines.add("@SP");
		writeLines.add("A=M-1");
		writeLines.add("M=!M");
	}
	
	public void translateAddress() {
		String[] arr;
		for(String arrayLines : lines) {
			arr = arrayLines.split("\\s+");
			System.out.println(arr[0]);
			if(arr[0].equals("push")) {
				push(arr);
			} else if(arr[0].equals("pop")) {
				pop(arr);
			} else if (arr[0].equals("add")) {
				add(arr);
			} else if (arr[0].equals("sub")) {
				sub(arr);
			} else if(arr[0].equals("label")) {
				label(arr);
			} else if(arr[0].equals("if-goto")) {
				ifgoto(arr);
			} else if(arr[0].equals("goto")) {
				thisgoto(arr);
			} else if(arr[0].equals("eq")){
				eq(arr);
			} else if(arr[0].equals("lt")) {
				lt(arr);
			} else if(arr[0].equals("gt")) {
				gt(arr);
			} else if(arr[0].equals("neg")) {
				neg(arr);
			} else if(arr[0].equals("and")) {
				and(arr);
			} else if(arr[0].equals("or")) {
				or(arr);
			} else if(arr[0].equals("not")) {
				not(arr);
			} else {
				
			}
		}
	}
	
	public void storeArray(String line) {
		lines.add(line);
	}

	public void readAddress() {
		String line = null;
		scan = new Scanner(System.in);
		System.out.println("What is the name of the file you're reading and writing? *filename*.vm or .asm");
		fileName = scan.next();
		try {
			FileReader fileReader = new FileReader(fileName + ".vm");
			bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				if(line.length() > 0) {
					if(line.charAt(0) != '/') {
						//System.out.println(line);
						storeArray(line);
					}
				}
			}
			
		} catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + ".vm'"); 
		} catch(IOException ex) {
			System.out.println("Error reading file '" + fileName + ".vm'");
		}
		
	}
	
	public void writeAddress() {
		try {
			writer = new PrintWriter(fileName+".asm","UTF-8");
			for(String linesOut : writeLines) {
				writer.println(linesOut);
				System.out.println(linesOut);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		VMTranslator vm = new VMTranslator();
		vm.readAddress();
		vm.translateAddress();
		vm.writeAddress();
	}
}

