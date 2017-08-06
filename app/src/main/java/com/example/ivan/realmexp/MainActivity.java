package com.example.ivan.realmexp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.first_name_edtx)
    EditText firstName;
    @BindView(R.id.second_name_edtx)
    EditText secondName;
    @BindView(R.id.save_to_db_btn)
    Button saveToDb;
    @BindView(R.id.drop_table_btn)
    Button dropTable;
    @BindView(R.id.display_info_txtv)
    TextView displayInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
