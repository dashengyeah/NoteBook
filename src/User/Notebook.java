package User;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import life.dashyeah.Notebook.*;

public class Notebook {
	private Pack pack = new Pack();
	
	private static void showHello(){
		System.out.println(" +++ Welcome to Dash Note! +++ ");
	}
	private static int showMenu(){
		int sel=0;
		System.out.print("****************\n"
				       + "*  Main  Menu  *\n"
				       + "*--------------*\n"
				       + "* 1.show all   *\n"
				       + "* 2.new record *\n"
				       + "* 3.search     *\n"
				       + "* 4.delete     *\n"
				       + "* 5.clear      *\n"
				       + "*              *\n"
				       + "* 0.exit       *\n"
				       + "****************\n");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true){
			try{
				System.out.print("choice> ");
				sel = scan.nextInt();
			}catch(InputMismatchException e){
				scan.next();
				System.out.println(" [!]wrong choice. retry.");
				continue;
			}
			if(sel<0 || sel>5) continue;
			break;
		}
		
		return sel;
	}
	private void showAll(){
		List<Record> all = pack.getRecords();
		Iterator<Record> it = all.iterator();
		while(it.hasNext()){
			Record r = it.next();
			System.out.format("#%d: \n%s",r.getNo(),r.getText());
		}
		System.out.println("");
	}
	private void newRecord(){
		StringBuilder s = new StringBuilder();
		System.out.print("Input text now. enter \"\\exit\" in a new line to finish.\n");
		String tmp = "";
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.print("Input>");
			tmp = scan.nextLine();
			if(tmp.equals("\\exit")) break;
			s.append(tmp+"\n");
		}
		this.pack.addRecord(s.toString());
	}
	private void searchRecord(){
		String s;
		int sel;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true){
			try{
				System.out.print("SN> ");
				sel = scan.nextInt();
			}catch(InputMismatchException e){
				scan.next();
				System.out.println(" [!]wrong choice. retry.");
				continue;
			}
			break;
		}
		s = pack.getRecord(sel);
		if(s != null){
			System.out.println("result>"+s);
		}
		else{
			System.out.println("msg>Not Found.");
		}
	}
	private void daleteRecord(){
		int sel;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true){
			try{
				System.out.print("SN> ");
				sel = scan.nextInt();
			}catch(InputMismatchException e){
				scan.next();
				System.out.println(" [!]wrong choice. retry.");
				continue;
			}
			break;
		}
		this.pack.removeRecord(sel);
		System.out.println("OK!");
	}
	private void clear(){
		this.pack.removeRecords();
	}
	
	public static void main(String args[]){
		Notebook nb = new Notebook();
		showHello();
		while(true){
			int sel;
			sel = showMenu();
			switch(sel){
			case 1:
				nb.showAll();
				break;
			case 2:
				nb.newRecord();
				break;
			case 3:
				nb.searchRecord();
				break;
			case 4:
				nb.daleteRecord();
				break;
			case 5:
				nb.clear();
				break;
			case 0:
			default:
				System.exit(0);
			}
		}
	}
}
