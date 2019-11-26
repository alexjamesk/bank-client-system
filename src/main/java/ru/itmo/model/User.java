package ru.itmo.model;

public class User {

    UserType type;
    String userName;
    String firstName;
    String lastName;
    String status;
    String address;
    int age;  //age of client - condition of approval credit or deposit
    int term; //The term of client's credit
    int monthlyIncome; //How much money client earn ia a month - it is the main condition of approval of credit
    int monthlyPay; //How much money exactly client need to pay for a credit
    int creditPercent;
    int depositPercent;
    int depositAccountBalance;
    int creditAccountBalance;
    String approval;
    String denial;
    int overpayment;
    int underpayment;
    int allBankClients;
    int allBankMoney;
    int bonus; // if client has no underpayments and has VIP-status, he can get bonus -1% of years
    int clientId;
}
