package com.ssh.utils;

import java.util.Collection;

public class ValidateUtils {
	
	public static boolean isValidate(Collection cll){
		if(cll==null||cll.isEmpty()){
			return false;
		}
		return true;
	}
	
	public static boolean isValidate(Object[] arr){
		if(arr==null||arr.length==0){
			return false;
		}
		return true;
	}

}
