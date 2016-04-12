package mgproject.inftel.mgproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.fragments.TabFragment;
import mgproject.inftel.mgproject.fragments.TasksFragment;
import mgproject.inftel.mgproject.model.Task;
import mgproject.inftel.mgproject.model.User;
import mgproject.inftel.mgproject.fragments.LoadingFragment;
import mgproject.inftel.mgproject.fragments.ProjectFragment;
import mgproject.inftel.mgproject.model.Project;
import mgproject.inftel.mgproject.util.RequestProject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ArrayList<Project> projectList = null;
    ArrayList<Task> taskList = null;
    private MGApp mMGappInstance;

    private GoogleApiClient mGoogleApiClient;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        //Get the user
        user = User.getInstance();
        printUserInformation(header);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Auth.GOOGLE_SIGN_IN_API)
                    .build();
        }

        //FRagmento de carga
        LoadingFragment loadingFragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_main, loadingFragment).commit();

        this.mMGappInstance = MGApp.getmInstance();
        String url = mMGappInstance.getServerUri()+"myproject/"+mMGappInstance.getmInstance().getUser().getIdGoogleUser();
        new RequestProject(this,"projectUser",null).execute(url);
    }

    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addProject) {
            Intent intent = new Intent(MainActivity.this,AddProjectActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_project) {

            LoadingFragment loadingFragment = new LoadingFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_main, loadingFragment).commit();

            String url = mMGappInstance.getServerUri()+"project/"+mMGappInstance.getmInstance().getUser().getIdGoogleUser();
            new RequestProject(this,"projectUser",null).execute(url);


        } else if (id == R.id.nav_collaborator) {

            LoadingFragment loadingFragment = new LoadingFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_main, loadingFragment).commit();

            String url = mMGappInstance.getServerUri()+"projectCollaborations/"+mMGappInstance.getmInstance().getUser().getIdGoogleUser();
            new RequestProject(this,"projectUser",null).execute(url);

        } else if (id == R.id.nav_logout) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);

            user = null;

            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString("email", "");
            editor.putString("username", "");
            editor.putString("photo", "");
            editor.putString("idUser", "");
            editor.commit();

            Intent logoutIntent = new Intent(this, LoginActivity.class);
            startActivity(logoutIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void printUserInformation (View header) {
        TextView textUserName = (TextView) header.findViewById(R.id.nickNameMenu);
        textUserName.setText(user.getUsername());

        ImageView userImage = (ImageView) header.findViewById(R.id.imageView);
        if(!user.getPhoto().equals("")){
            Picasso.with(this).load(user.getPhoto()).into(userImage);
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void showProjectListFragment(ArrayList<Project> projectList) {
        this.projectList = projectList;
        ProjectFragment projectListFragment = new ProjectFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("projectList", projectList);
        projectListFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, projectListFragment).commit();
    }
}
