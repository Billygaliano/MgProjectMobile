package mgproject.inftel.mgproject.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import mgproject.inftel.mgproject.activities.AddCollaboratorActivity;
import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class RequestUser extends AsyncTask<String,Void,String>{
    private String action;
    private Context context;
    private JSONObject json;
    private String response;
    public RequestUser(Context context, String action, JSONObject json) {
        this.context = context;
        this.action = action;
        this.json = json;
    }
    public RequestUser(Context context, String action) {
        this.context = context;
        this.action = action;
    }

    @Override
    protected String doInBackground(String... urls) {
        if (action.equals("loginUser")) {
            // params comes from the execute() call: params[0] is the url.
            try {
                return loginUser(urls[0]);

            } catch (IOException e) {
                Log.d("Login", "Unable to retrieve web page. URL may be invalid.");
                return null;
            }
        } else if (action.equals("getUsers")) {
            try {
                return getUsers(urls[0]);

            } catch (IOException e) {
                Log.d("Login", "Unable to retrieve web page. URL may be invalid.");
                return null;

            }

        }else{
            return null;
        }
    }

    @Override
    protected void onPostExecute(String json) {

        if(this.action.equals("getUsers")){
            try {
                ArrayList<User> userList = new ArrayList<User>();
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++){

                    User u = User.fromJSON(jsonArray.get(i).toString());
                    userList.add(u);

                }
                ((AddCollaboratorActivity) this.context).updateSpinner(userList);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
    private String getUsers(String myurl) throws IOException{
        StringBuilder response = new StringBuilder();
        User user = User.getInstance();
        String getURL = myurl;

        URL obj = new URL(getURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        int responseCode = con.getResponseCode();


        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");


        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        // Starts the query
        conn.connect();

        int response2 = conn.getResponseCode();
        Log.d("Login", "The response is: " + response2);


        con.disconnect();

        return response.toString();
    }

    private String loginUser(String myurl) throws IOException {
        User user = User.getInstance();
        String getURL = myurl + user.getEmail();

        URL obj = new URL(getURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        int responseCode = con.getResponseCode();


        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject jsonObject = user.toJSON(user);
        System.out.println(jsonObject.toString());
        Log.e("POST",jsonObject.toString());

        OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
        wr.write(jsonObject.toString());
        wr.flush();
        wr.close();

        // Starts the query
        conn.connect();

        int response2 = conn.getResponseCode();
        Log.d("Login", "The response is: " + response2);


        con.disconnect();

        return "";
    }
}

