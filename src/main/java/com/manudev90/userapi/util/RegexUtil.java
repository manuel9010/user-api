package com.manudev90.userapi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	
	

	    public static boolean isValid(String value,String patern) {
	    	Pattern pattern = Pattern.compile(patern);
	        Matcher matcher = pattern.matcher(value);
	        return matcher.matches();
	    }

}
