package mgproject.inftel.mgproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.location.places.Place;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by inftel23 on 7/4/16.
 */
public class Project implements Parcelable{
    private Long idProject;
    private String nameProject;
    private String description;
    private ArrayList<Long> tasksProject;
    private ArrayList<Long> collaborators;
    private Long adminProject;

    public Project() {
    }

    protected Project(Parcel in) {
        nameProject = in.readString();
        description = in.readString();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Long> getTasksProject() {
        return tasksProject;
    }

    public void setTasksProject(ArrayList<Long> tasksProject) {
        this.tasksProject = tasksProject;
    }

    public ArrayList<Long> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(ArrayList<Long> collaborators) {
        this.collaborators = collaborators;
    }

    public Long getAdminProject() {
        return adminProject;
    }

    public void setAdminProject(Long adminProject) {
        this.adminProject = adminProject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameProject);
        dest.writeString(description);
    }

    public static Project fromJSON(String response) throws JSONException {
        Project project = new Project();
        JSONObject jsonObject = new JSONObject(response);
        project.setDescription(jsonObject.getString("description"));
        project.setIdProject(jsonObject.getLong("idProject"));
        project.setNameProject(jsonObject.getString("name"));
        return project;
    }
}
