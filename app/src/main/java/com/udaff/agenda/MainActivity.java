package com.udaff.agenda;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


public void boton (View view){

switch (view.getId()){

    case R.id.btnGuardar:
        Intent agregar = new Intent(this, Agregar_Persona.class);
            startActivity(agregar);
        break;

    case R.id.btnListar:
        Intent lista = new Intent(this, Lista_Persona.class);
            startActivity(lista);
        break;



        }

    }



}
