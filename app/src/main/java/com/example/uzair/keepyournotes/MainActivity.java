package com.example.uzair.keepyournotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements NotesInterface {

    private CustomArrayAdapter arrayAdapter;
    RecyclerView recyclerView;
    Mydatabase myBase;
    static ArrayList<NotesModel> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        myBase = new Mydatabase(this, null, null, 1);


        notes = new ArrayList<>();

        getAllValues();

        notes.add(new NotesModel("uuu","qwerqwertyuiop[;lkjhgfdsazxc bnm,","2-2"));
        notes.add(new NotesModel("uuu","kjhgfdlkjhgazxcnbvcvbnmnbvbnm,","2-2"));
        notes.add(new NotesModel("uuu","wertyuiuhgvcxzsdfgbsazxc bnm,","2-2"));
        arrayAdapter = new CustomArrayAdapter(this,notes,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(arrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(),EditNotes.class);
                intent.putExtra("notes",false);
                startActivity(intent);
            }
        });

//        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int ii, long l) {
//
//                Log.i("sss","llllll");
//                new AlertDialog.Builder(MainActivity.this)
//                        .setIcon(android.R.drawable.alert_light_frame)
//                        .setMessage("This Item will be permanently delete")
//                        .setTitle("ARE YOU SURE!!!")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialogInterface,int i) {
//                                notes.remove(ii);
//                                MainActivity.arrayAdapter.notifyDataSetChanged();
//                                SharedPreferences sharedPreferences = getSharedPreferences("com.example.uzair.keepyournotes",MODE_PRIVATE);
//
//                                if (MainActivity.set == null) {
//                                    MainActivity.set = new HashSet<String>();
//                                }
//                                else {
//                                    MainActivity.set.clear();
//                                }
//                                MainActivity.set.addAll(MainActivity.notes);
//                                sharedPreferences.edit().putStringSet("set",MainActivity.set).apply();
//                            }
//                        })
//                        .setNegativeButton("No",null)
//                        .show();
//                return true;
//
//            }
//        });
    }

    public void getAllValues() {
        notes = myBase.getAllValues();
        return ;
    }

    @Override
    public void itemClicked(View view) {
        int itemPosition = recyclerView.getChildLayoutPosition(view);
        Intent intent  = new Intent(getApplicationContext(),EditNotes.class);
        intent.putExtra("notes",true);
        intent.putExtra("id",notes.get(itemPosition).getId());

        startActivity(intent);
    }
}
