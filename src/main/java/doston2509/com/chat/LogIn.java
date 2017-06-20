package doston2509.com.chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity implements View.OnClickListener{
    private Button singin;
    private EditText email;
    private EditText password;
    private TextView textViewSingUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActvity.class));
        }

        singin = (Button)findViewById(R.id.buttonSingin);
        email = (EditText)findViewById(R.id.editEmail2);
        password = (EditText)findViewById(R.id.editPassword2);
        textViewSingUp = (TextView)findViewById(R.id.textViewSingUp);

        singin.setOnClickListener(this);
        textViewSingUp.setOnClickListener(this);


    }
    private void userLogin(){

        String emailTaken = email.getText().toString().trim();
        String passwordTaken = password.getText().toString().trim();

        if(TextUtils.isEmpty(emailTaken)){
            // email is empty
            Toast.makeText(this, "Please, enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwordTaken)){
            // pasword empty
            Toast.makeText(this, "Please, enter passord", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering a user ......");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(emailTaken, passwordTaken)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(LogIn.this, "Welcome to Profile", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActvity.class));
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if(v == singin){
            userLogin();
        }
        if( v == textViewSingUp){
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
