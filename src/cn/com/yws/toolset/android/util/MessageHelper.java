package cn.com.yws.toolset.android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * <pre>
 * 文件名称: MessageHelper.java
 * 包路径: cn.com.yws.toolset.android.util cn.com.talkweb.sch.controller.build
 * 描述:	 
 * 内容摘要 
 *    作者: 杨文松
 *    版本: 1.0
 *    时间: 2015年1月15日
 *    邮箱: 272936993@qq.com
 * 修改历史:  
 * 修改日期           修改人员        版本	       修改内容  		说明
 * ----------------------------------------------
 * 
 * </pre>
 */
public class MessageHelper {

	private static WindowManager.LayoutParams sysParams; 
	
	public static WindowManager.LayoutParams getDefaultLayoutParams(){
		sysParams= new WindowManager.LayoutParams();
		 WindowManager.LayoutParams params = sysParams;
         params.height = WindowManager.LayoutParams.WRAP_CONTENT;
         params.width = WindowManager.LayoutParams.WRAP_CONTENT;
         params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                 | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
         params.format = PixelFormat.TRANSLUCENT;
         //params.windowAnimations = com.android.internal.R.style.Animation_Toast;
         params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;	//优先于手机UI
         params.setTitle("Toast");
         
         return params;
	}
	
	
	
	
	private MessageHelper() {
		throw new RuntimeException("don't create this Class");
	}

	/**
	 * 显示Toast消息
	 * 
	 * @param activity
	 *            
	 * @param text
	 *            显示的文本
	 * */
	public static void showToast(final Activity activity, final String text) {
        if("main".equals(Thread.currentThread().getName()))
		    Toast.makeText(activity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        else{
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }
            });
        }
	}
	
	
	
	
	/***
	 * 开启自定义Toast
	 * @param context 上下文对象
	 * @param view 自定义布局
	 * @param 显示参数
	 * */
	public static void automaticToastShow(Context context ,View view ,WindowManager.LayoutParams mParams){
		sysParams = mParams != null ? mParams : getDefaultLayoutParams();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.addView(view, sysParams);
	}
	
	/**
	 * 隐藏关闭自定义Toast
	 * @param context 上下文
	 * @param mView 显示的布局View
	 * */
	public static void automaticToastHide(Context context , View mView){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		if (mView != null) {
            // note: checking parent() just to make sure the view has
            // been added...  i have seen cases where we get here when
            // the view isn't yet added, so let's try not to crash.
            if (mView.getParent() != null) {
            	wm.removeView(mView);
            }

            mView = null;
        }
	}
	
	
	

	/**
	 * 弹出确定消息对话框
	 *  @param context
	 * @param title 标题头
     * @param message 内容消息
     * @param positiveButton 积极的,确定的按钮
     * @param negativeButton 消极的,取消的按钮   
     * @return AlertDialog
     * */
	public static AlertDialog showAlterDialog(Context context, String title,
                                              String message, Button positiveButton, Button negativeButton) {
		// 工厂设计模式,得到创建对话框的工厂
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// 设置标题
		builder.setTitle(title);
		// 设置描述信息
		builder.setMessage(message);
		// 设置去诶的确定和取消按钮
		if (positiveButton != null) {
			builder.setPositiveButton(positiveButton.getText(),
					positiveButton.getClick());
		}
		if (negativeButton != null) {
			builder.setNegativeButton(negativeButton.getText(),
					negativeButton.getClick());
		}
		
		
		return builder.create();
	}
	
    /**
     * Show Alert Dialog
     * @param context
     * @param titleId
     * @param messageId
     */
    public static void showAlert(Context context, int titleId,int messageId) {
        Dialog dlg = new AlertDialog.Builder(context)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(titleId)
            .setPositiveButton(android.R.string.ok, null)
            .setMessage(messageId)
            .create();
        
        dlg.show();
    }

	
    
    
    
    
	
	
	
	
	/**
	 * 按钮内部类
	 * */
	public static class Button {
		/**
		 * 显示在按钮中的文本信息
		 * */
		private String text;
		/**
		 * 按钮执行的点击事件操作
		 * */
		private OnClickListener click;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public OnClickListener getClick() {
			return click;
		}

		public void setClick(OnClickListener click) {
			this.click = click;
		}

		public Button(String text, OnClickListener click) {
			super();
			this.text = text;
			this.click = click;
		}
	}

}
