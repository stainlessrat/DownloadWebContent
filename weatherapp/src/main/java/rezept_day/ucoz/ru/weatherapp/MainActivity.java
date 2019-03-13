package rezept_day.ucoz.ru.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCity;
    private TextView textViewWeather;
    private final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=92bf0ae9ae9c1aa00cbfb8edbda92538&lang=ru&units=metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        editTextCity = findViewById(R.id.editTextCity);
        textViewWeather = findViewById(R.id.textViewWeather);
    }

    public void onClickShowWeather(View view) {
        String city = editTextCity.getText().toString().trim();
        if(!city.isEmpty()){
            DownloadWeatherTask task = new DownloadWeatherTask();
            String url = String.format(WEATHER_URL, city);
            try {
                String result = task.execute(url).get();
                task.onPostExecute(result, textViewWeather);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
