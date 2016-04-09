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
import mgproject.inftel.mgproject.model.User;
import mgproject.inftel.mgproject.recyclerView.RecyclerViewCollaborator;

public class CoollaboratorsFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<User> collaboratorsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collaborators_list,container,false);
        Bundle bundle = getArguments();
        collaboratorsList = bundle.getParcelableArrayList("collaboratorsList");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.collaborator_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewCollaborator(collaboratorsList,this.getContext());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
