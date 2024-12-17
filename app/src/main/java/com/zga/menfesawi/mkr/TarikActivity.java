package com.zga.menfesawi.mkr;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class TarikActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> mapper = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> mapp = new ArrayList<>();
	private ArrayList<String> keys = new ArrayList<>();
	
	private LinearLayout mainHolder;
	private LinearLayout actionBar;
	private LinearLayout engine;
	private TextView textview2;
	private Space textview3;
	private ImageView imageview1;
	private TextView textview1;
	private LinearLayout topics;
	private LinearLayout linear1;
	private ListView listview1;
	
	private Intent setToPager = new Intent();
	private DatabaseReference daba = _firebase.getReference("daba");
	private ChildEventListener _daba_child_listener;
	private SharedPreferences size;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tarik);
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
		textview1 = findViewById(R.id.textview1);
		topics = findViewById(R.id.topics);
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		size = getSharedPreferences("size", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog cus = new AlertDialog.Builder(TarikActivity.this).create();
				LayoutInflater cusLI = getLayoutInflater();
				View cusCV = (View) cusLI.inflate(R.layout.setting, null);
				cus.setView(cusCV);
				final LinearLayout ff = (LinearLayout)
				cusCV.findViewById(R.id.m2);
				final SeekBar fs = (SeekBar)
				cusCV.findViewById(R.id.seekbar1);
				final CheckBox fc = (CheckBox)
				cusCV.findViewById(R.id.checkbox1);
				final CheckBox fcc = (CheckBox)
				cusCV.findViewById(R.id.checkbox2);
				final TextView t1 = (TextView)
				cusCV.findViewById(R.id.t1);
				final TextView t2 = (TextView)
				cusCV.findViewById(R.id.t2);
				final TextView t3 = (TextView)
				cusCV.findViewById(R.id.t3);
				final TextView t4 = (TextView)
				cusCV.findViewById(R.id.t4);
				final ImageView ii1 = (ImageView)
				cusCV.findViewById(R.id.i1);
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ntr.ttf"), 1);
				
				t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
				
				t3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
				
				t4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
				ff.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)12, (int)1, 0xFFFFFFFF, 0xFFFFFFFF));
				ff.setElevation((float)8);
				
				fs.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
								@Override
								public void onProgressChanged(SeekBar _param1, int _param2, boolean _param3) {
										final int _progressValue = _param2;
										size.edit().putString("size", String.valueOf((long)(_progressValue))).commit();
								}
					
					@Override
								public void onStartTrackingTouch(SeekBar _param1) {
										
								}
								
								@Override
								public void onStopTrackingTouch(SeekBar _param2) {
										
								}
					
				});
				if (size.getString("size", "").equals("")) {
					
				} else {
					fs.setProgress((int)Integer.parseInt(size.getString("size", "")));
				}
				fc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
								@Override
								public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
										final boolean _isChecked = _param2;
										if (_isChecked) {
												size.edit().putString("check", "true").commit();
										}
										else {
												size.edit().putString("check", "false").commit();
										}
								}
						});
				        
				        fcc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
								@Override
								public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
										final boolean _isChecked = _param2;
										if (_isChecked) {
												size.edit().putString("checkf", "true").commit();
										}
										else {
												size.edit().putString("checkf", "false").commit();
										}
								}
						});
				if (size.getString("check", "").equals("")) {
					
				} else {
					if (size.getString("check", "").equals("true")) {
						fc.setChecked(true);
					} else {
						fc.setChecked(false);
					}
				}
				if (size.getString("checkf", "").equals("")) {
					
				} else {
					if (size.getString("checkf", "").equals("true")) {
						fcc.setChecked(true);
					} else {
						fcc.setChecked(false);
					}
				}
				ii1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						cus.dismiss();
						final AlertDialog ab = new AlertDialog.Builder(TarikActivity.this).create();
						LayoutInflater abLI = getLayoutInflater();
						View abCV = (View) abLI.inflate(R.layout.about, null);
						ab.setView(abCV);
						final TextView h = (TextView)
						abCV.findViewById(R.id.head);
						final TextView aa = (TextView)
						abCV.findViewById(R.id.about);
						final LinearLayout sh = (LinearLayout)
						abCV.findViewById(R.id.linear4);
						sh.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)12, 0xFFFFFFFF));
						h.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ntr.ttf"), 1);
						
						aa.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
						sh.setElevation((float)12);
						ab.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
						ab.show();
					}
				});
				cus.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				cus.show();
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				setToPager.setClass(getApplicationContext(), PagerActivity.class);
				setToPager.putExtra("title", mapp.get((int)_position).get("title").toString());
				setToPager.putExtra("content", mapp.get((int)_position).get("content").toString());
				startActivity(setToPager);
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				final AlertDialog cd = new AlertDialog.Builder(TarikActivity.this).create();
				LayoutInflater cdLI = getLayoutInflater();
				View cdCV = (View) cdLI.inflate(R.layout.editor, null);
				cd.setView(cdCV);
				final LinearLayout a = (LinearLayout)
				cdCV.findViewById(R.id.titleBar);
				final LinearLayout b = (LinearLayout)
				cdCV.findViewById(R.id.editBar);
				final LinearLayout c = (LinearLayout)
				cdCV.findViewById(R.id.buttonBar);
				final Button b1 = (Button)
				cdCV.findViewById(R.id.button1);
				final Button b2 = (Button)
				cdCV.findViewById(R.id.button2);
				final EditText e1 = (EditText)
				cdCV.findViewById(R.id.edittext1);
				final EditText e2 = (EditText)
				cdCV.findViewById(R.id.edittext2);
				b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
				
				b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
				
				e1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
				
				e2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
				a.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)12, (int)1, 0xFFFFFFFF, 0xFFFFFFFF));
				a.setElevation((float)8);
				b.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)12, (int)1, 0xFFFFFFFF, 0xFFFFFFFF));
				b.setElevation((float)8);
				c.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)12, (int)1, 0xFFFFFFFF, 0xFFFFFFFF));
				c.setElevation((float)8);
				e1.setText(mapp.get((int)_position).get("title").toString());
				e2.setText(mapp.get((int)_position).get("content").toString());
				b1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						cd.dismiss();
						getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
					}
				});
				b2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						mapper = new HashMap<>();
						mapper.put("title", e1.getText().toString());
						mapper.put("content", e2.getText().toString());
						daba.child(keys.get((int)(_position))).updateChildren(mapper);
						mapper.clear();
						cd.dismiss();
						getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
					}
				});
				e1.setEnabled(true);
				e1.setFocusable(true);
				e1.setClickable(true);
				
				e2.setEnabled(true);
				e2.setFocusable(true);
				e2.setClickable(true);
				cd.setCancelable(false);
				cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				return true;
			}
		});
		
		_daba_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				daba.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						mapp = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								mapp.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						keys.add(_childKey);
						listview1.setAdapter(new Listview1Adapter(mapp));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				daba.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						mapp = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								mapp.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(mapp));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				daba.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						mapp = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								mapp.add(_map);
							}
						} catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(mapp));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		daba.addChildEventListener(_daba_child_listener);
	}
	
	private void initializeLogic() {
		actionBar.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)2, 0xFF001D3F, 0xFFFFFFFF));
		engine.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)12, (int)1, 0xFFFFFFFF, 0xFFFFFFFF));
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ntr.ttf"), 1);
		
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ntr.ttf"), 1);
		listview1.setHorizontalScrollBarEnabled(false);
		listview1.setVerticalScrollBarEnabled(false);
		listview1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		actionBar.setElevation((float)5);
		engine.setElevation((float)15);
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.myview, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout topicJ = _view.findViewById(R.id.topicJ);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
			
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/tay.ttf"), 1);
			textview1.setText(_data.get((int)_position).get("title").toString());
			int maxLength = 88;
			String databaseText = _data.get((int)_position).get("content").toString();
			
			if (databaseText.length() > maxLength) {
				    String trimmedText = databaseText.substring(0, maxLength) + "...";
				    textview2.setText(trimmedText);
			} else {
				    textview2.setText(databaseText);
			}
			topicJ.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)1, 0xFF9E9E9E, 0xFFFFFFFF));
			
			return _view;
		}
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