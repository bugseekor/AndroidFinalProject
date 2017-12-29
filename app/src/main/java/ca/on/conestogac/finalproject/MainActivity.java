package ca.on.conestogac.finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.actions.NoteIntents;

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
            case R.id.Button4:
                sendMessage3(view);
                break;
            case R.id.Button5:
                sendMessage4(view);
                break;
            case R.id.Button6:
                startService(new Intent(this, MyService.class));
                finish();
                System.exit(0);
                break;
            case R.id.Button7:
                Intent intentText = new Intent(Intent.ACTION_SENDTO);
                intentText.setData(Uri.parse("smsto:" + Uri.encode("1234")));
                startActivity(intentText);
                break;
            case R.id.Button8:
                Intent intentDial = new Intent(Intent.ACTION_DIAL);
                intentDial.setData(Uri.parse("tel:" + Uri.encode("1234")));
                startActivity(intentDial);
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
    public void sendMessage3(View view){
        Intent intent = new Intent(this, FirebaseActivity.class);
        startActivity(intent);
    }
    public void sendMessage4(View view){
        Intent intent = new Intent(this, SQLliteActivity.class);
        startActivity(intent);
    }
}
