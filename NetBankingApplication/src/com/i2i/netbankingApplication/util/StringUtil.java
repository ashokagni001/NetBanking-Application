package com.i2i.netbankingApplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringUtil {
	
	/**
     * Check the employee Name valid Format or not.
     * 
     * @param input
     *        A String data type.  
     * @return Boolean
     *         Return true or false.
     */
    public static boolean isAlphabetic(String input) {
        return (input.matches("^[a-zA-Z]+$"));
    }  
    
    /**
     * Check the employee salary valid Format or not.
     * 
     * @param input
     *        A int data type.  
     * @return Boolean
     *         Return true or false.
     */
    public static boolean isNumeric(String input) {
        return (input.matches("^[0-9]+$"));
    }
    
    /**
     * Check the employee P.Number valid Format or not.
     * 
     * @param input
     *        A int data type.  
     * @return Boolean
     *         Return true or false.
     */
    public static boolean isSixDigit(String input) {
        return (input.matches("^[0-9][0-9][0-9][0-9][0-9][0-9]"));
    }
    
    /**
     * Check the employee address valid Format or not.
     * 
     * @param input
     *        A string data type.  
     * @return Boolean
     *         Return true or false.
     */
    public static boolean isAddress(String input) {
        return (input.matches("^[a-zA-Z0-9]+$"));
    }  
    
    /**
     * Check the employee DOB valid Format or not.
     * 
     * @param DOB
     *        DOB of employee.
     *        
     * @return Boolean
     *         Return true or false.
     */
    public static boolean isValidFormat(String DOB) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat.parse(DOB);
            return false;
            }
        catch(ParseException e) {
            FileUtil.exceptionOccurCreateFile("\nEMPLOYEE DATE OF BIRTH IS NOT VALID...\n" + e.toString());
            return true;
        }
    }
    /**
     * It is calculate the employee age.
     * 
     * @param employeeDateOfBirth
     *        DOB of employee.
     * @return year
     *         return age of employee.
     */
    public static int calculateAge (String employeeDateOfBirth) {
        int month;
        int year;
        String age[] = employeeDateOfBirth.split("/");
        int employeeDay = Integer.parseInt(age[0]);
        int employeeMonth = Integer.parseInt(age[1]);
        int employeeYear = Integer.parseInt(age[2]);
        Calendar employeeBirthDay = Calendar.getInstance();
        employeeBirthDay.set(employeeYear, employeeMonth, employeeDay);
        Calendar currentDate = Calendar.getInstance();
        year = currentDate.get(Calendar.YEAR) - employeeBirthDay.get(Calendar.YEAR);
        month = currentDate.get(Calendar.MONTH) - employeeBirthDay.get(Calendar.MONTH);
        if (month < 0) {
            return year--;
        }
        return year;
    }  
}
