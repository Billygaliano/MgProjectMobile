package mgproject.inftel.mgproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by andresbailen93 on 8/4/16.
 */
public class Attatch implements Parcelable{
    private String name;
    private String urlFile;

    public Attatch() {
    }

    protected Attatch(Parcel in) {
        name = in.readString();
        urlFile = in.readString();
    }

    public static final Creator<Attatch> CREATOR = new Creator<Attatch>() {
        @Override
        public Attatch createFromParcel(Parcel in) {
            return new Attatch(in);
        }

        @Override
        public Attatch[] newArray(int size) {
            return new Attatch[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(urlFile);
    }

    public static Attatch fromJSON(String response) throws JSONException {
        Attatch a = new Attatch();
        JSONObject jsonObject = new JSONObject(response);
        a.setName(jsonObject.getString("nombre"));
        a.setUrlFile(jsonObject.getString("blob"));
        return a;

    }
}
