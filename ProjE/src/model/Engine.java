package model;

import java.math.BigInteger;
import java.util.List;

public class Engine {

	private static Engine instance = null;
	
	private Engine() {}
	
	public synchronized static Engine getInstance() {
		if (instance == null) {
			instance = new Engine();
		}
		return instance;
	}
	
	public int doPrime(String lowVal, String highVal) throws Exception {
		int lowValInt, highValInt;
		try {
			lowValInt = Integer.parseInt(lowVal);
			highValInt = Integer.parseInt(highVal);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid entries!");
		}
		
		BigInteger lowBI = new BigInteger(lowValInt+"");
		
		if (lowBI.nextProbablePrime().intValue() >= highValInt) {
			throw new Exception("No more primes in range.");
		}
		
		BigInteger result;
		result = lowBI.nextProbablePrime();
		return result.intValue();
	}
	
	public List<StudentBean> doSis(String namePrefix, String minGpa, String sortOption) throws Exception {
		StudentDAO sd = new StudentDAO();
		double gpa;
		
		try {
			if (minGpa.equals("")) {
				minGpa = "0";
			}
			gpa = Double.parseDouble(minGpa);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Please enter a GPA");
		}
		
		if (sortOption.equals("NONE")) {
			sortOption = "SURNAME, GIVENNAME";
		}
		
		List<StudentBean> result = sd.retrieve(namePrefix, gpa, sortOption);	
		
		return result;
	}
	
}
