package net.as.big.phone;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PhoneActivity extends BaseActivity implements OnClickListener{
	
	private EditText phoneEdt = null;
	private Button searchBtn = null;
	private TextView resultTv = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view=LayoutInflater.from(this).inflate(R.layout.activity_phone, null);
		//将子类布局加入父类
		addView(view);
		setTitle("手机号码归属地查询");
		inintView();
	}

	@Override
	public void onClick(View v) {
		if(v == searchBtn){
			String number = phoneEdt.getText().toString().trim();
			if(number.length() == 11){
				new ReadHttpGet().execute("http://apis.juhe.cn/mobile/get?phone="+number+"&key=8de9dc6bb1d76976c1de70747061c466");
			}else{
				showToast("请正确输入号码");
			}
		}
	}
	
	private void inintView(){
		phoneEdt = (EditText)findViewById(R.id.et_phone_no);
		searchBtn = (Button)findViewById(R.id.btn_phone_search);
		searchBtn.setOnClickListener(this);
		resultTv = (TextView)findViewById(R.id.tv_phone_result);
	}
	//
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
				JSONObject jsonObject = new JSONObject(jsonResult);
				JSONObject qResult = jsonObject.getJSONObject("result");
				resultTv.setText(qResult.getString("province")+qResult.getString("city")+"\n"
						+"区号："+qResult.getString("areacode")+"\n"
						+qResult.getString("company")+qResult.getString("card"));
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
