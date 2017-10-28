package com.forjadesoftware.gerardo.economiahogar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_main_add){
            startActivity(new Intent(MainActivity.this, AddActivity.class));
            return true;
        }else
        if(id == R.id.action_logout){
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, EmailActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
