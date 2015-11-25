package by.khrapovitsky.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

import by.khrapovitsky.R;
import by.khrapovitsky.dialog.AboutDialog;
import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.NoteRepository;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static int REQ_CODE_CHILD = 1;

    private List<Note> notes = NoteRepository.GetNotes();
    ArrayAdapter<Note> adapter = null;
    Button createButton = null;
    EditText noteText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView notesListView = (ListView) findViewById(R.id.NotesListView);

        adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, notes);

        notesListView.setAdapter(adapter);
        noteText = (EditText) findViewById(R.id.noteText);
        createButton = (Button) findViewById(R.id.action_create);
        createButton.setOnClickListener(this);
        registerForContextMenu(notesListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select action");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_listview, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_delete:
                notes.remove(info.position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Note has successfully deleted", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_update:
                Intent intent = new Intent(this, UpdateNoteActivity.class);
                intent.putExtra("note", notes.get(info.position));
                Integer tmp = info.position;
                intent.putExtra("index",tmp.toString());
                startActivityForResult(intent, REQ_CODE_CHILD);
                return true;
            default:
                super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.action_create:
                String note =  noteText.getText().toString();
                if(StringUtils.isBlank(note)){
                    Toast.makeText(getApplicationContext(), "Note can't be empty", Toast.LENGTH_LONG).show();
                }else{
                    notes.add(new Note(note,new Date()));
                    adapter.notifyDataSetChanged();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    noteText.setText("");
                    Toast.makeText(getApplicationContext(), "Note has successfully created", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if(requestCode == REQ_CODE_CHILD) {
            switch (resultCode) {
                case RESULT_OK:
                    Note note = (Note) data.getExtras().getSerializable("note");
                    notes.set(Integer.parseInt(data.getExtras().getString("index")), note);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Note has successfully updated", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_about:
                AboutDialog aboutDialog = new AboutDialog();
                aboutDialog.show(getFragmentManager(),"About");
                return true;
            default:
                return true;
        }
    }

}
