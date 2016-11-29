package com.jira.jirahfu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jira.jirahfu.R;
import com.jira.jirahfu.core.ApiEndpoint;
import com.jira.jirahfu.core.ApiKeyConfig;
import com.jira.jirahfu.core.AppController;
import com.jira.jirahfu.core.PreferenceManager;
import com.jira.jirahfu.model.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private PreferenceManager mPreferenceManager;
    private Intent mIntentMainActivity;

    private EditText mEtUserName;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private TextView mTvTest; //////// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private String mUserName;
    private String mPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        getLayoutInflater().inflate(R.layout.activity_login, mFrameLayoutBase);

        
        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin =(Button) findViewById(R.id.btn_login);
        mTvTest = (TextView) findViewById(R.id.tv_test); //////// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        mBtnLogin.setOnClickListener(this);

        mPreferenceManager = new PreferenceManager(getApplicationContext());
        if(mPreferenceManager.isLoggedIn()) {
            mIntentMainActivity = new Intent(this, MainActivity.class);
            startActivity(mIntentMainActivity);
            finish();
        }
    }


    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        if(id== R.id.btn_login){
            checkEditTextContent();
        }
    }


    private void checkEditTextContent(){
        mUserName = mEtUserName.getText().toString();
        mPassword = mEtPassword.getText().toString();
        if (mUserName.trim().length() > 0 && mPassword.trim().length() > 0) {
            //mPgLogInLoad.setVisibility(View.VISIBLE);
            checkLogin(mUserName, mPassword);
        }
    }



    private void checkLogin(final String userName, final String password) {

        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put(ApiKeyConfig.KEY_USERNAME, userName);
        mapParams.put(ApiKeyConfig.KEY_PASSWORD, password);

        JSONObject params = new JSONObject(mapParams);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,
                ApiEndpoint.URL_AUTH, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONObject mJSONObjectSession = jsonObject.getJSONObject(ApiKeyConfig.KEY_SESSION_OBJECT);
                    String mSessionName = mJSONObjectSession.getString(ApiKeyConfig.KEY_SESSION_NAME);
                    String mSessionValue = mJSONObjectSession.getString(ApiKeyConfig.KEY_SESSION_VALUE);
                    Session mSession = new Session(mSessionName, mSessionValue);

                    mPreferenceManager.setLoginPreference(true, mSession);
                    mTvTest.setText("Response: " + jsonObject + " Test: " + mSessionName); //////// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                    mIntentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mIntentMainActivity);
                    finish();
                }catch (JSONException e){}
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                 mTvTest.setText(error.toString()); //////// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }

}
