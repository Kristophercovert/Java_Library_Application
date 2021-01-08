package com.main;
import java.util.Scanner;
import java.util.Vector;

public class Source {

    public static void main(String[] args) {
        Vector<Book> Books = new Vector<Book>(0);
        Vector<Journal> Journals = new Vector<Journal>(0);
        Vector<Magazine> Magazines = new Vector<Magazine>(0);


        {
            //LOADING THE LIBRARY WITH BOOKS
            Books.add(new Book("Java is Dope", 123, false, "Kristopher Covert"));
            Books.add(new Book("Java and Me", 374, false, "Michael Jackson"));
            Books.add(new Book("Java Coffee on my Java", 386, false, "Brad Pitt"));
            Books.add(new Book("The Java Handbook", 133, false, "Brittany Spears"));
            Books.add(new Book("Java Is My Homeboy", 773, false, "Dababy"));
            Books.add(new Book("ARGGHHH JAVA", 993, false, "Davey Jones"));

            //LOADING THE LIBRARY WITH MAGAZINES
            Magazines.add(new Magazine("Java Digest", 1234, false, 22));
            Magazines.add(new Magazine("Java Illustrated", 1734, false, 1));
            Magazines.add(new Magazine("The Java Magazine", 5554, false, 27));
            Magazines.add(new Magazine("J.A.V.A.", 5634, false, 125));
            Magazines.add(new Magazine("Java Digest", 1235, false, 23));

            //LOADING THE LIBRARY WITH JOURNALS
            Journals.add(new Journal("Java Journal", 12763, false, 2));
            Journals.add(new Journal("My Experiences with Java", 12893, false, 4));
            Journals.add(new Journal("Living with Java", 37767, false, 8));
            Journals.add(new Journal("3 Ways to Improve your Life with Java", 11125, false, 1));
            Journals.add(new Journal("Java Journal", 12765, false, 4));
        }

        boolean again = true;
        Scanner ScanObj = new Scanner(System.in);
        char choice;
        System.out.println("Welcome to Kristopher Covert's Library");


        while(again){

            System.out.println(" ");
            System.out.println("What would you like to do? Enter the number of the action you would like. ");
            showMenu();

            try{
                System.out.print("Enter your Number: ");
                choice = ScanObj.next().charAt(0);

               if(Character.getNumericValue(choice) > 6 || Character.getNumericValue(choice) < 1){
                    throw new Exception("Invalid Entry, Try again");
                }else if(Character.getNumericValue(choice) == 6){
                   break;
               }

               switch(choice){
                   case '1':
                       System.out.println("You Chose 1!");
                       Search(Books, Magazines,Journals);
                       break;
                   case '2':
                       System.out.println("You Chose 2!");
                       CheckOutItem(Books, Magazines,Journals);
                       break;
                   case '3':
                       System.out.println("You Chose 3!");
                       returnItem(Books, Magazines,Journals);
                       break;
                   case '4':
                       System.out.println("You Chose 4!");
                        addItem(Books, Magazines,Journals);
                       break;
                   case '5':
                       System.out.println("You Chose 5!");
                       System.out.println(" ");
                       displayInventory(Books, Magazines,Journals);
                       break;
                   case '6':
                       System.out.println("You Chose 6!");
                       break;

               }


            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Thank you For Using Kristopher Covert's Library");


    }

    public static void showMenu(){
        System.out.println(" ");
        System.out.println("1. Search Catalog");
        System.out.println("2. Check Out an Item");
        System.out.println("3. Return an Item");
        System.out.println("4. Add a New Item");
        System.out.println("5. Display Inventory");
        System.out.println("6. Exit");
        System.out.println(" ");
    }

    public static void addItem(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){
        Scanner scanForItem = new Scanner(System.in);
        String itemToBeAdded;
        System.out.println("What Type of Literature are you Adding Today?");
        itemToBeAdded = scanForItem.next();

        if(itemToBeAdded.equals("Book") || itemToBeAdded.equals("book")){
            addBook(Books);
        }else if(itemToBeAdded.equals("Magazine") || itemToBeAdded.equals("magazine")){
            addMagazine(Magazines);
        }else if(itemToBeAdded.equals("Journal") || itemToBeAdded.equals("journal")){
            addJournal(Journals);
        }else {
            System.out.println("Invalid Entry, We Only carry Books, Magazines, and Journals");
        }
    }

    public static void returnItem(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){

        Scanner scanID = new Scanner(System.in);
        int searchKey;
        System.out.print("Please Enter the Item ID for the Item you are trying to Return: ");
        searchKey = scanID.nextInt();

        if(searchKey < 100 || searchKey > 100000){
            System.out.println("Invalid Entry");
        }
        //BOOKS
        else if(searchKey > 99 && searchKey < 1000){
            Book bookCheckOut = findBook(searchKey,Books);
            if(bookCheckOut == null){
                System.out.println("Could Not Find Item With Matching ID");
            }else if(!bookCheckOut.isCheckedOutStatus()){
                System.out.println("That Book has Already Been Returned");
            }else{
                bookCheckOut.setCheckedOutStatus(false);
                System.out.println("'" + bookCheckOut.getTitle() + "' Has Been Successfully Returned");
            }
        }
        //MAGAZINES
        else if(searchKey > 999 && searchKey < 10000){
            Magazine magCheckOut = findMagazine(searchKey,Magazines);
            if(magCheckOut == null){
                System.out.println("Could Not Find Item With Matching ID");
            }else if(!magCheckOut.isCheckedOutStatus()){
                System.out.println("That Magazine has Already Been Returned");
            }else{
                magCheckOut.setCheckedOutStatus(false);
                System.out.println("'" + magCheckOut.getTitle() + "' Has Been Successfully Returned");
            }
        }
        //JOURNALS
        else{
            Journal journalCheckOut = findJournals(searchKey,Journals);
            if(journalCheckOut == null){
                System.out.println("Could Not Find Item With Matching ID");
            }else if(!journalCheckOut.isCheckedOutStatus()){
                System.out.println("That Journal has Already Been Returned");
            }else{
                journalCheckOut.setCheckedOutStatus(false);
                System.out.println("'" + journalCheckOut.getTitle() + "' Has Been Successfully Returned");
            }
        }


    }

    public static void CheckOutItem(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){

        Scanner scanID = new Scanner(System.in);
        int searchKey;
        System.out.print("Please Enter the Item ID for the Item you are trying to Check Out: ");
        searchKey = scanID.nextInt();

        if(searchKey < 100 || searchKey > 100000){
            System.out.println("Invalid Entry");
        }
        //BOOKS
        else if(searchKey > 99 && searchKey < 1000){
            Book bookCheckOut = findBook(searchKey,Books);
            if(bookCheckOut == null){
                System.out.println("Could Not Find Item With Matching ID");
            }else if(bookCheckOut.isCheckedOutStatus()){
                System.out.println("That Book has Already Been Checked Out");
            }else{
                bookCheckOut.setCheckedOutStatus(true);
                System.out.println("'" + bookCheckOut.getTitle() + "' Has Been Successfully Checked Out");
            }
        }
        //MAGAZINES
        else if(searchKey > 999 && searchKey < 10000){
            Magazine magCheckOut = findMagazine(searchKey,Magazines);
            if(magCheckOut == null){
                System.out.println("Could Not Find Item With Matching ID");
            }else if(magCheckOut.isCheckedOutStatus()){
                System.out.println("That Magazine has Already Been Checked Out");
            }else{
                magCheckOut.setCheckedOutStatus(true);
                System.out.println("'" + magCheckOut.getTitle() + "' Has Been Successfully Checked Out");
            }
        }
        //JOURNALS
        else{
            Journal journalCheckOut = findJournals(searchKey,Journals);
            if(journalCheckOut == null){
                System.out.println("Could Not Find Item With Matching ID");
            }else if(journalCheckOut.isCheckedOutStatus()){
                System.out.println("That Journal has Already Been Checked Out");
            }else{
                journalCheckOut.setCheckedOutStatus(true);
                System.out.println("'" + journalCheckOut.getTitle() + "' Has Been Successfully Checked Out");
            }
        }


    }

    public static void addBook(Vector<Book> Books){

        Scanner scanForTitle = new Scanner(System.in);
        Scanner scanForAuther= new Scanner(System.in);
        String titleToBeAdded;
        String autherToBeAdded;
        int ID;
        System.out.print("Please Enter the Title of the Book: ");
        titleToBeAdded=scanForTitle.nextLine();

        System.out.println(titleToBeAdded.length());

        System.out.print("Please Enter the Auther of the Book: ");
        autherToBeAdded=scanForAuther.nextLine();
        System.out.println(autherToBeAdded.length());

        ID = Books.get((Books.size()-1)).getID() + 1;

        Books.add(new Book(titleToBeAdded,ID,false,autherToBeAdded));

    }

    public static void addMagazine(Vector<Magazine> Magazines){
        Scanner scanForTitle = new Scanner(System.in);
        Scanner scanForIssueNum= new Scanner(System.in);
        String titleToBeAdded;
        int issueNumToBeAdded;
        int ID;
        System.out.print("Please Enter the Title of the Magazine: ");
        titleToBeAdded=scanForTitle.nextLine();

        System.out.println(titleToBeAdded.length());

        System.out.print("Please Enter the Issue Number of the Magazine: ");
        issueNumToBeAdded=scanForIssueNum.nextInt();

        ID = Magazines.get((Magazines.size()-1)).getID() + 1;

        Magazines.add(new Magazine(titleToBeAdded,ID,false,issueNumToBeAdded));
    }

    public static void addJournal(Vector<Journal> Journals){
        Scanner scanForTitle = new Scanner(System.in);
        Scanner scanForVolNum= new Scanner(System.in);
        String titleToBeAdded;
        int volNumToBeAdded;
        int ID;
        System.out.print("Please Enter the Title of the Journal: ");
        titleToBeAdded=scanForTitle.nextLine();

        System.out.println(titleToBeAdded.length());

        System.out.print("Please Enter the Volume Number of the Journal: ");
        volNumToBeAdded=scanForVolNum.nextInt();

        ID = Journals.get((Journals.size()-1)).getID() + 1;

        Journals.add(new Journal(titleToBeAdded,ID,false,volNumToBeAdded));
    }


    public static Book findBook(int idSearch, Vector<Book> Books){

        for (int i = 0; i < Books.size(); i++) {
                if(idSearch == Books.get(i).getID()){
                    System.out.println("Found Book");
                    return Books.get(i);
                }
        }
        return null;
    }

    public static Magazine findMagazine(int idSearch, Vector<Magazine> Magazines){

        for (int i = 0; i < Magazines.size(); i++) {
            if(idSearch == Magazines.get(i).getID()){
                System.out.println("Found Magazine");
                return Magazines.get(i);
            }
        }
        return null;

    }

    public static Journal findJournals(int idSearch, Vector<Journal> Journals){

        for (int i = 0; i < Journals.size(); i++) {
            if(idSearch == Journals.get(i).getID()){
                System.out.println("Found Journal");
                return Journals.get(i);
            }
        }
        return null;

    }

    public static void Search(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){
        String searchItem;
        String titleSearch;
        boolean found = false;

        System.out.println(" ");
        System.out.println("What are you looking for today?");
        Scanner ScannerObj = new Scanner(System.in);
        searchItem = ScannerObj.next();

        //SEARCHING FOR BOOKS
        if(searchItem.equals("book") || searchItem.equals("Book") || searchItem.equals("books") || searchItem.equals("Books")){
            String autherSearch;
            Scanner scanAuther = new Scanner(System.in);
            Scanner scanTitle = new Scanner(System.in);
            System.out.print("Please Specify the Title of the Book: ");
            titleSearch = scanTitle.nextLine();

            System.out.print("Please Specify the Auther of the Book: ");
            autherSearch = scanAuther.nextLine();

            for (int i = 0; i < Books.size(); i++) {

                if (titleSearch.equals(Books.get(i).getTitle()) && autherSearch.equals(Books.get(i).getAuther())) {
                    System.out.println(" ");
                    System.out.println("The Book: '" + Books.get(i).getTitle() + "' by " + Books.get(i).getAuther()+ " is in our system." );

                    if(Books.get(i).isCheckedOutStatus()){
                        System.out.println("This Book is Checked Out");
                    }else{
                        System.out.println("This Book is Available to be Checked Out");
                    }
                    found = true;
                }
            }
            if(!found){
                System.out.println("This Book is Not in our System");
            }

        }
        //SEARCHING FOR MAGAZINES
        else if(searchItem.equals("magazine") || searchItem.equals("Magazine") || searchItem.equals("magazines") || searchItem.equals("Magazines")){

            int issueNumSearch;
            Scanner scanIssueNum = new Scanner(System.in);
            Scanner scanTitle = new Scanner(System.in);
            System.out.print("Please specify the Title of the Magazine: ");
            titleSearch = scanTitle.nextLine();
            System.out.print("Please Specify the Issue Number of the Magazine: ");
            issueNumSearch = scanIssueNum.nextInt();

            for (int i = 0; i < Magazines.size(); i++) {
                if(titleSearch.equals(Magazines.get(i).getTitle()) && issueNumSearch == Magazines.get(i).getIssueNumber()){
                    System.out.println(" ");
                    System.out.println("The Magazine '" + Magazines.get(i).getTitle() + "', Issue " + Magazines.get(i).getIssueNumber()+ " is in our System");
                    if(Magazines.get(i).isCheckedOutStatus()){
                        System.out.println("This Magazine has been Checked Out.");
                    }else {
                        System.out.println("This Magazine is Available to be Checked Out");
                    }
                    found = true;
                }
            }if(!found){
                System.out.println("This Magazine is Not in our System");
            }
        }
        //SEARCHING FOR JOURNALS
        else if(searchItem.equals("journal") || searchItem.equals("Journal") || searchItem.equals("journals") || searchItem.equals("Journals")){

            int volumeNumSearch;
            Scanner scanVolumeNum = new Scanner(System.in);
            Scanner scanTitle= new Scanner(System.in);
            System.out.print("Please Specify the Title of the Journal: ");
            titleSearch = scanTitle.nextLine();
            System.out.print("Please Specify the Volume of the Journal: ");
            volumeNumSearch = scanVolumeNum.nextInt();

            for (int i = 0; i < Journals.size(); i++) {
                if(titleSearch.equals(Journals.get(i).getTitle()) && volumeNumSearch == Journals.get(i).getVolume()){
                    System.out.println(" ");
                    System.out.println("The Journal '" + Journals.get(i).getTitle() + "', Volume " + Journals.get(i).getVolume() + " is in our System");
                    if(Journals.get(i).isCheckedOutStatus()){
                        System.out.println("This Journal has been checked out");
                    }else{
                        System.out.println("This Journal is available to be checked out");
                    }
                    found = true;
                }
            }if(!found){
                System.out.println("This Journal is Not in our System");
            }

        }else{
            System.out.println("Invalid Entry, Try Again");
        }


    }

    public static void displayInventory(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){

        System.out.println("");
        System.out.println("Books");
        for (int i = 0; i < Books.size(); i++) {
            Books.get(i).print();
        }

        System.out.println("");
        System.out.println("Magazines");
        for (int i = 0; i < Magazines.size(); i++) {
            Magazines.get(i).print();
        }

        System.out.println("");
        System.out.println("Journals");
        for (int i = 0; i < Journals.size(); i++) {
            Journals.get(i).print();
        }


    }
}

