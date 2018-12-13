package com.example.user.expert;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {
    EditText edit_id;
    EditText edit_descroption;
    EditText edit_id_parent;
    EditText edit_count_children;
    EditText edit_weight_parent;
    EditText edit_obr;
    EditText edit_operation;
    Button   button_save;
    Button   button_add_children;
    List<Nodes>  list_node= new ArrayList<>();
    List<Integer>  list_id_children= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edit_id=(EditText)findViewById(R.id.id_node);
        edit_descroption=(EditText)findViewById(R.id.descriprion);
        edit_id_parent=(EditText)findViewById(R.id.id_parent);
        edit_count_children=(EditText)findViewById(R.id.count_children);
        edit_weight_parent=(EditText)findViewById(R.id.weight_parent);
        edit_obr=(EditText)findViewById(R.id.obr);
        edit_operation=(EditText)findViewById(R.id.operation);
        button_add_children= (Button) findViewById(R.id.add_children);
        button_save = (Button) findViewById(R.id.save);
        button_save.setOnClickListener(save_click);
        button_add_children.setOnClickListener(add_children_click);
        readFromFile();
    }
    View.OnClickListener add_children_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Add.this,children.class);

            startActivityForResult(intent, 1);
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String id = (data.getStringExtra("id_children"));
        list_id_children.add(Integer.valueOf(id));
    }
    View.OnClickListener save_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Save();
        }
    };
    public  void Save(){
        Nodes node= new Nodes();
        node.id=Integer.valueOf(edit_id.getText().toString());
        node.description=edit_descroption.getText().toString();
        node.id_parent=Integer.valueOf(edit_id_parent.getText().toString());
        node.count_children=Integer.valueOf(edit_count_children.getText().toString());
        node.weight_parent=Double.valueOf(edit_weight_parent.getText().toString());
        node.convertibility=edit_obr.getText().toString();
        node.operation=edit_operation.getText().toString();
        node.value=0;
        node.list_id_children = new ArrayList<>();
        for(int i=0;i<list_id_children.size();i++){
            node.list_id_children.add(list_id_children.get(i));
        }

        list_node.add(node);
        writeToFile();

    }
    public void readFromFile() {
        try {
            String filePath = getFilesDir().getPath().toString() + "/fileName4.txt";
            File f = new File(filePath);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            list_node= (List<Nodes>)inputStream .readObject();
            inputStream.close();
        } catch (Exception w) {
            w.printStackTrace();
        }
    }
    public  void writeToFile() {
        try {
            String filePath = getFilesDir().getPath().toString() + "/fileName4.txt";
            File f = new File(filePath);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream outStream = new ObjectOutputStream(fos);
           
            outStream.writeObject(list_node);
            outStream.flush();
            outStream.close();
            finish();

        } catch (IOException w) {
            w.printStackTrace();
        }
    }
}
