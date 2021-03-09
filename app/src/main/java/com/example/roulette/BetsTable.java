package com.example.roulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class BetsTable extends AppCompatActivity {

    private List<ImageButton> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bets_table);
        init();

    }
    //инициализации блок
    private void init() {
        buttons.add(findViewById(R.id.btnZero));
        buttons.add(findViewById(R.id.btnBlack));
        buttons.add(findViewById(R.id.btnRed));
        buttons.add(findViewById(R.id.btn_1_red));
        buttons.add(findViewById(R.id.btn_2_black));
        buttons.add(findViewById(R.id.btn_3_red));
        buttons.add(findViewById(R.id.btn_4_black));
        buttons.add(findViewById(R.id.btn_5_red));
        buttons.add(findViewById(R.id.btn_6_black));
        buttons.add(findViewById(R.id.btn_7_red));
        buttons.add(findViewById(R.id.btn_8_black));
        buttons.add(findViewById(R.id.btn_9_red));
        buttons.add(findViewById(R.id.btn_10_black));
        buttons.add(findViewById(R.id.btn_11_black));
        buttons.add(findViewById(R.id.btn_12_red));
        buttons.add(findViewById(R.id.btn_13_black));
        buttons.add(findViewById(R.id.btn_14_red));
        buttons.add(findViewById(R.id.btn_15_black));
        buttons.add(findViewById(R.id.btn_16_red));
        buttons.add(findViewById(R.id.btn_17_black));
        buttons.add(findViewById(R.id.btn_18_red));
        buttons.add(findViewById(R.id.btn_19_red));
        buttons.add(findViewById(R.id.btn_20_black));
        buttons.add(findViewById(R.id.btn_21_red));
        buttons.add(findViewById(R.id.btn_22_black));
        buttons.add(findViewById(R.id.btn_23_red));
        buttons.add(findViewById(R.id.btn_24_black));
        buttons.add(findViewById(R.id.btn_25_red));
        buttons.add(findViewById(R.id.btn_26_black));
        buttons.add(findViewById(R.id.btn_27_red));
        buttons.add(findViewById(R.id.btn_28_black));
        buttons.add(findViewById(R.id.btn_29_black));
        buttons.add(findViewById(R.id.btn_30_red));
        buttons.add(findViewById(R.id.btn_31_black));
        buttons.add(findViewById(R.id.btn_32_red));
        buttons.add(findViewById(R.id.btn_33_black));
        buttons.add(findViewById(R.id.btn_34_black));
        buttons.add(findViewById(R.id.btn_35_red));
        buttons.add(findViewById(R.id.btn_36_black));
        createButtons();
    }


    //присвоить каждой кнопке слушатель
    private void createButtons() {
            for (ImageButton btn : buttons) {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        turnToRoulette(btn.getTag());
                    }
                });
            }
    }

    //переход на активити рулетки
    private void turnToRoulette(Object btnId){
        Intent intent = new Intent(BetsTable.this, MainActivity.class);
        intent.putExtra("BET", ((String) btnId));
        startActivity(intent);
    }


}