package mgproject.inftel.mgproject.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import mgproject.inftel.mgproject.activities.MainActivity;
import mgproject.inftel.mgproject.activities.ProjectActivity;
import mgproject.inftel.mgproject.fragments.TabFragment;
import mgproject.inftel.mgproject.model.Attatch;
import mgproject.inftel.mgproject.model.Project;

/**
 * Created by andresbailen93 on 8/4/16.
 */
public class RequestAttatch extends AsyncTask<String,Void,String> {
    private Context context;

    public RequestAttatch(Context context) {
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
            ArrayList<Attatch> attatchList = new ArrayList<Attatch>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++){
                Attatch a = Attatch.fromJSON(jsonArray.get(i).toString());
                attatchList.add(a);
            }
            ((ProjectActivity)context).showAttatch(attatchList);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
