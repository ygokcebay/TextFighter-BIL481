package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Xp;

public class Loan {

	private static final double INTEREST_RATE_smallAmount = 0.30;
	private static final double INTEREST_RATE_avarageAmount = 0.20;
	private static final double INTEREST_RATE_largeAmount = 0.10;
	private static double INTEREST_RATE = 0.20;



	private static int currentLoan;
	private static int netDue;

	public static void menu() {
		while (true) {
			Ui.cls();
			Ui.println("-------------------------------");
			Ui.println("          PLAYER LOAN          ");
			Ui.println();
			Ui.println("Current interest rate for loans <= 300 : " + INTEREST_RATE_smallAmount);
			Ui.println("Current interest rate for loans 300 < loan < 600 : " + INTEREST_RATE_avarageAmount);
			Ui.println("Current interest rate for loans >= 600 : " + INTEREST_RATE_largeAmount);
			Ui.println("Max loan amount: " + getMaxLoan());
			Ui.println("Current loan: " + currentLoan);
			Ui.println("-------------------------------");
			Ui.println("Net due: " + netDue);
			setInterestRate(netDue);
			Ui.println("Interest due: " + (netDue * INTEREST_RATE));
			Ui.println("Gross due: " + getGrossDue());
			Ui.println("-------------------------------");
			Ui.println("1) Get loan");
			Ui.println("2) Pay off loan");
			Ui.println("3) Back");
			switch (Ui.getValidInt()) {
			case 1:
				createLoan();
				break;
			case 2:
				payLoan();
				break;
			case 3:
				return;
			}
		}
	}

	private static void createLoan() {



		if (hasLoan()) {
			Ui.msg("You can not request a loan while you already have one.");
			return;
		}

		Ui.cls();
		Ui.println("Enter the amount you would like");
		Ui.println("to borrow.");
		Ui.println("Your max amount allowed is " + getMaxLoan());
		int request = Ui.getValidInt();

		if (request > getMaxLoan()) {
			Ui.msg("Your max amount you can borrow is " + getMaxLoan() + "!");
			return;
		}
		if (request <= 0) {
			Ui.msg("You must enter at least 1 coin.");
			return;
		}

		//Give loan
		Coins.set(request, true);
		setInterestRate(request);
		Ui.println("With the amount you requested the interest will be " + INTEREST_RATE);


		currentLoan = request;
		netDue = request;
		Ui.cls();
		Ui.println("You have borrowed " + request + " from the bank.");
		Ui.println("You will not be able to deposit coins into the bank until your loan is fully paid off.");
		Ui.pause();
	}

	private static void payLoan() {
		if (getGrossDue() == 0) {
			Ui.println("You must enter at least 1 coin.");
			Ui.pause();
			return;
		}

		Ui.cls();
		Ui.println("You currently owe " + getGrossDue() + " coins, and have " + Coins.get() + " with you.");
		Ui.println("You will not be able to deposit coins into the bank until your loan is fully paid off.");
		Ui.println("How much would you like to pay off?");
		int amountToPay = Ui.getValidInt();

		Ui.cls();
		if (Coins.get() < amountToPay) {
			Ui.println("You don't have enough coins.");
			Ui.pause();
			return;
		}

		if (amountToPay <= 0) {
			Ui.println("You must enter at least 1 coin.");
			Ui.pause();
			return;
		}

		if (amountToPay > getGrossDue()) {
			Ui.println("You only owe " + getGrossDue() + "! Enter a small amount");
			Ui.pause();
		}
		netDue = getGrossDue() - amountToPay;
		Coins.set(-amountToPay, true);

		Ui.println("You have paid back " + amountToPay + " coins.\nYou now have " + getGrossDue() + " left to pay.");
		if (getGrossDue() == 0) currentLoan = 0;
		Ui.pause();
	}

	private static int getMaxLoan() {
		return Xp.getLevel() * 100;
	}

	public static int getCurrentLoan() {
		return currentLoan;
	}

	public static void setCurrentLoan(int loan) {
		currentLoan = loan;
	}

	public static boolean hasLoan() {
		return getCurrentLoan() > 0;
	}

	public static int getGrossDue() {
		setInterestRate(getNetDue());
		return (int) (netDue + (netDue * INTEREST_RATE));
	}

	public static int getNetDue() {
		return netDue;
	}

	public static void setInterestRate(int request) {
		if(request<=300)
			INTEREST_RATE = INTEREST_RATE_smallAmount;
		else if(request>300 && request<600)
			INTEREST_RATE = INTEREST_RATE_avarageAmount;
		else if(request>=600)
			INTEREST_RATE = INTEREST_RATE_largeAmount;

	}

	public static void setNetDue(int due) {
		netDue = due;
	}
}
