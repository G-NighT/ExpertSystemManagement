package com.example.user.expert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class check extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    EditText edit7;
    EditText edit8;
    EditText edit9;
    EditText edit10;
    EditText edit11;
    EditText edit12;
    EditText edit13;
    Button button_check;
    List<Nodes> list_pr=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        edit1=(EditText)findViewById(R.id.id1);
        edit2=(EditText)findViewById(R.id.id2);
        edit3=(EditText)findViewById(R.id.id3);
        edit4=(EditText)findViewById(R.id.id4);
        edit5=(EditText)findViewById(R.id.id5);
        edit6=(EditText)findViewById(R.id.id6);
        edit7=(EditText)findViewById(R.id.id7);
        edit8=(EditText)findViewById(R.id.id8);
        edit9=(EditText)findViewById(R.id.id9);
        edit10=(EditText)findViewById(R.id.id10);
        edit11=(EditText)findViewById(R.id.id11);
        edit12=(EditText)findViewById(R.id.id12);
        edit13=(EditText)findViewById(R.id.id13);
        button_check = (Button) findViewById(R.id.check);
        button_check.setOnClickListener(check);
        readFromFile();
    }

    View.OnClickListener check = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            List<Double> list_val=new ArrayList<>();
            List<Nodes> list_node=new ArrayList<>();
            list_val.clear();
            list_node.clear();
            readFromFile();

            for (int i=list_pr.size()-1; i>=0; i--) {
                list_node.add(list_pr.get(i));
            }

            if(edit1.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit2.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit3.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit4.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit5.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit6.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit7.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit8.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit9.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit10.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit11.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit12.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }
            if(edit13.getText().toString().equals("Да")){
                list_val.add(Double.valueOf(1));
            }
            else {
                list_val.add(Double.valueOf(0));
            }

            /*
            list_node.remove(0);
            list_node.get(5).list_id_children=new ArrayList<>();
            list_node.get(5).list_id_children.add(19);
            list_node.get(5).list_id_children.add(20);
            list_node.get(5).list_id_children.add(21);
            list_node.get(5).count_children=3;*/

            for (int i = 0; i < list_val.size(); i++)
            {
                for(int j=0;j<list_node.size();j++){
                    if(list_node.get(j).count_children==0 && list_node.get(j).value==0){
                        list_node.get(j).value=list_val.get(list_val.size()-1-i);
                        break;
                    }
                }
            }
            for (int i = 0; i < list_node.size(); i++)
            {
                //если есть дети
                if (list_node.get(i).count_children != 0)
                {
                    //если один ребенок
                    if (list_node.get(i).count_children == 1)
                    {
                        list_node.get(i).value = ValueForOneChild(list_node.get(i).weight_parent, list_node.get(i).value);
                    }
                    //если два ребенка
                    if (list_node.get(i).count_children == 2)
                    {
                        //если между ними стоит оператор ИЛИ
                        if (list_node.get(i).operation.equals("ИЛИ"))
                        {
                            double weight_1=0;
                            double value_1=0;
                            double weight_2=0;
                            double value_2=0;
                            for(int j=0;j<list_node.size();j++){
                                if(list_node.get(i).list_id_children.get(0) == list_node.get(j).id){
                                    weight_1=list_node.get(j).weight_parent;
                                    value_1=list_node.get(j).value;
                                    break;
                                }
                            }
                            for(int j=0;j<list_node.size();j++){
                                if(list_node.get(j).id==list_node.get(i).list_id_children.get(1)){
                                    weight_2=list_node.get(j).weight_parent;
                                    value_2=list_node.get(j).value;
                                    break;
                                }
                            }
                            //ищем макс
                            if (value_1 < value_2)
                            {
                                list_node.get(i).value = value_2 * weight_1;
                            }
                            else
                            {
                                list_node.get(i).value = value_1 * weight_1;
                            }
                        }
                        //если между ними оператор И
                        if (list_node.get(i).operation.equals("И"))
                        {
                            double weight_1=0;
                            double value_1=0;
                            double weight_2=0;
                            double value_2=0;
                            for(int j=0;j<list_node.size();j++){
                                if(list_node.get(i).list_id_children.get(0) == list_node.get(j).id){
                                    weight_1=list_node.get(j).weight_parent;
                                    value_1=list_node.get(j).value;
                                    break;
                                }
                            }
                            for(int j=0;j<list_node.size();j++){
                                if(list_node.get(j).id==list_node.get(i).list_id_children.get(1)){
                                    weight_2=list_node.get(j).weight_parent;
                                    value_2=list_node.get(j).value;
                                    break;
                                }
                            }
                            //ищем мин
                            if (value_1 < value_2)
                            {
                                list_node.get(i).value = value_1 * weight_1;
                            }
                            else
                            {
                                list_node.get(i).value = value_2 * weight_1;
                            }
                        }
                        //если между ними НЕТ оператора
                        if ( list_node.get(i).operation == "Нет")
                        {
                            double weight_1=0;
                            double value_1=0;
                            double weight_2=0;
                            double value_2=0;
                            for(int j=0;j<list_node.size();j++){
                                if(list_node.get(i).list_id_children.get(0) == list_node.get(j).id){
                                    weight_1=list_node.get(j).weight_parent;
                                    value_1=list_node.get(j).value;
                                    break;
                                }
                            }
                            for(int j=0;j<list_node.size();j++){
                                if(list_node.get(j).id==list_node.get(i).list_id_children.get(1)){
                                    weight_2=list_node.get(j).weight_parent;
                                    value_2=list_node.get(j).value;
                                    break;
                                }
                            }
                            list_node.get(i).value = ValueForTwoChildren(value_1, weight_1, value_2, weight_2);
                        }
                    }
                    //если детей 3
                    if (list_node.get(i).count_children == 3)
                    {
                        double weight_1=0;
                        double value_1=0;
                        double weight_2=0;
                        double value_2=0;
                        double weight_3=0;
                        double value_3=0;
                        for(int j=0;j<list_node.size();j++){
                            if(list_node.get(i).list_id_children.get(0) == list_node.get(j).id){
                                weight_1=list_node.get(j).weight_parent;
                                value_1=list_node.get(j).value;
                                break;
                            }
                        }
                        for(int j=0;j<list_node.size();j++){
                            if(list_node.get(j).id==list_node.get(i).list_id_children.get(1)){
                                weight_2=list_node.get(j).weight_parent;
                                value_2=list_node.get(j).value;
                                break;
                            }
                        }
                        for(int j=0;j<list_node.size();j++){
                            if(list_node.get(j).id==list_node.get(i).list_id_children.get(2)){
                                weight_3=list_node.get(j).weight_parent;
                                value_3=list_node.get(j).value;
                                break;
                            }
                        }
                        list_node.get(i).value = ValueForThreeChildren(value_1, weight_1, value_2, weight_2, value_3, weight_3);
                    }
                }
            }
            double val_end = list_node.get(list_node.size()-1).value;
            String message = list_node.get(list_node.size()-1).description + " на " + Math.round(val_end * 100) + " %";
            Toast.makeText(check.this, message, Toast.LENGTH_LONG).show();
            list_pr.clear();
        }
    };
    public double ValueForOneChild(double weight,double value)
    {
        double X1 = weight * value;
        return X1;
    }

    public double ValueForTwoChildren(double value1,double weight1,double value2, double weight2)
    {
        double X1 = value1 * weight1;
        double X2 = value2 * weight2;
        return X1 + X2 - X1 * X2;
    }

    public double ValueForThreeChildren(double value1, double weight1, double value2, double weight2, double value3, double weight3)
    {
        double X1 = value1 * weight1;
        double X2 = value2 * weight2;
        double X3 = value3 * weight3;
        //return (X1 + X2 - X1 * X2) + X3 - (X1 + X2 - X1 * X2) * X3;
        return X1 + X2 + X3 - X1 * X3 - X2 * X3 - X1 * X2 + X1 * X2 * X3;
    }
    public void readFromFile() {
        try {
            list_pr.clear();
            String filePath = getFilesDir().getPath().toString() + "/fileName4.txt";
            File f = new File(filePath);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            list_pr= (List<Nodes>)inputStream .readObject();
            inputStream.close();
        } catch (Exception w) {
            w.printStackTrace();
        }
    }
}
