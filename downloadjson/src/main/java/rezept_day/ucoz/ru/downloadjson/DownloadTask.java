package rezept_day.ucoz.ru.downloadjson;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {//Метод не имеет доступа до графических элементов приложения
        StringBuilder result = new StringBuilder();
        URL url = null;
        HttpsURLConnection urlConnection = null;
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

    @Override
    protected void onPostExecute(String s) {
        //Имеет доступ до графических элементов приложения, на вход принимает строку переданную из метода doInBackground
        super.onPostExecute(s);
        Log.i("MyResalt", s);
        //Переводим нашу строку в формат JSON
        try {
            JSONObject jsonObject = new JSONObject(s);
            String name = jsonObject.getString("name");//Получаем данные из объекта
            Log.i("MyResalt", name);

            JSONObject main = jsonObject.getJSONObject("main");//Получаем еще один объект JSON из предыдущего объекта
            String temp = main.getString("temp");
            String pressure = main.getString("pressure");
            Log.i("MyResalt", temp + " " + pressure);

            JSONArray jsonArray = jsonObject.getJSONArray("weather");//Получить массив данных из предыдущего объекта
            JSONObject weather = jsonArray.getJSONObject(0);
            String mainWeather = weather.getString("main");
            String description = weather.getString("description");
            Log.i("MyResult", mainWeather + " " + description);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
