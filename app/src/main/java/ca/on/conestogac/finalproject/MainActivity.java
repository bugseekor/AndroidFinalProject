package ca.on.conestogac.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//commit test

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Button2:
                sendMessage1(view);
                break;
            case R.id.Button3:
                sendMessage2(view);
                break;
        }
    }
    public void sendMessage1(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    public void sendMessage2(View view){
        Intent intent = new Intent(this, AsyncActivity.class);
        startActivity(intent);
    }
}
