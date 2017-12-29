package ca.on.conestogac.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SQLliteActivity extends AppCompatActivity {

    private AllDB db = new AllDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqllite);
    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Button2:
                selectAll();
                break;
            case R.id.Button3:
                insertNote();
                break;
            case R.id.Button4:
                deleteAll();
                break;
        }
    }
    public void selectAll(){
        StringBuilder sb = new StringBuilder();
        ArrayList<Note> notes = db.getNotes();
        for (Note note : notes) {
            sb.append(note.getLine() + "\n");
        }
        TextView ResultTextView = (TextView) findViewById(R.id.ResultTextView);
        ResultTextView.setText(sb.toString());
    }
    public void insertNote() {
        EditText noteEditText = (EditText) findViewById(R.id.noteEditText);
        String line = noteEditText.getText().toString();
        try {
            Note note = new Note(line);
            long insertID = db.insertNote(note);
            noteEditText.setText("");
            selectAll();
        } catch (Exception e) {
            TextView ResultTextView = (TextView) findViewById(R.id.ResultTextView);
            ResultTextView.setText(e.getMessage());
        }
    }
    public void deleteAll(){
        db.deleteAllNotes();
        selectAll();
    }
}
