package cn.com.yws.toolset.android.util.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BaseSimpleAdapter extends BaseAdapter{
	
	private List<?> datas;
	private ItemViewInterface intf;
	public BaseSimpleAdapter(List<?> data, ItemViewInterface intf){
		this.datas = data;
		this.intf = intf;
	}
	
	
	
	@Override
	public int getCount() {

		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return intf.getView(position, convertView, parent);
	}
	
	public static interface ItemViewInterface{
		
		public View getView(int position, View convertView, ViewGroup parent);
		
	}
	
	
	
}
