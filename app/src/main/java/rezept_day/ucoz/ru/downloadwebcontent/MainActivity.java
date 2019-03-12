package rezept_day.ucoz.ru.downloadwebcontent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String mailRu = "https://mail.ru/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Hello", mailRu);//Очень удобно пользоваться при отладке, когда нам нужно проверить как работает тот или иной метод
        DownloadTask task = new DownloadTask();//Создаем объект нашего класса
        task.execute(mailRu);//У объекта вызываем метод который запускает выполнение нашей задачи
    }
}
