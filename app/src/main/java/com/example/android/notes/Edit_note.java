package com.example.android.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

import static com.example.android.notes.MainActivity.notes;

public class Edit_note extends AppCompatActivity implements TextWatcher {
    int noteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText editText=(EditText)findViewById(R.id.editText);
        Intent i=getIntent();
        noteid=i.getIntExtra("noteid",-1);

        if(noteid!=-1)
        {
            editText.setText(notes.get(noteid));
        }
        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        notes.set(noteid,String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();
        if(MainActivity.set==null)
        {
            MainActivity.set=new HashSet<String>();
        }

else {
            MainActivity.set.clear();
        }
        MainActivity.set.addAll(notes);//adding the example notes to set
        MainActivity.sharedPreferences.edit().remove("notes").apply();//so that after deleting the notes vanishes and does not persisits in the memeory
        MainActivity.sharedPreferences.edit().putStringSet("notes",MainActivity.set).apply();//adding everything to the memory
        MainActivity.arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
