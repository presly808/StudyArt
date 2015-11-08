package ua.artcode.view;

import org.apache.log4j.Logger;
import ua.artcode.exception.AppValidationException;
import ua.artcode.service.SimpleTaskService;
import ua.artcode.service.SimpleUserService;
import ua.artcode.validation.ConsoleMenuInputValidator;

import java.util.Scanner;

/**
 * Created by Family on 11/7/2015.
 */
public class View {
    private static final Logger LOGGER = Logger.getLogger(View.class);
        private static final int EXIT_ITEM = 0;
        private static final int CREATE_NEW_TASK = 1;
        private static final int SAVE_TEMPLATE_TO_FILE = 2;
        private static final int SAVE_TEST_RESULT_TO_FILE= 3;
        private static final int LOAD_IMPLEMENTED_TASKS = 4;
        private static final int SAVE_HISTORY = 5;
        private static final int USER_LOG_IN = 6;
        private static final int USER_REGISTRATION = 7;

        private SimpleTaskService taskService;
        private SimpleUserService noteService2;
        private Scanner scan;

    public View(SimpleTaskService taskService, SimpleUserService noteService2) {
        this.taskService = taskService;
        this.noteService2 = noteService2;
        scan = new Scanner(System.in);
    }

    public void consoleMenu() {
            int choice = EXIT_ITEM;
            do {
                showMenu();
                choice = getInput();
                switch (choice) {
                    case EXIT_ITEM:  System.exit(0);break;
                    case CREATE_NEW_TASK:break;
                    case SAVE_TEMPLATE_TO_FILE:break;
                    case SAVE_TEST_RESULT_TO_FILE:break;
                    case LOAD_IMPLEMENTED_TASKS:break;
                    case SAVE_HISTORY:break;
                    case USER_LOG_IN:break;
                    case USER_REGISTRATION:break;
                    default: System.out.println("Wrong selection, please try another one"); break;
                }
            } while (choice != EXIT_ITEM);

        }
        public void showMenu() {
            System.out.println("-----------------");
            System.out.println("1.CREATE_NEW_TASK");
            System.out.println("2.SAVE_TASK_TEMPLATE_TO_FILE");
            System.out.println("3.SAVE_TEST_RESULT_TO_FILE");
            System.out.println("4. LOAD_IMPLEMENTED_TASKS");
            System.out.println("5. SAVE_HISTORY");
            System.out.println("6. USER_LOG_IN");
            System.out.println("7. USER_REGISTRATION");
            System.out.println("0. EXIT_PROGRAM");
        }

        private int getInput() {
        int res = -1;
        String inputStr = scan.nextLine();
            try {
                if(new ConsoleMenuInputValidator().validate(inputStr)==true){
                    res =Integer.parseInt(inputStr);
                }
            } catch (AppValidationException e) {
                LOGGER.error(e);
            }
        return res;
    }
 }




