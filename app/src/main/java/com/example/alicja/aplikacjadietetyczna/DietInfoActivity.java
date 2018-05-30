package com.example.alicja.aplikacjadietetyczna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DietInfoActivity extends AppCompatActivity {
    @BindView(R.id.weight_txt)
    EditText weight_txt;
    @BindView(R.id.height_txt)
    EditText height_txt;
    @BindView(R.id.age_txt)
    EditText age_txt;
    @BindView(R.id.sex_list)
    Spinner sex_list;
    @BindView(R.id.activity_list)
    Spinner activity_list;
    @BindView(R.id.target_list)
    Spinner target_list;
    @BindView(R.id.num_list)
    Spinner num_list;
    @BindView(R.id.pref_list)
    Spinner pref_list;
    String sex, target, preference;
    float weight,height;
    int age,number;
    double pal;

    @OnClick(R.id.save_btn)
    void OnClick() {
        String weightStr = weight_txt.getText().toString();
        String heightStr = height_txt.getText().toString();
        String ageStr = age_txt.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {

            Toast.makeText(DietInfoActivity.this, this.getString(R.string.warning_data), Toast.LENGTH_LONG).show();
        } else if (Float.parseFloat(weightStr) <= 0 || Float.parseFloat(weightStr) <= 0 || Integer.parseInt(ageStr) <= 0) {
            Toast.makeText(DietInfoActivity.this, this.getString(R.string.value_str), Toast.LENGTH_LONG).show();
        } else {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);
            int age = Integer.parseInt(ageStr);
            BMI new_bmi = new BMI();
            float bmi = new_bmi.BMI_Count(weight, height);
            CPM newCPM = new CPM();
            double cpm = newCPM.Count_CPM(weight, height, age, sex, pal);
        }
            try {
               DietInfoDatabaseHelper db=new DietInfoDatabaseHelper(this);
               db.insertUser(db,weight,height,age,sex,target,number,preference);
                Toast.makeText(DietInfoActivity.this, this.getString(R.string.success), Toast.LENGTH_LONG).show();
        }
        catch(DatabaseException ex){
            Toast.makeText(DietInfoActivity.this, this.getString(R.string.warning_database), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietinfo);
        ButterKnife.bind(this);
        String[] sex_table = {this.getString(R.string.woman), this.getString(R.string.man)};
        String[] act_table = {this.getString(R.string.activity_1), this.getString(R.string.activity_2),
                this.getString(R.string.activity_3), this.getString(R.string.activity_4), this.getString(R.string.activity_5)};
        String[] target_table = {this.getString(R.string.reduction), this.getString(R.string.cons_weight), this.getString(R.string.mass)};
        String[] num_table = {"4", "5", "6"};
        String[] pref_table = {this.getString(R.string.none), this.getString(R.string.veget)};
        ArrayAdapter<String> adapter_sx = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sex_table);
        sex_list.setAdapter(adapter_sx);
        ArrayAdapter<String> adapter_act = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, act_table);
        activity_list.setAdapter(adapter_act);
        ArrayAdapter<String> adapter_trg = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, target_table);
        target_list.setAdapter(adapter_trg);
        ArrayAdapter<String> adapter_num = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, num_table);
        num_list.setAdapter(adapter_num);
        ArrayAdapter<String> adapter_pref = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pref_table);
        pref_list.setAdapter(adapter_pref);

        sex_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {


                switch ((int) position) {
                    case 0:
                        sex = "k";
                        break;
                    case 1:
                        sex = "m";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        activity_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {


                switch ((int) position) {
                    case 0:
                        pal = 1.2;
                        break;
                    case 1:
                        pal = 1.375;
                        break;
                    case 2:
                        pal = 1.55;
                        break;
                    case 3:
                        pal = 1.725;
                        break;
                    case 4:
                        pal = 1.9;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        target_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {


                switch ((int) position) {
                    case 0:
                        target = "r";
                        break;
                    case 1:
                        target = "u";
                        break;
                    case 2:
                        target = "m";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        num_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {


                switch ((int) position) {
                    case 0:
                        number = 4;
                        break;
                    case 1:
                        number = 5;
                        break;
                    case 2:
                        number = 6;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        pref_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {


                switch ((int) position) {
                    case 0:
                        preference = "n";
                        break;
                    case 1:
                        preference = "w";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }
}

