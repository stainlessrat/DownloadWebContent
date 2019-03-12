package rezept_day.ucoz.ru.downloadimage;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    private String url = "https://images.ru.prom.st/329266019_w640_h640_belyj-plyushevyj-mishka.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public void onClickDownloadImage(View view) {
        DownloadImageTask task = new DownloadImageTask();
        Bitmap bitmap = null;
        try {
            bitmap = task.execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
    }
}
