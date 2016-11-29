package com.jira.jirahfu.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.jira.jirahfu.model.Session;


/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class PreferenceManager
{
	//***********************************************************************//
	//                              DECLARATION
	//***********************************************************************//

	private static final String TAG = PreferenceManager.class.getSimpleName();
	private static final String SHARED_PREFERENCES_NAME = "jiraAuth";
	private static final int SHARED_PREFERENCES_MODE = 0;
	private static final String LOGIN_STATE = "userLoginState";

	private Context mContext;
	private SharedPreferences mSharedPreferences;
	private Editor mEditor;
	private Session mSession;

	private String mSessionName;
	private String mSessionValue;



	//--------------------------- DECLARATION END ---------------------------//
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX





	//***********************************************************************//
	//                             CONSTRUCTOR
	//***********************************************************************//

	public PreferenceManager(Context context) {
		this.mContext = context;
		mSharedPreferences = mContext.getSharedPreferences(
				SHARED_PREFERENCES_NAME, SHARED_PREFERENCES_MODE);
		mEditor = mSharedPreferences.edit();
	}

	//-------------------------- CONSTRUCTOR END -----------------------------//
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX





	//*************************************************************************
	//                         1 setLogin()-METHOD
	//*************************************************************************

	public void setLoginPreference(boolean loginState, Session session) {

		mEditor.putBoolean(LOGIN_STATE, loginState);
		mEditor.putString(ApiKeyConfig.KEY_SESSION_NAME, session.getName());
		mEditor.putString(ApiKeyConfig.KEY_SESSION_VALUE, session.getValue());
		mEditor.commit();

		Log.e(TAG, "Session " + session.getName() +
				" with Value: "+ session.getValue() +
				" is stored in shared preferences.");
	}

	//------------------------------ METHOD END -----------------------------//
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
	//*************************************************************************
	//                         1 setLogin()-METHOD
	//*************************************************************************

	public void setSession(Session session) {
		mEditor.putString(ApiKeyConfig.KEY_SESSION_NAME, session.getName());
		mEditor.putString(ApiKeyConfig.KEY_SESSION_VALUE, session.getValue());
		mEditor.commit();

		Log.e(TAG, "Session " + session.getName() +
				" with Value: "+ session.getValue() +
				" is stored in shared preferences.");
	}

	//------------------------------ METHOD END -----------------------------//
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
*/


	//*************************************************************************
	//                         2 isLoogedIn()-METHOD
	//*************************************************************************

	public boolean isLoggedIn(){
		return mSharedPreferences.getBoolean(LOGIN_STATE, false);
	}
	//------------------------------ METHOD END -----------------------------//
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


	//*************************************************************************
	//                         2 isLoogedIn()-METHOD
	//*************************************************************************

	public Session getSession(){
		if(isLoggedIn())
		{
			mSessionName = mSharedPreferences.getString(ApiKeyConfig.KEY_SESSION_NAME, null);
			mSessionValue = mSharedPreferences.getString(ApiKeyConfig.KEY_SESSION_VALUE, null);

			mSession = new Session(mSessionName, mSessionValue);
			return mSession;
		}
		return null;
	}
	//------------------------------ METHOD END -----------------------------//
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX




	public void clear() {
		mEditor.clear();
		mEditor.commit();
	}

}
