package mgproject.inftel.mgproject.activities;

import android.content.ClipData;
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
import android.view.Menu;
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

public class ProjectActivity extends AppCompatActivity{
    private int control = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(MGApp.getmInstance().getProject().getNameProject());
        setContentView(R.layout.activity_project);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProject);
        setSupportActionBar(toolbar);


        new RequestTask(this).execute(MGApp.getServerUri() + "task/" + String.valueOf(MGApp.getmInstance().getProject().getIdProject()));
        new RequestCollaborators(this).execute(MGApp.getServerUri() + "collaboratorsProject/" + String.valueOf(MGApp.getmInstance().getProject().getIdProject()));
        new RequestAttatch(this).execute(MGApp.getServerUri() + "attatch/" + String.valueOf(MGApp.getmInstance().getProject().getIdProject()));
        showUri(MGApp.getChatUri() + String.valueOf(MGApp.getmInstance().getProject().getIdProject()));

        LoadingFragment loadingFragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_project, loadingFragment).commit();
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

    public void showUri(String uri) {
        MGApp.getmInstance().setUri(uri);
        control++;
        allLoad();
    }

    private void allLoad() {
        if (control == 4) {
            TabFragment tabFragment = new TabFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_project, tabFragment).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.project, menu);
        return true;
    }
    @Override
    public boolean  onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.addCollaboratorbton) {
            Intent intent = new Intent(ProjectActivity.this, AddCollaboratorActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}