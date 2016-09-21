package com.i2i.netbankingApplication.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * When request comes from FileUtil and FileUtil perform to write message in file.
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-15 
 */
public class FileUtil {
    /**
     * It is write a message in file with current date.
     * 
     * @param errorMessage
     *     A String data type.
     */
    public static void exceptionOccurCreateFile(String errorMessage) {
        try {
            Date date = new Date();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("abc.txt", true));
            bufferedWriter.write("\n" + date + "--->   " + errorMessage + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("SOME PROBLEM OCCUR PLEASE TRY AGAIN LATER");
        }
    }
}
