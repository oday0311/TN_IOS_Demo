package android.chengyu;

import java.util.ArrayList;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.GestureDetector;
public class dictionaryActivity extends ListActivity {
	private Dictionarydatamodel dm;
	private ArrayList<Map<String, Object>> coll;
//	private ViewFlipper flipper;//ViewFlipper实例  
//	private GestureDetector detector;//触摸监听实例  

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setTitle("成语浏览");
		dm = new Dictionarydatamodel();

		Context localContext = getApplicationContext();
		coll = dm.loadData2(localContext);
		this.setListAdapter(new SimpleAdapter(this, coll,
				android.R.layout.simple_list_item_1,
				new String[] { "prod_na" }, new int[] { android.R.id.text1 }));
		
		//add flipper effetive
		
		/*implements OnGestureListener
		detector = new GestureDetector(this);//初始化触摸探测  
		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);//获得ViewFlipper实例  
		   
		flipper.addView(addTextView("step 1"));//将View添加到flipper队列中  
		flipper.addView(addTextView("step 2"));  
		flipper.addView(addTextView("step 3"));  
		flipper.addView(addTextView("step 4"));  
		flipper.addView(addTextView("step 5"));  */

	}
	 
//    public boolean onTouchEvent(MotionEvent event)
//	{  
//        return this.detector.onTouchEvent(event);  
//    }  
	//@Override  
//	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
//	        float velocityY) {  
//	    if (e1.getX() - e2.getX() > 120) {//如果是从右向左滑动  
//	                    //注册flipper的进出效果  
//	        this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.left_in));  
//	        this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.left_out));  
//	        this.flipper.showNext();  
//	        return true;  
//	    } else if (e1.getX() - e2.getX() < -120) {//如果是从左向右滑动  
//	        this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.right_in));  
//	        this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.right_out));  
//	        this.flipper.showPrevious();  
//	        return true;  
//	    }  
//	    return false;  
//	}   
//	private View addTextView(String text) 
//	{  
//        TextView tv = new TextView(this);  
//        tv.setText(text);  
//        tv.setGravity(1);  
//        return tv;  
//    }  
//	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// setTitleColor(Color.RED);
		// setTitle(coll.get(position).get("prod_type").toString());
		// todo : pop up a dialog to list the detail information of the words;

		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.sqliteresultshowlayout,
				(ViewGroup) findViewById(R.id.sqliteresultshowlayout));
		new AlertDialog.Builder(this).setTitle(dm.hanziRecorder[position])
				.setView(layout).setPositiveButton("确定", null)
				.setNegativeButton("取消", null).show();

		//layout.
		TextView tv1 = (TextView) layout.findViewById(R.id.textView1);
		tv1.setTextColor(Color.BLACK);
		tv1.setText(dm.hanziRecorder[position] + ": "
				+ dm.pingyingRecorder[position]);

		TextView tvformat1 = (TextView) layout.findViewById(R.id.textView2);
		tvformat1.setTextColor(Color.BLACK);
		tvformat1.setText("~~~~~~~~~~~~~~~~~~~~~~~");
		
		TextView tv2 = (TextView) layout.findViewById(R.id.textView3);
		tv2.setTextColor(Color.BLACK);
		tv2.setText("解释：" + dm.jieshiRecorder[position]);

		TextView tvformat2 = (TextView) layout.findViewById(R.id.textView4);
		tvformat2.setTextColor(Color.BLACK);
		tvformat2.setText("~~~~~~~~~~~~~~~~~~~~~~~");
		
		TextView tv3 = (TextView) layout.findViewById(R.id.textView5);
		tv3.setTextColor(Color.BLACK);
		tv3.setText("出处：" + dm.chuchuRecorder[position]);

		TextView tvformat3 = (TextView) layout.findViewById(R.id.textView6);
		tvformat3.setTextColor(Color.BLACK);
		tvformat3.setText("~~~~~~~~~~~~~~~~~~~~~~~");
		
		TextView tv4 = (TextView) layout.findViewById(R.id.textView7);
		tv4.setTextColor(Color.BLACK);
		tv4.setText("例子：" + dm.lizRecorder[position]);

	}

	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// if (keyCode == KeyEvent.KEYCODE_BACK) {
	// /* 退出时，不要忘记关闭 */
	// dm.db.close();
	// this.finish();
	// return true;
	// }
	// return super.onKeyDown(keyCode, event);
	// }
	
	
//	@Override
//	public boolean onDown(MotionEvent e) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	@Override
//	public void onLongPress(MotionEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
//			float distanceY) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void onShowPress(MotionEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public boolean onSingleTapUp(MotionEvent e) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}
