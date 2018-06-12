package com.example.alicja.aplikacjadietetyczna;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DietPlanActivity extends AppCompatActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.breakfast_txt)
    TextView breakfast_txt;
    @BindView(R.id.breakfast_kcal)
    TextView breakfast_kcal;
    @BindView(R.id.secondbreakfast_txt)
    TextView second_breakfast_txt;
    @BindView(R.id.secondbreakfast_kcal)
    TextView second_breakfast_kcal;
    @BindView(R.id.dinner_txt)
    TextView dinner_txt;
    @BindView(R.id.dinner_kcal)
    TextView dinner_kcal;
    @BindView(R.id.dessert_txt)
    TextView dessert_txt;
    @BindView(R.id.dessert_kcal)
    TextView dessert_kcal;
    @BindView(R.id.supper_txt)
    TextView supper_txt;
    @BindView(R.id.supper_kcal)
    TextView supper_kcal;
    @BindView(R.id.day_list)
    Spinner day_spinner;
    private List<Food> foodSamples;
    ListItemHelper db;
    double cpm_daily;
    int a, b, c, d, e;
    Food food1, food2, food3, food4, food5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietplan);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        String[] day_table = {this.getString(R.string.mon), this.getString(R.string.tue),
                this.getString(R.string.wed),this.getString(R.string.thu),
                this.getString(R.string.fri),this.getString(R.string.sat),this.getString(R.string.sun)};
        ArrayAdapter<String> adapter_sx = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, day_table);
        day_spinner.setAdapter(adapter_sx);
        day_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                  @Override
                                                  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                      switch ((int) l) {
                                                          case 0:
                                                              setDailyMenu(1);
                                                              break;
                                                          case 1:
                                                              setDailyMenu(6);
                                                              break;
                                                          case 2:
                                                              setDailyMenu(11);
                                                              break;
                                                          case 3:
                                                              setDailyMenu(16);
                                                              break;
                                                          case 4:
                                                              setDailyMenu(21);
                                                              break;
                                                          case 5:
                                                              setDailyMenu(26);
                                                              break;
                                                          case 6:
                                                              setDailyMenu(31);
                                                              break;

                                                      }
                                                  }

                                                  @Override
                                                  public void onNothingSelected(AdapterView<?> adapterView) {

                                                  }
                                              });
                navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                       /* int id = menuItem.getItemId();
                        if (id == R.id.nav_mon) {
                            setDailyMenu(1);
                        }
                        if (id == R.id.nav_tue) {
                            setDailyMenu(6);
                        }
                        if (id == R.id.nav_wed) {
                            setDailyMenu(11);
                        }
                        if (id == R.id.nav_thu) {
                            setDailyMenu(16);
                        }
                        if (id == R.id.nav_fri) {
                            setDailyMenu(21);
                        }
                        if (id == R.id.nav_sat) {
                            setDailyMenu(26);
                        }
                        if (id == R.id.nav_sun) {
                            setDailyMenu(31);
                        }
                        */
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
        db = new ListItemHelper(this);
        if (db.getUserCount() != 0) {
            User user = db.getUser();
            if (user.getGoal().equals("r")) {
                cpm_daily = user.getCpm() - 200;
            } else if (user.getGoal().equals("m")) {
                cpm_daily = user.getCpm() + 200;
            } else {
                cpm_daily = user.getCpm();
            }
        } else {
            Toast.makeText(DietPlanActivity.this, this.getString(R.string.base_alert), Toast.LENGTH_LONG).show();
        }

        readAppDatabase();
        Random r = new Random();
        double sum = 0;
        double cpm1 = cpm_daily - 150;
        double cpm2 = cpm_daily + 150;
        int max = foodSamples.size() - 1;

        for (int i = 0; i < 7; i++) {
            while (!((sum > cpm1) & (sum < cpm2))) {

                a = r.nextInt((max - 1) + 1) + 1;
                b = r.nextInt((max - 1) + 1) + 1;
                c = r.nextInt((max - 1) + 1) + 1;
                d = r.nextInt((max - 1) + 1) + 1;
                e = r.nextInt((max - 1) + 1) + 1;
                food1 = foodSamples.get(a);
                food2 = foodSamples.get(b);
                food3 = foodSamples.get(c);
                food4 = foodSamples.get(d);
                food5 = foodSamples.get(e);
                sum = Double.parseDouble(food1.getCalories()) + Double.parseDouble(food2.getCalories()) +
                        Double.parseDouble(food3.getCalories()) + Double.parseDouble(food4.getCalories()) +
                        Double.parseDouble(food5.getCalories());
            }
            DailyMeal meal1 = new DailyMeal(food1.getName(), Double.parseDouble(food1.getCalories()));
            db.insertMeal(meal1);
            DailyMeal meal2 = new DailyMeal(food2.getName(), Double.parseDouble(food2.getCalories()));
            db.insertMeal(meal2);
            DailyMeal meal3 = new DailyMeal(food3.getName(), Double.parseDouble(food3.getCalories()));
            db.insertMeal(meal3);
            DailyMeal meal4 = new DailyMeal(food4.getName(), Double.parseDouble(food4.getCalories()));
            db.insertMeal(meal4);
            DailyMeal meal5 = new DailyMeal(food5.getName(), Double.parseDouble(food5.getCalories()));
            db.insertMeal(meal5);

        }
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


    private void readAppDatabase() {
        foodSamples = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.bazaappdietetyczna);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-16")));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] splitTable = line.split(";");
                Food food = new Food();
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

        } catch (IOException e) {
            Toast.makeText(DietPlanActivity.this, this.getString(R.string.warning_database), Toast.LENGTH_LONG).show();
        }
    }

    private void setDailyMenu(int i) {
        DailyMeal meal_daily = db.getMeal(i);
        breakfast_txt.setText(meal_daily.getName());
        breakfast_kcal.setText(String.valueOf(meal_daily.getCalories()));
        meal_daily = db.getMeal(i + 1);
        second_breakfast_txt.setText(meal_daily.getName());
        second_breakfast_kcal.setText(String.valueOf(meal_daily.getCalories()));
        meal_daily = db.getMeal(i + 2);
        dinner_txt.setText(meal_daily.getName());
        dinner_kcal.setText(String.valueOf(meal_daily.getCalories()));
        meal_daily = db.getMeal(i + 3);
        dessert_txt.setText(meal_daily.getName());
        dessert_kcal.setText(String.valueOf(meal_daily.getCalories()));
        meal_daily = db.getMeal(i + 4);
        supper_txt.setText(meal_daily.getName());
        supper_kcal.setText(String.valueOf(meal_daily.getCalories()));
    }


}


