package com.example.quanlytaikhoanthanhtoansinhvien.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlytaikhoanthanhtoansinhvien.R;
import com.example.quanlytaikhoanthanhtoansinhvien.TaiKhoan.MyDatabaseHelper;
import com.example.quanlytaikhoanthanhtoansinhvien.TaiKhoan.User;
import com.example.quanlytaikhoanthanhtoansinhvien.TrangChu.TrangChuController;

import java.util.List;


public class LoginController extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    Button btnLogin;
    ProgressBar loading;
    MyDatabaseHelper db= new MyDatabaseHelper(this);
    List<User> list=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWitget();
        if (db.getAllUser().equals(null)) txtUsername.setText("Null");
        list=db.getAllUser();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=0;
                String username= txtUsername.getText()+"";
                String password= txtPassword.getText()+"";
                for (User acc: list){
                    if (username.equals(acc.getUsername())&&password.equals(acc.getPassword())){
                        if (acc.getAdmin()==1){
                            Intent intent=new Intent(LoginController.this, TrangChuController.class);
                            startActivity(intent);
                            count++;
                        }
                    }
                }
                if(count==0) Toast.makeText(LoginController.this, "Login failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getWitget(){
        txtUsername=(EditText)findViewById(R.id.username);
        txtPassword=(EditText)findViewById(R.id.password);
        btnLogin=(Button)findViewById(R.id.login);
        loading=(ProgressBar)findViewById(R.id.loading);
    }
}