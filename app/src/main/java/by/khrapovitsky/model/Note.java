package by.khrapovitsky.model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Serializable{

    static Integer currentId = 0;

    private Integer id;
    private String noteText;
    private Date lastDateModify;

    public Note(){
    }

    public Note(String noteText, Date lastDateModify) {
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

    public Date getLastDateModify() {
        return lastDateModify;
    }

    public void setLastDateModify(Date lastDateModify) {
        this.lastDateModify = lastDateModify;
    }

    @Override
    public String toString() {
        SimpleDateFormat date = new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");
        return this.id + ". " + this.noteText + " [Date: " + date.format(this.lastDateModify) + "]";
    }
}
