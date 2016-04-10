package mgproject.inftel.mgproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.model.Task;

public class TasksFragment extends Fragment {
    private View view;
    ArrayList<Task> tasksList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle tasksBundle = getArguments();
        view = inflater.inflate(R.layout.tasks_layout, container, false);
        //tasksList = tasksBundle.getParcelableArrayList("taskList");

        return view;
    }


}
