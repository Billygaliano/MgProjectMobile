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

import mgproject.inftel.mgproject.fragments.TabFragment;
import mgproject.inftel.mgproject.model.Task;

/**
 * Created by Guillermo on 8/4/16.
 */
public class RequestTask extends AsyncTask<String,Void,String> {
    private Context context;
    private TabFragment fragment;

    public RequestTask(Context context) {
        this.context = context;
    }

    public RequestTask(TabFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected String doInBackground(String... url) {
        try {
            return taskProject(url[0]);

        } catch (IOException e) {
            Log.d("Login", "Unable to retrieve web page. URL may be invalid.");
            return null;
        }
    }

    private String taskProject(String myurl) throws IOException {
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
            ArrayList<Task> taskList = new ArrayList<Task>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++){
                Task t = Task.fromJSON(jsonArray.get(i).toString());
                System.out.println("Nombre tarea: " + t.getNameTask());
                taskList.add(t);
            }

            fragment.showTaskListFragment(taskList);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
