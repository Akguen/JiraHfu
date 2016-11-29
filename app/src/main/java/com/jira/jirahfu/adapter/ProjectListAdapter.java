package com.jira.jirahfu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jira.jirahfu.R;
import com.jira.jirahfu.model.Avatar;
import com.jira.jirahfu.model.Project;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


/**
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class ProjectListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final String TAG = ProjectListAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<Project> mProjectArrayList = new ArrayList<Project>();
    private Project mProject;


    public class ViewHolder extends RecyclerView.ViewHolder{
        //private ImageView mIvProjectAvatar;
        private WebView mIvProjectAvatar;
        private TextView mTvProjectId, mTvProjectName,
                mTvProjectKey, mTvProjectType, mTvProjectLead;

        public ViewHolder(View view) {
            super(view);
            //mIvProjectAvatar = (ImageView) itemView.findViewById(R.id.iv_project_avatar);
            mIvProjectAvatar = (WebView) itemView.findViewById(R.id.iv_project_avatar);
            mTvProjectId = (TextView) itemView.findViewById(R.id.tv_project_id);
            mTvProjectName = (TextView) itemView.findViewById(R.id.tv_project_name);
            mTvProjectKey = (TextView) itemView.findViewById(R.id.tv_project_key);
            mTvProjectType = (TextView) itemView.findViewById(R.id.tv_project_type);
            mTvProjectLead = (TextView) itemView.findViewById(R.id.tv_project_lead);
        }
    }


    public ProjectListAdapter(Context context, ArrayList<Project> projectArrayList) {
        this.mContext= context;
        this.mProjectArrayList = projectArrayList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.listitem_project, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Project project = mProjectArrayList.get(position);
        ((ViewHolder) holder).mTvProjectId.setText(String.valueOf(project.getId()));
        ((ViewHolder) holder).mTvProjectName.setText(String.valueOf(project.getName()));
        ((ViewHolder) holder).mTvProjectKey.setText(String.valueOf(project.getKey()));
        ((ViewHolder) holder).mTvProjectType.setText(String.valueOf(project.getProjectTypeKey()));

        Avatar avatar = project.getAvatar();
        //new LoadImageTask(((ViewHolder) holder).mIvProjectAvatar).execute(String.valueOf(avatar.getAvatarURL1616()));
        //Bitmap bitmapp = loadBitmap(avatar.getAvatarURL1616());

        //((ViewHolder) holder).mIvProjectAvatar.setImageBitmap(loadBitmap(avatar.getAvatarURL1616()));

        ((ViewHolder) holder).mIvProjectAvatar.setWebViewClient(new Callback());
        WebSettings webSettings = ((ViewHolder) holder).mIvProjectAvatar.getSettings();
        webSettings.setBuiltInZoomControls(false);
        ((ViewHolder) holder).mIvProjectAvatar.loadUrl("http://141.28.11.26:8081/secure/projectavatar?size=xsmall&avatarId=10324");



        /////////LoadImageTask task = new LoadImageTask((((ViewHolder) holder).mIvProjectAvatar));
        /////////task.execute(new String[] {"http://141.28.11.26:8081/secure/projectavatar?size=xsmall&avatarId=10324"});

        Log.e(TAG, avatar.getAvatarURL1616());

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

        private Bitmap loadBitmap(String url) {
            // InputStream in = new java.net.URL(urldisplay).openStream();
            //image = BitmapFactory.decodeStream(in);
            Bitmap bitmap;
            try {
                InputStream in = new URL("http://141.28.11.26:8081/secure/projectavatar?size=xsmall&avatarId=10324").openStream();
                bitmap = BitmapFactory.decodeStream(in);
                return bitmap;
                // URL image_url = new URL(" http://141.28.11.26:8081/secure/projectavatar?size=xsmall&avatarId=10324");
                //bitmap = BitmapFactory.decodeStream(image_url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "e");
                return null;
                //bitmap = null;
            }
            //return bitmap;
        }
    }


        @Override
        public int getItemCount() {
            return mProjectArrayList.size();
        }

}
