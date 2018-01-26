package com.example.uzair.keepyournotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class EditNotes extends AppCompatActivity implements View.OnClickListener {
    boolean note;
    EditText tvTitle,tvBody;
    Mydatabase mybase;
    private Button submit;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        Intent intent = getIntent();

        note = intent.getBooleanExtra("notes",false);
        mybase = Mydatabase.getInstance();
        submit = (Button) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(this);
        tvTitle = (EditText) findViewById(R.id.editText);
        tvBody = (EditText) findViewById(R.id.editText2);
        if (note){

            id  = intent.getStringExtra("id");

            NotesModel notesModel = mybase.getValue(id);
            tvTitle.setText(notesModel.getName());
            tvBody.setText(notesModel.getBody());
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSubmit:
                Date currentTime = Calendar.getInstance().getTime();
                final String time = String.valueOf(currentTime.getDate());
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.alert_light_frame)
                        .setMessage("Do you want to save this?")
                        .setTitle("ARE YOU SURE!!!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i) {
                                if(note){
                                    NotesModel nn=new NotesModel(tvTitle.getText().toString(),tvBody.getText().toString(),time);
                                    nn.setId(id);
                                    mybase.updateNotes(nn);
                                }
                                mybase.addNotes(new NotesModel(tvTitle.getText().toString(),tvBody.getText().toString(),time));
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                break;
        }
    }
}