package mgproject.inftel.mgproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
/**
 */
public class User implements Serializable, Parcelable {

    String username;
    String photo;
    String email;
    String idGoogleUser;

    private static User userInstance = null;

    public User(){};

    public User(String username, String photo, String email, String idGoogleUser) {
        this.username = username;
        this.photo = photo;
        this.email = email;
        this.idGoogleUser = idGoogleUser;
    }

    protected User(Parcel in) {
        username = in.readString();
        photo = in.readString();
        email = in.readString();
        idGoogleUser = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public static User getInstance() {
        if(userInstance == null) {
            userInstance = new User();
        }
        return userInstance;
    }

    public String getIdGoogleUser() {
        return idGoogleUser;
    }

    public void setIdGoogleUser(String idGoogleUser) {
        this.idGoogleUser = idGoogleUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.username;
    }

    public static JSONObject toJSON(User user){
        JSONObject json = new JSONObject();
        try {
            json.put("email",user.getEmail());
            json.put("idUser", user.getIdGoogleUser());
            json.put("nick",user.getUsername());
            json.put("urlImage",user.getPhoto());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    public static User fromJSON(String response) throws JSONException {
        User u = new User();

        JSONObject jsonObject = new JSONObject(response);
        u.setIdGoogleUser(jsonObject.getString("idUser"));
        u.setEmail(jsonObject.getString("email"));
        if(jsonObject.has("urlImage")) {
            u.setPhoto(jsonObject.getString("urlImage"));
        }
        u.setUsername(jsonObject.getString("nick"));

        return u;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(username);
        dest.writeString(photo);
        dest.writeString(email);
        dest.writeString(idGoogleUser);
    }
}
