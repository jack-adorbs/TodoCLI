package CommandLanguage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Scanner;

import TaskManager.Task;
import TaskManager.TaskManager;

public class CommandLanguage {
    private static TaskManager _taskManager;
    public static enum Command {
        quit,
        add,
        edit,
        remove,
        display,
        save,
        load,
        _unknown
    }

    private interface Callable {
        public void run(Object... args);
    }

    private static Map<Command, Callable> commandMap = Map.ofEntries(
        Map.entry(Command.quit, (args) -> CommandLanguage.quit((Scanner)args[0])),
        Map.entry(Command.add, (args) -> CommandLanguage.add((String)args[0])),
        Map.entry(Command.edit, (args) -> CommandLanguage.edit((String)args[0])),
        Map.entry(Command.remove, (args) -> CommandLanguage.remove(Integer.parseInt((String)args[0]))),
        Map.entry(Command.save, (_) -> CommandLanguage.save()),
        Map.entry(Command.load, (_) -> CommandLanguage.load()),
        Map.entry(Command.display, (_) -> CommandLanguage.display()),
        Map.entry((Command._unknown), (_) -> CommandLanguage.unknown())
    );

    public static void evaluate(Command command, Object... args) {
        commandMap.get(command).run(args);
    }

    private static void quit(Scanner scanner) {
        scanner.close();
        System.out.println("Exiting now.");
        System.exit(0);
    }

    private static void add(String name) {
        _taskManager.addTask(new Task(name));
    }

    private static void edit(String args) {
        String[] parts = args.split("\\s+", 2);

        if (
            (parts[1].equalsIgnoreCase("true") || parts[1].equalsIgnoreCase("false")) 
            && parts.length == 2
            ) {
            _taskManager.editTask(Integer.parseInt(parts[0]), Boolean.parseBoolean(parts[1]));
        } else {
            _taskManager.editTask(Integer.parseInt(parts[0]), parts[1]);
        }
    }

    private static void remove(Integer i) {
        _taskManager.removeTask(i);
    }

    private static void save() {
        String filename = "data.ser";
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(_taskManager);
            System.out.println("Tasks successfully saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void load() {
        String filename = "data.ser";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            _taskManager = (TaskManager)in.readObject();
            System.out.println("Tasks successfully loaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void display() {
        _taskManager.printTable();
    }

    private static void unknown() {
        System.out.println("Unknown Command!");
    }

    public static void setTaskManager(TaskManager taskManager) {
        _taskManager = taskManager;
    }
}
