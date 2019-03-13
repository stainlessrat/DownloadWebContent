package rezept_day.ucoz.ru.guessstar;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rezept_day.ucoz.ru.guessstar.downloadtask.DownloadContentTask;
import rezept_day.ucoz.ru.guessstar.downloadtask.DownloadImageTask;

public class MainActivity extends AppCompatActivity {

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private ImageView imageViewStar;

    private String url = "http://www.posh24.se/kandisar";
    private ArrayList<String> urls;
    private ArrayList<String> names;
    private ArrayList<Button> buttons;
    private int numberOfQuestion, numberOfRightAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        getContent();

        playGame();
    }

    private void initUI() {
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        imageViewStar = findViewById(R.id.imageViewStar);
        urls = new ArrayList<>();
        names = new ArrayList<>();
        buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
    }

    private void playGame(){
        generateQuestion();

        DownloadImageTask task = new DownloadImageTask();
        try {
            Bitmap bitmap = task.execute(urls.get(numberOfQuestion)).get();
            if(bitmap != null){
                imageViewStar.setImageBitmap(bitmap);
                for(int i = 0; i < buttons.size(); i++){
                    if(i == numberOfRightAnswer){
                        buttons.get(i).setText(names.get(numberOfQuestion));
                    }else {
                        int answer = generateWrongAnswer();
                        buttons.get(i).setText(names.get(answer));
                    }
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generateQuestion(){
        numberOfQuestion = (int)(Math.random() * names.size());
        numberOfRightAnswer = (int) (Math.random() * buttons.size());
    }

    private int generateWrongAnswer(){
        return (int)(Math.random() * names.size());
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

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onClickAnswer(View view) {
        Button button = (Button) view;
        String tag = button.getTag().toString();
        if(Integer.parseInt(tag) == numberOfRightAnswer){
            Toast.makeText(this, "Верно!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Неверно! Правильный ответ: " + names.get(numberOfRightAnswer), Toast.LENGTH_SHORT).show();
        }
        playGame();
    }
}
