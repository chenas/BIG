package net.as.big;

import net.as.big.ip.IPActivity;
import net.as.big.phone.PhoneActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener{

	private ImageButton barcodeScanBtn = null;
	private ImageButton phoneAreaBtn = null;
	private ImageButton ipaddrBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
	}

	@Override
	public void onClick(View v) {
		if(v == barcodeScanBtn){
			
		}else if(v == phoneAreaBtn){
			Intent phoneActivity = new Intent(MainActivity.this, PhoneActivity.class);
			startActivity(phoneActivity);
		}else if(v == ipaddrBtn){
			Intent ipaddrActivity = new Intent(MainActivity.this, IPActivity.class);
			startActivity(ipaddrActivity);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
			MainActivity.this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void initView(){
		barcodeScanBtn = (ImageButton) findViewById(R.id.btn_barcode_scan);
		barcodeScanBtn.setOnClickListener(this);
		phoneAreaBtn = (ImageButton)findViewById(R.id.btn_phone_area);
		phoneAreaBtn.setOnClickListener(this);
		ipaddrBtn = (ImageButton) findViewById(R.id.btn_ip_addr);
		ipaddrBtn.setOnClickListener(this);
	}

}
