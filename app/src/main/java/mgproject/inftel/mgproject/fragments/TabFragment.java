package mgproject.inftel.mgproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mgproject.inftel.mgproject.R;
import mgproject.inftel.mgproject.activities.MGApp;
import mgproject.inftel.mgproject.model.Attatch;
import mgproject.inftel.mgproject.model.Project;
import mgproject.inftel.mgproject.model.User;
import mgproject.inftel.mgproject.util.RequestCollaborators;
import mgproject.inftel.mgproject.model.Task;

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 5 ;
    private ArrayList<Task> tasksList;
    public ArrayList<User> collaboratorsList;
    public ArrayList<Attatch> attatchList;
    private CoollaboratorsFragment coollaboratorsFragment;
    private FilesFragment filesFragment;
    private TasksFragment tasksFragment;
    private FloatingActionButton fabAddCollaborator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        this.fabAddCollaborator = (FloatingActionButton) this.getActivity().findViewById(R.id.addCollaborator);
        fabAddCollaborator.setVisibility(View.VISIBLE);



        this.collaboratorsList = MGApp.getmInstance().getCollaboratorsList();
        this.tasksList = MGApp.getmInstance().getTaskList();
        this.attatchList = MGApp.getmInstance().getFilesList();

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager(),this));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {
        private TabFragment tabFragment;
        public MyAdapter(FragmentManager fm,TabFragment tabFragment) {
            super(fm);
            this.tabFragment = tabFragment;
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
          switch (position){
              case 0 :
                  DescriptionFragment descriptionFragment = new DescriptionFragment();
                  return descriptionFragment;
              case 1 :
                  Bundle bundleTask = new Bundle();
                  bundleTask.putParcelableArrayList("taskList",tasksList);
                  tasksFragment = new TasksFragment();
                  tasksFragment.setArguments(bundleTask);
                  return tasksFragment;
              case 2 :
                  Bundle bundleColla = new Bundle();
                  bundleColla.putParcelableArrayList("collaboratorsList",collaboratorsList);
                  coollaboratorsFragment = new CoollaboratorsFragment();
                  coollaboratorsFragment.setArguments(bundleColla);
                  return coollaboratorsFragment;

              case 3 :
                  Bundle bundleAttatch = new Bundle();
                  bundleAttatch.putParcelableArrayList("attatchList",attatchList);
                  filesFragment = new FilesFragment();
                  filesFragment.setArguments(bundleAttatch);
                  return  filesFragment;

              case 4 :
                  return new ChatFragment();
          }
        return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Resumen";
                case 1 :
                    return "Tareas";
                case 2 :
                    return "Equipo";
                case 3:
                    return "Ficheros";
                case 4:
                    return "Chat";
            }
                return null;
        }
    }

}
