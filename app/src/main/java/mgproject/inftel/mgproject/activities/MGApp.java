package mgproject.inftel.mgproject.activities;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;

import mgproject.inftel.mgproject.model.User;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class MGApp extends Application {
    private static String serverUri = "http://192.168.1.131:8080/MgProjectRest/webresources/mgproject.service/";
    private GoogleApiClient mGoogleApiClient;
    private static MGApp mInstance;
    private User user;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
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
