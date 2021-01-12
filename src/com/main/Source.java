package com.main;
import java.util.Scanner;
import java.util.Vector;

public class Source {

    public static void main(String[] args) {

        //INITIAL VECTORS THAT WILL HOLD THE CONTENTS OF THE LIBRARY
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


        //WHILE LOOP THAT WILL DISPLAY MENU AND REPEAT ONLY IF USER ALLOWS
        while(again){

            System.out.println(" ");
            System.out.println("What would you like to do? Enter the number of the action you would like. ");

            //SHOW MENU FUNCTION
            showMenu();

            try{
                System.out.print("Enter your Number: ");
                choice = ScanObj.next().charAt(0);

                //ERROR HANDLING: IF USER ENTERS INVALID CHARACTER, WHILE LOOP LOOPS AGAIN
               if(Character.getNumericValue(choice) > 6 || Character.getNumericValue(choice) < 1){
                    throw new Exception("Invalid Entry, Try again");
                }
               //IF USER ENTERS "6" THE WHILE LOOP EXITS AND THE PROGRAM FINISHES
               else if(Character.getNumericValue(choice) == 6){
                   break;
               }

               //DEPENDING ON THE USER INPUT, DIFFERENT FUNCTIONS ARE CALLED, ALL CONTENTS OF THE LIBRARY ARE PASSED IN AS ARGUMENTS
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

    //MENU SHOWING AVAILABLE ACTIONS
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

    //FUNCTION TO ADD AN ITEM TO THE LIBRARY
    public static void addItem(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){
        Scanner scanForItem = new Scanner(System.in);
        String itemToBeAdded;
        System.out.println("What Type of Literature are you Adding Today?");
        itemToBeAdded = scanForItem.next();

        //DEPENDING ON USER INPUT, DIFFERENT FUNCTIONS ARE CALLED TO ADD THE ITEM
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

    //FUNCTION TO RETURN AN ITEM TO THE LIBRARY, MUST HAVE THE ITEM ID TO DO SO
    public static void returnItem(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){

        Scanner scanID = new Scanner(System.in);
        String searchKey;
        int searchNum;
        System.out.print("Please Enter the Item ID for the Item you are trying to Return: ");
        searchKey = scanID.next();

        //ERROR HANDLING: IF THE INPUT CANNOT BE PARSED, THEN IT MUST NOT BE AN INTEGER, SO IT IS AN INVALID INPUT
        //REROUTES BACK TO MAIN MENU
        try{
            //WE STORE SEARCHKEY'S INTEGER VALUE INTO SEARCHNUM; THAT IS THE SEARCH NUMBER THAT WILL BE USED FOR THE REST OF THE FUNCTION
            searchNum = Integer.parseInt(searchKey);
        }catch(NumberFormatException e){
            System.out.println("Invalid Input, Rerouting to Main Menu");
            return;
        }

        //ID'S ARE NEVER LESS THAN 3 DIGITS AND MORE THAN 6 DIGITS, SO IF ITS ANY LOWER/HIGHER, THE INPUT IS INVALID
        //REROUTES TO MAIN MENU
        if(searchNum < 100 || searchNum > 100000){
            System.out.println("Invalid Entry");
        }

        //RETURNING BOOKS
        //IF SEARCHNUM IS 3 DIGITS LONG, WE CAN PROCEED TO RETURN A BOOK
        else if(searchNum > 99 && searchNum < 1000){

            //THE BOOK WE WANT TO WORK WITH WILL BE FOUND FROM THE LIST OF BOOKS VIA findBook() FUNCTION, AND STORED IN bookCheckOut
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
        //RETURNING MAGAZINES
        //IF SEARCHNUM IS 4 DIGITS LONG, WE CAN PROCEED TO RETURN A MAGAZINE
        else if(searchNum > 999 && searchNum < 10000){

            //THE MAGAZINE WE WANT TO WORK WITH WILL BE FOUND FROM THE LIST OF MAGAZINES VIA findMagazine() FUNCTION, AND STORED IN magCheckOut
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
        //RETURNING JOURNALS
        //IF SEARCHNUM IS 5 DIGITS LONG, WE CAN PROCEED TO RETURN A JOURNAL
        else{

            //THE JOURNAL WE WANT TO WORK WITH WILL BE FOUND FROM THE LIST OF JOURNALS VIA findJournals() FUNCTION, AND STORED IN journalCheckOut
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

    //FUNCTION THAT CHECKS OUT AN ITEM IN THE LIBRARY
    public static void CheckOutItem(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){

        Scanner scanID = new Scanner(System.in);
        String searchKey;
        int searchNum;
        System.out.print("Please Enter the Item ID for the Item you are trying to Check Out: ");
        searchKey = scanID.next();

        //ERROR HANDLING: IF THE INPUT CANNOT BE PARSED, THEN IT MUST NOT BE AN INTEGER, SO IT IS AN INVALID INPUT
        //REROUTES BACK TO MAIN MENU
        try{
            //WE STORE SEARCHKEY'S INTEGER VALUE INTO SEARCHNUM; THAT IS THE SEARCH NUMBER THAT WILL BE USED FOR THE RET OF THE FUNCTION
            searchNum = Integer.parseInt(searchKey);
        }catch(NumberFormatException e){
            System.out.println("Invalid Input, Rerouting to Main Menu");
            return;
        }

        if(searchNum < 100 || searchNum > 100000){
            System.out.println("Invalid Input, Rerouting to Main Menu");
        }

        //CHECKING OUT BOOKS
        //IF SEARCHNUM IS 3 DIGITS LONG, WE CAN PROCEED TO CHECK OUT A BOOK
        else if(searchNum > 99 && searchNum < 1000){

            //THE BOOK WE WANT TO WORK WITH WILL BE FOUND FROM THE LIST OF BOOKS VIA findBook() FUNCTION, AND STORED IN bookCheckOut
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
        //CHECKING OUT MAGAZINES
        //IF SEARCHNUM IS 4 DIGITS LONG, WE CAN PROCEED TO CHECK OUT A MAGAZINE
        else if(searchNum > 999 && searchNum < 10000){

            //THE MAGAZINE WE WANT TO WORK WITH WILL BE FOUND FROM THE LIST OF MAGAZINES VIA findMagazine() FUNCTION, AND STORED IN magCheckOut
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
        //CHECKING OUT JOURNALS
        //IF SEARCHNUM IS 5 DIGITS LONG, WE CAN PROCEED TO CHECK OUT A JOURNAL
        else{

            //THE JOURNAL WE WANT TO WORK WITH WILL BE FOUND FROM THE LIST OF JOURNALS VIA findJournals() FUNCTION, AND STORED IN journalCheckOut
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

    //FUNCTION TO ADD BOOKS TO THE LIBRARY
    public static void addBook(Vector<Book> Books){

        Scanner scanForTitle = new Scanner(System.in);
        Scanner scanForAuthor= new Scanner(System.in);

        String titleToBeAdded;          //HOLDS THE TITLE OF THE NEW BOOK
        String authorToBeAdded;         //HOLDS THE AUTHOR OF THE NEW BOOK
        int ID;                         //HOLDS THE ID OF THE NEW BOOK

        try {
            System.out.print("Please Enter the Title of the Book: ");
            titleToBeAdded = scanForTitle.nextLine();

            //ERROR HANDLING: IF NO TITLE IS ENTERED, THROW AN EXCEPTION
            if(titleToBeAdded.length() == 0){
                throw new Exception("Invalid Title Entry, Rerouting to Main Menu");
            }
            System.out.print("Please Enter the Author of the Book: ");
            authorToBeAdded=scanForAuthor.nextLine();

            //ERROR HANDLING: IF NO AUTHOR IS ENTERED, THROW AN EXCEPTION
            if(authorToBeAdded.length() == 0){
                throw new Exception("Invalid Author Entry, Rerouting to Main Menu");
            }

            //PROGRAM CREATES NEW ID BASED ON THE LAST ITEM IN THE VECTOR
            ID = Books.get((Books.size()-1)).getID() + 1;

            //ADDING THE NEW BOOK TO THE VECTOR
            Books.add(new Book(titleToBeAdded,ID,false,authorToBeAdded));

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    //FUNCTION TO ADD MAGAZINES TO THE LIBRARY
    public static void addMagazine(Vector<Magazine> Magazines){
        Scanner scanForTitle = new Scanner(System.in);
        Scanner scanForIssueNum= new Scanner(System.in);

        String titleToBeAdded;          //HOLDS THE TITLE OF THE NEW MAGAZINE
        String issueNumToBeAdded;       //HOLDS THE ISSUE NUMBER OF THE NEW MAGAZINE, NEEDS TO BE ABLE TO BE PARSED FOR ERROR HANDLING
        int issueNum;                   //HOLDS THE PARSED ISSUE NUMBER OF THE NEW MAGAZINE
        int ID;                         //HOLDS THE ID OF THE NEW MAGAZINE

        try {
            System.out.print("Please Enter the Title of the Magazine: ");
            titleToBeAdded = scanForTitle.nextLine();

            //ERROR HANDLING: IF NO TITLE IS ENTERED, THROW AN EXCEPTION
            if(titleToBeAdded.length() == 0){
                throw new Exception("Invalid Title Input");
            }

            System.out.print("Please Enter the Issue Number of the Magazine: ");
            issueNumToBeAdded = scanForIssueNum.next();

            //IF THIS ASSIGNMENT FAILS, WE CATCH EXCEPTION AND RETURNS TO MENU
            issueNum = Integer.parseInt(issueNumToBeAdded);

            //CREATING NEW ID FOR THE NEW MAGAZINE
            ID = Magazines.get((Magazines.size() - 1)).getID() + 1;

            //ADDING THE MAGAZINE TO THE LIST
            Magazines.add(new Magazine(titleToBeAdded, ID, false, issueNum));

        }catch(NumberFormatException e){
            System.out.println("Invalid Issue Number Input, Rerouting to Main Menu");
        }catch(Exception e){
            System.out.println( e.getMessage() + ", Rerouting to Main Menu");
        }
    }

    //FUNCTION TO ADD NEW JOURNALS TO THE LIBRARY
    public static void addJournal(Vector<Journal> Journals){
        Scanner scanForTitle = new Scanner(System.in);
        Scanner scanForVolNum= new Scanner(System.in);

        String titleToBeAdded;      //HOLDS THE TITLE OF THE NEW JOURNAL
        String volNumToBeAdded;     //STRING VALUE USED FOR ERROR HANDLING, TEMPORARILY HOLDS VOLUME NUMBER
        int volNum;                 //INTEGER VALUE USED TO HOLD VALUE NUMBER
        int ID;                     //HOLDS NEW ID FOR THE NEW JOURNAL


        try {
            System.out.print("Please Enter the Title of the Journal: ");
            titleToBeAdded = scanForTitle.nextLine();

            //ERROR HANDLING: IF NO TITLE IS ADDED, THEN WE THROW AN EXCEPTION
            if(titleToBeAdded.length() == 0){
                throw new Exception("Invalid Input");
            }

            System.out.println(titleToBeAdded.length());

            System.out.print("Please Enter the Volume Number of the Journal: ");
            volNumToBeAdded = scanForVolNum.next();

            //ERROR HANDLING: IF THIS ACTION FAILS, THROWS A NEW EXCEPTION
            volNum = Integer.parseInt(volNumToBeAdded);

            //CREATING A NEW ID FOR THE JOURNAL
            ID = Journals.get((Journals.size() - 1)).getID() + 1;

            //ADDING THE JOURNAL TO THE LIBRARY
            Journals.add(new Journal(titleToBeAdded, ID, false, volNum));

        }catch(NumberFormatException e){
            System.out.println("Invalid Volume Input, Rerouting to Main Menu");
        }catch(Exception e){

        }
    }

    //FUNCTION TO FIND BOOK FROM THE LIBRARY
    public static Book findBook(int idSearch, Vector<Book> Books){

        for (int i = 0; i < Books.size(); i++) {

                //IF THE ID ENTERED MATCHES A BOOK IN THE LIBRARY, THEN WE RETURN THE BOOK, ELSE RETURN NULL
                if(idSearch == Books.get(i).getID()){
                    System.out.println("Found Book : '" + Books.get(i).getTitle()+"'");
                    return Books.get(i);
                }
        }
        return null;
    }

    //FUNCTION TO FIND MAGAZINE FROM THE LIBRARY
    public static Magazine findMagazine(int idSearch, Vector<Magazine> Magazines){

        for (int i = 0; i < Magazines.size(); i++) {

            //IF THE ID ENTERED MATCHES A MAGAZINE IN THE LIBRARY, THEN WE RETURN THE MAGAZINE, ELSE RETURN NULL
            if(idSearch == Magazines.get(i).getID()){
                System.out.println("Found Magazine : '" + Magazines.get(i).getTitle()+"'");
                return Magazines.get(i);
            }
        }
        return null;

    }

    //FUNCTION TO FIND JOURNAL FROM THE LIBRARY
    public static Journal findJournals(int idSearch, Vector<Journal> Journals){

        for (int i = 0; i < Journals.size(); i++) {

            //IF THE ID ENTERED MATCHES A JOURNAL, THEN WE RETURN THE JOURNAL, ELSE WE RETURN NULL
            if(idSearch == Journals.get(i).getID()){
                System.out.println("Found Journal : '" + Journals.get(i).getTitle()+"'");
                return Journals.get(i);
            }
        }
        return null;

    }

    public static void Search(Vector<Book> Books, Vector<Magazine> Magazines, Vector<Journal> Journals){

        String searchItem;      //HOLDS THE TYPE OF ITEM THAT THE USER IS LOOKING FOR
        String titleSearch;     //HOLDS THE TITLE OF THE ITEM THAT THE USER IS LOOKING FOR
        boolean found = false;  //BOOLEAN VALUE TO DETERMINE IF FOUND OR NOT

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

            //ERROR HANDLING: IF THE LENGTH OF THE TITLE IS ZERO, THEN WE CATCH THIS EXCEPTION
            if(titleSearch.length() == 0){
                System.out.println("Invalid Entry, Rerouting to Main Menu");
                return;
            }

            System.out.print("Please Specify the Auther of the Book: ");
            autherSearch = scanAuther.nextLine();

            //ERROR HANDLING: IF THE LENGTH OF THE AUTHOR IS ZERO, THEN WE CATCH THIS EXCEPTION
            if(autherSearch.length() == 0){
                System.out.println("Invalid Entry, Rerouting to Main Menu");
                return;
            }

            for (int i = 0; i < Books.size(); i++) {

                //IF THERE EXISTS A BOOK CONTAINING THAT TITLE AND AUTHOR, THEN WE HAVE A MATCH
                if (titleSearch.equals(Books.get(i).getTitle()) && autherSearch.equals(Books.get(i).getAuther())) {
                    System.out.println(" ");
                    System.out.println("The Book: '" + Books.get(i).getTitle() + "' by " + Books.get(i).getAuther()+ " is in our system." );

                    //IF STATEMENT THAT SHOWS WHETHER THE BOOK IS CHECKED OUT OR NOT
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

            String issueNumSearch;      //HOLDS THE ISSUE NUMBER THAT IS TO BE SEARCHED FOR, STRING VERSION USED FOR ERROR HANDLING
            int issueNum;               //HOLDS THE ISSUE NUMBER THAT IS TO BE SEARCHED FOR, USED FOR THE REST OF THE FUNCTION
            Scanner scanIssueNum = new Scanner(System.in);
            Scanner scanTitle = new Scanner(System.in);

            System.out.print("Please specify the Title of the Magazine: ");
            titleSearch = scanTitle.nextLine();

            //ERROR HANDLING: IF TITLE HAS LENGTH 0, THEN WE CAN LOP BACK TO MENU
            //
            if(titleSearch.length() == 0){
                System.out.println("Invalid Entry, Rerouting to Main Menu");
                return;
            }

            System.out.print("Please Specify the Issue Number of the Magazine: ");
            issueNumSearch = scanIssueNum.next();

            //ERROR HANDLING: IF issueNum IS NOT PARSABLE. THEN WE CAN CATCH THIS EXCEPTION
            try{
                issueNum = Integer.parseInt(issueNumSearch);
            }catch(NumberFormatException e){
                System.out.println("Input was Not a Number, Rerouting to Main Menu");
                return;
            }


            for (int i = 0; i < Magazines.size(); i++) {

                //IF THERE EXISTS A MAGAZINE WITH MATCHING TITLE AND ISSUE NUM, THEN DISPLAY FOUND MESSAGE
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

            String volumeNumSearch;     //HOLDS THE VOLUME NUMBER, STRING VALUE USED FOR ERROR HANDLING
            int volNum;                 //HOLDS THE VOLUME NUMBER

            Scanner scanVolumeNum = new Scanner(System.in);
            Scanner scanTitle= new Scanner(System.in);
            System.out.print("Please Specify the Title of the Journal: ");
            titleSearch = scanTitle.nextLine();

            //ERROR HANDLING: IF THE TITLE HAS A LENGTH OF 0, THEN WE CATCH THIS ERROR AND RETURN
            if(titleSearch.length() == 0){
                System.out.println("Invalid Entry, Rerouting to Main Menu");
                return;
            }

            System.out.print("Please Specify the Volume of the Journal: ");
            volumeNumSearch = scanVolumeNum.next();

            try{
                //IF PARSING FAILS THEN WE CATCH THIS ERROR AND REROUTE TO MAIN MENU
                volNum = Integer.parseInt(volumeNumSearch);
            }catch(NumberFormatException e){
                System.out.println("Input was Not a Number, Rerouting to Main Menu");
                return;
            }

            for (int i = 0; i < Journals.size(); i++) {

                //IF THERE EXISTS A JOURNAL WITH MATCHING TITLE AND VOLUME NUMBER, WE DISPLAY FOUND MESSAGE
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


    //LOOPS THROUGH ALL OF THE VECTORS, CALLING ALL OF THE PRINT FUNCTIONS
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

