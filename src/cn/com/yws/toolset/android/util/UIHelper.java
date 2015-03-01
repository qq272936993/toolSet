package cn.com.yws.toolset.android.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * <pre>
 * 文件名称: UIHelper.java
 * 包路径: cn.com.yws.toolset.android.util 
 * 描述:	 UI控件的帮助类
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2015年2月5日
 *    邮箱:  272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ---------------------------------------------- 
 * 
 * </pre>
 */
public class UIHelper {
	
	
	/**
	 * EditText显示键盘
	 * @param editText 文本框主键
	 * */
	public static void showKeyboard(EditText editText){
		if(editText == null) return;
		
		InputMethodManager imm = (InputMethodManager) editText.getContext().
				getSystemService(Context.INPUT_METHOD_SERVICE);
		
		imm.showSoftInput(editText, 0);
	}
	
	
	/**
	 * 隐藏键盘
	 * @param editText
	 * */
	public static void hideKeyboard(EditText editText){
		if(editText == null) return;
		
		InputMethodManager imm = (InputMethodManager) editText.getContext().
				getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromInputMethod(editText.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		
	}
	
	
	
	
	
	
	
	
}
