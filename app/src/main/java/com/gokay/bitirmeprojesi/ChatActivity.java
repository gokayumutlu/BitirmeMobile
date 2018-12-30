package com.gokay.bitirmeprojesi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity {

    private String chatUser;
    private int aliciUser;
    private Toolbar toolbar;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        tv=findViewById(R.id.chattext);
        chatUser=getIntent().getStringExtra("user_id");
        aliciUser=getIntent().getIntExtra("alici_id",0);
        String bbb=String.valueOf(aliciUser);
        toolbar=findViewById(R.id.chat_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chatUser);

        tv.setText(bbb);

        Toast.makeText(getApplicationContext(),bbb,Toast.LENGTH_LONG).show();

    }

}
