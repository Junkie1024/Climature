package com.example.climature;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;



    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;



    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {

            addFragment(3534, 6077243, "Montréal");
        }




        setupNavgation();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.host_fragment),drawerLayout);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);

        }else {

            super.onBackPressed();

        }
    }

    public  void setupNavgation(){

        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerlayout);

        navigationView = findViewById(R.id.navigation_view);

        getSupportActionBar().show();

        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

}




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                title = item.getTitle().toString();
                addFragment(3534, 6077243, title);
                Toast.makeText(getApplicationContext(), navigationView.getMenu().getItem(0) + "'s Weather", Toast.LENGTH_LONG).show();
                break;
            case R.id.NewYork_city:
                title = item.getTitle().toString();
                addFragment(2459115, 2641507, title);
                Toast.makeText(getApplicationContext(), navigationView.getMenu().getItem(1) + "'s Weather", Toast.LENGTH_LONG).show();
                break;
            case R.id.Toronto_City:
                title = item.getTitle().toString();
                addFragment(4118, 6167865, title);
                Toast.makeText(getApplicationContext(), navigationView.getMenu().getItem(2) + "'s Weather", Toast.LENGTH_LONG).show();
                break;
            case R.id.Vancouver_city:
                title = item.getTitle().toString();
                addFragment(9807,6173331, title);
                Toast.makeText(getApplicationContext(), navigationView.getMenu().getItem(3) + "'s Weather", Toast.LENGTH_LONG).show();
                break;
              case R.id.Ahemdabad_city:
                title = item.getTitle().toString();
                addFragment(2295402, 1279233, title);
                Toast.makeText(getApplicationContext(), navigationView.getMenu().getItem(4) + "'s Weather", Toast.LENGTH_LONG).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        System.out.println("Title: " + item.getTitle());


        return true;
    }


    public void addFragment(int geoId, int bbcId, final String title) {

        Bundle bundle = new Bundle();
        bundle.putInt("geoid", geoId);
        bundle.putInt("bbcid", bbcId);
        bundle.putString("title", title);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        dashboardFragment dasFragment = new dashboardFragment();

        fragmentTransaction.replace(R.id.host_fragment, dasFragment);


        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(null);
        dasFragment.setArguments(bundle);
        fragmentTransaction.commit();

        System.out.println("Count1: " + getSupportFragmentManager().getBackStackEntryCount());

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                getSupportActionBar().setTitle(title.toUpperCase());
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    finish();
                }
                System.out.println("Count2: " + getSupportFragmentManager().getBackStackEntryCount());
            }
        });
    }




}

