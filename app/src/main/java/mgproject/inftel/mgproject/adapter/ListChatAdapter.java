package mgproject.inftel.mgproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
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
        holder.imageUser = (ImageView) view.findViewById(R.id.imageView);

        view.setTag(holder);

        RelativeLayout.LayoutParams paramsName = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams paramsMessage = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams paramsImage = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        paramsName.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramsMessage.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramsMessage.addRule(RelativeLayout.BELOW, R.id.nameUser);
        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramsImage.height = 40;
        paramsImage.width = 40;
        paramsImage.addRule(RelativeLayout.ALIGN_PARENT_END);

        // Set the results into TextViews
        if(!listMessages.get(position).isLeft()) {
            holder.name.setText(listMessages.get(position).getName());
            holder.message.setText(listMessages.get(position).getMsg());
            if(!listMessages.get(position).getImage().equals("")){
                Picasso.with(view.getContext()).load(listMessages.get(position).getImage()).into(holder.imageUser);
            }

            holder.name.setLayoutParams(paramsName);
            holder.message.setLayoutParams(paramsMessage);
            holder.imageUser.setLayoutParams(paramsImage);
        }else {
            holder.name.setText(listMessages.get(position).getName());
            holder.message.setText(listMessages.get(position).getMsg());
            if(!listMessages.get(position).getImage().equals("")){
                Picasso.with(view.getContext()).load(listMessages.get(position).getImage()).into(holder.imageUser);
            }
        }

        //notifyDataSetChanged();

        return view;
    }

    public class ViewHolder {
        TextView name;
        TextView message;
        ImageView imageUser;
    }
}
