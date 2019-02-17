package com.hotmail.kalebmarc.textfighter.main;

class Menu {
	
	private Menu(){}
	public static void load(){
		while(true){
			
			Ui.cls();
			//Menu Screen
			Ui.println("_____________________________________________");
			Ui.println("|          WELCOME TO TEXT FIGHTER          |");
			Ui.println("|        A Text-Based Fighting Game         |");
			Ui.println("|*******************************************|");
			Ui.println("|                                           |");
			Ui.println("|   To get started, Type in a number below  |");
			Ui.println("|             and press enter.              |");
			Ui.println("|                                           |");
			Ui.println("| 1) Start Game                             |");
			Ui.println("| 2) About Game                             |");
			Ui.println("| 3) Exit                                   |");
			Ui.println("|             www.TextFighter.tk            |");
			Ui.println("|___________________________________________|");

            switch (Ui.getValidInt()) {
                case 1:
                    Ui.cls();
                    Ui.guiEnabled = false;
                    Game.start();

                    //Saves the game before exiting
                    Saves.save();
                    return;
                case 2:
                    About.view(false);
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }//Loop
    }//Method
}//Class
