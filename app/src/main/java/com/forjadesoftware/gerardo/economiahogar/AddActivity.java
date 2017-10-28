package com.forjadesoftware.gerardo.economiahogar;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    DatabaseReference db;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        db = database.getReference();

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        Toast.makeText(AddActivity.this, mAuth.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();

    }
    public void enviar(View v){
        String postEt1 = et1.getText().toString();
        String postEt2 = et2.getText().toString();
        String postEt3 = et3.getText().toString();
        String postEt4 = et4.getText().toString();
        //db.setValue("Hello, world");
        String key = db.child("post").push().getKey();
        Gasto nuevo = new Gasto(mAuth.getCurrentUser().getUid(),postEt1,postEt2,postEt3,postEt4);
        Map<String, Object>postValues=nuevo.toMap();
        Map<String,Object> chilUpdates=new HashMap<>();
        chilUpdates.put("/post/"+key,postValues);
        db.updateChildren(chilUpdates);

        Toast.makeText(AddActivity.this,"Click",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddActivity.this, MainActivity.class));

/**
 *
 Prueba prueba=new Prueba(postEt1,postEt2,postEt3,postEt4);AsyncTask asyncTask= new AsyncTask() {
@Override
protected Object doInBackground(Object[] objects) {
return null;
}
};
 asyncTask.execute(prueba);*/


    }



    class Gasto{
        String userid;
        String monto;
        String etiqueta;
        String lugar;

        public Gasto(String userid,String monto, String etiqueta, String lugar, String fecha) {
            this.userid=userid;
            this.monto = monto;
            this.etiqueta = etiqueta;
            this.lugar = lugar;
            this.fecha = fecha;
        }

        public String getMonto() {
            return monto;
        }

        public void setMonto(String monto) {
            this.monto = monto;
        }

        public String getEtiqueta() {
            return etiqueta;
        }

        public void setEtiqueta(String etiqueta) {
            this.etiqueta = etiqueta;
        }

        public String getLugar() {
            return lugar;
        }

        public void setLugar(String lugar) {
            this.lugar = lugar;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        String fecha;



        // [START post_to_map]
        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("userid",userid);
            result.put("monto", monto);
            result.put("etiqueta", etiqueta);
            result.put("lugar", lugar);
            result.put("fecha", fecha);
            return result;
        }
    }
}
