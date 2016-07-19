package merkin.ron.drawme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class Registration extends AppCompatActivity {
    private EditText userName, password;
    private Button register;
    private Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ref = new Firebase("https://drawmemerkin.firebaseio.com/");
        userName = (EditText) findViewById(R.id.reg_username);
        password = (EditText) findViewById(R.id.reg_password);
        register = (Button) findViewById(R.id.reg_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.createUser(userName.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {

                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        setResult(RESULT_OK, null);
                        finish();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        AlertDialog.Builder errDialog = new AlertDialog.Builder(Registration.this);
                        errDialog.setMessage("Few possible errors occured:\n1. Not a valid email.\n" +
                                "2. The email is already in use.");
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



}
