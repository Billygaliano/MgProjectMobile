package mgproject.inftel.mgproject.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.model.Task;
import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 10/4/16.
 */
public class RecyclerViewTask extends RecyclerView.Adapter<RecyclerViewTask.ViewHolder>{
    private Context context;
    private ArrayList<Task> taskArrayList;

    public RecyclerViewTask(ArrayList<Task> taskArrayList,Context context){
        this.taskArrayList = taskArrayList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public TextView mTextView2;
        public ImageView mimageView;
        public RelativeLayout mBackground;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.taskName);
            mTextView2 = (TextView) itemView.findViewById(R.id.timeTask);
            mimageView = (ImageView) itemView.findViewById(R.id.imageTask );
            mBackground = (RelativeLayout) itemView.findViewById(R.id.taskBackgroud);

        }
    }
    @Override
    public RecyclerViewTask.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow_task_layout, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewTask.ViewHolder holder, int position) {

        if(taskArrayList.get(position).getPriority().equals("acuciante")){
            holder.mBackground.setBackgroundResource(R.color.acuciante);
        }
        if(taskArrayList.get(position).getPriority().equals("repentino")){
            holder.mBackground.setBackgroundResource(R.color.repentino);
        }
        if(taskArrayList.get(position).getPriority().equals("plani")){
            holder.mBackground.setBackgroundResource(R.color.planificado);
        }
        if(taskArrayList.get(position).getPriority().equals("accesorio")){
            holder.mBackground.setBackgroundResource(R.color.accesorio);
        }
        holder.mTextView.setText(taskArrayList.get(position).getNameTask());
        holder.mTextView2.setText(taskArrayList.get(position).getTime() +" "+ taskArrayList.get(position).getTimeType());

    }
    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
}
