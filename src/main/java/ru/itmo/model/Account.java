package ru.itmo.model;

public abstract class Account {
   String number;
   User owner;
   double balance;
   //public abstract deposit(double amount)

   class Debit extends Account{

   }
   class Credit extends Account{
      double percent =15;

   }

}
