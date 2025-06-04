package trials;

public class TonisMain implements functionTrial {

    public void trial () {
        String welcomeString = "Go on";
        System.out.println(welcomeString.toUpperCase() + "\n");

        int option;
        MainMenu menu = new MainMenu();

        do{
            option = menu.mainMenu();

            switch(option){
                /*case 1:           ON PROGRESS
                case 2:
                case 3:
                case 4:
                case 0:*/
            }


        }while(option != 0);
    }
}
