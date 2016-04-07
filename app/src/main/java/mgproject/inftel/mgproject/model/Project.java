package mgproject.inftel.mgproject.model;

import java.util.ArrayList;

/**
 * Created by inftel23 on 7/4/16.
 */
public class Project {
    private Long idProject;
    private String nameProject;
    private String description;
    private ArrayList<Long> tasksProject;
    private ArrayList<Long> collaborators;
    private Long adminProject;

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
}
