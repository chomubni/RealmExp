package com.example.ivan.realmexp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
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

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
    }

    @OnClick(R.id.save_to_db_btn)
    public void onSaveUser(View view){
        String fName = firstName.getText().toString().trim();
        String sName = secondName.getText().toString().trim();
        saveUser(fName, sName);
    }

    @OnClick(R.id.display_btn)
    public void displayInfo(View view){
        if(realm!=null){
//            Realm realmForDisplay = Realm.getDefaultInstance();
//            realmForDisplay.beginTransaction();
            RealmResults<User> results = realm.where(User.class).findAll();
            //realmForDisplay.close();
            for(User user : results){
                displayInfo.append(user.getId() + "   " + user.getFirstName() + "   " + user.getSecondName() + "\n");
            }
        }
    }

    private void saveUser(final String fName, final String sName) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentIdNum = realm.where(User.class).max("id");
                int nextId;
                if(currentIdNum == null) {
                    nextId = 1;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }
                User user = realm.createObject(User.class,nextId);
                user.setFirstName(fName);
                user.setSecondName(sName);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();

    }
}
