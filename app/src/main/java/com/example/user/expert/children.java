package com.example.user.expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class children extends AppCompatActivity {
    EditText edit_id_children;

    Button button_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children);
        edit_id_children=(EditText)findViewById(R.id.id_children);
        button_save = (Button) findViewById(R.id.save_children);
        button_save.setOnClickListener(save_click);

    }
    View.OnClickListener save_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            String val=(edit_id_children.getText().toString());
            intent.putExtra("id_children", val);

            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
