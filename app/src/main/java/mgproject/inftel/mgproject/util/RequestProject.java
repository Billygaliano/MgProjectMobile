package mgproject.inftel.mgproject.util;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.AddCollaboratorActivity;
import mgproject.inftel.mgproject.activities.AddProjectActivity;
import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.activities.MainActivity;
import mgproject.inftel.mgproject.activities.ProjectActivity;
import mgproject.inftel.mgproject.model.Project;
import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class  RequestProject extends AsyncTask<String,Void,String> {
    private Context context;
    private String action;
    private JSONObject json;

    public RequestProject(Context context,String action,JSONObject json) {
        this.context = context;
        this.action = action;
        this.json = json;
    }

    @Override
    protected String doInBackground(String... url) {
        try {
            if(this.action.equals("projectUser")) {
                return projectUser(url[0]);
            }else if(this.action.equals("addProject")){
                return addProject(url[0]);
            }else if(this.action.equals("addCollaborator")) {
                return addCollaborator(url[0]);
            }else{
                return null;
            }

        } catch (IOException e) {
            Log.d("Login", "Unable to retrieve web page. URL may be invalid.");
            return null;
        }
    }
    private String addProject(String myurl) throws IOException{
        StringBuilder response = new StringBuilder();
        System.out.println(json.toString());

        try {
            URL obj = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
            System.out.println("USUARIO"+json.toString());
            dStream.writeBytes(String.valueOf(json)); //Writes out the string to the underlying output stream as a sequence of bytes
            dStream.flush(); // Flushes the data output stream.
            dStream.close();

            connection.getResponseCode();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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

    private String addCollaborator(String myurl) throws IOException{
        StringBuilder response = new StringBuilder();
        System.out.println(json.toString());

        try {
            URL obj = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
            System.out.println("USUARIO" + json.toString());
            dStream.writeBytes(String.valueOf(json)); //Writes out the string to the underlying output stream as a sequence of bytes
            dStream.flush(); // Flushes the data output stream.
            dStream.close();

            connection.getResponseCode();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result){

        if(this.action.equals("projectUser")) {
            try {
                ArrayList<Project> projectList = new ArrayList<Project>();
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    Project p = Project.fromJSON(jsonArray.get(i).toString());
                    projectList.add(p);
                }
                ((MainActivity) this.context).showProjectListFragment(projectList);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(this.action.equals("addProject")){
            Toast toast = Toast.makeText(context,"Proyecto añadido", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(context,MainActivity.class);
            context.startActivity(intent);
            ((AddProjectActivity)this.context).finish();


        }else if(this.action.equals("addCollaborator")){
            Toast toast = Toast.makeText(context,"Colaborador añadido", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(context,ProjectActivity.class);

            context.startActivity(intent);
            ((AddCollaboratorActivity)this.context).finish();
        }



    }
}
