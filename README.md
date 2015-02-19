NewQuickAction
==============

NewQuickAction is a small android library to create QuickAction dialogs.

Installation
===============

### Android Studio

 1. Paste or clone this library into the `/libs` folder, in the root directory of your project. Create a new folder: `/libs` if not already present. (This step is not required - only for keeping cleaner project structure)

 2. Edit `settings.gradle` by adding the library. You have also define a project directory for the library. Your `settings.gradle` should look like below:
	```
	include ':app', ':NewQuickAction'
	project(':NewQuickAction').projectDir = new File('libs/NewQuickAction')
	```
 3. In `app/build.gradle` add the NewQuickAction library as a dependency:
	```
	dependencies {
	    compile fileTree(dir: 'libs', include: ['*.jar'])
	    compile 'com.android.support:appcompat-v7:21.0.3'
	    compile project(":NewQuickAction")
	}
	```

 4. Sync project, clean and build. You can use the NewQuickAction as part of your project now.

### Eclipse

 1. Before you can add a NewQuickAction to your application, you must first add a library reference:
 2. Clone or download a copy of the library
 3. Import the library into Eclipse: File menu -> Import -> Existing Project into Workspace
 4. Open your application's project properties and add a library reference to `NewQuickAction`


How to Use
==========
This repo includes a sample Activity (__ExampleActivity1.java__) to show how to use QuickAction.

	public class Example1Activity extends Activity {
		private static final int ID_ADD = 1;
		private static final int ID_ACCEPT = 2;
		private static final int ID_UPLOAD = 3;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		
			setContentView(R.layout.example1);
		
			ActionItem addItem 		= new ActionItem(ID_ADD, "Add", getResources().getDrawable(R.drawable.ic_add));
			ActionItem acceptItem 	= new ActionItem(ID_ACCEPT, "Accept", getResources().getDrawable(R.drawable.ic_accept));
        	ActionItem uploadItem 	= new ActionItem(ID_UPLOAD, "Upload", getResources().getDrawable(R.drawable.ic_up));
       
        	//use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
        	uploadItem.setSticky(true);
        
			final QuickAction mQuickAction 	= new QuickAction(this);
		
			mQuickAction.addActionItem(addItem);
			mQuickAction.addActionItem(acceptItem);
			mQuickAction.addActionItem(uploadItem);
		
			//setup the action item click listener
			mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				@Override
				public void onItemClick(QuickAction quickAction, int pos, int actionId) {
					ActionItem actionItem = quickAction.getActionItem(pos);
				
					if (actionId == ID_ADD) {
						Toast.makeText(getApplicationContext(), "Add item selected", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
					}
				}
			});
		
			mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
				@Override
				public void onDismiss() {
					Toast.makeText(getApplicationContext(), "Ups..dismissed", Toast.LENGTH_SHORT).show();
				}
			});
		
			Button btn1 = (Button) this.findViewById(R.id.btn1);
			btn1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mQuickAction.show(v);
				}
			})

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

**See http://www.londatiga.net/it/how-to-create-quickaction-dialog-in-android/ for more information.**

![Example Image](http://londatiga.net/images/quickaction1.png)  ![Example Image](http://londatiga.net/images/quickaction2.png) 

Developed By
============

* Lorensius W. L. T - <lorenz@londatiga.net>

Contributors
============

* Kevin Peck - <kevinwpeck@gmail.com>

Changes
=======

See [CHANGELOG](https://github.com/lorensiuswlt/NewQuickAction/blob/master/CHANGELOG.md) for details

License
=======

    Copyright 2011 Lorensius W. L. T

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
