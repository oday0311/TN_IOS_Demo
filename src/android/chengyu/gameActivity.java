package android.chengyu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class gameActivity extends Activity {
	
	private final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	private static final String DB_NAME = "StudDB.db";
	private static final int DB_VERSION = 2;
	private Cursor cur;
	
	private  EditText text;
	private ListView lvContentFromSqlite;
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}
	private DatabaseHelper mOpenHelper;
	SQLiteDatabase db ;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        text = (EditText)findViewById(R.id.editText1);
        text.setText("");
        Context localContext = getApplicationContext();
        mOpenHelper = new DatabaseHelper(localContext);
		db = mOpenHelper.getReadableDatabase();
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(listener);
        setTitle("成语查询");
    }

    OnClickListener listener = new OnClickListener()
    {
    	public void onClick(View v) 
    	{ 
    		
			String col[] = {"chengyu_index",  //0
					"chengyu_hanzi",          //1
					"chengyu_pingyin" ,       //2
					"chengyu_jieshi",         //3
					"chengyu_chuchu",         //4
					"chengyu_lizi"};          //5
			String selectString  = "chengyu_hanzi=?";
			String userInput = text.getText().toString();
			String  selectArg[] = {"chengyu_hanzi",userInput};
			//cur = db.query("Student", col, selectString, selectArg, null, null, null);
			//cur = db.query("Student", col, null, null, null, null, null);
			cur =db.rawQuery("select chengyu_index,chengyu_hanzi,chengyu_pingyin," +
					"chengyu_jieshi,chengyu_chuchu,chengyu_lizi" +
					" from Student where chengyu_hanzi=?",new String[]{userInput}); 
			cur.moveToNext();
			//int n = cur.getCount();
			if(cur!=null)
			{
				int n = cur.getCount();
				if(n != 0)
				{
				String hanzi = cur.getString(1);
				setTitle(hanzi);
				}
				else
				{
					setTitle("查找失败，请重新输入后再查");
				}
			}
			//setTitle( n+ " records");
			
			//set the content search from database
			lvContentFromSqlite = (ListView)findViewById(R.id.listView1);
			
			ArrayList<Map<String, Object>> contents  = new ArrayList<Map<String, Object>>();;
//			for (int i = 0; i < 10; i++) {
//				Map<String, Object> map = new HashMap<String, Object>();
//				//map.put("TITLE", "Test Title");
//				map.put("CONTENT", "Test Content");
//				contents.add(map);
//				}
			String hanziString = cur.getString(1);
			String pingyinString =  cur.getString(2);
			String jieshiString = cur.getString(3);
			String chuchuString = cur.getString(4);
			String liziString = cur.getString(5);
			
			Map<String, Object> map1 = new HashMap<String, Object>();
			//map.put("TITLE", "Test Title");
			map1.put("CONTENT", hanziString+":"+ pingyinString);
			contents.add(map1);
			
			Map<String, Object> map2 = new HashMap<String, Object>();
			//map.put("TITLE", "Test Title");
			map2.put("CONTENT", "解释"+":"+ jieshiString);
			contents.add(map2);
			
			Map<String, Object> map3 = new HashMap<String, Object>();
			//map.put("TITLE", "Test Title");
			map3.put("CONTENT", "出处"+":"+ chuchuString);
			contents.add(map3);
			
			Map<String, Object> map4 = new HashMap<String, Object>();
			//map.put("TITLE", "Test Title");
			map4.put("CONTENT", "例子"+":"+ liziString);
			contents.add(map4);
			Context localContext = getApplicationContext();
			SimpleAdapter adapter = new SimpleAdapter(
					localContext,(ArrayList<Map<String, Object>>)contents,
					android.R.layout.simple_list_item_1,
					new String[] {"CONTENT" }, 
					new int[] {android.R.id.text1 //?? what is this for? for click and identify the 
					 }
					);
			lvContentFromSqlite.setAdapter(adapter);
			cur.close();
    	}
	};
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
		  if (keyCode == KeyEvent.KEYCODE_BACK) {
		   /* 退出时，不要忘记关闭 */
			  db.close();
		   this.finish();
		   return true;
		  }
		  return super.onKeyDown(keyCode, event);
		 }
}