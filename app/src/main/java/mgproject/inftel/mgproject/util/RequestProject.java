package mgproject.inftel.mgproject.util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.List;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MainActivity;
import mgproject.inftel.mgproject.model.Project;
import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class  RequestProject extends AsyncTask<String,Void,String> {
    private Context context;

    public RequestProject(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... url) {
        try {
            return projectUser(url[0]);

        } catch (IOException e) {
            Log.d("Login", "Unable to retrieve web page. URL may be invalid.");
            return null;
        }
    }

    private String projectUser(String myurl) throws IOException {
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
            ArrayList<Project> projectList = new ArrayList<Project>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++){
                Project p = Project.fromJSON(jsonArray.get(i).toString());
                projectList.add(p);
            }
            ((MainActivity) this.context).showPlaceListFragment(projectList);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
