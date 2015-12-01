package by.khrapovitsky.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteRepository {

    private static List<Note> NoteData = null;

    public static List<Note> GetNotes() {
        SimpleDateFormat date = new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");
        if (NoteData == null) {
            NoteData = new ArrayList<>();
            NoteData.add(new Note("This is first note", date.format(new Date())));
            NoteData.add(new Note("Test note", date.format(new Date())));
            NoteData.add(new Note("And again", date.format(new Date())));
            NoteData.add(new Note("Let's do it!", date.format(new Date())));
            NoteData.add(new Note("А может запись на русском языке", date.format(new Date())));
            NoteData.add(new Note("И еще одну", date.format(new Date())));
            NoteData.add(new Note("Как же мне надоело вводить эти записи", date.format(new Date())));
            NoteData.add(new Note("Ладно пожалуй введу еще одну", date.format(new Date())));
            NoteData.add(new Note("Эх, еще раз. еще много много раз", date.format(new Date())));
            NoteData.add(new Note("Ладно шучу последня запись", date.format(new Date())));
        }
        return NoteData;
    }

}
