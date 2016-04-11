package mgproject.inftel.mgproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.activities.ProjectActivity;
import mgproject.inftel.mgproject.model.Project;
import mgproject.inftel.mgproject.model.User;
import mgproject.inftel.mgproject.recyclerView.RecyclerItemClickListener;
import mgproject.inftel.mgproject.recyclerView.RecyclerViewAdapter;
import mgproject.inftel.mgproject.util.RequestAttatch;
import mgproject.inftel.mgproject.util.RequestCollaborators;
import mgproject.inftel.mgproject.util.RequestTask;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class ProjectFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Project> projectList;

    private MGApp mMGappInstance;

    private GoogleApiClient mGoogleApiClient;
    private User user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_proyect_list, container, false);

        Bundle bundle = getArguments();
        projectList = bundle.getParcelableArrayList("projectList");

        if(!projectList.isEmpty()) {

            mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new RecyclerViewAdapter(projectList, this.getContext());
            mRecyclerView.setAdapter(mAdapter);

            mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Project project = projectList.get(position);

                    Bundle projectParam = new Bundle();
                    projectParam.putParcelable("project", project);


                    TabFragment tabFragment = new TabFragment();
                    tabFragment.setArguments(projectParam);
                    //Guardar project en la sesion
                    MGApp.getmInstance().setProject(projectList.get(position));

                    Intent intent = new Intent(getActivity(), ProjectActivity.class);
                    startActivity(intent);
                }
            }));
        }
        return view;
    }

}