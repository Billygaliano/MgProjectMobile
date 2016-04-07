package mgproject.inftel.mgproject.util;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class RequestUser {

    public void loginUser(JSONObject json){
        String url = MGApp.getServerUri() + "user";


    }

    private class LoginUserClass extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... url){
            try{
                return loginUser(url[0]);
            }catch (IOException e){
                Log.d("login", "URL INVALIDA");
                return null;
            }
        }
    }


private String loginUser(String myurl) throws IOException{
    User user = User.getInstance();
    String getURL = myurl + user.getEmail();

    URL obj = new URL(getURL);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    con.setRequestMethod("GET");

    int responseCode = con.getResponseCode();

    if (responseCode == 204) {

        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");

        JSONObject jsonObject = User.toJSON(user);
        System.out.println(jsonObject.toString());

        OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
        wr.write(jsonObject.toString());
        wr.flush();
        wr.close();

// Starts the query
        conn.connect();

        int response2 = conn.getResponseCode();
        Log.d("Login", "The response is: " + response2);
    }

    con.disconnect();

    return "";
}
}

