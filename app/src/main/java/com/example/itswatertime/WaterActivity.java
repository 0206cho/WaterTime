package com.example.itswatertime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WaterActivity extends AppCompatActivity {
    public WaterActivity(){ }

    TextView receiveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnstore; //도전 물양 입력 후 저장버튼
        final EditText challenge; //도전 물 양 에디트텍스트
        TextView daydrink;  //마셔야 할 물 양 표시해주는 텍스트뷰

        Button btnadd;
        final EditText one;  //마신 물 양 에디트텍스트
        TextView onedrink;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        btnstore = (Button) findViewById(R.id.btnstore);
        challenge = (EditText) findViewById(R.id.drinkamount); //목표물양 에디트 텍스트에서 받아온 값 변수에 저장
        daydrink = (TextView) findViewById(R.id.daydrink); //오늘 마셔야 할 물의 양 출력하는 텍스트

        btnadd = (Button) findViewById(R.id.btnadd);
        one = (EditText) findViewById(R.id.drinkwater); //마신 물 양 에디트 텍스트에서 받아온 값 변수에 저장
        onedrink = (TextView) findViewById(R.id.onedrink); //마신 물의 양 출력하는 텍스트

        btnstore.setOnClickListener(new View.OnClickListener() { //사용자가 저장 버튼을 누르면
            @Override
            public void onClick(View v) {
                daydrink.setText(challenge.getText().toString());
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() { //사용자가 추가 버튼을 누르면
            @Override
            public void onClick(View v) {
                double ww = Double.parseDouble(onedrink.getText().toString()); //이미 마신 물의 양 실수로 변환
                double water = Double.parseDouble(one.getText().toString()); //지금 마신 물의 양 실수로 변환

                onedrink.setText("");
                onedrink.setText((ww + water) + "");
                one.setText(""); //칸 빈칸으로 만들기

            }
        });
    }
}