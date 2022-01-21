import java.util.*;
class Atm{
    static Scanner sc=new Scanner(System.in);
    static int money[]={100,200,500,2000};
    static int count[]={0,0,0,0};
    static int maxMoney[]={200,200,200,200};
    static int totalAmount=0;
    static UserDetails use[]=new UserDetails[3];
    public static void main(String args[]){
        use[0]=new UserDetails("Soundaria",1030,10000);
        use[1]=new UserDetails("Jingilee",5533,50000);
        use[2]=new UserDetails("Buddy",1234,90000);
        System.out.println("HI!! WELCOME!!");
        boolean var=true;
        while(var){
            System.out.printf("1.Admin %n2.User %n3.Exit%n");
            int a=sc.nextInt();
            switch(a){
                case 1:
                    admin();
                    break;
                case 2:
                    user();
                    break;
                case 3:
                    exit();
                    var=false;
                    System.out.println("Visit Again!!");
                    break;
                default:
                    System.out.println("Invalid Input");
            } 
        }
    }
   
    static void admin(){
        boolean res=true;
        System.out.print("Enter Admin Name: ");
        String name=sc.next();
        System.out.print("Enter your Password: ");
        int pass=sc.nextInt();
        if(name.equals("Admin") && pass==1030){
            while(res){
                System.out.printf("1.Add Money %n2.Check Balance %n3.Exit%n");
                int a=sc.nextInt();
                switch(a){
                    case 1:
                        addMoney();
                        break;
                    case 2 :
                        checkBalance();
                        break;
                    case 3:
                        exit();
                        res=false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
        }
        else{
            System.out.println("Enter a Valid Name or Password!!");
        } 
    }
    static void addMoney(){
        int cash;
        for(int i=0;i<money.length;){
            System.out.print("Enter number of "+money[i]+"s : ");
            cash=sc.nextInt();
            count[i]=count[i]+cash;
            if(count[i]<=maxMoney[i]){
                totalAmount=totalAmount+(money[i]*cash);
                i++;
            }
            else{
                System.out.println("Enter a lower amount : ");
                count[i]=count[i]-cash;
            }
        }
        System.out.println("Amount added : "+totalAmount);
    }
    static void checkBalance(){
        for(int i=0;i<money.length;i++){
            System.out.println("Number of "+money[i]+"s : "+ count[i]);
        }
        System.out.println("Total amount in ATM :"+totalAmount);
    }

    static void user(){
        boolean val=true;
        int c=0;
        System.out.print("Enter the User Name : ");
        String username=sc.next();
        System.out.print("Enter your Pin : ");
        int password=sc.nextInt();
        for(int id=0;id<use.length;id++){
            if(username.equals(use[id].name) && password==use[id].pin){
                while(val){
                    System.out.printf("1.Deposit %n2.Withdraw %n3.Balance %n4.Exit%n");
                    int a=sc.nextInt();
                    switch(a){
                        case 1:
                            deposit(id);
                            break;
                        case 2:
                            withdraw(id);
                            break;
                        case 3:
                            balance(id);
                            break;
                        case 4:
                            exit();
                            val=false;
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                }
            }
            else
            {
                c=c+1;
            }
            if(c==use.length){
                System.out.println("Invalid user Details");            
            }
        }
    }
    static void deposit(int id){
        int total=0;
        System.out.print("Enter the amount to be deposited : ");
        int cash=sc.nextInt();
        for(int i=0;i<4;){
            System.out.print("Enter number of "+money[i]+"s : ");
            int num=sc.nextInt();
            count[i]=count[i]+num;
            if(count[i]<=maxMoney[i]){
                total=total+(money[i]*num);
                i++;
            }
            else{
                System.out.println("Enter lower amount!!");
                count[i]=count[i]-num;
            }
        }
        if(cash==total){
            System.out.println("Money deposited!!");
            use[id].amount+=total;
            totalAmount+=total;
        }
        else{
            System.out.println("Invalid amount");
        }
    }
    static void withdraw(int id){
        System.out.print("Enter the amount to withdraw : ");
        int withdrawMoney=sc.nextInt();
        int temp=withdrawMoney;
        int countwith[]={0,0,0,0};
        int tempcount[]=count;
        if(withdrawMoney<=use[id].amount && withdrawMoney<=totalAmount ){
           if( withdrawMoney%100==0 && withdrawMoney%10==0){
                while(temp>=2000 && count[3]>0){
                    temp-=2000;
                    countwith[3]++;
                    tempcount[3]--;
                }
                while(temp>=500 && count[2]>0){
                    temp-=500;
                    countwith[2]++;
                    tempcount[2]--;
                }
                while(temp>=200 && count[1]>0){
                    temp-=200;
                    countwith[1]++;
                    tempcount[1]--;
                }
                while(temp>=100 && count[0]>0){
                    temp-=100;
                    countwith[0]++;
                    tempcount[0]--;
                }
                if(temp==0){
                    use[id].amount-=withdrawMoney;
                    totalAmount-=withdrawMoney;
                    count=tempcount;
                    System.out.println("Amount withdrawed successfully!!");
                }
                else{
                    System.out.println("Unable to withdraw money");
                }
            }
            else{
               System.out.println("Enter a valid amount");
            }
        }
        else{
            System.out.println("Enter a lower amount");
        }
    }
    static void balance(int id){
        System.out.println("Your balance amount : "+use[id].amount);
    }

    static void exit(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}

class UserDetails{
    String name;
    int pin,amount;
    UserDetails(String name,int pin,int amount){
        this.name=name;
        this.pin=pin;
        this.amount=amount;
    }
}