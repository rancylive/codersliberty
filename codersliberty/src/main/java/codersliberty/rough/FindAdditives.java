package codersliberty.rough;

import org.apache.commons.lang3.StringUtils;

/**
 * Given a number n, find all combination of its additives e.g. for n=4, the output would be 
 * 1111
 * 112
 * 121
 * 211
 * 13
 * 31
 * 4
 * @author rchoudhury
 *
 */
public class FindAdditives {
	
	public static void main(String[] args) {
		System.out.println(printAllAdditives(7));
	}
	
	public static void wrong(String[] args) {
		String out="";
		int num=4; // i/p
		int remainder=0;
		while(num-remainder>0) {
			int toPrint = remainder+1;
			int sum = 0;
			while(sum<num) {
				out+=toPrint;
				sum+=toPrint;
			}
			remainder++;
			out+="\n";
		}
		System.out.println(out);
	}
	
	public static String addtives(int num, int upperLimit, int position,String out) {
		int remainder = num-upperLimit;
		while(remainder-->0) {
			out+="1";
		}
		return StringUtils.substring(out, 0, position)+upperLimit+StringUtils.substring(out, position, out.length());
	}
	
	public static String printAllAdditives(int num) {
		String out = "";
		int position = num;
		for(int i=0;i<num;i++) {
			int upperLimit = i+1;
			int j=0;
			String line;
			line=addtives(4, upperLimit, position, "");
			out+=line+"\n";
			position=line.length();
			while(j<line.length() && upperLimit!=1) {
				line=addtives(4, upperLimit, position, "");
				out+=line+"\n";
				j++;
				position=position-j;
			} 
		}
		return out;
	}
}