package de.toomuchcoffee.hackandstash.domain;

import de.toomuchcoffee.hackandstash.utilities.Dice;

public class Hero extends Combatant {

    private int experience;

    private int level = 1;

    public Hero(int strength, int dexterity, int stamina) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.stamina = stamina;
        this.currentStamina = stamina;
    }

    public int getExperience() {
        return experience;
    }

    public void increaseExperience(int experience) {
        this.experience += experience;
    }

    public int damage() {
        return (getWeapon() != null ? getWeapon().damage() : Dice.D2.roll()) + getAttributeBonus(getStrength());
    }

    public void stash(Treasure... treasures) {
        for (Treasure treasure : treasures) {
            if (treasure instanceof Armor) {
                setArmor((Armor) treasure);
            } else if (treasure instanceof Weapon) {
                setWeapon((Weapon) treasure);
            }
        }
    }

    public void recoverStaminaBy(int recovery) {
        this.currentStamina = Math.min(this.currentStamina + recovery, this.stamina);
    }

    @Override
    public boolean isAlive() {
        return getStrength() > 0 && super.isAlive();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
