package mgproject.inftel.mgproject.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.model.User;
import mgproject.inftel.mgproject.util.RequestProject;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class AddProjectActivity extends AppCompatActivity {

    // UI references.
    private TextView mNameProject;
    private TextView mDescriptionProject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        // Set up the login form.
        mNameProject = (TextView) findViewById(R.id.nameProject);
        mDescriptionProject = (TextView) findViewById(R.id.descriptionProject);

        Button mAddProjectButton = (Button) findViewById(R.id.addProjectButton);
    }
    public void onClickButton(View view) throws JSONException {
            JSONObject jsonUser = new JSONObject();
            jsonUser = User.toJSON(MGApp.getmInstance().getUser());

            JSONObject jsonProject = new JSONObject();
            jsonProject.put("name",mNameProject.getText());
            jsonProject.put("description",mDescriptionProject.getText());
            jsonProject.put("idAdmin",jsonUser);

            String url = MGApp.getServerUri()+"project/";
            new RequestProject(this,"addProject",jsonProject).execute(url);
    }

    }




