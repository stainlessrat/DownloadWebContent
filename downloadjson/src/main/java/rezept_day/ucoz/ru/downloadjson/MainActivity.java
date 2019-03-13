package rezept_day.ucoz.ru.downloadjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String url = "https://api.openweathermap.org/data/2.5/weather?q=Новокузнецк&appid=92bf0ae9ae9c1aa00cbfb8edbda92538";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute(url);
    }
}
