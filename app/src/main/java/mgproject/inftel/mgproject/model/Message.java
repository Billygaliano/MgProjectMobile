package mgproject.inftel.mgproject.model;

import org.json.JSONException;
import org.json.JSONObject;

import mgproject.inftel.mgproject.activities.MGApp;

/**
 * Created by inftel23 on 11/4/16.
 */
public class Message {
    private boolean left;
    private String id;
    private String name;
    private String msg;

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Message fromJSON(String response) throws JSONException {
        Message message = new Message();

        JSONObject jsonObject = new JSONObject(response);
        message.setId(jsonObject.getString("id"));
        message.setName(jsonObject.getString("user"));
        message.setMsg(jsonObject.getString("description"));
        message.setLeft(true);
        if(jsonObject.getString("user").equals(MGApp.getmInstance().getUser().getUsername())){
            message.setLeft(false);
        }

        return message;
    }
}
