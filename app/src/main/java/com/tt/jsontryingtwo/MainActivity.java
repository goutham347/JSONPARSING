package com.tt.jsontryingtwo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private static String url = "  http://api.androidhive.info/songs/album_tracks.php?id=9 ";

    ArrayList<HashMap<String,String>>songsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songsList = new ArrayList<>();

        listView = (ListView)findViewById(R.id.list_view);
        new Getsongs().execute();






    }

    private class Getsongs extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void...params){
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG,"Response from url:"+jsonStr);

            if(jsonStr != null){
                try{
                    JSONObject object = new JSONObject(jsonStr);
                    JSONArray songs = object.getJSONArray("songs");

                    for (int i=0;i<songs.length();i++){
                        JSONObject ob = songs.getJSONObject(i);

                        String id = ob.getString("id");
                        String name = ob.getString("name");
                        String duration = ob.getString("duration");

                        HashMap<String,String>song = new HashMap<>();

                        song.put("id",id);
                        song.put("name",name);
                        song.put("duration",duration);

                        songsList.add(song);
                    }
                }catch (final JSONException e){
                    Toast.makeText(getApplicationContext(),"JSON parssing error:",Toast.LENGTH_LONG).show();
                    }
            }else {
                Toast.makeText(getApplicationContext(),"Couldn't get json from server. check Logcat for possible errors!",
                        Toast.LENGTH_LONG).show();
            }
            return null;
        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void songs){

            ListAdapter adapter = new SimpleAdapter(MainActivity.this,songsList,R.layout.list_item,new String[]{"id","name","duration"},
                    new int[]{R.id.id,
                              R.id.name,
                              R.id.duration});
            listView.setAdapter(adapter);
//System.out.println("Getin");



            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent i = new Intent(MainActivity.this,DuplicateActivity.class);
                    i.putExtra("id",songsList.get(position).get("id"));
                    i.putExtra("name",songsList.get(position).get("name"));
                    i.putExtra("duration",songsList.get(position).get("duration"));
                    startActivity(i);

                }
            });




        /*    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(MainActivity.this,DuplicateActivity.class);
                     i.putExtra("id",id);
                    System.out.println("welcome" + id);
                    i.putExtra("name");

                    i.putExtra("duration");

                    startActivity(i);

                }
            });*/

//           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//               @Override
//               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                  Intent i = new Intent(MainActivity.this,DuplicateActivity.class);
//
//                     i.putExtra("id",id);
//                   System.out.println("welcome");
//                     i.putExtra("name",name);
//                   String ab = tv2.getText().toString();
//                   System.out.println("welcome" + ab);
//                     i.putExtra("duration",duration);
////                   i.putExtra("position",position);
//                   startActivity(i);
//               }
//           });
        }

    }
}
