import java.util.Date;

public class TodoItem  {
    private String title;
    private boolean isCompleted;
    private Date dueDate;

    public TodoItem() {
        // Default constructor required for Firebase
    }

    public TodoItem(String title, boolean isCompleted, Date dueDate) {
        this.title = title;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
