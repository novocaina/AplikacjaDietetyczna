package com.example.alicja.aplikacjadietetyczna;

/**
 * Created by Alicja on 2018-04-18.
 */

public class BMI {
    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    float weight;
    float height;
    public float BMI_Count(float weight, float height){
        float BMI=weight/((height/100)*(height/100));
        return BMI;
    }
}
