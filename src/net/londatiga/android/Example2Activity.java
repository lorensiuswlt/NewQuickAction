package net.londatiga.android;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener; 
import android.widget.PopupWindow;

/**
 * QuickAction demo activity. 
 * 
 * This demo shows how to use quickaction, add items, setup listener for 
 * action item click and dismiss. It also shows how to implements quickaction on listview, get the
 * listview selected row and perform the action for each action item.
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 *
 */
public class Example2Activity extends Activity {
	/**
	 * Listview selected row
	 */
	private int mSelectedRow = 0;
	
	/**
	 * Right arrow icon on each listview row
	 */
	private ImageView mMoreIv = null;
	
	private static final int ID_ADD = 1;
	private static final int ID_ACCEPT = 2;
	private static final int ID_UPLOAD = 3;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.example2);
        
        ListView mList 			= (ListView) findViewById(R.id.l_list);
        
        NewQAAdapter adapter 	= new NewQAAdapter(this);
        
        final String[] data 	= {"Test 01", "Test 02", "Test 03", "Test 04", "Test 05", "Test 06", "Test 07", "Test 08",
        					   	  "Test 09", "Test 10"};
        
        adapter.setData(data);
        mList.setAdapter(adapter);
        
        ActionItem addItem 		= new ActionItem(ID_ADD, "Add", getResources().getDrawable(R.drawable.ic_add));
		ActionItem acceptItem 	= new ActionItem(ID_ACCEPT, "Accept", getResources().getDrawable(R.drawable.ic_accept));
        ActionItem uploadItem 	= new ActionItem(ID_UPLOAD, "Upload", getResources().getDrawable(R.drawable.ic_up));
		
		final QuickAction mQuickAction 	= new QuickAction(this);
		
		mQuickAction.addActionItem(addItem);
		mQuickAction.addActionItem(acceptItem);
		mQuickAction.addActionItem(uploadItem);
		
		//setup the action item click listener
		mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			@Override
			public void onItemClick(QuickAction quickAction, int pos, int actionId) {
				ActionItem actionItem = quickAction.getActionItem(pos);
				
				if (actionId == ID_ADD) { //Add item selected
					Toast.makeText(getApplicationContext(), "Add item selected on row " + mSelectedRow, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), actionItem.getTitle() + " item selected on row " 
							+ mSelectedRow, Toast.LENGTH_SHORT).show();
				}	
			}
		});
		
		//setup on dismiss listener, set the icon back to normal
		mQuickAction.setOnDismissListener(new PopupWindow.OnDismissListener() {			
			@Override
			public void onDismiss() {
				mMoreIv.setImageResource(R.drawable.ic_list_more);
			}
		});
		
		mList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mSelectedRow = position; //set the selected row
				
				mQuickAction.show(view);
				
				//change the right arrow icon to selected state 
				mMoreIv = (ImageView) view.findViewById(R.id.i_more);
				mMoreIv.setImageResource(R.drawable.ic_list_more_selected);
			}
		});
    }
}