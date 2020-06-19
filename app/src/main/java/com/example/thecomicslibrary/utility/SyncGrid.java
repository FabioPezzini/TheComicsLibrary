package com.example.thecomicslibrary.utility;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListView;
import com.example.thecomicslibrary.domain.Comic;
import com.example.thecomicslibrary.domain.ComicAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SyncGrid extends AsyncTask<String,String,Void> {
    private String endpoint;
    private AbsListView gridView;
    private Activity activity;
    private Context context;
    private ArrayList<Comic> comicsList;
    private String generalIcon = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            NetworkManager networkManager = new NetworkManager(context);

            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String json;
            while ((json = bufferedReader.readLine()) != null) {
                sb.append(json).append("\n");
            }
            try {
                loadIntoListView(sb.toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void Void) {

        if(generalIcon != null) {
            changeIconTitle();
        }
        ComicAdapter adapter = new ComicAdapter(activity,comicsList);
        gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        gridView.setAdapter(adapter);
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] comics = new String[jsonArray.length()];
        for(int i=0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            comicsList.add(new Comic(obj.getString("issue_title"),obj.getString("link_albo"),obj.optString("issue_subtitle"),obj.optString("serie_title"),obj.optString("serie_year"),obj.optString("issue_date"),obj.optString("issue_originalstories"),obj.getString("publisher"),obj.getString("issue_link_image"),obj.optString("issue_description"),obj.optString("serie_numbers")));
            comics[i] = obj.getString("issue_title") + "\t" + obj.getString("link_albo");
            Log.d("COMIC:", comics[i]);
        }
    }

    private void changeIconTitle() {
        switch (generalIcon) {
            case "publisher":
                for (int i = 0; i < comicsList.size();i++) {
                    Comic item = comicsList.get(i);
                    item.setIssue_title(item.getPublisher());
                    comicsList.set(i, item);
                }
                break;
            case "serie":
                for (int i = 0; i < comicsList.size();i++) {
                    Comic item = comicsList.get(i);
                    item.setIssue_title(item.getSerie_title());
                    comicsList.set(i, item);
                }
                break;
        }
    }

    public SyncGrid( String endpoint, GridView gridView, Activity activity, Context context, ArrayList<Comic> itemArrayList) {
        this.endpoint = endpoint;
        this.gridView = gridView;
        this.activity = activity;
        this.context = context;
        this.comicsList = itemArrayList;
    }

    public SyncGrid( String endpoint, GridView gridView, Activity activity, Context context, ArrayList<Comic> itemArrayList, String generalIcon) {
        this.endpoint = endpoint;
        this.gridView = gridView;
        this.activity = activity;
        this.context = context;
        this.comicsList = itemArrayList;
        this.generalIcon = generalIcon;
    }
}
