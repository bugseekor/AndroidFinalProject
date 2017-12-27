package ca.on.conestogac.finalproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    List<String> comments = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        viewList();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Button2:
                EditText commentEditText = (EditText) findViewById(R.id.commentEditText);
                String comment = commentEditText.getText().toString();
                comments.add(comment);
                viewList();
                break;
            case R.id.Button3:
                comments.clear();
                viewList();
        }
    }

    public void viewList(){
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, comments);
        ListView listView = (ListView) findViewById(R.id.comment_list);
        listView.setAdapter(adapter);
    }
}
