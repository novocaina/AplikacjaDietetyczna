package com.example.alicja.aplikacjadietetyczna.Model;

/**
 * Created by Alicja on 2018-04-18.
 */

public class CPM {
float weight;
float height;
int age;

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public double getPal() {
        return pal;
    }

    public void setPal(double pal) {
        this.pal = pal;
    }

    char sex;
double pal;
    public double Count_CPM(float weight, float height, int age, char sex,double pal){
    double CPM;
        if(sex=='k')
        {
            double PPM = 665.09+(9.56*weight)+(1.85*height)-(4.67*age);
            CPM=PPM*pal;

        }
        else
        {
            double PPM = 66.47 + (13.75*weight) + (5*height)-(6.75*age);
            CPM=PPM*pal;

        }
        return CPM;
    }

}
