package com.udaff.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void button(View view) {

        switch (view.getId()) {
            case R.id.button_register_user:
                Intent register = new Intent(this, AddUserActivity.class);
                startActivity(register);
                break;

            case R.id.button_detail:
                Intent detail = new Intent(this, UserDetailActivity.class);
                startActivity(detail);
                break;


        }

    }


}
