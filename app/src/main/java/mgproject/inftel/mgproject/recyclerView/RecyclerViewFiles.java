package mgproject.inftel.mgproject.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.model.Attatch;
import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 8/4/16.
 */
public class RecyclerViewFiles extends RecyclerView.Adapter<RecyclerViewFiles.ViewHolder> {
    private Context context;
    private ArrayList<Attatch> attatchArrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.fileName);
        }
    }
    public RecyclerViewFiles(ArrayList<Attatch> attatchArrayList,Context context){
        this.attatchArrayList = attatchArrayList;
        this.context = context;
    }
    @Override
    public RecyclerViewFiles.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow_files_layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewFiles.ViewHolder holder, int position) {
        holder.mTextView.setText(attatchArrayList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return attatchArrayList.size();
    }
}
