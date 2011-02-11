package net.londatiga.android;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.content.Context;

import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewQAAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private String[] data;
	
	public NewQAAdapter(Context context) { 
		mInflater = LayoutInflater.from(context);
	}

	public void setData(String[] data) {
		this.data = data;
	}
	
	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int item) {
		return data[item];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			convertView	= mInflater.inflate(R.layout.list, null);
			
			holder 		= new ViewHolder();
			
			holder.mTitleText	= (TextView) convertView.findViewById(R.id.t_name);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.mTitleText.setText(data[position]);
		
		return convertView;
	}

	static class ViewHolder {
		TextView mTitleText;
	}
}