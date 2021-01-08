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
                       Search(Books, Magazines,Journals);
                       break;
                   case '2':
                       CheckOutItem(Books, Magazines,Journals);
                       break;
                   case '3':
                       returnItem(Books, Magazines,Journals);
                       break;
                   case '4':
                        addItem(Books, Magazines,Journals);
                       break;
                   case '5':
                       displayInventory(Books, Magazines,Journals);
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

        if(itemToBeAdded.equals("Book") || itemToBeAdded.equals("book") || itemToBeAdded.equals("Books") || itemToBeAdded.equals("books")){
            addBook(Books);
        }else if(itemToBeAdded.equals("Magazine") || itemToBeAdded.equals("magazine") || itemToBeAdded.equals("Magazines") || itemToBeAdded.equals("magazines")){
            addMagazine(Magazines);
        }else if(itemToBeAdded.equals("Journal") || itemToBeAdded.equals("journal") || itemToBeAdded.equals("Journals") || itemToBeAdded.equals("journals")){
            addJournal(Journals);
        }else {
            System.out.println("Invalid Entry, We Only carry Books, Magazines, and Journals");
        }
    }

    public static void returnItem(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){

        Scanner scanID = new Scanner(System.in);
        String searchKey;
        int searchNum;
        System.out.print("Please Enter the Item ID for the Item you are trying to Return: ");
        searchKey = scanID.next();
        try{
            searchNum = Integer.parseInt(searchKey);
        }catch(NumberFormatException e){
            System.out.println("Invalid Input, Rerouting to Main Menu");
            return;
        }

        if(searchNum < 100 || searchNum > 100000){
            System.out.println("Invalid Entry");
        }
        //BOOKS
        else if(searchNum > 99 && searchNum < 1000){
            Book bookCheckOut = findBook(searchNum,Books);
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
        else if(searchNum > 999 && searchNum < 10000){
            Magazine magCheckOut = findMagazine(searchNum,Magazines);
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
            Journal journalCheckOut = findJournals(searchNum,Journals);
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
        String searchKey;
        int searchNum;
        System.out.print("Please Enter the Item ID for the Item you are trying to Check Out: ");
        searchKey = scanID.next();
        try{
            searchNum = Integer.parseInt(searchKey);
        }catch(NumberFormatException e){
            System.out.println("Invalid Input, Rerouting to Main Menu");
            return;
        }

        if(searchNum < 100 || searchNum > 100000){
            System.out.println("Invalid Input, Rerouting to Main Menu");
        }
        //BOOKS
        else if(searchNum > 99 && searchNum < 1000){
            Book bookCheckOut = findBook(searchNum,Books);
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
        else if(searchNum > 999 && searchNum < 10000){
            Magazine magCheckOut = findMagazine(searchNum,Magazines);
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
            Journal journalCheckOut = findJournals(searchNum,Journals);
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
        Scanner scanForAuthor= new Scanner(System.in);
        String titleToBeAdded;
        String authorToBeAdded;
        int ID;
        try {
            System.out.print("Please Enter the Title of the Book: ");
            titleToBeAdded = scanForTitle.nextLine();
            if(titleToBeAdded.length() == 0){
                throw new Exception("Invalid Title Entry, Rerouting to Main Menu");
            }
            System.out.print("Please Enter the Author of the Book: ");
            authorToBeAdded=scanForAuthor.nextLine();
            if(authorToBeAdded.length() == 0){
                throw new Exception("Invalid Author Entry, Rerouting to Main Menu");
            }

            ID = Books.get((Books.size()-1)).getID() + 1;

            Books.add(new Book(titleToBeAdded,ID,false,authorToBeAdded));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void addMagazine(Vector<Magazine> Magazines){
        Scanner scanForTitle = new Scanner(System.in);
        Scanner scanForIssueNum= new Scanner(System.in);
        String titleToBeAdded;
        String issueNumToBeAdded;
        int issueNum;
        int ID;
        try {
            System.out.print("Please Enter the Title of the Magazine: ");
            titleToBeAdded = scanForTitle.nextLine();
            if(titleToBeAdded.length() == 0){
                throw new Exception("Invalid Title Input");
            }

            System.out.print("Please Enter the Issue Number of the Magazine: ");
            issueNumToBeAdded = scanForIssueNum.next();

            issueNum = Integer.parseInt(issueNumToBeAdded);

            ID = Magazines.get((Magazines.size() - 1)).getID() + 1;

            Magazines.add(new Magazine(titleToBeAdded, ID, false, issueNum));
        }catch(NumberFormatException e){
            System.out.println("Invalid Issue Number Input, Rerouting to Main Menu");
        }catch(Exception e){
            System.out.println( e.getMessage() + ", Rerouting to Main Menu");
        }
    }

    public static void addJournal(Vector<Journal> Journals){
        Scanner scanForTitle = new Scanner(System.in);
        Scanner scanForVolNum= new Scanner(System.in);
        String titleToBeAdded;
        String volNumToBeAdded;
        int volNum;
        int ID;
        try {
            System.out.print("Please Enter the Title of the Journal: ");
            titleToBeAdded = scanForTitle.nextLine();
            if(titleToBeAdded.length() == 0){
                throw new Exception("Invalid Input");
            }

            System.out.println(titleToBeAdded.length());

            System.out.print("Please Enter the Volume Number of the Journal: ");
            volNumToBeAdded = scanForVolNum.next();
            volNum = Integer.parseInt(volNumToBeAdded);


            ID = Journals.get((Journals.size() - 1)).getID() + 1;

            Journals.add(new Journal(titleToBeAdded, ID, false, volNum));
        }catch(NumberFormatException e){
            System.out.println("Invalid Volume Input, Rerouting to Main Menu");
        }catch(Exception e){

        }
    }

    public static Book findBook(int idSearch, Vector<Book> Books){

        for (int i = 0; i < Books.size(); i++) {
                if(idSearch == Books.get(i).getID()){
                    System.out.println("Found Book : '" + Books.get(i).getTitle()+"'");
                    return Books.get(i);
                }
        }
        return null;
    }

    public static Magazine findMagazine(int idSearch, Vector<Magazine> Magazines){

        for (int i = 0; i < Magazines.size(); i++) {
            if(idSearch == Magazines.get(i).getID()){
                System.out.println("Found Magazine : '" + Magazines.get(i).getTitle()+"'");
                return Magazines.get(i);
            }
        }
        return null;

    }

    public static Journal findJournals(int idSearch, Vector<Journal> Journals){

        for (int i = 0; i < Journals.size(); i++) {
            if(idSearch == Journals.get(i).getID()){
                System.out.println("Found Journal : '" + Journals.get(i).getTitle()+"'");
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

            if(titleSearch.length() == 0){
                System.out.println("Invalid Entry, Rerouting to Main Menu");
                return;
            }

            System.out.print("Please Specify the Auther of the Book: ");
            autherSearch = scanAuther.nextLine();

            if(autherSearch.length() == 0){
                System.out.println("Invalid Entry, Rerouting to Main Menu");
                return;
            }

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

            String issueNumSearch;
            int issueNum;
            Scanner scanIssueNum = new Scanner(System.in);
            Scanner scanTitle = new Scanner(System.in);
            System.out.print("Please specify the Title of the Magazine: ");
            titleSearch = scanTitle.nextLine();
            if(titleSearch.length() == 0){
                System.out.println("Invalid Entry, Rerouting to Main Menu");
                return;
            }
            System.out.print("Please Specify the Issue Number of the Magazine: ");
            issueNumSearch = scanIssueNum.next();
            try{
                issueNum = Integer.parseInt(issueNumSearch);
            }catch(NumberFormatException e){
                System.out.println("Input was Not a Number, Rerouting to Main Menu");
                return;
            }

            for (int i = 0; i < Magazines.size(); i++) {
                if(titleSearch.equals(Magazines.get(i).getTitle()) && issueNum == Magazines.get(i).getIssueNumber()){
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

            String volumeNumSearch;
            int volNum;
            Scanner scanVolumeNum = new Scanner(System.in);
            Scanner scanTitle= new Scanner(System.in);
            System.out.print("Please Specify the Title of the Journal: ");
            titleSearch = scanTitle.nextLine();
            if(titleSearch.length() == 0){
                System.out.println("Invalid Entry, Rerouting to Main Menu");
                return;
            }
            System.out.print("Please Specify the Volume of the Journal: ");
            volumeNumSearch = scanVolumeNum.next();
            try{
                volNum = Integer.parseInt(volumeNumSearch);
            }catch(NumberFormatException e){
                System.out.println("Input was Not a Number, Rerouting to Main Menu");
                return;
            }

            for (int i = 0; i < Journals.size(); i++) {
                if(titleSearch.equals(Journals.get(i).getTitle()) && volNum == Journals.get(i).getVolume()){
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

