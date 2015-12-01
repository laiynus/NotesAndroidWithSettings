package by.khrapovitsky.model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Serializable{

    static Integer currentId = 0;

    private Integer id;
    private String noteText;
    private String lastDateModify;

    public Note(){
    }

    public Note(Integer id,String noteText, String lastDateModify) {
        this.id = id;
        this.noteText = noteText;
        this.lastDateModify = lastDateModify;
    }

    public Note(String noteText,String lastDateModify){
        this.id = currentId++;
        this.noteText = noteText;
        this.lastDateModify = lastDateModify;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getLastDateModify() {
        return lastDateModify;
    }

    public void setLastDateModify(String lastDateModify) {
        this.lastDateModify = lastDateModify;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.noteText + " [Date: " + this.lastDateModify + "]";
    }
}
