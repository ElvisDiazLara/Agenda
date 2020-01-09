package com.udaff.agenda;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.udaff.agenda.Dao.UserDBAdapter;

public class AddUserActivity extends AppCompatActivity {


    private boolean userExists(String name) {
        UserDBAdapter connection = new UserDBAdapter(AddUserActivity.this, UserDBAdapter.DATABASE_NAME, null, UserDBAdapter.DATABASE_VERSION);
        SQLiteDatabase bd = connection.getReadableDatabase();
        String[] params = {name};
        String[] fields = {UserDBAdapter.COL_PHONE};
        Cursor cursor = bd.query(UserDBAdapter.TABLE_USER,
                fields,
                UserDBAdapter.COL_NAME + "=?",
                params,
                null,
                null,
                null);

        boolean queryResult = cursor.moveToFirst();

        cursor.close();

        return queryResult;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        final EditText edit_name = findViewById(R.id.edit_register_user_name),
                edit_email = findViewById(R.id.edit_register_user_email),
                edit_phone = findViewById(R.id.edit_register_user_phone),
                edit_address = findViewById(R.id.edit_register_user_address),
                edit_birhtday = findViewById(R.id.edit_register_user_birthday);


        Button btnRegistrar = findViewById(R.id.button_register_user);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name.getText().toString(),
                        email = edit_email.getText().toString(),
                        phone = edit_phone.getText().toString(),
                        address = edit_address.getText().toString(),
                        birthday = edit_birhtday.getText().toString();
                if (userExists(name)) {
                    Toast.makeText(AddUserActivity.this, "Contacto existente", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || birthday.isEmpty())
                    return;
                ContentValues contentValues = new ContentValues();
                contentValues.put(UserDBAdapter.COL_NAME, name);
                contentValues.put(UserDBAdapter.COL_PHONE, phone);
                contentValues.put(UserDBAdapter.COL_EMAIL, email);
                contentValues.put(UserDBAdapter.COL_ADDRESS, address);
                contentValues.put(UserDBAdapter.COL_BIRTHDAY, birthday);

                UserDBAdapter connection = new UserDBAdapter(AddUserActivity.this,
                        UserDBAdapter.DATABASE_NAME, null, 1);
                SQLiteDatabase bd = connection.getWritableDatabase();
                long respuesta = bd.insert(UserDBAdapter.TABLE_USER, null, contentValues);
                Toast.makeText(AddUserActivity.this, "Se registro correctamete " + respuesta, Toast.LENGTH_SHORT).show();
                bd.close();
                finish();
            }
        });
    }

}
