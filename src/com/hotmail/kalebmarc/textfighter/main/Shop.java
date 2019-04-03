package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.item.FirstAid;
import com.hotmail.kalebmarc.textfighter.item.InstaHealth;
import com.hotmail.kalebmarc.textfighter.item.Power;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Health;
import com.hotmail.kalebmarc.textfighter.player.Potion;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.player.Xp;

import java.util.ArrayList;

class Shop {
	public Shop() {
	}

	public void menu() {
		while (true) {
			Ui.cls();
			Ui.println("-------------------------------------------------------------------");
			Ui.println("                        Welcome to the shop!                       ");
			Ui.println();
			Ui.println("Coins: " + Coins.get());
			Ui.println("First-Aid kits: " + FirstAid.get());
			Ui.println("Potions: " + (Potion.get("survival") + Potion.get("recovery")));
			Ui.println();
			Ui.println("-------------------------------------------------------------------");
			Ui.println("1) Health");
			Ui.println("2) Weapons/Ammo");
			Ui.println("3) Body Armour");
			Ui.println("4) Property");
			Ui.println("5) XP");
			Ui.println("6) Enhance");
			Ui.println("7) Weapon Roulette");
			Ui.println("8) Back");
			Ui.println("-------------------------------------------------------------------");
			switch (Ui.getValidInt()) {
			case 1:
				health();
				break;
			case 2:
				weapons();
				break;
			case 3:
				armour();
				break;
			case 4:
				property();
				break;
			case 5:
				xp();
				break;
			case 6:
				Shop s = new Shop();
				s.enhanceS();
				break;
			case 7:
				weaponRoulette();
				return;
			case 8:
				return;
			default:
				break;
			}
		}
	}

	private static void health() {

		while (true) {
			Ui.cls();
			Ui.println("-------------------------------------------------------------------");
			Ui.println("                               Health                              ");
			Ui.println();
			NPC.welcome("Health");
			Ui.println();
			Ui.println("Coins: " + Coins.get());
			Ui.println("First-Aid kits: " + FirstAid.get());
			Ui.println("Potions: " + (Potion.get("survival") + Potion.get("recovery")));
			Ui.println("Insta-Healths: " + InstaHealth.get());
			Ui.println();
			Ui.println("-------------------------------------------------------------------");
			Ui.println("1) FIRST-AID KIT");
			Ui.println("   Price - " + FirstAid.price + " coins");
			Ui.println("   Level - " + FirstAid.level);
			Ui.println();
			Ui.println("2) SURVIVAL POTION");
			Ui.println("   Price - " + Potion.spPrice + " coins");
			Ui.println("   Level - " + Potion.spLevel);
			Ui.println();
			Ui.println("3) RECOVERY POTION");
			Ui.println("   Price - " + Potion.rpPrice + " coins");
			Ui.println("   Level - " + Potion.rpLevel);
			Ui.println();
			Ui.println("3) STRENGTH POTION");
			Ui.println("   Price - " + Potion.rpPrice + " coins");
			Ui.println("   Level - " + Potion.rpLevel);
			Ui.println();
			Ui.println("3) AGILITY POTION");
			Ui.println("   Price - " + Potion.rpPrice + " coins");
			Ui.println("   Level - " + Potion.rpLevel);
			Ui.println();
			Ui.println("3) CHARM POTION");
			Ui.println("   Price - " + Potion.rpPrice + " coins");
			Ui.println("   Level - " + Potion.rpLevel);
			Ui.println();
			Ui.println("4) INSTA-HEALTH");
			Ui.println("   Price - " + InstaHealth.price + " coins");
			Ui.println("   Level - " + InstaHealth.level);
			Ui.println();
			Ui.println("5) Back");
			Ui.println("-------------------------------------------------------------------");
			switch (Ui.getValidInt()) {
			case 1:
				Ui.cls();
				FirstAid.buy();
				NPC.gratitude("Health", "purchase");
				break;
			case 2:
				Ui.cls();
				Potion.buy("survival");
				NPC.gratitude("Health", "purchase");
				break;
			case 3:
				Ui.cls();
				Potion.buy("recovery");
				NPC.gratitude("Health", "purchase");
				break;
			case 4:
				Ui.cls();
				InstaHealth.buy();
				NPC.gratitude("Health", "purchase");
				break;
			case 5:
				return;
			default:
				break;
			}
		}
	}

	private static void weapons() {
		while (true) {
			Ui.cls();
			Ui.println("-------------------------------------------------------------------");
			Ui.println("                              Weapons                              ");
			Ui.println();
			NPC.welcome("Weapon");
			Ui.println();
			Ui.println("Coins: " + Coins.get());
			Ui.println("Level: " + Xp.getLevel());
			Ui.println();
			Ui.println("-------------------------------------------------------------------");
			int j = 0;
			int[] offset = new int[Weapon.arrayWeapon.size()];
			for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
				if (Weapon.arrayWeapon.get(i).isBuyable()) {
					Ui.println((j + 1) + ") " + Weapon.arrayWeapon.get(i).getName());
					Ui.println("   Price: " + Weapon.arrayWeapon.get(i).price);
					Ui.println("   Level: " + Weapon.arrayWeapon.get(i).level);
					offset[j] = i - j;
					j++;
					Ui.println();
				}
			}
			Ui.println((j + 1) + ") POWER");
			Ui.println("   Price: " + Power.price);
			Ui.println("   Level: " + Power.level);
			Ui.println();
			Ui.println((j + 2) + ") AMMO");
			Ui.println();
			Ui.println((j + 3) + ") Back");

			while (true) {//Make it easy to break, without going back to main store menu

				int menuItem = Ui.getValidInt();

				try { //This is probably pretty bad practice. Using exceptions as a functional part of the program.. Use variables!

					//choices other than options in the array go here:
					if (menuItem == (j + 1))
						Power.buy();
					if (menuItem == (j + 2))
						buyAmmo();
					if (menuItem == (j + 3) || menuItem > j)
						return;

					//reverts back to Weapon indexing
					menuItem--;
					menuItem = menuItem + offset[menuItem];

					//Results go here:
					Weapon.arrayWeapon.get(menuItem).buy();
					return;

				} catch (Exception e) {
					Ui.println();
					Ui.println(menuItem + " is not an option.");
				}
			}
		}
	}

	private static void xp() {

		//Makes sure player has enough money
		boolean valid;

		while (true) {

			//Makes sure player isn't level 10 already
			if (Xp.getLevel() == 100) {
				Ui.msg("You're already level 100! You cannot buy any more xp.");
				return;
			}

			Ui.cls();
			Ui.println("-------------------------------------------------------------------");
			Ui.println("                                 XP                                ");
			Ui.println();
			NPC.welcome("XP");
			Ui.println();
			Ui.println("Level: " + Xp.getLevel());
			Ui.println("XP: " + Xp.getFull());
			Ui.println("Coins: " + Coins.get());
			Ui.println();
			Ui.println("You can buy XP for 1 coin per XP. How much would you like to buy?");
			Ui.println("**Enter 0 to go back**");
			Ui.println("-------------------------------------------------------------------");

			int buy = Ui.getValidInt();
			valid = true;

			//Tests
			if (buy > Coins.get()) {
				//Not enough coins
				Ui.msg("You don't have enough coins to buy this much xp.");
				valid = false;
			}
			if (Xp.getLevel() == 100) {
				Ui.msg("You are already level 100; which is the maximum level.");
				valid = false;
			}
			if (buy < 0) {
				Ui.msg("You can't buy a negative amount of Xp.. Nice try though ;)");
				valid = false;
			}
			if (buy == 0) {
				return;
			}

			if (valid) {
				Ui.msg("You have bought " + buy + " xp.");

				//Results
				Xp.set(buy, true);
				Coins.set(-buy, true);
				Stats.xpBought += buy;
				NPC.gratitude("XP", "purchase");
			}

		}
	}

	private static void buyAmmo() {


		while (true) {
			Ui.cls();
			Ui.println("-------------------------------------------------------------------");
			Ui.println("                                Ammo                               ");
			Ui.println();
			Ui.println("Coins: " + Coins.get());
			Ui.println("Level: " + Xp.getLevel());
			Ui.println();
			Ui.println("-------------------------------------------------------------------");
			ArrayList<Weapon> validWeapons = new ArrayList<Weapon>();
			for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
				if (Weapon.arrayWeapon.get(i).isBuyable() && !Weapon.arrayWeapon.get(i).melee && Weapon.arrayWeapon.get(i).owns()) {
					Ui.println((validWeapons.size() + 1) + ") " + Weapon.arrayWeapon.get(i).getName());
					Ui.println("   Price: " + Weapon.arrayWeapon.get(i).getAmmoPrice());
					Ui.println("   Level: " + Weapon.arrayWeapon.get(i).level);
					validWeapons.add(Weapon.arrayWeapon.get(i));
				}
			}
			Ui.println((validWeapons.size() + 1) + ") Back");

			while (true) {//Make it easy to break, without going back to main store menu

				int menuItem = Ui.getValidInt();

				try { //This is probably pretty bad practice. Using exceptions as a functional part of the program.. Use variables!
					validWeapons.get(menuItem - 1).buyAmmo();
					NPC.gratitude("Weapon", "purchase");
					break;

				} catch (Exception e) {

					if (menuItem == (validWeapons.size() + 1)) {
						return;
					}
					Ui.println();
					Ui.println(menuItem + " is not an option.");
					Ui.pause();
					Ui.cls();
				}
			}
		}
	}

	private static void property(){
		while (true){

			Ui.cls();
			Ui.println("________________________________________________");
			Ui.println("                    Property                    ");
			NPC.welcome("property");
			Ui.println("Level: " + Xp.getLevel());
			Ui.println("Coins: " + Coins.get());
			Ui.println("________________________________________________");

			//TODO do stuff to buy property
			Ui.pause();//temp


			return;
		}
	}

	private static void armour() {
		while (true) {
			Ui.cls();
			Ui.println("-------------------------------------------------------------------");
			Ui.println("                            Body Armour                            ");
			Ui.println();
			NPC.welcome("Armour");
			Ui.println();
			Ui.println("Coins: " + Coins.get());
			Ui.println("Level: " + Xp.getLevel());
			Ui.println();
			Ui.println("-------------------------------------------------------------------");
			int j = 0;
			int[] armourShopOffset = new int[Armour.getArmours().size()];
			for (int i = 0; i < Armour.getArmours().size(); i++) {
				if (Armour.getArmours().get(i).getPrice() != 0) {
					Ui.println((j + 1) + ") " + Armour.getArmours().get(i).getName());
					Ui.println("   Price: " + Armour.getArmours().get(i).getPrice());
					Ui.println("   Level: " + Armour.getArmours().get(i).getLevel());
					armourShopOffset[j] = i - j;
					j++;
					Ui.println();
				}
			}
			Ui.println((j + 1) + ") Back");

			while (true) {

				int menuItem = Ui.getValidInt();

				try {

					//choices other than options in the array go here:
					if (menuItem == j + 1 || menuItem > j)
						return;

					//reverts back to armour indexing
					menuItem--;
					menuItem = menuItem + armourShopOffset[menuItem];

					//Results go here:
					Armour.getArmours().get(menuItem).buy();
					return;

				} catch (Exception e) {
					Ui.println();
					Ui.println(menuItem + " is not an option.");
				}
			}
		}
	}

	private static void weaponRoulette() {
		while (true) {
			Ui.cls();
			Ui.println("-------------------------------------------------------------------");
			Ui.println("                              Weapon Roulette                              ");
			Ui.println("You can randomly craft weapons to make hybrid weapons.Each try costs 50 coins");
			Ui.println("1) Craft Weapons!                                                    ");
			Ui.println("2) Back to the menu                                            ");
			Ui.println("-------------------------------------------------------------------");
			int menuChoice = Ui.getValidInt();

			switch (menuChoice) {
			case 1:
				if(50>Coins.get()){
					Ui.println("You don't have enough coins to craft weapons");
					return;
				}
				Coins.set(-(50), true);
				ArrayList<Weapon> ownedWeapons = new ArrayList<>();

				for (int i = 0; i < Weapon.arrayWeapon.size(); i++) {
					if (Weapon.arrayWeapon.get(i).owns()) {
						ownedWeapons.add(Weapon.arrayWeapon.get(i));
					}
				}

				if(ownedWeapons.size()<=2){
					Ui.println("You don't own enough weapons to craft weapons");

					return;	
				}


				Weapon weap1 = ownedWeapons.get(Random.RInt(ownedWeapons.size()));	
				Weapon weap2 = Weapon.arrayWeapon.get(Random.RInt(Weapon.arrayWeapon.size()));	

				while(weap1.melee!=weap2.melee){
					weap1 = ownedWeapons.get(Random.RInt(ownedWeapons.size()));	
					weap2 = Weapon.arrayWeapon.get(Random.RInt(Weapon.arrayWeapon.size()));	

				}



				if(weap1.melee){
					Ui.println("Current stats of your weapon "+weap1.getmaxDamage());
					weap1.setminDamage(weap2.getminDamage());
					weap1.setmaxDamage(weap2.getmaxDamage());
					Ui.println("Stats of your weapon after crafting "+weap1.getmaxDamage());
				}else{
					Ui.println("Current ammo stats of your weapon "+weap1.getAmmo());

					weap1.setAmmo(weap2.getAmmo(), false); 
					Ui.println("Ammo stats of your weapon after crafting "+weap1.getAmmo());

				}

				break;
			case 2:
				return;
			default:
				break;
			}



		}
	}

	private void enhanceS(){
		int price = 100 + (Weapon.get().enhancement * 10);

		Ui.println("Do you want to enhance your weapon?");
		Ui.println();
		Ui.println("Price  : " + price);
		Ui.println();
		Ui.println("Write 1 if yes, 0 otherwise.");

		while(true){
			if(Ui.getValidInt() == 1 && Coins.get() >= price){
				if(Weapon.get().enhancement != 5){
					Coins.set(-1 * price,true);
				}
				Weapon.get().enhance();
				break;
			}
			else if(Coins.get() < price){
				Ui.println("You don't have enough money!");
				break;
			}
			else{
				Ui.println("Wrong command!");
				Ui.println();
				Ui.println("Do you want to enhance your weapon?");
				Ui.println();
				Ui.println("Price  : " + price);
				Ui.println();
				Ui.println("Write 1 if yes, 0 otherwise.");

			}
		}
	}
}