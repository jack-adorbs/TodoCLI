package TaskManager;
import java.io.Serializable;
import java.util.ArrayList;

public class TaskManager implements Serializable {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int tableId) {
        tasks.remove(tableId);
    }

    public void editTask(int tableId, String name) {
        tasks.get(tableId).setName(name);
    }

    public void editTask(int tableId, boolean done) {
        tasks.get(tableId).setDone(done);
    }

    public void printTable() {
        StringBuilder sb = new StringBuilder();
        int maxIdLength = (tasks.size() > 0) ? (int)Math.log(tasks.size()) + 3 : 3;
        int maxTaskLength = 5;
        int maxDoneLength = 5;

        for (Task task : tasks) {
            int current = task.getName().length();
            if (current > maxTaskLength) {
                maxTaskLength = current;
            }
        }
        maxTaskLength++;

        sb.append(
            "|" + padRight("Id", maxIdLength) + 
            "|" + padRight("Task", maxTaskLength) + 
            "|" + padRight("Done", maxDoneLength) + "|\n"
        );

        int i = 0;
        for (Task task : tasks) {
            sb.append(
                "|" + padRight(Integer.toString(i), maxIdLength) + 
                "|" + padRight(task.getName(), maxTaskLength) + 
                "|" + padRight(Boolean.toString(task.isDone()), maxDoneLength) + "|\n"
            );
            i++;
        }

        sb.trimToSize();
        System.out.print(sb.toString());
    }

    private String padRight(String s, int pad) {
        return s + " ".repeat(Math.abs(pad - s.length()));
    }
        
}