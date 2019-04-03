package com.hotmail.kalebmarc.textfighter.player;

import com.hotmail.kalebmarc.textfighter.main.Handle;
import com.hotmail.kalebmarc.textfighter.main.Ui;

public class Potion {
    public static int spUsed = 0;
    public static int spLevel;
    public static int spPrice;
    //TODO will eventually add potions to heal status ailments
    //TODO possibly add potion that gives player a temporary strength boost (Does more damage)
    public static int rpUsed = 0;
    public static int rpLevel;
    public static int rpPrice;
    public static int stpPrice;
    public static int agipPrice;
    public static int cpPrice;
    public static int stpLevel;
    public static int agipLevel;
    public static int cpLevel;
    private static int survivalPotion; //potion that heals 25% of health
    private static int recoveryPotion; //potion that heals 75% of health
    private static int statsPotion;//potion that add +1 to all stats
    private static int STRpotion;//+1 to STR
    private static int AGIpotion;//+1 to AGI
    private static int CHRpotion;//+1 to INT

    private Potion() {
    }

    public static int get(String kind) {
        switch (kind.toLowerCase()) {
            case "survival":
                return survivalPotion;
            case "recovery":
                return recoveryPotion;
            case "stats":
                return statsPotion;
            case "strength":
                return STRpotion;
            case "intelligence":
                return CHRpotion;
            case "agility":
                return AGIpotion;
            default:
                return 0; //need to modify
        }
    }

    public static void set(String kind, int amount, boolean add) {
        switch (kind.toLowerCase()) {
            case "survival":
                if (!add) {
                    survivalPotion = amount;
                } else {
                    survivalPotion += amount;
                    if (survivalPotion < 0) survivalPotion = 0;
                }
                break;
            case "recovery":
                if (!add) {
                    recoveryPotion = amount;
                } else {
                    recoveryPotion += amount;
                    if (recoveryPotion < 0) recoveryPotion = 0;
                }
                break;
            case "allstats":
                if (!add) {
                    statsPotion = amount;
                } else {
                    statsPotion += amount;
                    if (statsPotion < 0) statsPotion = 0;
                }
                break;
            case "strength":
                if (!add) {
                    STRpotion = amount;
                } else {
                    STRpotion += amount;
                    if (STRpotion < 0) STRpotion = 0;
                }
                break;
            case "agility":
                if (!add) {
                    AGIpotion = amount;
                } else {
                    AGIpotion += amount;
                    if (AGIpotion < 0) AGIpotion = 0;
                }
                break;
            case "charm":
                if (!add) {
                    CHRpotion = amount;
                } else {
                    CHRpotion += amount;
                    if (CHRpotion < 0) CHRpotion = 0;
                }
                break;
            default:
                Handle.error("Unknown potion type: " + kind);
        }
    }

    public static void use(String k) {
        String kind = k.trim().substring(0, 1).toUpperCase()
                + k.substring(1).toLowerCase();
        Ui.cls();

        if (get(kind) <= 0) {

            Ui.println("----------------------------------------------------");
            Ui.println("You have no " + kind + " Potions left!");
            Ui.println("Go to the shop to buy some more.");
            Ui.println("----------------------------------------------------");
            Ui.pause();

        } else if (Health.get() == 100) {

            Ui.println("----------------------------------------------------");
            Ui.println("You already have full health!");
            Ui.println("You don't need to use a " + kind + " Potion!");
            Ui.println("----------------------------------------------------");
            Ui.println("Your health: " + Health.getStr());
            Ui.println(kind + " Potions: " + get(kind));
            Ui.println("----------------------------------------------------");
            Ui.pause();

        } else {

            set(kind, -1, true);
            int heal = (int) Math.round(healBy(kind));
            Health.gain(heal);
            used(kind);

            Ui.println("----------------------------------------------------");
            Ui.println("You have used a " + kind + " Potion.");
            Ui.println("You've gained " + heal + " health.");
            Ui.println("----------------------------------------------------");
            Ui.println("Your health: " + Health.getStr());
            Ui.println(kind + " Potions: " + get(kind));
            Ui.println("----------------------------------------------------");
            Ui.pause();

        }

    }

    public static double healBy(String kind) {
        switch (kind.toLowerCase()) {
            case "survival":
                return Health.getOutOf() * .25;
            case "recovery":
                return Health.getOutOf() * .75;
            default:
                return 0;
        }
    }

    public static void used(String kind) {
        switch (kind.toLowerCase()) {
            case "survival":
                spUsed++;
            case "recovery":
                rpUsed++;
        }
    }

    public static void buy(String kind) {

        int level = getLevel(kind);
        int price = getPrice(kind);

        if (Xp.getLevel() < level) {
            Ui.println("You have to be at least level " + level + " to buy this!");
            Ui.pause();
        } else if (price <= Coins.get()) {
            Coins.set(-price, true);
            Stats.coinsSpentOnHealth += price;
            set(kind, 1, true);
            Ui.println("Thank you for your purchase. Come again soon! ");
            Ui.pause();
        } else {
            Ui.println("You do not have enough coins.");
            Ui.pause();
        }
    }

    public static int getLevel(String kind) {
        switch (kind.toLowerCase()) {
            case "survival":
                return spLevel;
            case "recovery":
                return rpLevel;
            default:
                return 0; //need to modify
        }
    }

    public static int getPrice(String kind) {
        switch (kind.toLowerCase()) {
            case "survival":
                return spPrice;
            case "recovery":
                return rpPrice;
            default:
                return 0; //need to modify
        }
    }
}
