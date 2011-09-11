package android.chengyu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.R.string;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.KeyEvent;
import android.widget.LinearLayout;

public class Dictionarydatamodel {
	private final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	private static final String DB_NAME = "StudDB.db";
	private static final int DB_VERSION = 2;
	private Cursor cur;
	private DatabaseHelper mOpenHelper;
	SQLiteDatabase db ;
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
	   /*"chengyu_index",  //0
		"chengyu_hanzi",          //1
		"chengyu_pingyin" ,       //2
		"chengyu_jieshi",         //3
		"chengyu_chuchu",         //4
		"chengyu_lizi"};          //5
		*/
		public String[] IndexRecorder = new String[20];
		public String[] hanziRecorder = new String[20];
		public String[] pingyingRecorder = new String[20];
		public String[] jieshiRecorder = new String[20];
		public String[] chuchuRecorder = new String[20];
		public String[] lizRecorder = new String[20];
	
	public Dictionarydatamodel() {}
	
	
	
	//load data from sqlite
	public  ArrayList<Map<String, Object>> loadData2(Context c)
	{
		ArrayList<Map<String, Object>> coll = new ArrayList<Map<String, Object>>();
		Map<String, Object> item;
		mOpenHelper = new DatabaseHelper(c);
		db = mOpenHelper.getReadableDatabase();
		Cursor result=db.rawQuery("select * from Student ORDER BY RANDOM() LIMIT 20;",null); 
		result.moveToNext();
		
		int j = 0;
		while(result!=null && j < 20)
		{
			int n = result.getCount();
			if(n != 0)
			{
				String hanzi = result.getString(1);
				item = new HashMap<String, Object>();
				item.put("prod_na", hanzi);
				item.put("prod_type", "Mobile");
				coll.add(item);
				
				IndexRecorder[j] = result.getString(0);
				hanziRecorder[j] = result.getString(1);
				pingyingRecorder[j] = result.getString(2);
				jieshiRecorder[j] = result.getString(3);
				chuchuRecorder[j] = result.getString(4);
				lizRecorder[j] = result.getString(5);
			}
			else
			{
				//setTitle("查找失败，请重新输入后再查");
			}
			result.moveToNext();
			j++;
		}
		
		result.close();

		 db.close();
		return coll;
	}
	

	//this is not used at all, just an example code
	public ArrayList<Map<String, Object>> loadData1() 
	{
	ArrayList<Map<String, Object>> coll = new ArrayList<Map<String, Object>>();
	Map<String, Object> item;
	item = new HashMap<String, Object>();
	item.put("prod_na", "Linux"); 
	item.put("prod_type", "ST");
	coll.add(item);
	item = new HashMap<String, Object>();
	item.put("prod_na", "Windows");
	item.put("prod_type", "Mobile");
	coll.add(item);
	
	for(int i = 0;i<100;i++)
	{
		item = new HashMap<String, Object>();
		item.put("prod_na", i);
		item.put("prod_type", "Mobile");
		coll.add(item);
	}
	return coll;
	}
}
