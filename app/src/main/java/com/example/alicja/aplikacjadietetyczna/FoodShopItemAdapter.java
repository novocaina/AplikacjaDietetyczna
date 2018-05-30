package com.example.alicja.aplikacjadietetyczna;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alicja on 2018-05-28.
 */

public class FoodShopItemAdapter extends RecyclerView.Adapter<FoodShopItemAdapter.ViewHolder> {
    private ArrayList<FoodShopItem> foodshop = new ArrayList<>();


    FoodShopItemAdapter(ArrayList<FoodShopItem> items) {
        this.foodshop = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list, null);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setFoodName(foodshop.get(position).getName());
        holder.setAmount(foodshop.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return foodshop.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.food)
        TextView foodname;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.image)
        ImageView Image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setFoodName(String name) {

            foodname.setText(name);

        }

        private void setAmount(String name) {

            amount.setText(name);

        }

    }
}
