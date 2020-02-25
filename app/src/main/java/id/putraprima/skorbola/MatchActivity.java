package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {

    private TextView homeText, awayText, homeScoreText, awayScoreText;
    private ImageView homeImage, awayImage;
    private Bitmap homeBitmap, awayBitmap;
    private Uri homeUri, awayUri;
    private int homeScore, awayScore;
    private String homeTeamName, awayTeamName ,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeScoreText = findViewById(R.id.score_home);
        awayScoreText = findViewById(R.id.score_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            DataScore data = bundle.getParcelable("DATA_KEY");
            homeTeamName = data.getHomeText();
            awayTeamName = data.getAwayText();
            homeText.setText(homeTeamName);
            awayText.setText(awayTeamName);
            homeScore = data.getHomeScore();
            awayScore = data.getAwayScore();
            homeScoreText.setText(Integer.toString(homeScore));
            awayScoreText.setText(Integer.toString(awayScore));
            homeUri = data.getHomeUri();
            awayUri = data.getAwayUri();
            try {
                homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeImage.setImageBitmap(homeBitmap);
            awayImage.setImageBitmap(awayBitmap);
        }
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    public void handleAddHomeScore(View view) {
        homeScore += 1;
        homeScoreText.setText(Integer.toString(homeScore));
    }

    public void handleAddAwayScore(View view) {
        awayScore += 1;
        awayScoreText.setText(Integer.toString(awayScore));
    }

    public void handleCekHasil(View view) {
        if (homeScore > awayScore){
            message = "Pemenangnya adalah " + homeTeamName;
        }else if(homeScore < awayScore){
            message = "Pemenangnya adalah " + awayTeamName;
        }else if(homeScore == awayScore){
            message = "DRAW";
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("MESSAGE_KEY", message);
        startActivity(intent);
    }
}