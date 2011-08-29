package net.londatiga.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Quickaction demo activity.
 * 
 * This demo shows how to use quickaction, add items, setup listener for action
 * item click and dismiss.
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 * 
 */
public class Example1Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.example1);

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
                    Toast.makeText(Example1Activity.this, "Add item selected", Toast.LENGTH_SHORT).show();
                } else if (pos == 1) { // Accept item selected
                    Toast.makeText(Example1Activity.this, "Accept item selected", Toast.LENGTH_SHORT).show();
                } else if (pos == 2) { // Upload item selected
                    Toast.makeText(Example1Activity.this, "Upload items selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btn1 = (Button) this.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickAction.show(v);
            }
        });

        Button btn2 = (Button) this.findViewById(R.id.btn2);
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuickAction.show(v);
                mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
            }
        });
    }
}