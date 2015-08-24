package com.example.geo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";

	private Button mTrueButton;
	private Button mFalseButton;
	private ImageButton mNextButton;
	private ImageButton mPrevButton;
	private TextView mTextView;
	private int mIndex = 0;

	private TrueFalse[] arr = new TrueFalse[] { new TrueFalse(R.string.q1, true), new TrueFalse(R.string.q2, true),
			new TrueFalse(R.string.q3, true), new TrueFalse(R.string.q4, false), new TrueFalse(R.string.q5, false), };

	private void frash() {
		// mTextView = (TextView)findViewById(R.id.question_text_view);
		mTextView.setText(arr[mIndex].getQuestion());
	}

	private void nextQuestion() {
		mIndex = (mIndex + 1) % arr.length;
	}

	private void prevQuestion() {
		// mIndex = (mIndex - 1) >= 0 ? (mIndex - 1) : (mIndex - 1) +
		// arr.length;
		// mIndex = (mIndex - 1) == -1 ? arr.length - 1 : mIndex - 1 ;
		mIndex--;
		if (mIndex < 0) {
			mIndex = arr.length - 1;
		}
	}

	private void check(boolean userPressed) {
		boolean answer = arr[mIndex].isTrueQuestion();
		int message;
		if (userPressed == answer) {
			message = R.string.correct_toast;
		} else {
			message = R.string.incorrect_toast;
		}
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "OnCreate(bundle) called");
		setContentView(R.layout.activity_main);

		mTextView = (TextView) findViewById(R.id.question_text_view);
		mTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				nextQuestion();
				frash();
			}
		});

		if (savedInstanceState != null) {
			mIndex = savedInstanceState.getInt(KEY_INDEX, 0);
		}
		frash();

		// true的方法
		mTrueButton = (Button) findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//
				check(true);
			}
		});

		mFalseButton = (Button) findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//
				check(false);
			}
		});

		mNextButton = (ImageButton) findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//
				nextQuestion();
				frash();
			}
		});
		mPrevButton = (ImageButton) findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//
				prevQuestion();
				frash();
			}
		});

	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		savedInstanceState.putInt(KEY_INDEX, mIndex);
	}

	// 其他生命周期

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart called");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume called");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause called");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop called");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy called");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
