package com.udaff.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udaff.agenda.Adapter.UserAdapter;
import com.udaff.agenda.Dao.UserDBAdapter;
import com.udaff.agenda.Entity.User;

import java.util.ArrayList;

public class UserDetailActivity extends AppCompatActivity implements UserAdapter.UserCallback {

    private RecyclerView recyclerView;
    private EditText editUserName;
    private EditText editUserEmail;
    private EditText editUserPhone;
    private EditText editUserAddress;
    private EditText editUserBirthday;

    private UserDBAdapter dbConnection;
    private ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        this.recyclerView = findViewById(R.id.recycler_users);
        this.editUserName = findViewById(R.id.edit_text_user_name);
        this.editUserEmail = findViewById(R.id.edit_text_user_email);
        this.editUserPhone = findViewById(R.id.edit_text_user_phone);
        this.editUserAddress = findViewById(R.id.edit_text_user_address);
        this.editUserBirthday = findViewById(R.id.edit_text_user_birthday);

        this.dbConnection = new UserDBAdapter(UserDetailActivity.this, UserDBAdapter.DATABASE_NAME, null, 1);

        setupDB();

    }

    @Override
    public void showUserDetail(User user) {
        this.editUserName.setText(user.getName());
        this.editUserEmail.setText(user.getEmail());
        this.editUserPhone.setText(user.getPhone());
        this.editUserAddress.setText(user.getAddress());
        this.editUserBirthday.setText(user.getBirthday());
    }

    private void setupDB() {
        SQLiteDatabase bd = this.dbConnection.getReadableDatabase();
        String[] campos = {UserDBAdapter.COL_NAME, UserDBAdapter.COL_EMAIL, UserDBAdapter.COL_PHONE, UserDBAdapter.COL_ADDRESS, UserDBAdapter.COL_BIRTHDAY};
        Cursor cursor = bd.query(UserDBAdapter.TABLE_USER,
                campos,
                null,
                null,
                null,
                null,
                null);
        if (!cursor.moveToFirst()) {
            cursor.close();
            bd.close();
            return;
        }

        do {
            String nombre = cursor.getString(cursor.getColumnIndex(UserDBAdapter.COL_NAME));
            String correo = cursor.getString(cursor.getColumnIndex(UserDBAdapter.COL_EMAIL));
            String telefono = cursor.getString(cursor.getColumnIndex(UserDBAdapter.COL_PHONE));
            String direccion = cursor.getString(cursor.getColumnIndex(UserDBAdapter.COL_ADDRESS));
            String fecha_nac = cursor.getString(cursor.getColumnIndex(UserDBAdapter.COL_BIRTHDAY));

            User user = new User(nombre, correo, telefono, direccion, fecha_nac);
            users.add(user);
        } while (cursor.moveToNext());


        cursor.close();
        setupUserList();
    }

    private void setupUserList() {
        UserAdapter userAdapter = new UserAdapter(users);
        userAdapter.setUserListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.recyclerView.setLayoutManager(mLayoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setAdapter(userAdapter);
    }
}
