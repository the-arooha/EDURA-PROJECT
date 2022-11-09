package com.example.loginactivity;

import static com.example.loginactivity.R.id.drawer_layout;
import static com.example.loginactivity.R.id.nav_arts;
import static com.example.loginactivity.R.id.nav_commerce;
import static com.example.loginactivity.R.id.nav_currentaffairs;
import static com.example.loginactivity.R.id.nav_feedback;
import static com.example.loginactivity.R.id.nav_physicaleducation;
import static com.example.loginactivity.R.id.nav_profile;
import static com.example.loginactivity.R.id.nav_science;
import static com.example.loginactivity.R.id.nav_share;
import static com.example.loginactivity.R.id.nav_technology;
import static com.example.loginactivity.R.id.nav_tests;
import static com.example.loginactivity.R.id.navigation_view;
import static com.example.loginactivity.R.id.topAppbar;
import static com.example.loginactivity.R.layout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.Adapters.PostAdapter;
import com.example.loginactivity.Model.Post;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ProgressBar progress_circular;

    private PostAdapter postAdapter;
    private List<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);


        fab=findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recyclerView);
        progress_circular=findViewById(R.id.progress_circular);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        MaterialToolbar toolbar=findViewById(topAppbar);
        DrawerLayout drawerLayout=findViewById(drawer_layout);
        NavigationView navigationView=findViewById(navigation_view);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AskAQuestionActivity.class);
                startActivity(intent);

            }
        });

        postList=new ArrayList<>();
        postAdapter=new PostAdapter(MainActivity.this,postList);
        recyclerView.setAdapter(postAdapter);
        readQuestionsPosts();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id=item.getItemId();
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (id)
            {
                case nav_profile:
                    Intent intent=new Intent(MainActivity.this,userprofile.class);
                    startActivity(intent);
                    break;
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

    private void readQuestionsPosts() {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("question posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Post post=dataSnapshot.getValue(Post.class);
                    postList.add(post);


                }
                postAdapter.notifyDataSetChanged();
                progress_circular.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();

        return super.onCreateOptionsMenu(menu);
    }
}