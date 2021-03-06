package mgproject.inftel.mgproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.model.User;
import mgproject.inftel.mgproject.util.RequestUser;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    //Login Google
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions gso;
    private int RC_SIGN_IN = 1;
    //SharedPreference for login
    private SharedPreferences sharedPref;
    //URL restful Login
    private String serverUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize variables
        serverUri = MGApp.getServerUri();


        //Load content sharedPreference
        sharedPref =  getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        String username = sharedPref.getString("username", "");
        String photo = sharedPref.getString("photo", "");
        String id = sharedPref.getString("idUser","");
        if (!email.equals("")){
            User user = User.getInstance();
            user.setEmail(email);
            user.setPhoto(photo);
            user.setUsername(username);
            user.setIdGoogleUser(id);

            MGApp.getmInstance().setUser(user);

            goMainActivity(user, false);

        } else { //Login

            setContentView(R.layout.activity_login);

            // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            // Build a GoogleApiClient with access to the Google Sign-In API and the
            // options specified by gso.
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

            //Set the listener button signIn
            findViewById(R.id.sign_in_button).setOnClickListener(this);

        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult result){
        Log.d("Login", "Conection Failed");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {

        Log.d("Login", "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            User user = User.getInstance();
            user.setEmail(acct.getEmail());
            if (acct.getPhotoUrl() == null) {
                user.setPhoto("");
            } else {
                user.setPhoto(acct.getPhotoUrl().toString());
            }

            user.setUsername(acct.getDisplayName());
            user.setIdGoogleUser(acct.getId());
            MGApp.getmInstance().setUser(user);
            new RequestUser(this,"loginUser").execute(this.serverUri + "user");
            goMainActivity(user, true);

        } else {
            Log.d("Login", "NameSignInResult Error");
        }
    }


    private void goMainActivity(User user, boolean newSession){

        if (newSession) {
            sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString("email", user.getEmail());
            editor.putString("username", user.getUsername());
            editor.putString("photo", user.getPhoto());
            editor.putString("idUser", user.getIdGoogleUser());
            editor.commit();

        }

        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
        finish();

    }



}
