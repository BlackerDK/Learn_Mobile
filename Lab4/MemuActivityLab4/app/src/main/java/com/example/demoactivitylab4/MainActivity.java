package com.example.demoactivitylab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FOOD = 1;
    private static final int REQUEST_CODE_DRINK = 2;

    private TextView selectedItemsTextView;
    private String selectedFood;
    private String selectedDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedItemsTextView = findViewById(R.id.selected_items);

        Button selectFoodButton = findViewById(R.id.select_food_button);
        Button selectDrinkButton = findViewById(R.id.select_drink_button);
        Button clearButton = findViewById(R.id.clear_button);

        selectFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOOD);
            }
        });

        selectDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                startActivityForResult(intent, REQUEST_CODE_DRINK);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFood = null;
                selectedDrink = null;
                selectedItemsTextView.setText("Chưa chọn món");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String selectedItem = data.getStringExtra("selected_item");
            if (requestCode == REQUEST_CODE_FOOD) {
                selectedFood = selectedItem;
            } else if (requestCode == REQUEST_CODE_DRINK) {
                selectedDrink = selectedItem;
            }
            updateSelectedItems();
        }
    }

    private void updateSelectedItems() {
        String selectedItems = "";
        if (selectedFood != null) {
            selectedItems += selectedFood;
        }
        if (selectedDrink != null) {
            if (!selectedItems.isEmpty()) {
                selectedItems += " + ";
            }
            selectedItems += selectedDrink;
        }
        if (selectedItems.isEmpty()) {
            selectedItems = "Chưa chọn món";
        }
        selectedItemsTextView.setText(selectedItems);
    }
}
