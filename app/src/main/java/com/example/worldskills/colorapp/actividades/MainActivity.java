package com.example.worldskills.colorapp.actividades;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.worldskills.colorapp.R;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.jugar:intent=new Intent(MainActivity.this,JugarActivity.class);
                break;
            case R.id.puntajes:
                break;
            case R.id.configuracion:
                break;
        }
        startActivity(intent);
    }

}
