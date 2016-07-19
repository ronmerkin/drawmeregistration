package merkin.ron.drawme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Rooms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        Intent intent = new Intent("merkin.ron.drawme.Navigation");
        startActivity(intent);
    }

}
