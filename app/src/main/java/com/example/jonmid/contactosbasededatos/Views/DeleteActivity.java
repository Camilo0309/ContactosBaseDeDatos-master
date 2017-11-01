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
        sqliteHelper = new SqliteHelper(this, "db_users", null, 1);

        textViewid.setText(Integer.toString(getIntent().getExtras().getInt("id")));
        textViewName.setText(getIntent().getExtras().getString("name"));
        textViewPhone.setText(getIntent().getExtras().getString("phone"));
        textViewEmail.setText(getIntent().getExtras().getString("email"));

    }

    public void onClickShowPrincipal(View view){

        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void onClickDeleteContact(View view){

       SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("DELETE from users WHERE id ='"+Integer.parseInt(textViewid.getText().toString())+"'", null);
        cursor.close();
        Toast.makeText(this, "EL CONTACTO SE HA ELIMINADO CON EXITO", Toast.LENGTH_SHORT).show();
        onClickShowPrincipal(view);

        Toast.makeText(this, "Id: "+ Integer.parseInt(textViewid.getText().toString()), Toast.LENGTH_SHORT).show();

    }

}
