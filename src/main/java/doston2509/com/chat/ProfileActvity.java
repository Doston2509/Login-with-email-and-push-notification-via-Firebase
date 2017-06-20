package doston2509.com.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class ProfileActvity extends AppCompatActivity implements View.OnClickListener{


    private TextView email;
    private Button logout;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_actvity);
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
        email = (TextView)findViewById(R.id.displayEmail);


        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(this);

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LogIn.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        email.setText(user.getEmail());

    }

    @Override
    public void onClick(View v) {
        if( v== logout){
            Toast.makeText(this, "Good bye", Toast.LENGTH_SHORT);
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LogIn.class));
        }


    }
}
