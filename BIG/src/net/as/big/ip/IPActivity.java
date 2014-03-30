package net.as.big.ip;

import java.io.IOException;

import net.as.big.R;
import net.as.big.base.BaseActivity;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
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
	
	private JSONObject jsonObject = null;
	
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
				String ipaddr = ipaddrEt.getText().toString().trim();
				new ReadHttpGet().execute("http://apis.juhe.cn/ip/ip2addr?ip="+ipaddr+"&key=ea84726df1d952b8271e118eca51be34");
				if(jsonObject != null){
					
				}
				break;
				
			case ADDR2IP:
				String addr2ip = ipaddrEt.getText().toString().trim();
				new ReadHttpGet().execute("http://apis.juhe.cn/ip/addr2ip?addr="+addr2ip+"&key=ea84726df1d952b8271e118eca51be34");
				if(jsonObject != null){
				}
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

	 class ReadHttpGet extends AsyncTask<Object, Object, Object>
	    {
	      @Override
	      protected Object doInBackground(Object... params) {
	         HttpGet httpRequest = new HttpGet(params[0].toString());
	         try
	         {
	            HttpClient httpClient = new DefaultHttpClient();
	            HttpResponse httpResponse = httpClient.execute(httpRequest);
	            if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
	            {
	                String strResult = EntityUtils.toString(httpResponse.getEntity());
	                return strResult;
	            }
	            else
	            {
	                return "请求出错";
	            }
	         }
	         catch(ClientProtocolException e)
	         {
	         }catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         closeDialog();
	         return null;
	      }

	      @SuppressLint("NewApi")
	      @Override
	      protected void onCancelled(Object result) {
	         super.onCancelled(result);
	      }

	      @Override
	      protected void onPostExecute(Object result) {
	         super.onPostExecute(result);
	         String jsonResult = result.toString();
	         try {
				jsonObject = new JSONObject(jsonResult);
				ipTv.setText(jsonObject.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
	         closeDialog();
	      }

	      @Override
	      protected void onPreExecute() {
	         showDialog("正在查询，请稍后。。。");;

	      }
	      @Override
	      protected void onProgressUpdate(Object... values) {
	         super.onProgressUpdate(values);
	      }
	    }
	
}
