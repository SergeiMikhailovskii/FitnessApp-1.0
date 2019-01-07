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

public class ArticlesFragment extends Fragment {
    private final String article = "ARTICLE";
    private final String[] articlesNames = { "10 ways to keep your body fit", "How to make simple exercises for beginners?", "Which gymnastic complexes are suitable for adults?",
            "How to lift socks on training apparatus?", "How to recover yourself after the training?", "How to recover yourself at night?"};

    public ArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        setHasOptionsMenu(true);
        GridView countriesList = (GridView) view.findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, articlesNames);
        countriesList.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                switch (position){
                    case 0:
                        intent.putExtra(article,"ten_ways");
                        break;
                    case 1:
                        intent.putExtra(article,"how_to_make");
                        break;
                    case 2:
                        intent.putExtra(article,"for_adults");
                        break;
                    case 3:
                        intent.putExtra(article,"lifting_socks");
                        break;
                    case 4:
                        intent.putExtra(article,"recovering");
                        break;
                    case 5:
                        intent.putExtra(article,"sleeping");
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        };
        countriesList.setOnItemClickListener(itemClickListener);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu,menuInflater);
        menuInflater.inflate(R.menu.add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        super.onOptionsItemSelected(menuItem);
        switch (menuItem.getItemId()){
            case R.id.add:
                Intent intent = new Intent(getActivity(), AddingProgramOrArticleActivity.class);
                intent.putExtra("variant",0);
                startActivity(intent);
                break;
        }
        return true;
    }

}
