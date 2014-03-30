package net.as.big.base;

import net.as.big.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Activity{

	protected ProgressDialog progressDialog = null;
	protected Button backBtn = null;
	protected TextView titleTv = null;
	
	private LinearLayout subView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		titleTv = (TextView) findViewById(R.id.tv_title);
		backBtn = (Button) findViewById(R.id.base_btn_back);
		backBtn.setOnClickListener(new backOnClickListener());
	}

	class backOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	protected void addView(View v){
		subView = (LinearLayout) findViewById(R.id.layout_sub_activity);
		subView.addView(v);
	}
	
	@Override
	public void setTitle(CharSequence title) {
		titleTv.setText(title);
		super.setTitle(title);
	}

	 
	protected void showDialog(String s) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage(s);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

	protected void closeDialog() {
			if (progressDialog != null) {
				progressDialog.dismiss();
				progressDialog = null;
			}
		}
		
	protected void showToast(String s){
			Toast.makeText(BaseActivity.this, s, Toast.LENGTH_SHORT).show();
		}
	
}
