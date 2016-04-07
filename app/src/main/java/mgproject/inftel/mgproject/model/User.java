package mgproject.inftel.mgproject.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
/**
 */
public class User implements Serializable {

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
        return "User{" +
                "idGoogleUser='" + idGoogleUser + '\'' +
                ", username='" + username + '\'' +
                ", photo='" + photo + '\'' +
                ", email='" + email + '\'' +
                '}';
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
}