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
    private ArrayList<User> collaborators;
    private User adminProject;

    public Project() {
    }


    protected Project(Parcel in) {
        nameProject = in.readString();
        description = in.readString();
        collaborators = in.createTypedArrayList(User.CREATOR);
        adminProject = in.readParcelable(User.class.getClassLoader());
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

    public ArrayList<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(ArrayList<User> collaborators) {
        this.collaborators = collaborators;
    }

    public User getAdminProject() {
        return adminProject;
    }

    public void setAdminProject(User adminProject) {
        this.adminProject = adminProject;
    }


    public static Project fromJSON(String response) throws JSONException {
        Project project = new Project();
        JSONObject jsonObject = new JSONObject(response);
        if(jsonObject.has("description")) {
            project.setDescription(jsonObject.getString("description"));
        }else{
            project.setDescription("");
        }
        project.setIdProject(jsonObject.getLong("idProject"));
        project.setNameProject(jsonObject.getString("name"));
        JSONObject adminProject = new JSONObject(jsonObject.getString("idAdmin"));
        project.setAdminProject(User.fromJSON(adminProject.toString()));
        return project;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(nameProject);
        dest.writeString(description);
        dest.writeTypedList(collaborators);
        dest.writeParcelable(adminProject, flags);
    }


}
