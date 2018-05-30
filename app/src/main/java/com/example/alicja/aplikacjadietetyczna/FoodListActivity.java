package com.example.alicja.aplikacjadietetyczna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodListActivity extends AppCompatActivity {
    @OnClick(R.id.add_food_btn)
    void onAddFoodClick() {
        Intent intent = new Intent(this,FoodListItem.class);
        startActivity(intent);
    }
    @OnClick(R.id.see_list_btn)
    void onSeeListClick() {
        Intent intent2 = new Intent(this,FoodListView.class);
        startActivity(intent2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        ButterKnife.bind(this);
    }
}
