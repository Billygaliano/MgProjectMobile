package mgproject.inftel.mgproject.util;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;

import mgproject.inftel.mgproject.activities.MainActivity;
import mgproject.inftel.mgproject.fragments.ProjectFragment;
import mgproject.inftel.mgproject.fragments.TabFragment;
import mgproject.inftel.mgproject.model.Project;
import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 8/4/16.
 */
public class RequestCollaborators  extends AsyncTask<String,Void,String> {
    private Context context;
    private TabFragment fragment;

    public RequestCollaborators(Context context) {
        this.context = context;
    }
    public RequestCollaborators(TabFragment fragment){
        this.fragment = fragment;
    }
    @Override
    protected String doInBackground(String... url) {
        try {
            return collaboratorsUser(url[0]);

        } catch (IOException e) {
            Log.d("Login", "Unable to retrieve web page. URL may be invalid.");
            return null;
        }
    }

    private String collaboratorsUser(String myurl) throws IOException {
        StringBuilder response = new StringBuilder();

        try {

            URL obj = new URL(myurl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            int responseCode = con.getResponseCode();


            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            connection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
    @Override
    protected void onPostExecute(String result){
        try {
            ArrayList<User> collaboratorsList = new ArrayList<User>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++){

                User u = User.fromJSON(jsonArray.get(i).toString());
                collaboratorsList.add(u);

            }
            fragment.showCollaborators(collaboratorsList);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
