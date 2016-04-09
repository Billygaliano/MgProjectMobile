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
import mgproject.inftel.mgproject.model.Attatch;
import mgproject.inftel.mgproject.recyclerView.RecyclerViewFiles;

public class FilesFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Attatch> attatchArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_file_list, container, false);
        Bundle bundle = getArguments();
        attatchArrayList = bundle.getParcelableArrayList("attatchList");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.file_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewFiles(attatchArrayList,this.getContext());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
