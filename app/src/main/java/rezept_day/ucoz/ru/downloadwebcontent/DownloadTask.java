package rezept_day.ucoz.ru.downloadwebcontent;

import android.os.AsyncTask;

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
        return null;
    }
}
