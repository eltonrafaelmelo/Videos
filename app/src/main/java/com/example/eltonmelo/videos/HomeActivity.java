package com.example.eltonmelo.videos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import Adapter.ImageAdapter;

@EActivity(R.layout.home_grid_view)
@OptionsMenu(R.menu.menu_home)
public class HomeActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @ViewById
    GridView gridview1;

    ImageAdapter imageAdapter;


    @AfterViews
    void AfterViews(){

        setSupportActionBar(toolbar);
        imageAdapter = new ImageAdapter(this);

        gridview1.setAdapter(imageAdapter);

    }

    @OptionsItem(R.id.action_settings)
    void myMethod() {
        // You can specify the ID in the annotation, or use the naming convention
    }

    @OptionsItem(R.id.action_ordenar_por_data)
    void ordenarPorData() {
        // You can specify the ID in the annotation, or use the naming convention
    }

    @OptionsItem(R.id.action_ordenar_por_Favorito)
    void ordenarPorFavorito() {
        // You can specify the ID in the annotation, or use the naming convention
    }

    @ItemClick
    public void gridview1(int item){
        Toast.makeText(HomeActivity.this, "" + item,
                Toast.LENGTH_SHORT).show();
    }

}
