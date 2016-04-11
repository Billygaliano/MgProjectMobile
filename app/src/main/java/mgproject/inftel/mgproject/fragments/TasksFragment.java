package mgproject.inftel.mgproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.model.Task;
import mgproject.inftel.mgproject.recyclerView.RecyclerViewCollaborator;
import mgproject.inftel.mgproject.recyclerView.RecyclerViewTask;

public class TasksFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Task> tasksList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_task_list,container,false);
        tasksList = MGApp.getmInstance().getTaskList();

        if(!tasksList.isEmpty()) {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new RecyclerViewTask(tasksList, this.getContext());
            mRecyclerView.setAdapter(mAdapter);
        }
        return view;
    }


}
