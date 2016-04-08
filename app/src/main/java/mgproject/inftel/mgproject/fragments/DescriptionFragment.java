package mgproject.inftel.mgproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import mgproject.inftel.mgproject.R;

public class DescriptionFragment extends Fragment {

    public static TextView textView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle descriptionBundle = getArguments();

        view = inflater.inflate(R.layout.description_layout, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(descriptionBundle.getString("description"));
        System.out.println("Description: " + descriptionBundle.getString("description"));


        return view;
    }
}
