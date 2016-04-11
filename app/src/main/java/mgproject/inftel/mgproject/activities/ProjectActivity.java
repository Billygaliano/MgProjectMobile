package mgproject.inftel.mgproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.fragments.LoadingFragment;
import mgproject.inftel.mgproject.fragments.TabFragment;
import mgproject.inftel.mgproject.model.Attatch;
import mgproject.inftel.mgproject.model.Task;
import mgproject.inftel.mgproject.model.User;
import mgproject.inftel.mgproject.util.RequestAttatch;
import mgproject.inftel.mgproject.util.RequestCollaborators;
import mgproject.inftel.mgproject.util.RequestProject;
import mgproject.inftel.mgproject.util.RequestTask;

public class ProjectActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private int control = 0;
    private MGApp mMGappInstance;

    private GoogleApiClient mGoogleApiClient;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabCollaborator = (FloatingActionButton) findViewById(R.id.addCollaborator);
        fabCollaborator.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this,AddCollaboratorActivity.class);
                startActivity(intent);
            }
        });

        if(MGApp.getmInstance().getUser().getIdGoogleUser().equals(MGApp.getmInstance().getProject().getAdminProject().getIdGoogleUser())){
            fabCollaborator.setVisibility(View.VISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        //Get the user
        user = MGApp.getmInstance().getUser();
        printUserInformation(header);


        new RequestTask(this).execute(MGApp.getServerUri() + "task/" + String.valueOf(MGApp.getmInstance().getProject().getIdProject()));
        new RequestCollaborators(this).execute(MGApp.getServerUri() + "collaboratorsProject/" + String.valueOf(MGApp.getmInstance().getProject().getIdProject()));
        new RequestAttatch(this).execute(MGApp.getServerUri() + "attatch/" + String.valueOf(MGApp.getmInstance().getProject().getIdProject()));

        LoadingFragment loadingFragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_project, loadingFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_project) {

            LoadingFragment loadingFragment = new LoadingFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_main, loadingFragment).commit();

            String url = MGApp.getServerUri() + "project/" + MGApp.getmInstance().getUser().getIdGoogleUser();
            new RequestProject(this, "projectUser", null).execute(url);


        } else if (id == R.id.nav_collaborator) {

            LoadingFragment loadingFragment = new LoadingFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_main, loadingFragment).commit();

            String url = MGApp.getServerUri() + "projectCollaborations/" + MGApp.getmInstance().getUser().getIdGoogleUser();
            new RequestProject(this, "projectUser", null).execute(url);

        } else if (id == R.id.nav_logout) {
            Auth.GoogleSignInApi.signOut(MGApp.getmInstance().getmGoogleApiClient());

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

    public void showTaskListFragment(ArrayList<Task> taskList) {
        MGApp.getmInstance().setTaskList(taskList);
        control++;
        allLoad();
    }

    public void showCollaborators(ArrayList<User> collaboratorsList) {
        MGApp.getmInstance().setCollaboratorsList(collaboratorsList);
        control++;
        allLoad();
    }

    public void showAttatch(ArrayList<Attatch> filesList) {
        MGApp.getmInstance().setFilesList(filesList);
        control++;
        allLoad();
    }

    private void allLoad() {
        if (control == 3) {
            TabFragment tabFragment = new TabFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_project, tabFragment).commit();
        }
    }
}