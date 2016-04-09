package mgproject.inftel.mgproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by inftel23 on 7/4/16.
 */
public class Task implements Parcelable{

    private long idTask;
    private String nameTask;
    private int time;
    private String timeType;
    private String priority;
    private long idProject;
    private ArrayList<Long> usersTask;

    public Task() {}

    protected Task(Parcel in) {
        idTask = in.readLong();
        nameTask = in.readString();
        time = in.readInt();
        timeType = in.readString();
        priority = in.readString();
        idProject = in.readLong();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

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


    public static Task fromJSON(String response) throws JSONException {
        Task task = new Task();

        JSONObject jsonObject = new JSONObject(response);
        task.setIdTask(jsonObject.getLong("idTask"));
        task.setNameTask(jsonObject.getString("name"));
        task.setPriority(jsonObject.getString("priority"));
        task.setTime(jsonObject.getInt("time"));
        task.setTimeType(jsonObject.getString("timetype"));

        return task;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(idTask);
        dest.writeString(nameTask);
        dest.writeInt(time);
        dest.writeString(timeType);
        dest.writeString(priority);
        dest.writeLong(idProject);
    }
}
