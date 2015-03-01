package cn.com.yws.toolset.android.util;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class FragmentUtils {
	
	
	/***
	 * 添加Fragment操作类型
	 * add
	 * */
	public final static int OPT_ADD_TYPE = 1;
	
	/**
	 * 删除Fragment操作类型
	 * delete
	 * */
	public final static int OPT_DEL_TYPE = 2;
	
	/**
	 * 代替Fragment操作类型
	 * replace 
	 * */
	public final static int OPT_RPL_TYPE = 3;
	
	/**
	 * 默认添加至回退栈中 
	 * */
	public final static boolean IS_TO_BACK_STACK = false;
	
	
	private FragmentManager manager;
	
	public FragmentUtils(FragmentManager manager){
 		this.manager =  manager;
	}
	
	
	/**
	 *  对Fragment进行操作后的事务(添加和替换操作)	
	 *  @param containerViewId 所在的视图ID
	 *  @param fragment 需要操作的Fragment
	 *  @param tag	标签
	 *  @param args 可加可不加类型参数
	 *  	int type //类型	不加则为 replace
	 *  	boolean isToBackStack //是否添加栈  不加则为true
	 *  当使用remove的时,containerViewId输入任何值不会对该方法产生影响
	 * */
	public void transact(int containerViewId, Fragment fragment,String tag,Object...args ){
		FragmentTransaction transaction = manager.beginTransaction();
		
		int type =  (args != null && args.length != 0 && args[0] != null) ? ((Integer)args[0]).intValue() : OPT_RPL_TYPE;
		boolean isToBackStack = ( args != null && args.length >=2 ) ? ((Boolean)args[1]).booleanValue() :IS_TO_BACK_STACK; 
		
		switch (type) {
			case OPT_ADD_TYPE:
				transaction.add(containerViewId, fragment, tag);
				break;
			default:
				transaction.replace(containerViewId, fragment, tag);
				break;
		}
		
		//添加至回退栈中
		if(isToBackStack)
			transaction.addToBackStack(tag);
		else
			transaction.disallowAddToBackStack();
		
		transaction.commit();
	}
	
	
	public void removeFragment(int fragmentId){
		FragmentTransaction transaction = manager.beginTransaction();
		Fragment fragment = manager.findFragmentById(fragmentId);
		transaction.remove(fragment);
		transaction.commit();
	}
	
	
}
