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

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.model.Task;

public class DescriptionFragment extends Fragment {

    public static TextView textView, pressing_textView, sudden_textView, planned_textView, accesory_textView;
    private View view;
    ArrayList<Task> tasksList;
    int pressing = 0, sudden = 0, planned = 0, accesory = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.description_layout, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(MGApp.getmInstance().getProject().getDescription());
        tasksList = MGApp.getmInstance().getTaskList();

        for (int i = 0; i < tasksList.size(); i++) {
            switch (tasksList.get(i).getPriority()) {
                case "acuciante":
                    pressing++;
                    break;
                case "repentino":
                    sudden++;
                    break;
                case "plani":
                    planned++;
                    break;
                case "accesorio":
                    accesory++;
                    break;
            }
        }

        pressing_textView = (TextView) view.findViewById(R.id.pressing_textView);
        pressing_textView.setText(String.valueOf(pressing)+" Tareas Acuciantes");

        sudden_textView = (TextView) view.findViewById(R.id.sudden_textView);
        sudden_textView.setText(String.valueOf(sudden)+" Tareas Importantes");

        planned_textView = (TextView) view.findViewById(R.id.planned_textView);
        planned_textView.setText(String.valueOf(planned)+" Tareas Planeadas");

        accesory_textView = (TextView) view.findViewById(R.id.accessory_textView);
        accesory_textView.setText(String.valueOf(accesory)+" Tareas Accesorias");
                    pressing= 0;
                    sudden = 0;
        planned = 0;
        accesory = 0;
                    return view;
            }
        }
