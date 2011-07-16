package net.londatiga.android;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.widget.Button;

import android.view.View;

/**
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 *
 */
public class QuickActionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		Button example1Btn	= (Button) findViewById(R.id.btn1);
		Button example2Btn	= (Button) findViewById(R.id.btn2);
		
		example1Btn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Example1Activity.class));
			}
		});
		
		example2Btn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Example2Activity.class));
			}
		});
	}
}