package com.example.jonmid.contactosbasededatos.Views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonmid.contactosbasededatos.ContactsActivity;
import com.example.jonmid.contactosbasededatos.Helpers.SqliteHelper;
import com.example.jonmid.contactosbasededatos.R;
import com.example.jonmid.contactosbasededatos.Utilities.Constants;

public class DeleteActivity extends AppCompatActivity {

    TextView textViewid;
    TextView textViewName;
    TextView textViewPhone;
    TextView textViewEmail;
    SqliteHelper sqliteHelper;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        textViewid = (TextView) findViewById(R.id.delete_id);
        textViewName = (TextView) findViewById(R.id.delete_name);
        textViewPhone = (TextView) findViewById(R.id.delete_phone);
        textViewEmail = (TextView) findViewById(R.id.delete_Email);
        sqliteHelper = new SqliteHelper(this, "db_contacts", null, 1);

        textViewid.setText(Integer.toString(getIntent().getExtras().getInt("id")));
        textViewName.setText(getIntent().getExtras().getString("name"));
        textViewPhone.setText(getIntent().getExtras().getString("phone"));
        textViewEmail.setText(getIntent().getExtras().getString("email"));

    }

    public void onClickShowPrincipal(){

        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void onClickDeleteContact(View view){

       SQLiteDatabase db = sqliteHelper.getWritableDatabase();
       int id = Integer.parseInt(textViewid.getText().toString());
       Integer date = db.delete(Constants.TABLA_NAME_USERS,Constants.TABLA_FIELD_ID+ "=" + id, null);
       db.close();
       if (date != 0){
           Toast.makeText(this,"El usuario: "+textViewName.getText()+ " EL USUARIO SE ELIMINO CON EXITO! ",Toast.LENGTH_SHORT).show();
           onClickShowPrincipal();

       }else {

           Toast.makeText(this,"El usuario: "+textViewName.getText()+ " NO HAY DATOS PARA MOSTRAR ",Toast.LENGTH_SHORT).show();
           onClickShowPrincipal();

       }
       //db.delete(Constants.TABLA_NAME_USERS, Constants.TABLA_FIELD_ID+"='"+Integer.parseInt(textViewid.getText().toString())+"'", null);
        //Cursor cursor = db.rawQuery("DELETE from users WHERE id ='"+Constants.TABLA_FIELD_ID+"'", null);
        //cursor.close();
        //Toast.makeText(this, "EL CONTACTO SE HA ELIMINADO CON EXITO", Toast.LENGTH_SHORT).show();
        //onClickShowPrincipal(view);

    }

}
