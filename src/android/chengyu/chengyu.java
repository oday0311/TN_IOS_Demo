package android.chengyu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class chengyu extends Activity {
	public static final int ABOUT_ID = Menu.FIRST;
	public static final int DELETE_ID = Menu.FIRST + 1;
	public static final int EXIT_ID = Menu.FIRST + 2;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chengyu);
        ImageButton btn = (ImageButton)findViewById(R.id.imageButton2);
        ImageButton btn2 = (ImageButton)findViewById(R.id.imageButton3);
        ImageButton btn3 = (ImageButton)findViewById(R.id.imageButton4);
        //button used to exit
        ImageButton btn4 = (ImageButton)findViewById(R.id.ImageButton5);
       
        btn.setOnClickListener(listener);
        btn2.setOnClickListener(listener2);
        btn3.setOnClickListener(listener3);
        btn4.setOnClickListener(listener4);
        
        setTitle("成语大全");
    }


    OnClickListener listener = new OnClickListener()
    {
    	public void onClick(View v) 
    	{ 
    		//setTitle("this is OK button1");
    		Intent in = new Intent(chengyu.this, gameActivity.class);
    		startActivity(in);
    	}
	};
	OnClickListener listener2 = new OnClickListener()
	{
		public void onClick(View v)
		{ 
			//setTitle("this is OK button2");
			Intent in = new Intent(chengyu.this, dictionaryActivity.class);
    		startActivity(in);
		}
	};
	OnClickListener listener3 = new OnClickListener()
	{
		public void onClick(View v) { 
			setTitle("this is OK button3"); 
		}
	};
	OnClickListener listener4 = new OnClickListener()
	{
		public void onClick(View v) { 
			finish();
		}
	};

	@Override public boolean onCreateOptionsMenu(Menu menu) {
	super.onCreateOptionsMenu(menu);
	menu.add(0, ABOUT_ID, 0, "关于");
	menu.add(0, DELETE_ID, 1, "@_@");
	menu.add(0, EXIT_ID, 2, "退出");
	return true; } 
	
	@Override public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case ABOUT_ID:
				
				LayoutInflater inflater = getLayoutInflater();
				   View layout = inflater.inflate(R.layout.aboutdialoglayout,
				     (ViewGroup) findViewById(R.id.aboutdialoglayout));
				   new AlertDialog.Builder(this).setTitle("关于..oday0311@gmail.com").setView(layout)
				     .setPositiveButton("确定", null)
				     .setNegativeButton("取消", null).show();
				break;
			case DELETE_ID: 
				setTitle("Delete...");
				break;
			case EXIT_ID: 
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
		}
}