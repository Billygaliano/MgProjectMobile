package mgproject.inftel.mgproject.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.model.Attatch;
import mgproject.inftel.mgproject.recyclerView.RecyclerItemClickListener;
import mgproject.inftel.mgproject.recyclerView.RecyclerViewFiles;

public class FilesFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Attatch> attatchArrayList;
    private String urlAttachment = "http://192.168.1.37:8080/MgProjects-war/faces/";

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

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Attatch attatch = attatchArrayList.get(position);
                urlAttachment = urlAttachment + attatch.getUrlFile();
                Log.d("URL", urlAttachment);
                Intent intent = null;
                intent = new Intent(intent.ACTION_VIEW,Uri.parse(urlAttachment));
                startActivity(intent);

            }
        }));

        return view;
    }
}
