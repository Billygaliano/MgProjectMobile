package mgproject.inftel.mgproject.activities;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import mgproject.inftel.mgproject.model.Attatch;
import mgproject.inftel.mgproject.model.Project;
import mgproject.inftel.mgproject.model.Task;
import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class MGApp extends Application {
    private static String serverUri = "http://192.168.1.132:8080/MgProjectRest/webresources/mgproject.service/";
    private GoogleApiClient mGoogleApiClient;
    private static MGApp mInstance;
    private User user;
    private Project project;
    private ArrayList<User> collaboratorsList;
    private ArrayList<Attatch> filesList;
    private ArrayList<Task> taskList;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }

    public ArrayList<User> getCollaboratorsList() {
        return collaboratorsList;
    }

    public void setCollaboratorsList(ArrayList<User> collaboratorsList) {
        this.collaboratorsList = collaboratorsList;
    }

    public ArrayList<Attatch> getFilesList() {
        return filesList;
    }

    public void setFilesList(ArrayList<Attatch> filesList) {
        this.filesList = filesList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static synchronized MGApp getmInstance() {
        return mInstance;
    }

    public static void setmInstance(MGApp mInstance) {
        MGApp.mInstance = mInstance;
    }

    public static String getServerUri() {
        return serverUri;
    }

    public static void setServerUri(String serverUri) {
        MGApp.serverUri = serverUri;
    }

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }
}
