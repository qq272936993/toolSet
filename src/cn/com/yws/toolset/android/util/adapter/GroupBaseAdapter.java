package cn.com.yws.toolset.android.util.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

public class GroupBaseAdapter extends BaseAdapter {

	private List<Param>  	params;
	/**
	 * 用于记录Label描述的下标position
	 * */
	private List<Integer>	labelPosition 	= 	new ArrayList<Integer>();
	private GroupBaseItemViewInterface intf;
	
	public GroupBaseAdapter(List<Param> params,GroupBaseItemViewInterface intf){
		this.params 		= 	params;
		this.intf			=	intf;
	}
	
	private int calculateCount(){
		labelPosition.clear();
		int size = 0;
		int index = 0;
		for(Param param : params){
			if(param.getIsShow()){
				param.setListIndex(index ++);
				labelPosition.add(size);
				size +=1;
				param.setStartIndex(size);
				size += param.getDatas().size();
				param.setEndIndex(size);
			}
		}
		return size;
	}
	
	
	
	@Override
	public int getCount() {
		return calculateCount();
	}
	
	@Override
	public Object getItem(int position) {
		if(labelPosition.contains(position))
			return null;
		//不是Label显示,找到这条记录在哪个Param中记录的
		Param param = findParamByPosition(position);
		List<?> datas = param.getDatas();
		return datas.get(position - param.getStartIndex());
	}
	
	private Param findParamByPosition(int position){
		for(Param param : params){
			if(param.startIndex <= position && position <= param.endIndex){
				return param;
			}
		}
		return null;
	}
	
	
	/**
	 * position位置关系转换换算
	 * */
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object item = getItem(position);
		View view = null;
		if(item == null){
			//表示其为Label描述信息
			int index = labelPosition.indexOf(position);
			Param param = params.get(index);
			
			view = intf.isLable(position, convertView, parent, param.getLabel() ,index);
			
		}else{
			Param param = findParamByPosition(position);
			view = intf.isItem(position, convertView, parent, item, param);
		}
		
		return view;
	}
	
	
	
	
	
	/**
	 * Adapter中的getView相关接口回调
	 * */
	public static interface GroupBaseItemViewInterface{
		
		public View isLable(int position, View convertView, ViewGroup parent, String label ,int listIndex);
		
		public View isItem(int position, View convertView, ViewGroup parent,
				Object item , Param param);
		
	}
	
	/**
	 * 分组ListView 滑动的接口
	 * */
	public static interface GroupBaseScrollInterface{
		
		public void onScrollStateChanged(AbsListView view, int scrollState);
		
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount,Param param);
		
	}
	
	
	
	
	
	/***
	 * 数据传递的桥梁
	 * */
	public static class Param{
		private String 				id;				//用于唯一标识的id值
		private String				label;			//用于显示的分组的label
		private boolean				isShow = true;	//用于控制该组数据是否显示
		
		private List<?> 			datas;			//用于显示的每个组的信息	
		
		private int 				listIndex;		//在集合中的位置
		//以下两个类型由系统设置
		private int 				startIndex;		//开始的位置
		private int 				endIndex;		//结束的位置
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public List<?> getDatas() {
			return datas;
		}

		public void setDatas(List<?> datas) {
			this.datas = datas;
		}

		public int getStartIndex() {
			return startIndex;
		}

		public void setStartIndex(int startIndex) {
			this.startIndex = startIndex;
		}

		public int getEndIndex() {
			return endIndex;
		}

		public void setEndIndex(int endIndex) {
			this.endIndex = endIndex;
		}

		public int getListIndex() {
			return listIndex;
		}
		public void setListIndex(int listIndex) {
			this.listIndex = listIndex;
		}

		public boolean getIsShow() {
			return isShow;
		}

		public void setIsShow(boolean isShow) {
			this.isShow = isShow;
		}
		
	}


	
	

	
	/**
	 * 获取下标所在组的所有信息
	 * @param position 下标
	 * */
	public Param getParam(int position){
		for(int i =0; i< labelPosition.size() ; i++){
			Param param = params.get(i);
			if(param.getStartIndex()-1 <= position && param.getEndIndex() > position){
				return param;
			}
		}
		
		return null;
	}
	
	
	public List<Param> getParams(){
		return this.params;
	}
	
	

}
