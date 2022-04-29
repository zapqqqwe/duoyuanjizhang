package com.hui.tally;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.hui.tally.utils.userData;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{
   EditText username;  //展示今日收支情况的ListView
    EditText password;
    Button Sign_in;
    Button Sign_up;
    userData data;
    CheckBox checkBox;
    Boolean login_state=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initLogin();
    }
    private void initView() {

        data=new userData(LoginActivity.this);
        checkBox=findViewById(R.id.rememberpass);
       username= findViewById(R.id.Username);
       password=findViewById(R.id.Password);
       Sign_up=findViewById(R.id.Sign_up);
        Sign_in=findViewById(R.id.Sign_in);
        Sign_in.setOnClickListener(this);
        Sign_up.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                login_state=isChecked;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Sign_in:
            {

                if(data.verifyPassword(username.getText().toString(),password.getText().toString()))
                {
                    Intent intent=new Intent(this,MainActivity.class);
                    saveLogin(login_state);
                    startActivity(intent);
                    //Toast.makeText(LoginActivity.this,muesername.getText().toString()+"欢迎您！",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"登陆失败！请检查用户是否存在或者密码错误！",Toast.LENGTH_SHORT).show();
                }

            }
                break;
            case R.id.Sign_up: {

                if(data.getRegister(username.getText().toString(),password.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"注册失败！用户名重复或者为空！",Toast.LENGTH_SHORT).show();
                }
                break;
            }}

    }

    private void saveLogin(boolean flag)
    {
        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("ACCOUNT_REMEMBER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String secreat_name = username.getText().toString();
        String secreat_password=password.getText().toString();
        if(sharedPreferences.getBoolean("flag",false))
        {
            return;
            //验证通过，不需要再次保存了
        }
        if(flag) {
            secreat_password= Base64.encodeToString(secreat_password.getBytes(),Base64.NO_WRAP);
            editor.putString("name",secreat_name);
            editor.putString("password", secreat_password);
            editor.putBoolean("flag", flag);
            editor.commit();
        }
        else{
            editor.clear();
            editor.putString("name",secreat_name);
            editor.putBoolean("flag",false);
            editor.commit();
        }

    }
    private void cleanState()
    {
        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("ACCOUNT_REMEMBER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
    private void initLogin()
    {
        SharedPreferences sharedPreferences= LoginActivity.this.getSharedPreferences("ACCOUNT_REMEMBER", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("flag",false)){
            String decode_password=sharedPreferences.getString("password","");
            decode_password= new String(Base64.decode(decode_password.getBytes(),Base64.NO_WRAP));
           username.setText(sharedPreferences.getString("name",""));
            password.setText(decode_password);
            checkBox.setChecked(true);
        }
        else {
            username.setText(sharedPreferences.getString("name",""));
            checkBox.setChecked(false);
        }
    }


}
