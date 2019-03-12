package rezept_day.ucoz.ru.downloadwebcontent;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadTask extends AsyncTask<String, Void, String> {
    //Данный класс нужен нам, чтобы наша задача выполнялась в другом потоке, отличном от главного потока
    //Согласно политике Android любой код который будет выполняться какое-то длительное время
    //Должен быть запущен в другом потоке. Главный поток UI Thread - поток пользовательского интерфейса.
    //Здесь будет код который должен выполняться в другом потоке
    //AsyncTask принимает три параметра - первый(String) - то что подается на вход, нашему классу DownloadTask
    //второй(Void) - это данные, которые будут передаваться в процессе загрузки (в данном случае ничего передаваться не юудет)
    //третий(String) - это данные, которые будут возвращаться после выполнения задачи классом DownloadTask


    @Override
    protected String doInBackground(String... strings) {
        //Log.i("URL", strings[0]);//Отправляем в лог то что получили в массив
        StringBuilder result = new StringBuilder();//Создаем переменную, в ней мы будем формировать наш результат
        URL url = null;//Объект для хранения нашего адреса в интернете
        HttpsURLConnection urlConnection = null;//Объект который открывает наш url и берет из него данные
        try {
            url = new URL(strings[0]);//1.Получаем наш url из массива
            urlConnection = (HttpsURLConnection) url.openConnection();//2. открываем соединение
            //3. Можно начинать читать данные.
            InputStream in = urlConnection.getInputStream();//Для этого создаем поток ввода
            InputStreamReader reader = new InputStreamReader(in);//Создаем reader для чтения данных
            //Так как reader будет читать символ за символом, это долго, необходимо создать BufferedReader
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();//Считываем одну строку из нашего ридера и заносим ее в переменную
            while (line != null){
                result.append(line);//пока строки не закончатся, прочитав строку мы добавляем ее в переменную result
                line = bufferedReader.readLine();//и читаем следующую строку
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Независимо от того как у нас стаботоло чтение, необходимо закрыть интернет соединение
            if(urlConnection != null){
                urlConnection.disconnect();//Закрываем соединение
            }
        }

        return result.toString();//Возвращаем result приведенный к строке
    }
}
