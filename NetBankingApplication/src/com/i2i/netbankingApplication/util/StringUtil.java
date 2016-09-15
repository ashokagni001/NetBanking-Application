package com.i2i.netbankingApplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringUtil {
	
	/**
     * Check the customer Name valid Format or not.
     * 
     * @param input
     *     A String data type.  
     * @return Boolean
     *     Return true or false.
     */
    public static boolean isAlphabetic(String input) {
        return (input.matches("^[a-zA-Z]+$"));
    }  
    
    /**
     * Check the customer salary valid Format or not.
     * 
     * @param input
     *     A int data type.  
     * @return Boolean
     *     Return true or false.
     */
    public static boolean isNumeric(String input) {
        return (input.matches("^[0-9]+$"));
    }
    
    /**
     * Check the customer P.Number valid Format or not.
     * 
     * @param input
     *     A int data type.  
     * @return Boolean
     *     Return true or false.
     */
    public static boolean isSixDigit(String input) {
        return (input.matches("^[0-9][0-9][0-9][0-9][0-9][0-9]"));
    }
    
    /**
     * Check the customer address valid Format or not.
     * 
     * @param input
     *     A string data type.  
     * @return Boolean
     *     Return true or false.
     */
    public static boolean isAddress(String input) {
        return (input.matches("^[a-zA-Z0-9]+$"));
    }  
    
    /**
     * Check the customer DOB valid Format or not.
     * 
     * @param DOB
     *     DOB of customer.
     *        
     * @return Boolean
     *     Return true or false.
     */
    public static boolean isValidFormat(String DOB) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat.parse(DOB);
            return false;
            }
        catch(ParseException e) {
            return true;
        }
    }
    /**
     * It is calculate the customer age.
     * 
     * @param customerDateOfBirth
     *     DOB of customer.
     *     
     * @return year
     *     return age of customer.
     */
    public static int calculateAge (String customerDateOfBirth) {
        String age[] = customerDateOfBirth.split("/");
        int customerDay = Integer.parseInt(age[0]);
        int customerMonth = Integer.parseInt(age[1]);
        int customerYear = Integer.parseInt(age[2]);
        Calendar customerBirthDay = Calendar.getInstance();
        customerBirthDay.set(customerYear, customerMonth, customerDay);
        Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR) - customerBirthDay.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH) - customerBirthDay.get(Calendar.MONTH);
        if (month < 0) {
            return year--;
        }
        return year;
    }  
}
