package com.quantastudio.cryptoinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.MenuItem;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        setContentView(R.layout.activity_main);
        getWindow().setEnterTransition(new Fade());
        getWindow().setExitTransition(new Fade());

        setToolbar();
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportActionBar().show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,Home.class,null)
                        .commit();
            }
        },2500);
    }

    private void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout
                ,R.string.nav_app_bar_open_drawer_description
                ,R.string.nav_app_bar_navigate_up_description);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((NavigationView)findViewById(R.id.nav_view)).setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if(item.getItemId() == R.id.home2){
                            drawerLayout.closeDrawer(GravityCompat.START);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,Home.class,null)
                                    .commit();
                            return true;
                        }
                        else if(item.getItemId() == R.id.settings2){
                            drawerLayout.closeDrawer(GravityCompat.START);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,Settings.class,null)
                                    .commit();
                            return true;
                        }
                        else if(item.getItemId() == R.id.about2){
                            drawerLayout.closeDrawer(GravityCompat.START);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,About.class,null)
                                    .commit();
                            return true;
                        }
                        else return false;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isOpen()){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else super.onBackPressed();
    }
}