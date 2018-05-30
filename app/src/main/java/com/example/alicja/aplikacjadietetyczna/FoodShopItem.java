package com.example.alicja.aplikacjadietetyczna;

import java.io.Serializable;

/**
 * Created by Alicja on 2018-05-28.
 */

public class FoodShopItem implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String amount;

    public FoodShopItem(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }
}
