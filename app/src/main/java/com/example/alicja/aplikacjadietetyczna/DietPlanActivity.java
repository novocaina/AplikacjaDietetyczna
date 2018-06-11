package com.example.alicja.aplikacjadietetyczna;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DietPlanActivity extends AppCompatActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietplan);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                }
        );

        readAppDatabase();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Food> foodSamples=new ArrayList<>();
    private void readAppDatabase() {
        InputStream inputStream =getResources().openRawResource(R.raw.bazaappdietetyczna);
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-16")));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] splitTable=line.split(";");
                Food food=new Food();
                food.setName(splitTable[0]);
                food.setCalories(splitTable[1]);
                food.setPreferences(splitTable[2]);
                food.setMeal(splitTable[3]);
                food.setComponents(splitTable[4]);
                food.setGrams(splitTable[5]);
                food.setPrepare(splitTable[6]);
                food.setPortion(splitTable[7]);
                foodSamples.add(food);

            }

        }
        catch(IOException e){
            Toast.makeText(DietPlanActivity.this,this.getString(R.string.warning_database),Toast.LENGTH_LONG).show();
        }
    }

}
