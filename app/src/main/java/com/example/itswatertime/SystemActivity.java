package com.example.itswatertime;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class SystemActivity extends AppCompatActivity {
    public SystemActivity(){ }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText age;
        final EditText height;
        final EditText weight;
        Button btnSave;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_system);
        age = (EditText) findViewById(R.id.age); //나이 에디트 텍스트에서 받아온 값 age변수에 저장
        height = (EditText) findViewById(R.id.height); //키 에디트 텍스트에서 받아온 값 height변수에 저장
        weight = (EditText) findViewById(R.id.weight); //몸무게 에디트 텍스트에서 받아온 값 weight변수에 저장

        TextView daydrink;
        daydrink = (TextView) findViewById(R.id.drinkday); //오늘 마셔야 할 물의 양 출력하는 텍스트
        btnSave = (Button) findViewById(R.id.btnsave);

        btnSave.setOnClickListener(new View.OnClickListener() { //사용자가 저장 버튼을 누르면
            @Override
            public void onClick(View v) {
                double heightwater = Double.parseDouble(height.getText().toString()); //받은 키 실수형으로 변환
                double weightwater = Double.parseDouble(weight.getText().toString()); //받은 몸무게 실수형으로 변환
                daydrink.setText(" : " + ((heightwater + weightwater) / 100) + "L");  //마셔야 할 물의 양 계산

                String str = "입력이 완료되었습니다. \n 오늘 마셔야 할 물의 양은 " + daydrink.getText().toString() + "입니다.";
                //CharSequence 형식의 문자열이므로 toString()을 불러서 String 객체로 변환
                Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void onToggleClicked(View view){
        boolean on = ((ToggleButton) view).isChecked();
        if(on){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("It's Water Time");
            builder.setContentText("잠깐! 물 마시고 기지개 필 시간입니다");

            builder.setColor(Color.RED);
            // 사용자가 탭을 클릭하면 자동 제거
            builder.setAutoCancel(true);

            // 알림 표시
            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
            }

            // id값은
            // 정의해야하는 각 알림의 고유한 int값
            notificationManager.notify(1, builder.build());

        }
        else{
            NotificationManagerCompat.from(this).cancel(1);
        }
    }
}