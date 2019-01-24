package com.codecool.battleofcards.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {
    private int id;
    private int melee;
    private int magic;
    private int dexterity;
    private int intelligence;
    private int strength;
    private String name;


    public Card(int strength, int melee, int magic, int dexterity, int intelligence, String name, int id) {
        this.strength = strength;
        this.melee = melee;
        this.magic = magic;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMelee() {
        return melee;
    }

    public int getMagic() {
        return magic;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setMelee(int value) {
        melee = value;
    }

    public void setMagic(int value) {
        magic = value;
    }

    public void setDexterity(int value) {
        dexterity = value;
    }

    public void setIntelligence(int value) {
        intelligence = value;
    }

    public void setStrength(int value) {
        strength = value;
    }

    public List<Integer> getAttributeList() {
        return new ArrayList<>(Arrays.asList(getStrength(), getMelee(), getMagic(), getDexterity(), getIntelligence()));
    }


}