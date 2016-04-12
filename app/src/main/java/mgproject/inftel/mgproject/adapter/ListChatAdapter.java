package mgproject.inftel.mgproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import mgproject.inftel.mgproject.R;

import mgproject.inftel.mgproject.model.Message;

/**
 * Created by inftel23 on 11/4/16.
 */
public class ListChatAdapter extends BaseAdapter{

    private Context context;
    LayoutInflater inflater;
    private ArrayList<Message> listMessages;

    public ListChatAdapter(Context context, ArrayList<Message> listMessages) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listMessages = listMessages;
    }

    @Override
    public int getCount() {
        //System.out.println("Cuantos mensajes: " + listMessages.size());
        return listMessages.size();
    }

    @Override
    public Object getItem(int position) {
        //System.out.println("Mensaje en posición: " + listMessages.get(position));
        return listMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;

        holder = new ViewHolder();
        view = inflater.inflate(R.layout.content_chat_list, null);

        // Locate the TextViews in listfragment.xml

        holder.name = (TextView) view.findViewById(R.id.nameUser);
        holder.message = (TextView) view.findViewById(R.id.messageUser);
        System.out.println("Mensajes: " + listMessages.get(position).getMsg());

        view.setTag(holder);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        // Set the results into TextViews
        if(!listMessages.get(position).isLeft()) {
            holder.name.setText(listMessages.get(position).getName());
            holder.message.setText(listMessages.get(position).getMsg());
            holder.name.setLayoutParams(params);
            holder.message.setLayoutParams(params);
        }else {
            holder.name.setText(listMessages.get(position).getName());
            holder.message.setText(listMessages.get(position).getMsg());
        }

        return view;
    }

    public class ViewHolder {
        TextView name;
        TextView message;
    }
}
