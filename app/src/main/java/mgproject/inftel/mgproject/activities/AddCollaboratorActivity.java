package mgproject.inftel.mgproject.activities;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.model.User;
import mgproject.inftel.mgproject.util.RequestProject;
import mgproject.inftel.mgproject.util.RequestUser;

/**
 * A login screen that offers login via email/password.
 */
public class AddCollaboratorActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collaborator);
        // Set up the login form.
        spinner = (Spinner) findViewById(R.id.spinnerUser);
        addButton = (Button)findViewById(R.id.addCollaboratorButton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAddCollaboration);
        setSupportActionBar(toolbar);

        new RequestUser(this,"getUsers").execute(MGApp.getServerUri() + "user/" + MGApp.getmInstance().getUser().getIdGoogleUser() + "/" + MGApp.getmInstance().getProject().getIdProject());

    }

    public void onClickButton(View view) throws JSONException {
       JSONObject jsonUser = new JSONObject();
        jsonUser = User.toJSON((User)spinner.getSelectedItem());

        String url = MGApp.getServerUri()+"project/"+MGApp.getmInstance().getProject().getIdProject();
        new RequestProject(this,"addCollaborator",jsonUser).execute(url);


    }
    public void updateSpinner(ArrayList<User> userArrayList){

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,R.layout.support_simple_spinner_dropdown_item,userArrayList);

        spinner.setAdapter(adapter);
        spinner.setSelection(0);



    }
}



