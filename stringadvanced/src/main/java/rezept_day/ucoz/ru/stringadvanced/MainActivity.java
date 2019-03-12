package rezept_day.ucoz.ru.stringadvanced;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//        String geometry = "Геометрия";
//        String meter = geometry.substring(3, 7);
//        Log.i("MyName", meter);

        // Используем регулярные  выражения
        String river = "Mississippi";
        //Создаем шаблон
        Pattern pattern = Pattern.compile("Mi(.*?)pi");//Найди строчку которая начинается на Mi и заканчивается на pi, и верни все что находится между.
        //Чтобы обработать наш паттерн, нам нужен объект Matcher
        Matcher matcher = pattern.matcher(river);
        //matcher.find() - ищет первое вхождение паттерна
        while (matcher.find()){
            Log.i("MyName", matcher.group(1));//чтобы вызвать первое совпадение используем matcher.group(1)
        }
    }
}
