package com.example.alicja.aplikacjadietetyczna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid=findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for(int i=0;i<mainGrid.getChildCount();i++) {
            CardView cardView=(CardView)mainGrid.getChildAt(i);
            final int finall=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finall==0)
                    {
                        Intent intent=new Intent(MainActivity.this,bmiActivity.class);
                        startActivity(intent);
                    }
                    if(finall==1)
                    {
                        Intent intent=new Intent(MainActivity.this,cpmActivity.class);
                        startActivity(intent);
                    }
                    if(finall==2)
                    {
                        Intent intent=new Intent(MainActivity.this,DietInfoActivity.class);
                        startActivity(intent);
                    }
                    if(finall==3)
                    {
                        Intent intent=new Intent(MainActivity.this,DietPlanActivity.class);
                        startActivity(intent);
                    }
                    if(finall==4)
                    {
                        Intent intent=new Intent(MainActivity.this,FoodListActivity.class);
                        startActivity(intent);
                    }
                    if(finall==5)
                    {
                        Intent intent=new Intent(MainActivity.this,cateringActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
