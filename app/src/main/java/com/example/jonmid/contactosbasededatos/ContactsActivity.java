package com.example.jonmid.contactosbasededatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jonmid.contactosbasededatos.Adapters.ContactAdapter;
import com.example.jonmid.contactosbasededatos.Helpers.SqliteHelper;
import com.example.jonmid.contactosbasededatos.Models.Contact;
import com.example.jonmid.contactosbasededatos.Views.RegisterContactActivity;
import com.example.jonmid.contactosbasededatos.Views.SearchContactActivity;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView recyclerViewContacts;
    ContactAdapter contactAdapter;
    List<Contact> contactList = new ArrayList<>();
    SqliteHelper sqliteHelper;
    TextInputEditText textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        recyclerViewContacts = (RecyclerView) findViewById(R.id.id_rv_contacts);
        textInputEditText = (TextInputEditText) findViewById(R.id.ced1);
        sqliteHelper = new SqliteHelper(this, "db_contacts", null, 1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewContacts.setLayoutManager(linearLayoutManager);


        listContacts();
        proccesData();

    }

    public void onClickShowWindowRegister(View view){
        Intent intent = new Intent(this, RegisterContactActivity.class);
        startActivity(intent);
    }

    public void onClickShowWindowSearch(View view){
        Intent intent = new Intent(this, SearchContactActivity.class);
        startActivity(intent);
    }

   // public void onClickbuscar(View view){
     //   listContactsIndex();
    //}


    public void listContacts(){

        SQLiteDatabase  db = sqliteHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id,name,phone,email FROM users order by id desc",null);

        while (cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
            contact.setEmail(cursor.getString(3));
            contactList.add(contact);

        }
        cursor.close();
        proccesData();
    }

    public void listContactsIndex(View view){
        contactList.clear();
        recyclerViewContacts.setAdapter(null);
        SQLiteDatabase  db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id,name,phone,email FROM users where name = '"+textInputEditText.getText().toString()+"'", null);
        try {
            while (cursor.moveToNext()){
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contactList.add(contact);
            }
            cursor.close();
            proccesData();

        }catch (Exception e){
            Toast.makeText(this, "No hay usuarios para mostrar", Toast.LENGTH_SHORT).show();

        }

    }

    public void proccesData() {
        if (contactList.size() != 0) {
            contactAdapter = new ContactAdapter(contactList, getApplicationContext());
            recyclerViewContacts.setAdapter(contactAdapter);

        }else{
            Toast.makeText(this, "No tienes registros para mostrar.", Toast.LENGTH_SHORT).show();
        }
    }

}
