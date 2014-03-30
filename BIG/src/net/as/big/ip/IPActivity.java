package net.as.big.ip;

import net.as.big.base.BaseActivity;
import net.as.big.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class IPActivity extends BaseActivity implements OnClickListener{

	private Spinner seleSpinner = null;
	private EditText ipaddrEt = null;
	private Button searchBtn = null;
	private TextView ipTv = null;
	
	private ArrayAdapter<String> adapter = null;  
	private static final String[] ip={"根据IP/域名查询地址","根据地址查询IP"};
	
	private int searchType = 1;
	private final int IP2ADDR = 1, ADDR2IP = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = LayoutInflater.from(this).inflate(R.layout.activity_ipaddr, null);
		addView(view);
		setTitle("IP地址查询");
		initView();
	}
	
	public void initView(){
		seleSpinner = (Spinner) findViewById(R.id.spin_ip_select);
		//将可选内容与ArrayAdapter连接起来  
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ip);  
        //设置下拉列表的风格  
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        //将adapter 添加到spinner中  
        seleSpinner.setAdapter(adapter);
		seleSpinner.setOnItemSelectedListener(new SpinSelectedListener());
		ipaddrEt = (EditText) findViewById(R.id.et_ip_ipaddr);
		searchBtn = (Button)findViewById(R.id.btn_ip_search);
		searchBtn.setOnClickListener(this);
		ipTv = (TextView)findViewById(R.id.tv_ip_result);
	}

	@Override
	public void onClick(View v) {
		if(v == searchBtn){
			switch (searchType) {
			case IP2ADDR:
				
				break;
				
			case ADDR2IP:
				
				break;

			default:
				break;
			}
		}
	}

	class SpinSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int index,
				long arg3) {
			searchType = index+1;
			if(index == 0){
				ipaddrEt.setHint("请输入ip地址或域名");
			}else if(index == 1){
				ipaddrEt.setHint("请输入地址");
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {}
	}
	
}
