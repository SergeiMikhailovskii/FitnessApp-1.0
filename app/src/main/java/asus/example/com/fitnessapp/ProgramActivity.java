package asus.example.com.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ProgramActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        ImageView imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String PROGRAM = "PROGRAM";
        String path = getIntent().getStringExtra(PROGRAM);
        imageView.setImageResource(getResources().getIdentifier(path, "drawable", getPackageName()));
        readFromFile(path);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void readFromFile(String path){
        byte[] buffer = null;
        InputStream inputStream;
        try {
            inputStream = getAssets().open(path);
            int size = inputStream.available();
            buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = new String(buffer);
        textView.setText(data);
    }

}
