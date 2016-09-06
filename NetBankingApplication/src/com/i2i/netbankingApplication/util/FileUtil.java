package com.i2i.netbankingApplication.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.Exception;
import java.util.Date;

/**
 * When request comes from FileUtil and FileUtil perform to write message in file.
 * 
 * @author Ashok
 * 
 * @created 2016-08-27 
 *
 */
public class FileUtil {
	/**
	 * It is write a message in file.
     * 
	 * @param errorMessage
	 *        A String data type.
	 */
    public static void  exceptionOccurCreateFile(String errorMessage) {
        try {
            Date date = new Date();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("abc.txt",true));
            bufferedWriter.write("\n" + date + "--->   " + errorMessage + "\n");  
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("File Could Not Be Created");
        }
    }
}
      
