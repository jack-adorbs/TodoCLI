package TaskManager;
public class Task {
    private String name;
    private boolean done;
    
    public Task(String name) {
        this.name = name;
        done = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}