package com.example.rdsingh;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rdsingh.seaquamclubsapp.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;

import layout.ClubList;


public class MainActivity extends AppCompatActivity {

    TextView t;
    public Button loginBtn;
    public Button signUpBtn;
    private EditText mSearchBoxEditText;


    EditText username, Password;


    private TextView mUrlDisplayTextView;

    private TextView mSearchResultsTextView;


    public void init() {


        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });
    }




    public void init1(){


        signUpBtn = (Button)findViewById(R.id.registerBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, SignUpActivity.class));


            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       username = findViewById(R.id.userName);
        Password = findViewById(R.id.password);



        t = (TextView) findViewById(R.id.userName);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "font/atr.ttf");
        t.setTypeface(myCustomFont);

        t = (TextView) findViewById(R.id.password);
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(), "font/atr.ttf");
        t.setTypeface(myCustomFont2);

        t = (TextView) findViewById(R.id.loginBtn);
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(), "font/atr.ttf");
        t.setTypeface(myCustomFont3, Typeface.BOLD);

        t = (TextView) findViewById(R.id.registerBtn);
        Typeface myCustomFont4 = Typeface.createFromAsset(getAssets(), "font/atr.ttf");
        t.setTypeface(myCustomFont4, Typeface.BOLD);

        init1();
        init();

    }

    public void onLogin() {
        String user_name = username.getText().toString();
        String password = Password.getText().toString();

        String type = "login";
        WebhostQueryLoginTask webhostQueryLoginTask = new WebhostQueryLoginTask(this);
        webhostQueryLoginTask.execute(type, user_name, password);

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(type, user_name);

    }

    public class WebhostQueryLoginTask extends AsyncTask<String, Void, String>
    {

        private Context mContext;
        public String result = "";
        private android.app.AlertDialog alertDialog;



        WebhostQueryLoginTask (Context ctx)
        {
            mContext = ctx;
        }
        @Override
        protected String doInBackground(String... params) {

            String type = params[0];
            String url = "http://techhelp101.000webhostapp.com/php/login.php";
            if (type.equals("login"))
            {
                try {

                    URL url1 = new URL(url);

                    String user_name = params[1];
                    String password = params[2];

                    HttpURLConnection httpUrlConnection = (HttpURLConnection) url1.openConnection();
                    httpUrlConnection.setRequestMethod("POST");

                    httpUrlConnection.setDoOutput(true);
                    httpUrlConnection.setDoInput(true);

                    OutputStream outputStream = httpUrlConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"+
                            URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");

                    Log.i("printPostString", "daPost");
                    Log.e("postString", post_data);
                    Log.d("finishPost", "itsDone");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpUrlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                    result = "";
                    String line;

                    while((line = bufferedReader.readLine()) != null)
                    {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpUrlConnection.disconnect();

                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("result", result);

            username = (EditText) findViewById(R.id.userName);
            Password = (EditText) findViewById(R.id.password);
            if(result.trim().equals("true") ) {
                startActivity(new Intent(MainActivity.this ,StartupActivity.class));
                username.setBackgroundResource(R.color.white);
                Password.setBackgroundResource(R.color.white);
            } else if (result.trim().equals("false")){
                if(Password.getText().toString().equals("") && username.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Missing Multiple Fields!\nPlease check fields!",Toast.LENGTH_LONG).show();
                    username.setBackgroundResource(R.color.red);
                    Password.setBackgroundResource(R.color.red);
                }
                else if(username.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(),"Missing Username Field!\nPlease check and Try Again!",Toast.LENGTH_LONG).show();
                    username.setBackgroundResource(R.color.red);
                    Password.setBackgroundResource(R.color.white);
                }
                else if(Password.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Missing Password Field!\nPlease check and Try Again!",Toast.LENGTH_LONG).show();
                    Password.setBackgroundResource(R.color.red);
                    username.setBackgroundResource(R.color.white);
                }
                else {
                    Toast.makeText(getBaseContext(), "Invalid Username/Password!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(result.trim().equals("account not active")){
                Toast.makeText(getBaseContext(),"Pleas Activate Account!\nTry again!",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getBaseContext(),"Invalid Username!!",Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}


