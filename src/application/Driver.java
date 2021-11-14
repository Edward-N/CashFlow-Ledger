package application;

import java.util.ArrayList;
import java.util.HashMap;

public class Driver {

	static ArrayList<Double> income = new ArrayList<Double>();
	static ArrayList<Double> expense = new ArrayList<Double>();
	static ArrayList<Double> savingsList = new ArrayList<Double>();
	static HashMap<String, Double> monthSavings =  new HashMap<String, Double>();
	static String[] months = {"January", "February", "March", "April", "May", "June", "July"
            , "August", "September", "October", "November", "December"};
	
	/**
	 * This method sets all the values received from scene2
	 * @param incomeInput, income received
	 * @param expenseInput, expense received 
	 * @param Month, the month that was chosen to put income and expense values
	 */
	public void setter(double incomeInput, double expenseInput) {
		income.add(incomeInput);
		expense.add(expenseInput); 
	}
	
	
	/**
	 * This method will calculate the savings from the user input
	 * The savings will be stored with the month in a hashmap (monthSavings).
	 */
	public void savings() {	
        for (int i = 0; i < 12; i++) {
            savingsList.add(income.get(i) - expense.get(i));
            monthSavings.put(months[i], savingsList.get(i));
        	}
    }
	
}
