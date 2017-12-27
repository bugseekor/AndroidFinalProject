package ca.on.conestogac.finalproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class AsyncActivity extends AppCompatActivity {

    private static String file_url = "http://image.fourwheeler.com/f/242489519+w186+h124+re0+cr1+ar0/icon-1965-ford-1.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Button2:
                AsyncTaskRunner atr = new AsyncTaskRunner();
                atr.execute(file_url);
                break;
            case R.id.Button3:
                openFile();
                break;
        }
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String message;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                int statusCode = connection.getResponseCode(); //get response code
                if (statusCode == HttpURLConnection.HTTP_MOVED_TEMP
                        || statusCode == HttpURLConnection.HTTP_MOVED_PERM){ // if file is moved, then pick new URL
                    file_url = connection.getHeaderField("Location");
                    url = new URL(file_url);
                    connection = (HttpURLConnection)url.openConnection();
                }
                connection.connect();
                InputStream input = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(input);
                OutputStream output = new FileOutputStream(Environment
                        .getExternalStorageDirectory().toString()
                        + "/download/truck.jpg");
                int i = 0;
                while ((i = bis.read()) != -1)
                    output.write(i);
                output.flush();
                output.close();
                input.close();

                message = "download completed\n" + Environment
                        .getExternalStorageDirectory().toString()
                        + "/download/truck.jpg";
            } catch (Exception e) {
                e.printStackTrace();
                message = e.getMessage();
            }
            return message;
        }
        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            TextView ResultTextView = (TextView) findViewById(R.id.ResultTextView);
            ResultTextView.setText(result);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(AsyncActivity.this,
                    "Heads Up!",
                    "file downloading from a web site");
        }

        @Override
        protected void onProgressUpdate(String... text) {
            TextView ResultTextView = (TextView) findViewById(R.id.ResultTextView);
            ResultTextView.setText(text[0]);

        }
    }
    protected void openFile() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "/download/truck.jpg");
        Uri path = Uri.fromFile(file);
        Intent openfile = new Intent(Intent.ACTION_VIEW);
        openfile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        openfile.setDataAndType(path, "image/*");
        startActivity(openfile);
    }
}
