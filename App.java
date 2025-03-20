import java.io.File;
import java.util.Scanner;

import CommandLanguage.CommandLanguage;
import CommandLanguage.CommandLanguage.Command;
import TaskManager.TaskManager;

public class App {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner stdIn = new Scanner(System.in);
        String command;
        String[] parts;
        Command potentialCommand;
        File filename = new File("data.ser");
        
        CommandLanguage.setTaskManager(taskManager);
        if (filename.exists()) {
            CommandLanguage.evaluate(Command.load);
        } else {
            taskManager = new TaskManager();
        }
        

        while (true) {
            System.out.print("Enter a command: ");
            parts = stdIn.nextLine().trim().split("\\s+", 2);
            command = parts[0];

            try {
                potentialCommand = Command.valueOf(command.toLowerCase());
            } catch (IllegalArgumentException e) {
                potentialCommand = Command._unknown;
            }

            switch (potentialCommand) {
                case Command.quit:
                    CommandLanguage.evaluate(Command.quit, stdIn);
                    break;
                case Command.add:
                    CommandLanguage.evaluate(Command.add, parts[1]);
                    break;
                case Command.edit:
                    CommandLanguage.evaluate(Command.edit, parts[1]);
                    break;
                case Command.remove:
                    CommandLanguage.evaluate(Command.remove, parts[1]);
                    break;
                case Command.save:
                    CommandLanguage.evaluate(Command.save);
                    break;
                case Command.load:
                    CommandLanguage.evaluate(Command.load);
                    break;
                case Command.display:
                    CommandLanguage.evaluate(Command.display);
                    break;
                default:
                    CommandLanguage.evaluate(Command._unknown);
                    break;
            }
        }
    }
}