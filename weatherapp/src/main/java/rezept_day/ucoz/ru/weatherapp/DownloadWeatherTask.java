package rezept_day.ucoz.ru.weatherapp;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;

import javax.net.ssl.HttpsURLConnection;

public class DownloadWeatherTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        HttpsURLConnection urlConnection = null;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpsURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null){
                result.append(line);
                line = bufferedReader.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
        return result.toString();
    }


    protected void onPostExecute(String s, TextView v) {
        super.onPostExecute(s);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
            String cityJSON = jsonObject.getString("name");
            String temp = jsonObject.getJSONObject("main").getString("temp");
            String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            String weather = String.format("%s\nТемпература: %s\nНа улице: %s", cityJSON, temp, description);
            v.setText(weather);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
