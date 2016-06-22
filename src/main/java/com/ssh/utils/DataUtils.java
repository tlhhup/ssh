package com.ssh.utils;

import java.util.Collection;

import com.ssh.entity.BaseEntity;

public class DataUtils {

	public static String array2str(Integer[] array){
		if(ValidateUtils.isValidate(array)){
			String result="";
			for(Integer i:array){
				result+=i+",";
			}
			return result.substring(0, result.length()-1);
		}
		return null;
	}

	public static String extractEntityIds(Collection<? extends BaseEntity> entitys) {
		if(ValidateUtils.isValidate(entitys)){
			String result="";
			for(BaseEntity entity:entitys){
				result+=entity.getId()+",";
			}
			return result.substring(0, result.length()-1);
		}
		return null;
	}
	
}
