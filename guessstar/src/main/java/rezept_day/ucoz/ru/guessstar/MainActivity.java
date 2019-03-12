package rezept_day.ucoz.ru.guessstar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rezept_day.ucoz.ru.guessstar.downloadtask.DownloadContentTask;

public class MainActivity extends AppCompatActivity {

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private ImageView imageViewStar;

    private String url = "http://www.posh24.se/kandisar";
    private ArrayList<String> urls;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        getContent();
    }

    private void initUI() {
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        imageViewStar = findViewById(R.id.imageViewStar);
        urls = new ArrayList<>();
        names = new ArrayList<>();
    }
    private void getContent(){
        DownloadContentTask task = new DownloadContentTask();
        try {
            String content = task.execute(url).get();
            String start = "<p class=\"link\">Topp 100 kändisar</p>";
            String finish = "<div class=\"col-xs-12 col-sm-6 col-md-4\">";
            Pattern pattern = Pattern.compile(start + "(.*?)" + finish);
            Matcher matcher = pattern.matcher(content);
            String splitContent = "";
            while(matcher.find()){
                splitContent = matcher.group(1);
            }

            Pattern patternImg = Pattern.compile("<img src=\"(.*?)\"");
            Pattern patternName = Pattern.compile("alt=\"(.*?)\"/>");
            Matcher matcherImg = patternImg.matcher(splitContent);
            Matcher marcherName = patternName.matcher(splitContent);
            while (matcherImg.find()){
                urls.add(matcherImg.group(1));
            }
            while (marcherName.find()){
                names.add(marcherName.group(1));
            }

            for (String s : urls) {
                Log.i("MyResult", s);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
