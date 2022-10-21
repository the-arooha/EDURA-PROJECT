package com.example.loginactivity;

import static com.example.loginactivity.R.id.drawer_layout;
import static com.example.loginactivity.R.id.nav_Home;
import static com.example.loginactivity.R.id.nav_arts;
import static com.example.loginactivity.R.id.nav_commerce;
import static com.example.loginactivity.R.id.nav_currentaffairs;
import static com.example.loginactivity.R.id.nav_feedback;
import static com.example.loginactivity.R.id.nav_physicaleducation;
import static com.example.loginactivity.R.id.nav_science;
import static com.example.loginactivity.R.id.nav_share;
import static com.example.loginactivity.R.id.nav_technology;
import static com.example.loginactivity.R.id.nav_tests;
import static com.example.loginactivity.R.id.navigation_view;
import static com.example.loginactivity.R.id.topAppbar;
import static com.example.loginactivity.R.layout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        MaterialToolbar toolbar=findViewById(topAppbar);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) DrawerLayout drawerLayout=findViewById(drawer_layout);
        NavigationView navigationView=findViewById(navigation_view);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id=item.getItemId();
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (id)
            {
                case nav_Home:
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();break;
                case nav_science:
                    Toast.makeText(MainActivity.this, "Science", Toast.LENGTH_SHORT).show();break;
                case nav_arts:
                    Toast.makeText(MainActivity.this, "Arts", Toast.LENGTH_SHORT).show();break;
                case nav_commerce:
                    Toast.makeText(MainActivity.this, "Commerce", Toast.LENGTH_SHORT).show();break;
                case nav_technology:
                    Toast.makeText(MainActivity.this, "Technology", Toast.LENGTH_SHORT).show();break;
                case nav_physicaleducation:
                    Toast.makeText(MainActivity.this, "Physical Education", Toast.LENGTH_SHORT).show();break;
                case nav_currentaffairs:
                    Toast.makeText(MainActivity.this, "Current Affairs", Toast.LENGTH_SHORT).show();break;
                case nav_tests:
                    Toast.makeText(MainActivity.this, "Tests", Toast.LENGTH_SHORT).show();break;
                case nav_share:
                    Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();break;
                case nav_feedback:
                    Toast.makeText(MainActivity.this, "Feedback", Toast.LENGTH_SHORT).show();break;

                default:
                    return true;

            }

            return true;
        });
    }
}