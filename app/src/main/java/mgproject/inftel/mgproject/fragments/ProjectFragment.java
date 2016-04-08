package mgproject.inftel.mgproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MainActivity;
import mgproject.inftel.mgproject.activities.ProjectInfoActivity;
import mgproject.inftel.mgproject.model.Project;
import mgproject.inftel.mgproject.recyclerView.RecyclerItemClickListener;
import mgproject.inftel.mgproject.recyclerView.RecyclerViewAdapter;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class ProjectFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Project> projectList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_proyect_list, container, false);

        Bundle bundle = getArguments();
        projectList = bundle.getParcelableArrayList("projectList");


        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter( projectList,this.getContext());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TabFragment tabProjectFragment = new TabFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, tabProjectFragment).commit();
//                Intent intent = new Intent(getContext(), ProjectInfoActivity.class);
//                intent.putExtra("admin", projectList.get(position).getAdminProject());
//                intent.putExtra("collaborators", projectList.get(position).getCollaborators());
//                intent.putExtra("description", projectList.get(position).getDescription());
//                intent.putExtra("idProject", projectList.get(position).getIdProject());
//                intent.putExtra("nameProject",projectList.get(position).getNameProject());
//                intent.putExtra("task",projectList.get(position).getTasksProject());
//                startActivity(intent);
            }
        }));

        return view;
    }
}
