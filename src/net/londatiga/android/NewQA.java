package net.londatiga.android;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener; 
import android.widget.PopupWindow.OnDismissListener;

public class NewQA extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        ListView mList = (ListView) findViewById(R.id.l_list);
        
        NewQAAdapter adapter = new NewQAAdapter(this);
        
        final String[] data = {"Test 01", "Test 02", "Test 03", "Test 04", "Test 05", "Test 06", "Test 07", "Test 08",
        					   "Test 09", "Test 10"};
        
        adapter.setData(data);
        mList.setAdapter(adapter);
        
        final ActionItem addAction = new ActionItem();
		
		addAction.setTitle("Add");
		addAction.setIcon(getResources().getDrawable(R.drawable.ic_add));

		final ActionItem accAction = new ActionItem();
		
		accAction.setTitle("Accept");
		accAction.setIcon(getResources().getDrawable(R.drawable.ic_accept));
		
		final ActionItem upAction = new ActionItem();
		
		upAction.setTitle("Upload");
		upAction.setIcon(getResources().getDrawable(R.drawable.ic_up));
		
		mList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				final QuickAction mQuickAction 	= new QuickAction(view);
				
				final ImageView mMoreImage 		= (ImageView) view.findViewById(R.id.i_more);
				
				final String text				= data[position];
				
				mMoreImage.setImageResource(R.drawable.ic_list_more_selected);
				
				addAction.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(NewQA.this, "Add " + text, Toast.LENGTH_SHORT).show();
				    	
						mQuickAction.dismiss();
					}
				});

				accAction.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(NewQA.this, "Accept " + text, Toast.LENGTH_SHORT).show();
				    	
						mQuickAction.dismiss();
					}
				});
				
				upAction.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(NewQA.this, "Upload " + text, Toast.LENGTH_SHORT).show();
				    	
						mQuickAction.dismiss();
					}
				});
				
				mQuickAction.addActionItem(addAction);
				mQuickAction.addActionItem(accAction);
				mQuickAction.addActionItem(upAction);
				
				mQuickAction.setAnimStyle(QuickAction.ANIM_AUTO);
				
				mQuickAction.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss() {
						mMoreImage.setImageResource(R.drawable.ic_list_more);
					}
				});
				
				mQuickAction.show();
			}
		});
    }
}