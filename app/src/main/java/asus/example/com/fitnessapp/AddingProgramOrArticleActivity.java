package asus.example.com.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;

public class AddingProgramOrArticleActivity extends AppCompatActivity {

    private EditText title;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_program_or_article);
        Intent intent = getIntent();
        int var = intent.getIntExtra("variant",0);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setSelection(var);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        title = findViewById(R.id.title);
        message = findViewById(R.id.content);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                this.finish();
                return true;
            case R.id.save:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"mikhaiovskii.s@tut.by"});
                email.putExtra(Intent.EXTRA_SUBJECT, title.getText().toString());
                email.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Enter email client"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }


}
