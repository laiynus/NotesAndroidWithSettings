package by.khrapovitsky.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteRepository {

    private static List<Note> NoteData = null;

    public static List<Note> GetNotes() {
        if (NoteData == null) {
            NoteData = new ArrayList<>();
            NoteData.add(new Note("This is first note", new Date()));
            NoteData.add(new Note("Test note", new Date()));
            NoteData.add(new Note("And again", new Date()));
            NoteData.add(new Note("Let's do it!", new Date()));
            NoteData.add(new Note("А может запись на русском языке", new Date()));
            NoteData.add(new Note("И еще одну", new Date()));
            NoteData.add(new Note("Как же мне надоело вводить эти записи", new Date()));
            NoteData.add(new Note("Ладно пожалуй введу еще одну", new Date()));
            NoteData.add(new Note("Эх, еще раз. еще много много раз", new Date()));
            NoteData.add(new Note("Ладно шучу последня запись", new Date()));
        }
        return NoteData;
    }

}
