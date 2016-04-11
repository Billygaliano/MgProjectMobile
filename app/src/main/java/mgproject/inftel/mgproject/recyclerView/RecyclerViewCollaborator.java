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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 8/4/16.
 */
public class RecyclerViewCollaborator extends RecyclerView.Adapter<RecyclerViewCollaborator.ViewHolder> {
    private Context context;
    private ArrayList<User> collaboratorsList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public CircleImageView circleImageView;
        public RelativeLayout mBackgroudView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.collaboratorName);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.collaboratorImage);
            mBackgroudView = (RelativeLayout) itemView.findViewById(R.id.collaborator_background_Image);
        }
    }
    public RecyclerViewCollaborator(ArrayList<User> collaboratorsList,Context context){
        this.collaboratorsList = collaboratorsList;
        this.context = context;
    }

    @Override
    public RecyclerViewCollaborator.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow_collaborator_layout, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewCollaborator.ViewHolder holder, int position) {
        if(!collaboratorsList.get(position).getPhoto().equals("undefined")) {
            Picasso.with(this.context).load(collaboratorsList.get(position).getPhoto()).into(holder.circleImageView);
        }
        if(collaboratorsList.get(position).getIdGoogleUser().equals(MGApp.getmInstance().getProject().getAdminProject().getIdGoogleUser())){
            holder.mBackgroudView.setBackgroundResource(R.color.primary_light);
        }
        holder.mTextView.setText(collaboratorsList.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return collaboratorsList.size();
    }
}
