package asus.example.com.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ArticleActivity extends AppCompatActivity {


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String article = "ARTICLE";
        String path = getIntent().getStringExtra(article);
        textView = (TextView) findViewById(R.id.article);
        readFromFile(path);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    private void readFromFile(String path){
        byte[] buffer = null;
        InputStream is;
        try {
            is = getAssets().open(path);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = new String(buffer);
        textView.setText(data);
    }
}
