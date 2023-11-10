package quyetmtph28802.fpoly.bt4_adrnw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import quyetmtph28802.fpoly.bt4_adrnw.Adapter.AlbumAdapter;
import quyetmtph28802.fpoly.bt4_adrnw.DTO.Albums;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new FetchAlbumsTask().execute();
    }

    private class FetchAlbumsTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.24.32.233/Bt4/Photo.json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                List<Albums> albums = parseJson(result);
                displayAlbums(albums);
            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private List<Albums> parseJson(String json) {
        List<Albums> albums = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int albumId = jsonObject.getInt("albumId");
                int id = jsonObject.getInt("id");
                String title = jsonObject.getString("title");
                String url = jsonObject.getString("url");
                String thumbnailUrl = jsonObject.getString("thumbnailUrl");

                albums.add(new Albums(albumId, id, title, url, thumbnailUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return albums;
    }

    private void displayAlbums(List<Albums> albums) {
        // Hiển thị danh sách Album lên RecyclerView
        albumAdapter = new AlbumAdapter(albums);
        recyclerView.setAdapter(albumAdapter);
    }
}