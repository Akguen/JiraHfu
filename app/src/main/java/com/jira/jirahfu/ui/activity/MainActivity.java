package com.jira.jirahfu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jira.jirahfu.R;
import com.jira.jirahfu.adapter.ProjectListAdapter;
import com.jira.jirahfu.core.ApiEndpoint;
import com.jira.jirahfu.core.ApiKeyConfig;
import com.jira.jirahfu.core.AppController;
import com.jira.jirahfu.core.PreferenceManager;
import com.jira.jirahfu.model.Avatar;
import com.jira.jirahfu.model.Project;
import com.jira.jirahfu.model.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class MainActivity extends RegisteredAreaBaseActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    //private TextView mTvTest;
    private RecyclerView mRvProjectList;

    private Intent mIntentLoginActivity;
    private Session mSession;
    private String mCookie;

    private ProjectListAdapter mProjectListAdapter;

    private ArrayList<Project> mProjectList = new ArrayList<Project>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, mFrameLayout);

        //mTvTest = (TextView) findViewById(R.id.tv_test_project);
        mRvProjectList = (RecyclerView) findViewById(R.id.rv_project_list);

        mPreferenceManager = new PreferenceManager(getApplicationContext());
        if(!mPreferenceManager.isLoggedIn()) {
            mIntentLoginActivity = new Intent(this, LoginActivity.class);
            startActivity(mIntentLoginActivity);
            finish();
        }

        getProjects();

        mProjectListAdapter = new ProjectListAdapter(this, mProjectList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvProjectList.setLayoutManager(layoutManager);
        mRvProjectList.setItemAnimator(new DefaultItemAnimator());
        mRvProjectList.setAdapter(mProjectListAdapter);
    }

    private void getProjects(){
        mSession = mPreferenceManager.getSession();
        mCookie = mSession.getName() + "=" + mSession.getValue();

        JsonArrayRequest jsObjRequest = new JsonArrayRequest(
                ApiEndpoint.URL_PROJECT, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try{
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject dd = jsonArray.getJSONObject(i);

                        String mProjectId = dd.getString(ApiKeyConfig.KEY_PROJECT_ID);
                        String mProjectName = dd.getString(ApiKeyConfig.KEY_PROJECT_NAME);
                        String mProjectKey = dd.getString(ApiKeyConfig.KEY_PROJECT_KEY);
                        String mProjectType = dd.getString(ApiKeyConfig.KEY_PROJECT_TYPE_KEY);
                       // String mProjectLead = dd.getString(ApiKeyConfig.KEY_PROJECT_LEAD);

                        JSONObject mAvatarURLS = (JSONObject) dd.getJSONObject(ApiKeyConfig.KEY_PROJECT_AVATAR_OBJECT);
                        String  mAvatarURL4848 = mAvatarURLS.getString(ApiKeyConfig.KEY_PROJECT_AVATAR_URL_4848);
                        String mAvatarURL3232 = mAvatarURLS.getString(ApiKeyConfig.KEY_PROJECT_AVATAR_URL_3232);
                        String mAvatarURL2424 = mAvatarURLS.getString(ApiKeyConfig.KEY_PROJECT_AVATAR_URL_2424);
                        String mAvatarURL1616 = mAvatarURLS.getString(ApiKeyConfig.KEY_PROJECT_AVATAR_URL_1616);

                        Avatar avatar = new Avatar(mAvatarURL4848, mAvatarURL3232, mAvatarURL2424, mAvatarURL1616);
                        Project project = new Project(mProjectId, mProjectName, mProjectKey, mProjectType, avatar);

                        mProjectList.add(project);
                    }
                    mProjectListAdapter.notifyDataSetChanged();
                    if (mProjectListAdapter.getItemCount() > 1) {
                        mRvProjectList.getLayoutManager().smoothScrollToPosition(mRvProjectList, null, mProjectListAdapter.getItemCount() - 1);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTvTest.setText(error.toString()); //////// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put(ApiKeyConfig.KEY_COOKIE, mCookie);
                Log.e(TAG, "Request: " + headers.toString());
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }
}
