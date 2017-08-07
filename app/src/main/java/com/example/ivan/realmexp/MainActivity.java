package com.example.ivan.realmexp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.first_name_edtx)
    EditText firstName;
    @BindView(R.id.second_name_edtx)
    EditText secondName;
    @BindView(R.id.save_to_db_btn)
    Button saveToDb;
    @BindView(R.id.display_btn)
    Button dropTable;
    @BindView(R.id.display_info_txtv)
    TextView displayInfo;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.where_to_update)
    EditText whereUpdate;
    @BindView(R.id.update_btn)
    Button updateButton;
    @BindView(R.id.update_data)
    EditText dataUpdate;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
    }

    @OnClick(R.id.save_to_db_btn)
    public void onSaveUser(View view) {
        String fName = firstName.getText().toString().trim();
        String sName = secondName.getText().toString().trim();
        saveUser(fName, sName);
        firstName.getText().clear();
        secondName.getText().clear();
    }

    @OnClick(R.id.display_btn)
    public void displayInfo(View view) {
        if (realm != null) {
            RealmResults<User> results = realm.where(User.class).findAll();
            displayInfo.setText("");
            for (User user : results) {
                displayInfo.append(user.getId() + "   " + user.getFirstName() + "   " + user.getSecondName() + "\n");
            }
        }
    }

    @OnClick(R.id.delete_btn)
    public void deleteAll(View view) {
        if (realm != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.deleteAll();
                }
            });
        }
    }

    @OnClick(R.id.update_btn)
    public void udateDate(View view) {
        String data = whereUpdate.getText().toString().trim();
        String newData = dataUpdate.getText().toString().trim();
        realm.beginTransaction();
        User user = realm.where(User.class).equalTo("firstName", data).findFirst();
        user.setFirstName(newData);
        realm.commitTransaction();
    }

    private void saveUser(final String fName, final String sName) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int nextId = getNextId(realm);
                User user = realm.createObject(User.class, nextId);
                user.setFirstName(fName);
                user.setSecondName(sName);
            }
        });
    }

    private int getNextId(Realm realm) {
        Number currentIdNum = realm.where(User.class).max("id");
        int nextId;
        if (currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }
        return nextId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
