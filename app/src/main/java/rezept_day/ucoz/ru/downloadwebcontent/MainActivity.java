package rezept_day.ucoz.ru.downloadwebcontent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String mailRu = "https://mail.ru/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Hello", mailRu);//Очень удобно пользоваться при отладке, когда нам нужно проверить как работает тот или иной метод
        DownloadTask task = new DownloadTask();//Создаем объект нашего класса
        //task.execute(mailRu);//У объекта вызываем метод который запускает выполнение нашей задачи
        try {//Обязательно при получении данных из интернета необходимо обработать исключения
            //task.execute(mailRu, "google.com", "rezept-day.ucoz.ru");//так как наш метод doInBackground принимает массив строк, можем передавать туда несколько строк
            String result = task.execute(mailRu).get();//Получить результат выполнения задачи и сохраняем его в переменной
            Log.i("URL", result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
