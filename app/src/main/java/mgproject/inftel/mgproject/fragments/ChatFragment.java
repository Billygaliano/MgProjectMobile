package mgproject.inftel.mgproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MGApp;
import autobahn.WebSocket;
import autobahn.WebSocketConnection;
import autobahn.WebSocketConnectionHandler;
import autobahn.WebSocketException;
import mgproject.inftel.mgproject.activities.ProjectActivity;
import mgproject.inftel.mgproject.adapter.ListChatAdapter;
import mgproject.inftel.mgproject.model.Message;
import mgproject.inftel.mgproject.model.Task;

/**
 * Created by Guillermo on 10/04/2016.
 */
public class ChatFragment extends Fragment {
    static Button mStart;
    static EditText mMessage;
    static ImageButton mSendMessage;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_layout, container, false);

        mStart = (Button) view.findViewById(R.id.start);
        mMessage = (EditText) view.findViewById(R.id.msg);
        mSendMessage = (ImageButton) view.findViewById(R.id.sendMsg);

        start();
        mSendMessage.setEnabled(false);
        mMessage.setEnabled(false);

        mSendMessage.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String prueba = "{\"user\":\""+MGApp.getmInstance().getUser().getUsername()+"\",\"description\": \""+ mMessage.getText().toString() + "\",\"urlImage\":\""+MGApp.getmInstance().getUser().getPhoto()+"\"}";
                mConnection.sendTextMessage(mMessage.getText().toString());
                mConnection.sendTextMessage(prueba);
                mMessage.setText("");
            }
        });

        return view;
    }

    private final WebSocket mConnection = new WebSocketConnection();

    private void start() {

        final String wsuri = MGApp.getmInstance().getUri();

        try {
            mConnection.connect(wsuri, new WebSocketConnectionHandler() {
                @Override
                public void onOpen() {
                    mSendMessage.setEnabled(true);
                    mMessage.setEnabled(true);
                }

                @Override
                public void onTextMessage(String payload) {

                    ArrayList<Message> listMessages = new ArrayList<Message>();
                    try {
                        JSONArray jsonArray = new JSONArray(payload);
                        for (int i = 0; i < jsonArray.length(); i++){
                            Message m = Message.fromJSON(jsonArray.get(i).toString());
                            System.out.println("Nombre: " + m.getName() + " - Mensaje: "+ m.getMsg());
                            listMessages.add(m);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //Aquí recibimos el texto.
                    ListView messageList = (ListView) view.findViewById(R.id.listView);
                    ListChatAdapter adapter = new ListChatAdapter(getContext(), listMessages);
                    messageList.setAdapter(adapter);
                    System.out.println("Got echo: " + payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    System.out.println("Connection lost.");
                    mSendMessage.setEnabled(false);
                    mMessage.setEnabled(false);
                }
            });
        } catch (WebSocketException e) {

            //Log.d(TAG, e.toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mConnection.isConnected()) {
            mConnection.disconnect();
        }
    }
}
