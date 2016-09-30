package com.i2i.netbankingapplication.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**]
 * <p>
 *     This class have a static method.
 *     If any request comes this class return the response based on the
 *     request.
 * </p>
 * 
 * @author TEAM -2 
 *
 * @created 2016-09-26.
 */
public class StringUtil {
	
	/**
	 * </p>
	 *    This method connect to the ApplicationInformation.properties file.
	 *    Return to the properties references.
	 * <p>
	 * 
	 * @return properties object.
	 */
    public static Properties informationReader() {
    	try {
            Properties p = new Properties();  
            p.load(new FileReader(new File("/home/ubuntu/workspace/NetBankingApplicationMaven/src/main/resources/ApplicationInformation.properties")));
            return p;
    	} catch(IOException io) {
    		System.out.println("file not found.. " + io);
    	}
		return null;
    }
}