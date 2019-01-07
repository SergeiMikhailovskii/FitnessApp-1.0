package asus.example.com.fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramsFragment extends Fragment {

    private final String[] programs = {"Slim body for a four weeks", "Exercises for woman at home", "Effective exercises for biceps at home",
            "Program of effective trainings twice a week", "Training on a horizontal bar for increasing muscle mass",
            "Fitness program for woman: trainings at home at in gym"};
    private final String PROGRAM ="PROGRAM";

    public ProgramsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_programs, container, false);
        setHasOptionsMenu(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,programs);
        GridView gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProgramActivity.class);
                String value="p"+(position+1);
                intent.putExtra(PROGRAM,value);
                startActivity(intent);
            }
        };
        gridView.setOnItemClickListener(itemClickListener);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu,menuInflater);
        menuInflater.inflate(R.menu.add,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        super.onOptionsItemSelected(menuItem);
        switch (menuItem.getItemId()){
            case R.id.add:
                Intent intent = new Intent(getActivity(), AddingProgramOrArticleActivity.class);
                intent.putExtra("variant",1);
                startActivity(intent);
                break;
        }
        return true;
    }

    private class DBHelper{

    }
}
