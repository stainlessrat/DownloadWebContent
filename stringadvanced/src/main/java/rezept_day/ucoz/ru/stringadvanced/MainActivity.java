package rezept_day.ucoz.ru.stringadvanced;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String nameString = "Андрей, Алексей, Роман, Павел, Михаил";
//        String[] names = nameString.split(", ");//Разбиваем строку по именам, использую для разделения символ запятой и пробел
//        for (String name: names){
//            Log.i("MyName", name);
//        }
        //Как получить часть строки
        String geometry = "Геометрия";
        String meter = geometry.substring(3, 7);
        Log.i("MyName", meter);
    }
}
