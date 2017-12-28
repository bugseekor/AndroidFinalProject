package ca.on.conestogac.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Button2:
                showToken();
                break;
        }
    }

    private void showToken() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(FirebaseActivity.this, token, Toast.LENGTH_SHORT).show();
    }

}
