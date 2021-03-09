package com.example.roulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.roulette.Bank.getBank;
import static com.example.roulette.Bank.setBank;

public class MainActivity extends AppCompatActivity {

//    //test
//    private String tvResultTest = "31 BLACK";

    private String resultColor;
    private TextView tvResult;
    private TextView tvCurrentBet;
    private TextView tvBank;
    private String betNumber;
    private ImageView rul;
    private Random random;
    private int old_deegre = 0;
    private int deegre = 0;
    private static final float FACTOR = 4.86f;
    private String[] numbers = {
            "32 RED", "15 BLACK", "19 RED", "4 BLACK",
            "21 RED", "2 BLACK", "25 RED", "17 BLACK", "34 RED",
            "6 BLACK", "27 RED", "13 BLACK", "36 RED", "11 BLACK", "30 RED",
            "8 BLACK", "23 RED", "10 BLACK", "5 RED", "24 BLACK", "16 RED", "33 BLACK",
            "1 RED", "20 BLACK", "14 RED", "31 BLACK", "9 RED", "22 BLACK", "18 RED",
            "29 BLACK", "7 RED", "28 BLACK", "12 RED", "35 BLACK", "3 RED", "26 BLACK", "ZERO"};

    public String getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(String betNumber) {
        this.betNumber = betNumber;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //инициализация
    private void init() {
        tvResult = findViewById(R.id.tvResult);
        tvCurrentBet = findViewById(R.id.tvCurrentBet);
        tvBank = findViewById(R.id.tvBank);
        rul = findViewById(R.id.rul);
        random = new Random();
        //ставка игрока (с Teg)
        betNumber = getIntent().getStringExtra("BET");
        tvCurrentBet.setText(betNumber);
        refreshScoreBank();


    }

    //обновить значение в банке
    public void refreshScoreBank() {
        tvBank.setText(Integer.toString(getBank()));
    }

    //обновить банк
    public void refreshBank() {
        if (Bank.getBank() == 0) {
            Bank.setBank(10000);
            Toast.makeText(this, "Bank has been refreshed!", Toast.LENGTH_SHORT).show();
        }
    }

    //крутить колесо
    public void onClickStart(View view) {
        Button btn = findViewById(R.id.btn_menu_bets);
        btn.setClickable(false);
        refreshScoreBank();
        if (Bank.getBank() != 0) {

            old_deegre = deegre % 360;
            deegre = random.nextInt(3600) + 720;
            RotateAnimation rotate = new RotateAnimation(old_deegre, deegre, RotateAnimation.RELATIVE_TO_SELF,
                    0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(3600);
            rotate.setFillAfter(true);
            rotate.setInterpolator(new DecelerateInterpolator());
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    tvResult.setText("");

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    resultColor = getResult(360 - (deegre % 360));
                    resultColor = resultColor.substring(resultColor.indexOf(" ") + 1);// result = RED or BLACK
                    tvResult.setText(getResult(360 - (deegre % 360)));
                    gameLogic();
                    Button btn = findViewById(R.id.btn_menu_bets);
                    btn.setClickable(true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            rul.startAnimation(rotate);
        } else {
            refreshBank();
        }

    }

    private String getResult(int deegre) {
        String text = "";

        int factor_x = 1;
        int factor_y = 3;
        for (int i = 0; i < 37; i++) {
            if (deegre >= (FACTOR * factor_x) && deegre < (FACTOR * factor_y)) {
                text = numbers[i];
            }
            factor_x += 2;
            factor_y += 2;
        }
        if (deegre >= (FACTOR * 73) && deegre < 360 || deegre >= 0 && deegre < (FACTOR * 1))
            text = numbers[numbers.length - 1];

        return text;
    }

    //переход на активити ставок
    public void onClickBetsMenu(View view) {
        Intent intent = new Intent(MainActivity.this, BetsTable.class);
        startActivity(intent);
    }

    //логика игры
    public void gameLogic() {
        //совпадение по конкретной ставке
        if ((tvResult.getText()).equals(tvCurrentBet.getText())) {
            Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_SHORT).show();
            Bank.setBank(getBank() + 35000);
        //совпадение цвета
        } else if (resultColor.contentEquals(tvCurrentBet.getText())) {
            Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_SHORT).show();
            Bank.setBank(getBank() + 1000);
        //если не угадали
        } else {
            Toast.makeText(MainActivity.this, "You lose!", Toast.LENGTH_SHORT).show();
            Bank.setBank(getBank() - 1000);
        }
        //test method
//        if ((tvResultTest).contentEquals(tvCurrentBet.getText())) {
//            Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_SHORT).show();
//            Bank.setBank(getBank() + 35000);
        refreshBank();
        refreshScoreBank();
    }

}