package merkin.ron.drawme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {
    private EditText userName, password;
    private TextView signUp;
    private Button loginButton;
    private Firebase ref;
    private static final int REQUEST_SIGNUP = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://drawmemerkin.firebaseio.com/");
        login();

    }

    public void login(){
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUp = (TextView) findViewById(R.id.signup);

        signUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("merkin.ron.drawme.Registration");
                startActivityForResult(intent,REQUEST_SIGNUP);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.authWithPassword(userName.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Toast.makeText(LoginActivity.this, "Welcom " + userName.getText().toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent("merkin.ron.drawme.Rooms");
                        startActivity(intent);
                        userName.setText("");
                        password.setText("");
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        AlertDialog.Builder errDialog = new AlertDialog.Builder(LoginActivity.this);
                        errDialog.setMessage("Few possible errors occured:\n1. Not a valid email.\n" +
                                "2. Not a valid password.\nPlease try again.\n\n Don't own a user. Just " +
                                "register and start playing!");
                        errDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                userName.setText("");
                                password.setText("");
                            }
                        });
                        errDialog.create().show();
                    }
                });
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_SIGNUP){
            if (resultCode == RESULT_OK){

            }
        }
    }
}
