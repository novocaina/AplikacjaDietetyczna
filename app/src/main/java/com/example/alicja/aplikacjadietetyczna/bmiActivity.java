package com.example.alicja.aplikacjadietetyczna;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class bmiActivity extends AppCompatActivity {
    @BindView(R.id.weight_txt)
    EditText weight_txt;
    @BindView(R.id.height_txt)
    EditText height_txt;
    @BindView(R.id.bmi_txt)
    TextView bmi_txt;
    @BindView(R.id.your_bmi_txt)
    TextView your_bmi_txt;
    @BindView(R.id.bmi_info_txt)
    TextView bmi_info_txt;

    @OnClick(R.id.bmi_btn)
    void OnClick(){
      String weightStr=weight_txt.getText().toString();
        String heightStr=height_txt.getText().toString();

        if(weightStr.isEmpty() && heightStr.isEmpty())
        {
            Toast.makeText(bmiActivity.this,this.getString(R.string.warning_data),Toast.LENGTH_LONG).show();
        }
        else if(Float.parseFloat(weightStr) <=0  || Float.parseFloat(weightStr)<=0)
        {
            Toast.makeText(bmiActivity.this,this.getString(R.string.value_str),Toast.LENGTH_LONG).show();
        }
        else
        {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);
            BMI newBMI=new BMI();
            float bmi=newBMI.BMI_Count(weight,height);
            your_bmi_txt.setVisibility(View.VISIBLE);
            String bmiStr = String.format("%.2f", bmi);
            bmi_txt.setText(bmiStr);
            String bmi_text=BMI_text(bmi);
            bmi_info_txt.setText(bmi_text);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        ButterKnife.bind(this);
    }


    public String BMI_text(float bmi){
        if(bmi<16)
        {return  this.getString(R.string.starvation);}
        else if(bmi>= 16 && bmi <=16.99)
        {return  this.getString(R.string.emaciaton);}
        else if(bmi>= 17 && bmi <=18.49)
        {return  this.getString(R.string.low_weight);}
        else if(bmi>= 18.5 && bmi <=24.99)
        {return  this.getString(R.string.normal_weight);}
        else if(bmi>= 25 && bmi <=29.99)
        {return  this.getString(R.string.obeseI);}
        else if(bmi>= 30 && bmi <=39.99)
        {return  this.getString(R.string.obeseII);}
        else
        {return  this.getString(R.string.obeseIII);}
    }
}
