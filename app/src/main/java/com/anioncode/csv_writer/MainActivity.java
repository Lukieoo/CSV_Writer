package com.anioncode.csv_writer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.data);
        ///SAVE CSV FILE
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File CsvFile = new File(path, "");

        List<List<String>> rows = Arrays.asList(
                Arrays.asList("Olaf", "Corpi", "true"),
                Arrays.asList("Robert", "Alos", "true"),
                Arrays.asList("Carol", "Faras", "false")
        );

        FileWriter writer = null;

        try {
            writer = new FileWriter(path + "/csvfile.csv");
            writer.append("Name");
            writer.append(",");
            writer.append("Surname");
            writer.append(",");
            writer.append("Name");
            writer.append("Code");
            writer.append("\n");
            for (List<String> rowData : rows) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    writer.append(String.join(",", rowData));
                }
                writer.append("\n");

            }
            Toast.makeText(this, "Work" + path, Toast.LENGTH_LONG).show();
            writer.flush();
            writer.close();

        } catch (IOException e) {
            Toast.makeText(this, "Not Work", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        ///NOW WY ARE READING DATA BY CSV FILE
        File fileRead = new File(path + "/csvfile.csv");
        if (fileRead.isFile()) {
            BufferedReader bufferedReader = null;
            try {
                String row;
                String row1 = "";

                String[] data = new String[0];
                bufferedReader = new BufferedReader(new FileReader(path + "/csvfile.csv"));

                while ((row = bufferedReader.readLine()) != null) {
                    data=row.split(",");
                    for(String words:data){
                        row1+=words+" ";
                    }
                    row1+="\n";
                }
                view.setText(row1);
                bufferedReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
