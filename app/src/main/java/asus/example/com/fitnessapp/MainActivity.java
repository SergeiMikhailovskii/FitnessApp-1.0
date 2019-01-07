package asus.example.com.fitnessapp;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private View main;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = findViewById(R.id.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        toolbar.setLogo(R.mipmap.ic_launcher);
        Fragment fragment = new ProgramsFragment();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.share, menu);
        getMenuInflater().inflate(R.menu.screen,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"Hi, I'm using FitnessApp. Please, join us!");
                startActivity(intent);
                return true;
            case R.id.screen:
                imageView.setVisibility(View.VISIBLE);
                Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
                imageView.setImageBitmap(b);
                main.setBackgroundColor(Color.parseColor("#999999"));
                String filename = "screen_for_print";
                String path = Environment.getExternalStorageDirectory().toString() + "/" + filename;
                File imageFile = new File(path);

                try (OutputStream out = new FileOutputStream(imageFile)) {
                    b.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        imageView.setVisibility(View.GONE);
                        imageView.setImageResource(0);
                        main.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        FragmentTransaction ft;
        switch (item.getItemId()) {
            case R.id.programs:
                fragment = new ProgramsFragment();
                break;
            case R.id.indications:
                fragment = new IndicationsFragment();
                break;
            case R.id.calculator:
                fragment = new CalculatorFragment();
                break;
            case R.id.articles:
                fragment = new ArticlesFragment();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        return true;
    }
}