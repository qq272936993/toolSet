package cn.com.yws.toolset.base.common.util;

import java.util.ArrayList;
import java.util.List;


/***
 * 进制转换
 * 		十进制  -->   二进制
 * 		十进制  -->   十六进制
 * */
public class HexConversion {

	/**
	 * 十进制 ---> 二进制
	 * @return 
	 * */
	public static List<Character> toBin(int num){
		return trans(num, 1, 1);
	}
	
	/**
	 * 十进制 ---> 八进制
	 * */
	public static List<Character> toBa(int num){
		return trans(num, 7, 3);
	}
	
	/**
	 * 十进制--->十六进制
	 * */
	public static List<Character> toHex(int num){
		return trans(num , 15 , 4);
	}
	
	
	public static List<Character> trans(int num , int base , int offset){
		if(num == 0){
			System.out.println(num);
			return null;
		}
		char[] chs = {'0' , '1' , '2' ,'3', '4', '5', '6','7',
				'8','9','A','B','C','D','E','F'};
		
		char[] arr = new char[32];
		int pos = arr.length;
		while(num != 0){
			int temp = num & base;
			arr[--pos] = chs[temp];
			num = num >>> offset;
		}
		
		List<Character> list = new ArrayList<Character>();
		for(int x=pos; x < arr.length; x++){
			//System.out.print(arr[x]);
			list.add(arr[x]);
		}
		return list;
	}
	
	public static void main(String[] args) {
		List<Character> list = toBin(22);
		
		for(Character chr : list){
			System.out.print(chr);
		}
	}
	
}
