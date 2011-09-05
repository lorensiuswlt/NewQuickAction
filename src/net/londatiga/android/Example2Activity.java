package net.londatiga.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Quickaction demo activity.
 * 
 * This demo shows how to use quickaction, add items, setup listener for action
 * item click and dismiss. It also shows how to implements quickaction on
 * listview, get the listview selected row and perform the action for each
 * action item.
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 * 
 */
public class Example2Activity extends Activity {
    /**
     * Listview selected row
     */
    private int       mSelectedRow = 0;

    /**
     * Right arrow icon on each listview row
     */
    private ImageView mMoreIv      = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.example2);

        ListView mList = (ListView) findViewById(R.id.l_list);

        NewQAAdapter adapter = new NewQAAdapter(this);

        final String[] data = { "Test 01", "Test 02", "Test 03", "Test 04", "Test 05", "Test 06", "Test 07", "Test 08",
                "Test 09", "Test 10" };

        adapter.setData(data);
        mList.setAdapter(adapter);

        // Add action item
        ActionItem addAction = new ActionItem();

        addAction.setTitle("Add");
        addAction.setIcon(getResources().getDrawable(R.drawable.ic_add));

        // Accept action item
        ActionItem accAction = new ActionItem();

        accAction.setTitle("Accept");
        accAction.setIcon(getResources().getDrawable(R.drawable.ic_accept));

        // Upload action item
        ActionItem upAction = new ActionItem();

        upAction.setTitle("Upload");
        upAction.setIcon(getResources().getDrawable(R.drawable.ic_up));

        final QuickAction mQuickAction = new QuickAction(this);

        mQuickAction.addActionItem(addAction);
        mQuickAction.addActionItem(accAction);
        mQuickAction.addActionItem(upAction);

        // setup the action item click listener
        mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(View anchor, int pos) {

                if (pos == 0) { // Add item selected
                    Toast.makeText(Example2Activity.this, "Add item selected on row " + mSelectedRow,
                            Toast.LENGTH_SHORT).show();
                } else if (pos == 1) { // Accept item selected
                    Toast.makeText(Example2Activity.this, "Accept item selected on row " + mSelectedRow,
                            Toast.LENGTH_SHORT).show();
                } else if (pos == 2) { // Upload item selected
                    Toast.makeText(Example2Activity.this, "Upload items selected on row " + mSelectedRow,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // setup on dismiss listener, set the icon back to normal
        mQuickAction.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mMoreIv.setImageResource(R.drawable.ic_list_more);
            }
        });

        mList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectedRow = position; // set the selected row

                mQuickAction.show(view);

                // change the right arrow icon to selected state
                mMoreIv = (ImageView) view.findViewById(R.id.i_more);
                mMoreIv.setImageResource(R.drawable.ic_list_more_selected);
            }
        });
    }
}