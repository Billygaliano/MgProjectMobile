package mgproject.inftel.mgproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mgproject.inftel.mgproject.R;

/**
 * Created by andresbailen93 on 7/4/16.
 */
public class LoadingFragment extends Fragment {
    public LoadingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.loading_fragment, container, false);
    }
}