package ruc.irm.similarity.phrase;

import java.util.ArrayList;
import java.util.List;

import ruc.irm.similarity.Similaritable;


public class PhraseSimilarity implements Similaritable {

	@Override
	public double getSimilarity(String item1, String item2) {		
		return (getSC(item1, item2) + getSC(item2, item1))/2.0;
	}
	
	public List<Integer> getC(String first, String second, int pos){
		List<Integer> results = new ArrayList<Integer>();
		char ch = first.charAt(pos);
		for(int i=0; i<second.length(); i++){
			if(ch == second.charAt(i)){
				results.add(i);
			}
		}
		return results;
	}
	
	public int getDistance(String first, String second, int pos){
		int d = second.length();
		for(int k: getC(first, second, pos)){
			int value = Math.abs(k - pos);
			if(d>value){
				d = value;
			}
		}
		
		return d;
	}
	
	public double getCC(String first, String second, int pos){
		return (second.length()-getDistance(first, second, pos))*1.0/second.length();
	}
	
	public double getSC(String first, String second){
		double total = 0.0;
		for(int i=0; i<first.length(); i++){
			total = total + getCC(first, second, i);			
		}
		return total/first.length();
	}

}
