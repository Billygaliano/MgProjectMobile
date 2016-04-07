package mgproject.inftel.mgproject.model;

import java.util.ArrayList;

/**
 * Created by inftel23 on 7/4/16.
 */
public class Task {

    private long idTask;
    private String nameTask;
    private int time;
    private String timeType;
    private String priority;
    private long idProject;
    private ArrayList<Long> usersTask;

    public long getIdTask() {
        return idTask;
    }

    public void setIdTask(long idTask) {
        this.idTask = idTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }

    public ArrayList<Long> getUsersTask() {
        return usersTask;
    }

    public void setUsersTask(ArrayList<Long> usersTask) {
        this.usersTask = usersTask;
    }
}
