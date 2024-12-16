# Project Assignment POO  - J. POO Morgan - Phase One

![](https://s.yimg.com/ny/api/res/1.2/aN0SfZTtLF5hLNO0wIN3gg--/YXBwaWQ9aGlnaGxhbmRlcjt3PTcwNTtoPTQyNztjZj13ZWJw/https://o.aolcdn.com/hss/storage/midas/b23d8b7f62a50a7b79152996890aa052/204855412/fit.gif)

## Structure projectile:

* src/
    * main/
    * utils/
    * implementation/
      * AccountType/
        * Account
        * SavingsAccount
      * CardType
        * Card
        * OneTimeCard
      * CommandSystem
        * CommandsImplementation (contine implementarile pentru toate comenzile)
          * ...
        * CommandFactory
        * CommandHandler
        * Commands
      * TransactionThings
        * Transaction
        * TransactionBuilder
      * Bank
      * DataParser
      * Exchange
      * OutputCreator
      * User

## Project Implementation:

### First I separate the data using 4 main classes:

* *Bank* that is like database keeping all information about users, their account and card.
* *User* a class that represent a user keeping information about all of his transaction and containing all of his accounts.
* *Account* this class contain all the basic thing that are necessary for a bank account, like account balance how is the user that have that account and keep track off all cards which are associated with that account.
  * The Account class have an extension *SavingsAccount* that create a saving account adding the interest functionality.
  * Both of them are in *AccountsType* package.
* *Card* the class that represent a bank card keeping information about who owns it and which account it is associated with.
  * The Card class how to an extension that introduce the *OneTimeCard* a card that it can be used once and that need to be regenerated.
  * Both of them are in *CardType* package.

* For the separation I used the *DataParser* class who distributed the information.

### The next important thing is how the currency conversions work:

* *Exchange* is a class that converts all the currency to a mainRate that is the first
    currency that is wanted to be changed in another one.
* *The mainRate == first " from "*.
* The way the conversion works is by using the method *" getRate "*;
  * This method get to parameters " from " and " to " and return the exchange rate. *( to == getRate * from )*.

### The commands:
* are implemented using a *COMMAND PATTERN*
* all the logic of the commends functionalities are located in the *CommandsSystem* package.
* all commends are implemented in *CommandsImplementation*:
  * AddAccount
    * this functionality add an account to an user and to the bank database.
  * AddFunds
    * this functionality add money to an account.
  * AddInterest
    * this functionality add only to the savings accounts the interest obtained by that account.
  * ChangeInterest
    * this functionality change the interest of a saving account.
  * CheckCardStatus
    * this functionality verify what is the state of card.
  * CreatCard
    * this functionality create a new card for an account, and he also put it in the bank database.
  * CreateOneTimeCard
    * this functionality create a new one time card for an account, and he also put it in the bank database.
    * this type of card need to be regenerated each time a payment is done with it.
  * DefaultCommand
    * this functionality return a warning showing that a wrong command was entered.
  * DeleteAccount
    * this functionality delete an account if its balance is *0* and with it deletes all the card that associated with it.
  * DeleteCard
    * this functionality delete a card.
  * PayOnline 
    * this functionality is a card payment done between a user and a "comerciant".
  * PrintTransaction
    * this functionality show a list of all transaction that a user have done.
  * PrintUsers
    * this functionality show  a list of all users and all of their account and all the cards that the account are associated with.
  * Report 
    * this functionality show a list between two timestamp of all the transaction that were done with an account.
  * SendMoney
    * this functionality make a money transfer between two accounts.
  * SetAlias
    * this functionality associate to an account/IBAN an alias.
  * SetMinimumBalance
    * this functionality set a minimum amount that an account need to have left after any transaction.
  * SpendingReport
    * this functionality show a list o expenses that were done from an account.
  * SplitPayment
    * this functionality provide a way to make a safe payment using multiple accounts ensuring that before the payment is done all the accounts have enough money to pay there part.

### The transactions:

* Were implemented using a BUILDER PATTER:
* The transaction were implemented in the *TransactionThings* package.
* A transaction is build using class *TransactionBuilder*
* The transaction is stored using an object made with the class *Transaction* format.


### In the final is the *OutputCreator* class:
* In this class are multiple methods that have a single purpose to lode the data which need to be sent to th output in *json* format.