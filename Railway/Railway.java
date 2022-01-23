import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
class Railway{
    static Scanner sc=new Scanner(System.in);
    static int ucount=0,tcount=0;
    static ArrayList<User> use= new ArrayList<>(); 
    static ArrayList<History> history=new ArrayList<>();
    static  ArrayList<Waitingclass> waiting=new ArrayList<>(5);
    static int ticket[][]=new int[5][10];
        public static void main(String[] args) {
        boolean r=true;
        while(r){
            System.out.println("Welcome to the Travelling Spot!!");
            System.out.printf("1.User%n2.Exit%n");
            int a=sc.nextInt();
            switch(a){
                case 1:
                    User();
                    break;
                case 2:
                    exit();
                    r=false;
                    break;
                default :
                    System.out.println("Enter a valid input");
                    break;
            }
        }
    }
    static void User(){
        boolean r=true;
        while(r){
            System.out.printf("1.New User%n2.Existing User%n3.Exit%n");
            int a=sc.nextInt();
            switch(a){
                case 1:
                    newUser();
                    break;
                case 2:
                    existingUser();
                    break;
                
                case 3:
                    exit();
                    r=false;
                    break;
                default : 
                    System.out.println("Enter a valid input");
            }
        }
    }
    static void newUser(){
        System.out.print("Enter you name : ");
        String name=sc.next();
        System.out.print("Enter your password : ");
        String pass=sc.next();
        String uid="U0"+ucount;
        int c=0;
        use.add(new User(name, uid,pass));
        for(int i=0;i<use.size();i++){
            if(name.equals(use.get(i).name) && pass.equals(use.get(i).pass)){
                c=c+1;
            }
        }
        if(c==2){
            System.out.println("Acoount already exists...Sign in to existing account");
            use.remove(use.size()-1);
        }
        else{
            System.out.println("Account created Successfully!!"); 
            System.out.println("Your User Id : "+uid);
            ucount++;
        }
        
    }
    static void existingUser(){
        System.out.print("Enter your name : ");
        String name=sc.next();
        System.out.print("Enter your password : ");
        String pass=sc.next();
        boolean r=true;
        int c=0;
        for(int i=0;i<use.size();i++){
            if(name.equals(use.get(i).name) && pass.equals(use.get(i).pass)){
                while(r){
                    System.out.printf("1.Book Tickets%n2.Cancel Tickets%n3.View Ticket History%n4.Waiting List%n5.Exit%n");
                    int a=sc.nextInt();
                    switch(a){
                        case 1:
                            bookTickets(use.get(i).name,use.get(i).uid);
                            break;     
                        case 2:
                            cancelTicket(use.get(i).uid);
                            break;
                        case 3:
                            viewHistory(use.get(i).name,use.get(i).uid);
                            break;
                        case 4:
                            waitingList();
                            break;
                        case 5:
                            exit();
                            r=false;
                            break;
                        default :
                            System.out.println("Enter a valid input");
                            break;
                    }
                }
            }
            else{
                c++;
            }
            if(c==use.size()){
                System.out.println("No user registered with these credentials");
                System.out.println("Enter a valid name or password");
            }
        }
    }
    static void bookTickets(String name,String uid){
        String station[]={"Tirupur","Erode","Salem","Dharmapuri","Thirupatthur","Ambur","Vellore","Kanchipuram","Thiruvallur","Chennai"};
        System.out.print("Enter number of tickets you want to book? : ");
        int num=sc.nextInt();
        for(int i=0;i<num;){
            System.out.println("Ticket "+ (i+1));
            System.out.println("This train starts from Tirupur and departs in Chennai.");
            System.out.printf("We have the following stations.%n1.Tirupur  2.Erode  3.Salem  4.Dharmapuri  5.Thirupatthur");
            System.out.printf("%n6.Ambur  7.Vellore  8.Kanchipuram  9.Thiruvallur  10.Chennai%n");
            System.out.print("Enter the starting point : ");
            String start=sc.next();
            System.out.print("Enter the depature point : ");
            String depart=sc.next();
            int startindex=Arrays.asList(station).indexOf(start);
            int endindex=Arrays.asList(station).indexOf(depart);
            if(startindex>=0 && endindex>=0){
                int c=0;
                first:
                for(int j=0;j<5;j++){
                    int tick=0;
                    if(ticket[j][startindex]==0){
                        second:
                        for(int k=startindex;k<endindex;k++){
                            if(ticket[j][k]==0){
                                tick++;
                            }
                            else{
                                c=c+1;
                                break second;
                            }
                            if(tick==(endindex-startindex)){
                                System.out.println("Ticket Booked!!");
                                String tid="T0"+tcount;
                                tcount++;
                                for(int l=startindex;l<endindex;l++){
                                    ticket[j][l]=tcount;
                                }
                                history.add(new History(name,uid,start,depart,j,tid,tcount,startindex,endindex));
                                break first;
                            }
                        }
                    }
                    else{
                        c=c+1;
                       
                    }
                    if(c>=5){
                        if(waiting.size()<5){
                            waiting.add(new Waitingclass(name,uid,start,depart,startindex,endindex));
                            System.out.println("Your ticket is not confirmed..Your ticket is in waiting list");
                        }
                        else {
                            System.out.println("Your ticket is not booked..Waiting list is also closed..");
                        }
                    }
                }
                i++;
            }
            else{
                System.out.println("Enter a valid Station Name!!");
            }
        }
        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){
                System.out.println(i+" "+j+" "+ticket[i][j]);
            }
        }
    }
    static void cancelTicket(String uid){
        System.out.println("Your Ticket details!!..");
        for(int i=0;i<history.size();i++){
            if(uid.equals(history.get(i).uid)){
                System.out.println(history.get(i).name+"  "+history.get(i).uid+"  "+history.get(i).start+"  "+history.get(i).depart+"  "+history.get(i).seat+"  Ticket ID : "+history.get(i).tid);
            } 
        }
        System.out.print("Enter the ticket id : ");
        String tid=sc.next();
        int c=0,seat=0;
        for(int i=0;i<history.size();i++){
            if(uid.equals(history.get(i).uid) && tid.equals(history.get(i).tid)){
                System.out.print("Are you sure you want to cancel the ticket[yes/no] : ");
                String confirm=sc.next();
                int startindex=0,endindex=0;
                if(confirm.equalsIgnoreCase("Yes")){
                    for(int j=0;j<10;j++){
                        if(ticket[history.get(i).seat][j]==history.get(i).count){
                            seat=history.get(i).seat;
                            startindex=history.get(i).startindex;
                            endindex=history.get(i).endindex;
                            ticket[history.get(i).seat][j]=0;
                        }
                    }
                    history.remove(i);
                    System.out.println("Ticket removed Successfully");
                }
            }
            else{
                c=c+1;
            }
        }
        if(c>history.size()){
            System.out.println("Enter a valid Ticket Id!!");
        }
        waiting(seat);
    }
    static void waiting(int seat){
        int c1=0;
        int size=waiting.size();
        for(int i=0;i<size;i++){
            int tick=0;
            int startindex=waiting.get(i-c1).startindex;
            if(ticket[seat][startindex]==0){
                for(int j=startindex;j<waiting.get(i-c1).endindex;j++){
                    if(ticket[seat][j]==0){
                        tick++;
                    }
                }
                if(tick==(waiting.get(i-c1).endindex-waiting.get(i-c1).startindex)){
                    System.out.println(waiting.get(i-c1).name+"... Your ticket is Booked!!");
                    String tid="T0"+tcount;
                    System.out.println("Your ticket id : "+tid);
                    tcount++;
                    for(int l=waiting.get(i-c1).startindex;l<waiting.get(i-c1).endindex;l++){
                        ticket[seat][l]=tcount;
                    }
                    history.add(new History(waiting.get(i-c1).name,waiting.get(i-c1).uid,waiting.get(i-c1).start,waiting.get(i-c1).depart,seat,tid,tcount,startindex,waiting.get(i-c1).endindex));
                    waiting.remove(i-c1);
                    c1++;
                }
            }
        }
    }
    static void viewHistory(String name,String uid){
        System.out.println("Your travelling history : ");
        for(int i=0;i<history.size();i++){
            if(uid.equals(history.get(i).uid)){
                System.out.println(history.get(i).name+"  "+history.get(i).uid+"  "+history.get(i).start+"  "+history.get(i).depart+"  "+history.get(i).seat+"  "+history.get(i).tid);
            }
        }

    }
    static void waitingList(){
        if(waiting.isEmpty()){
            System.out.println("Waiting List is Empty!!");
        }
        else{
            for(int i=0;i<waiting.size();i++){
                System.out.println(waiting.get(i).name+"  "+waiting.get(i).uid+" "+waiting.get(i).start+"  "+waiting.get(i).depart);
            }
        }   
    }
    static void exit(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
   
    static class User{
        String name,uid,pass;
        User(String name,String uid,String pass){
            this.name=name;
            this.uid=uid;
            this.pass=pass;
        }
    }
    static class Waitingclass{
        String name,start,depart,uid;
        int startindex,endindex;
        Waitingclass(String name,String uid,String start,String depart,int startindex,int endindex){
            this.name=name;
            this.uid=uid;
            this.start=start;
            this.depart=depart;
            this.startindex=startindex;
            this.endindex=endindex;
        }
    }
    static class History{
        String name,uid,start,depart,tid;
        int seat,count,startindex,endindex;
        History(String name,String uid,String start,String depart,int seat,String tid,int count,int startindex,int endindex){
            this.name=name;
            this.uid=uid;
            this.start=start;
            this.depart=depart;
            this.seat=seat;
            this.tid=tid;
            this.count=count;
            this.startindex=startindex;
            this.endindex=endindex;
        }
    }
}