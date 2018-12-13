package com.example.user.expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class  Nodes implements Serializable {
    public int id;
    public String description;
    public int id_parent;
    public double weight_parent;
    public String convertibility;
    public String operation;
    public double value;
    public List<Integer> list_id_children;
    public int count_children;
}
public class MainActivity extends AppCompatActivity {
    Button btn_add;
    Button btn_check;
    Button refresh_btn;
    TextView TexV;
    List<Nodes> list_node=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = (Button) findViewById(R.id.add_click);
        btn_add.setOnClickListener(onClickAdd);
        btn_check = (Button) findViewById(R.id.clic_check);
        refresh_btn = (Button) findViewById(R.id.refresh);
        btn_check.setOnClickListener(onClickCheck);
        refresh_btn.setOnClickListener(onClickRefresh);
        TexV = (TextView) findViewById(R.id.myTextView);
        readFromFile2(TexV);
    }

    private  final View.OnClickListener onClickAdd= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this,Add.class);
            startActivity(intent);
        }
    };
    private  final View.OnClickListener onClickCheck= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this,check.class);
            startActivity(intent);
        }
    };
    private  final View.OnClickListener onClickRefresh= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TexV.setText("");
            readFromFile2(TexV);
        }
    };
    public void readFromFile2(TextView TexV) {
        try {
            String str = "ID | ID Родителя | Вес | Обратимость | Утверждение \n";
            String filePath = getFilesDir().getPath().toString() + "/fileName4.txt";
            File f = new File(filePath);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            list_node= (List<Nodes>)inputStream .readObject();
            inputStream.close();
            for(int i = 0; i < list_node.size(); i++) {
                str = str + list_node.get(i).id + " | " + list_node.get(i).id_parent + " | " + list_node.get(i).weight_parent + " | " + list_node.get(i).convertibility + " | " + list_node.get(i).description + "\n";
            }
            TexV.setText(str);
        } catch (Exception w) {
            w.printStackTrace();
        }
    }
}
