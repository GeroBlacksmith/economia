package com.forjadesoftware.gerardo.economiahogar;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView welcomeUser,contenido;
    FirebaseDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        welcomeUser=(TextView)findViewById(R.id.welcome_user);
        contenido=(TextView)findViewById(R.id.tv_contenido);
        welcomeUser.setText(mAuth.getCurrentUser().getEmail());
        mDatabase=FirebaseDatabase.getInstance();
        DatabaseReference mReference=mDatabase.getReference();
        DatabaseReference post = mReference.child("post/").getRef();
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {

                    String jsonformatted=JSONObject.quote(dataSnapshot.getValue().toString());
                    JSONObject jsonObject = new JSONObject(jsonformatted);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //dataSnapshot.getValue().toString();

                contenido.append("\n\n"+dataSnapshot.getValue().toString());



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        post.addChildEventListener(childEventListener);
        //Toast.makeText(MainActivity.this,post.getDatabase().toString(),Toast.LENGTH_SHORT).show();
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
