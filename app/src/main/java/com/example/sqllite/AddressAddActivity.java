package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddressAddActivity extends AppCompatActivity {
    EditText name_input, address_input, email_input, contact_input;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);
//        setting up the field by id
        name_input = findViewById(R.id.name_input);
        address_input = findViewById(R.id.address_input);
        email_input = findViewById(R.id.email_input);
        contact_input = findViewById(R.id.contact_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                check that any of one field is empty or not
                if (TextUtils.isEmpty(name_input.getText().toString()) ||
                    TextUtils.isEmpty(address_input.getText().toString()) ||
                    TextUtils.isEmpty(email_input.getText().toString()) ||
                    TextUtils.isEmpty(contact_input.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Every field is required !!!", Toast.LENGTH_SHORT).show();
                }else{
//                    use databaseHelper adding new record
                    DatabaseHelper myDB = new DatabaseHelper(AddressAddActivity.this);
                    myDB.addNew(name_input.getText().toString().trim(),
                            address_input.getText().toString().trim(),
                            email_input.getText().toString().trim(),
                            contact_input.getText().toString().trim());
                    }
//                calling clearField function
                clearField();
                }
            });
    }
//    clearing all field after insert the data
    public void clearField(){
        name_input.setText("");
        address_input.setText("");
        contact_input.setText("");
        email_input.setText("");

    }
}