package com.example.appsteam.motivision.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appsteam.motivision.R;
import com.example.appsteam.motivision.circular_Progress.Progress;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView register=(TextView)findViewById(R.id.textView4);
        TextView forgot_password=(TextView)findViewById(R.id.forgotpswd);
        Button login=(Button)findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cir_prgrs=new Intent(MainActivity.this,Progress.class);
                startActivity(cir_prgrs);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg_intent=new Intent(MainActivity.this,Registration.class);
                startActivity(reg_intent);
            }
        });


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_forget_password, null);
                TextView security_frgt = (TextView) mView.findViewById(R.id.security_frgt);
                final EditText mAnswer = (EditText) mView.findViewById(R.id.Answer);
                mBuilder.setPositiveButton("Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

               Intent reset_intent=new Intent(MainActivity.this,ResetPassword.class);
               startActivity(reset_intent);
                    }
                });

                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
               });
                mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                dialog.show();


            }
        });


    }
}
