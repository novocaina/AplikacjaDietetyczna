package com.example.alicja.aplikacjadietetyczna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class cpmActivity extends AppCompatActivity {
    @BindView(R.id.weightcpm_txt)
    EditText weightcpm_txt;
    @BindView(R.id.heightcpm_txt)
    EditText heightcpm_txt;
    @BindView(R.id.age_txt)
    EditText age_txt;
    @BindView(R.id.cpm_txt)
    TextView cpm_txt;
    @BindView(R.id.your_cpm_txt)
    TextView your_cpm_txt;
    @BindView(R.id.sex_list)
    Spinner sex_list;
    @BindView(R.id.activity_list)
    Spinner activity_list;
    String sex;
    double pal;

    @OnClick(R.id.cpm_btn)
    void OnClick() {
        String weightStr = weightcpm_txt.getText().toString();
        String heightStr = heightcpm_txt.getText().toString();
        String ageStr = age_txt.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {

            Toast.makeText(cpmActivity.this, this.getString(R.string.warning_data), Toast.LENGTH_LONG).show();
        } else if (Float.parseFloat(weightStr) <= 0 || Float.parseFloat(weightStr) <= 0 || Integer.parseInt(ageStr) <= 0) {
            Toast.makeText(cpmActivity.this, this.getString(R.string.value_str), Toast.LENGTH_LONG).show();
        } else {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);
            int age = Integer.parseInt(ageStr);
            your_cpm_txt.setVisibility(View.VISIBLE);
            CPM newCPM = new CPM();
            double cpm = newCPM.Count_CPM(weight, height, age, sex, pal);
            String cpmStr = String.format("%.2f", cpm);
            cpm_txt.setText(cpmStr);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpm);
        ButterKnife.bind(this);
        String[] sex_table = {this.getString(R.string.woman), this.getString(R.string.man)};
        String[] act_table = {this.getString(R.string.activity_1), this.getString(R.string.activity_2),
                this.getString(R.string.activity_3), this.getString(R.string.activity_4), this.getString(R.string.activity_5)};
        ArrayAdapter<String> adapter_sx = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sex_table);
        sex_list.setAdapter(adapter_sx);
        ArrayAdapter<String> adapter_act = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, act_table);
        activity_list.setAdapter(adapter_act);

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
    }

}
