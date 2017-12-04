package tercyduk.appngasal.modules.auth.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tercyduk.appngasal.R;
import tercyduk.appngasal.apihelper.Adapter.AdapterLapangan;
import tercyduk.appngasal.apihelper.BaseApiService;
import tercyduk.appngasal.apihelper.UtilsApi;
import tercyduk.appngasal.coresmodel.Lapagans;
import tercyduk.appngasal.coresmodel.LapanganImage;

public class Lapangan extends AppCompatActivity {
    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVLapangan;
    private AdapterLapangan mAdapter;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapangan);

        LapanganImage LapanganA = new LapanganImage();
        LapanganA.setImg(R.drawable.lap1);
        LapanganImage LapanganB = new LapanganImage();
        LapanganB.setImg(R.drawable.lap1);
        new AsyncFetch().execute();
    }
    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Lapangan.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
//            mApiService = UtilsApi.getAPIService();
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://10.0.3.2:1337/futsal_field");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(READ_TIMEOUT);
//                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }

        }
        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            List<Lapagans> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    Lapagans lapangandatas= new Lapagans();
                    lapangandatas.namalapang= json_data.getString("futsal_name");
                    lapangandatas.tipelapang= json_data.getString("type_field");
                    lapangandatas.kecamatan= json_data.getString("address");
                    lapangandatas.price= json_data.getInt("price");
                    data.add(lapangandatas);
                }

                // Setup and Handover data to recyclerview
                mRVLapangan = (RecyclerView)findViewById(R.id.rvLapangan);
                mAdapter = new AdapterLapangan(Lapangan.this, data);
                mRVLapangan.setAdapter(mAdapter);
                mRVLapangan.setLayoutManager(new LinearLayoutManager(Lapangan.this));

            } catch (JSONException e) {
                Toast.makeText(Lapangan.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
}
