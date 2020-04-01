package ru.itmo.model;

public abstract class Account {
   private String number;
   private User owner;
   private double balance;

   public User getOwner() {
      return owner;
   }

   public void setOwner(User owner) {
      this.owner = owner;
   }

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }
}