package by.khrapovitsky.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import by.khrapovitsky.R;
import by.khrapovitsky.model.Note;

public class UpdateNoteActivity extends AppCompatActivity implements View.OnClickListener{

    EditText noteText = null;
    EditText dateModify = null;
    Button updateButton = null;
    Note note = null;
    String index = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        noteText = (EditText) findViewById(R.id.noteTextUpdate);
        dateModify = (EditText) findViewById(R.id.dateModifyUpdate);
        SimpleDateFormat date = new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");
        note = (Note) getIntent().getSerializableExtra("note");
        index = getIntent().getStringExtra("index");
        noteText.setText(note.getNoteText());
        noteText.setSelection(noteText.getText().length());
        dateModify.setText(date.format(note.getLastDateModify()));
        dateModify.setEnabled(false);
        dateModify.setFocusable(false);
        updateButton = (Button) findViewById(R.id.button_update);
        updateButton.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_update:
                String noteTmp =  noteText.getText().toString();
                if(StringUtils.isBlank(noteTmp)){
                    Toast.makeText(getApplicationContext(), "Note can't be empty", Toast.LENGTH_LONG).show();
                }else{
                    note.setNoteText(noteTmp);
                    note.setLastDateModify(new Date());
                    Intent resultIntent = new Intent();
                    setResult(Activity.RESULT_OK, resultIntent);
                    resultIntent.putExtra("note", note);
                    resultIntent.putExtra("index",index);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
