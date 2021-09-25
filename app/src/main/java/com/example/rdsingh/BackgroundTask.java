package com.example.rdsingh;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rdsingh.seaquamclubsapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BackgroundTask extends AsyncTask<String, GetClubs, String>
{

    String Url = "http://techhelp101.000webhostapp.com/php/getClubs.php";

    private Context ctx;

    Activity activity;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<GetClubs> arrayList = new ArrayList<>();



    public BackgroundTask (Context ctx)
    {
        this.ctx = ctx;
        activity = (Activity)ctx;


    }

    @Override
    protected void onPreExecute() {

        recyclerView = (RecyclerView)activity.findViewById(R.id.rv_clubs);

        layoutManager = new LinearLayoutManager(ctx);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new ClubAdapter(arrayList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected String doInBackground(String... params) {



        try {
            URL url = new URL(Url);

            String userName = params[1];

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("userName", "UTF-8")+"="+URLEncoder.encode(userName, "UTF-8");

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            Log.i("printPostString", "daPost");
            Log.e("postString", post_data);
            Log.d("finishPost", "itsDone");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while((line=bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line + "/n");
            }
            bufferedReader.close();
            inputStream.close();



            httpURLConnection.disconnect();
            String jsonString = stringBuilder.toString().trim();

            Log.e("JSON STRING", jsonString);

            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray jsonArray = jsonObject.getJSONArray("resultArray");

            int count = 0;

            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;
                GetClubs getClubs = new GetClubs(JO.getString("name"));
                publishProgress(getClubs);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(GetClubs... values) {

        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }
}