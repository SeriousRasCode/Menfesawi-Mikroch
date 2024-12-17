package com.zga.menfesawi.mkr;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class PagerActivity extends AppCompatActivity {
	
	private LinearLayout mainHolder;
	private LinearLayout actionBar;
	private LinearLayout engine;
	private TextView textview2;
	private Space textview3;
	private ImageView imageview1;
	private LinearLayout topics;
	private ScrollView vscroll1;
	private TextView content;
	
	private SharedPreferences size;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.pager);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		mainHolder = findViewById(R.id.mainHolder);
		actionBar = findViewById(R.id.actionBar);
		engine = findViewById(R.id.engine);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);
		imageview1 = findViewById(R.id.imageview1);
		topics = findViewById(R.id.topics);
		vscroll1 = findViewById(R.id.vscroll1);
		content = findViewById(R.id.content);
		size = getSharedPreferences("size", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		actionBar.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)2, 0xFF001D3F, 0xFFFFFFFF));
		engine.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)12, (int)1, 0xFFFFFFFF, 0xFFFFFFFF));
		textview2.setText(getIntent().getStringExtra("title"));
		content.setText(getIntent().getStringExtra("content"));
		if (size.getString("size", "").equals("")) {
			
		} else {
			content.setTextSize((int)Integer.parseInt(size.getString("size", "")));
		}
		if (size.getString("check", "").equals("")) {
			
		} else {
			if (size.getString("check", "").equals("true")) {
				getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			} else {
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			}
		}
		if (size.getString("checkf", "").equals("")) {
			
		} else {
			if (size.getString("checkf", "").equals("true")) {
				
			} else {
				
			}
		}
		content.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ntr.ttf"), 1);
		
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
		vscroll1.setHorizontalScrollBarEnabled(false);
		vscroll1.setVerticalScrollBarEnabled(false);
		vscroll1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		actionBar.setElevation((float)5);
		engine.setElevation((float)15);
		content.setTextIsSelectable(true);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}