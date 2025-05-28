package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    public int mainMenu(){
        Scanner sc = new Scanner(System.in);
        int option = -1;

        do{
        System.out.println("Select an option\n" +
                            "1. Create\n" +
                            "2. Update\n" +
                            "3. View\n" +
                            "4. Delete\n" +
                            "0. Exit\n");
        try{
            option = sc.nextInt();
            if(option < 0 || option > 4){
                System.out.println("Wrong number!! Enter an integer between 0 - 4");
            }
        }catch (InputMismatchException e){
            System.out.println("The input isn't valid, must be an integer between 0 - 4");
            sc.nextLine();
        }

        }while(option < 0 || option > 4);
        return option;
    }
}