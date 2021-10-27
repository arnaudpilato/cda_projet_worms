package org.wcscda.worms;

import java.awt.Color;
import java.util.ArrayList;

import org.wcscda.worms.board.weapons.*;

public class Player {
  private final String name;
  private final Color color;
  private final ArrayList<Worm> worms = new ArrayList<Worm>();

  public boolean isBeginner() {
    return beginner;
  }

  private boolean beginner = false;

  public void setBeginner(boolean beginner) {
    this.beginner = beginner;
  }

  public void setCurrentWeapon(AbstractWeapon currentWeapon) {
    this.currentWeapon = currentWeapon;
  }

  private AbstractWeapon currentWeapon;
  private int currentWormIndex = 0;

  public Player(String name, Color color) {
    this.name = name;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public Worm createWorm(String nom) {
    Worm worm = new Worm(this, nom);
    worms.add(worm);

    return worm;
  }

  public Color getColor() {
    return color;
  }

  public AbstractWeapon getCurrentWeapon() {
    return currentWeapon;
  }

  public ArrayList<Worm> getWorms() {
    return worms;
  }

  public Worm getActiveWorm() {
    if (getWorms().isEmpty()) {
      return null;
    }
    return getWorms().get(currentWormIndex);
  }

  public void setNextWorm() {
    if (worms.isEmpty()) return;

    currentWormIndex += 1;
    currentWormIndex %= worms.size();
  }

  /* NRO 2021-09-30 : TODO-student make a better version of
   * this, this is just a temporary version :-)
   * This should call the inventory, and handle
   */
  public void changeWeapon() {
    if (currentWeapon.isChangingWeaponDisabled()) {
      return;
    }

    if (currentWeapon instanceof Shotgun) {
      currentWeapon = new Hadoken();
    } else if (currentWeapon instanceof Hadoken) {
      currentWeapon = new Grenade();
    } else if ((currentWeapon instanceof Grenade) && (Helper.getActiveWorm().getAmmunitionSuperGrenade() == 1))  {
       {
        currentWeapon = new SuperGrenade();
      }
    } else if (currentWeapon instanceof SuperGrenade) {
      currentWeapon = new GrenadeBanane();
    } else {
      currentWeapon = new Shotgun();
    }
  }

  public void initWeapon() {
    currentWeapon = new Hadoken();
  }

  public boolean hasWorms() {
    return !getWorms().isEmpty();
  }
}
