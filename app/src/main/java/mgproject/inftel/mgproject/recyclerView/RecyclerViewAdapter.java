package mgproject.inftel.mgproject.recyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.model.Project;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Project> projectList;
    private ArrayList<Project> projectCollaborator;
    private String url;
    private Context context;
    private MGApp mMGappInstance;
    Drawable image1;
    Drawable image2;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;
        public RelativeLayout mImageBackground;
        public ImageView mImageView;
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.projectName);
            mImageBackground = (RelativeLayout) v.findViewById(R.id.backgroundImage);
            mImageView = (ImageView) v.findViewById(R.id.imageProject);
        }

    }

    public RecyclerViewAdapter(ArrayList<Project> projectList, Context context) {
        this.projectList = projectList;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(projectList.get(position).getNameProject());

        if(projectList.get(position).getAdminProject().getIdGoogleUser().equals(MGApp.getmInstance().getUser().getIdGoogleUser())){
            holder.mImageView.setImageResource(R.drawable.ic_person_black_24dp);

        }else{
        }


    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }
}
