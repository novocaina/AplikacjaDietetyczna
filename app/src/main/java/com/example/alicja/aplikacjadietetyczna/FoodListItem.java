package com.example.alicja.aplikacjadietetyczna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodListItem extends AppCompatActivity {
    ArrayList<FoodShopItem> items = new ArrayList<>();
    @BindView(R.id.name_txt)
    TextView name_txt;
    @BindView(R.id.amount_txt)
    TextView amount_txt;
    @BindView(R.id.prop_list)
    Spinner prop_list;

    @OnClick(R.id.add_item_btn)
    void OnClick() {
        String nameStr = name_txt.getText().toString();
        String amountStr = amount_txt.getText().toString();


        if (nameStr.isEmpty() || amountStr.isEmpty()) {
            Toast.makeText(FoodListItem.this, this.getString(R.string.warning_data), Toast.LENGTH_LONG).show();
        }
        amountStr = amountStr + " " + prop_list.getSelectedItem().toString();
        try {
            items.add(new FoodShopItem(nameStr, amountStr));
            Toast.makeText(FoodListItem.this, this.getString(R.string.list_success), Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(FoodListItem.this, this.getString(R.string.list_error), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_item);
        ButterKnife.bind(this);
        String[] prop_table = {this.getString(R.string.number), this.getString(R.string.gram), this.getString(R.string.kilogram), this.getString(R.string.litr), this.getString(R.string.mililitr)};
        ArrayAdapter<String> adapter_pr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prop_table);
        prop_list.setAdapter(adapter_pr);


        Intent intent = new Intent(this, FoodListView.class);
        intent.putExtra("mylist", items);
        
    }
}
