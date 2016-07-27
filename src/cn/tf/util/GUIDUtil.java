package cn.tf.util;

import java.math.BigInteger;
import java.util.Random;

public class GUIDUtil {
	
	public static String generateGUID(){
		return new BigInteger(165,new Random()).toString(36).toUpperCase();
	}
	public static void main(String[] args){
		for(int i=0;i<100;i++){
			System.out.println(generateGUID());
		}
	}
	

}
