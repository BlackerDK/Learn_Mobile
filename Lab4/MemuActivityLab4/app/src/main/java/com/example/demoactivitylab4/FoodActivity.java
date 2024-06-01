package com.example.demoactivitylab4;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class FoodActivity extends AppCompatActivity {

    private String[] foods = {"Phở Hà Nội", "Bún Bò Huế", "Mì Quảng", "Hủ Tíu Sài Gòn"};
    private String selectedFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ListView foodListView = findViewById(R.id.food_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, foods);
        foodListView.setAdapter(adapter);
        foodListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFood = foods[position];
            }
        });

        Button orderButton = findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFood != null) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selected_item", selectedFood);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}