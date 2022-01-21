package Amazon;
import java.util.Scanner;
import java.util.ArrayList;
class Amazon{
    static Scanner sc=new Scanner(System.in);
    static int mcount=1,pcount=1;
    static ArrayList<Merchant> merchant=new ArrayList<>();
    static ArrayList<Product> product=new ArrayList<>();
    static ArrayList<User> user=new ArrayList<>();
    static ArrayList<UserOrder> userorder=new ArrayList<>();
    static ArrayList<Cart> cart=new ArrayList<>();
    static ArrayList<SoldHistory> sold=new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Welcome to the world of shopping..");
        boolean r=true;
        while(r){
            System.out.printf("1.Admin %n2.Merchants %n3.User %n4.Exit%n");
            int a=sc.nextInt();
            switch(a){
                case 1:
                    admin();
                    break;
                case 2:
                    merchant();
                    break;
                case 3:
                    user();
                    break;
                case 4:
                    exit();
                    r=false;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
    static void admin(){
        boolean r=true;
        System.out.print("Enter Admin name  : ");
        String name=sc.next();
        System.out.print("Enter password : ");
        int pass=sc.nextInt();
        if(name.equals("Admin") && pass==1030){
            while(r){
                System.out.printf("1.Merchants view%n2.Products view%n3.Add Merchants%n4.Remove Merchants%n5.Merchant verification%n6.Exit%n");
                int a=sc.nextInt();
                switch(a){
                    case 1:
                        merchantView();
                        break;
                    case 2:
                        productView();
                        break;
                    case 3:
                        addMerchant();
                        break;
                    case 4:
                        removeMerchant();
                        break;
                    case 5:
                        merchantVerification();
                        break;
                    case 6:
                        exit();
                        r=false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
        }
        else{
            System.out.println("Enter a valid input");
        }
    }
    static void merchantView(){
        if(merchant.isEmpty()){
            System.out.println("No Merchants added !!");
        }
        else{
            for(int i=0;i<merchant.size();i++){
            System.out.println(i+1 +" "+merchant.get(i).name+"  "+merchant.get(i).mid+"  "+merchant.get(i).verifystatus);
            }
        }
    }
    static void productView(){
        if(product.isEmpty()){
            System.out.println("No Products added!!");
        }
        else{
            for(int i=0;i<product.size();i++){
                System.out.println(i+1 +" "+product.get(i).name+"  "+product.get(i).pid+"  "+product.get(i).mid+" "+product.get(i).desc+"  "+product.get(i).price+"   Delivery in "+product.get(i).delivery+"  Product Count : "+product.get(i).count);
            }
        }
    }
    static void addMerchant(){
        System.out.print("Enter your name : ");
        String name=sc.next();
        String id="M0"+mcount;
        System.out.println("Your id is : "+id);
        String verifystatus="Verified";
        merchant.add(new Merchant(name,id,verifystatus));
        mcount++;
        System.out.println(name+"... You are mercahnt now!!");
    }
    static void removeMerchant(){
        int c=0;
        System.out.print("Enter the merchant id : ");
        String id=sc.next();
        for(int i=0;i<merchant.size();i++){
            if(id.equals(merchant.get(i).mid)){
                System.out.println(merchant.get(i).name+" removed sucessfullyy..");
                merchant.remove(i);
                break;
            }
            else{
                c=c+1;
            }
        }
        if(c==merchant.size()){
            System.out.println("Enter a valid ID");
        }
    }
    static void merchantVerification(){
        int f=0;
        for(int i=0;i<merchant.size();i++){
            if(merchant.get(i).verifystatus.equals("Verified")){
               f=f+1;
            }
            else{
                System.out.println("List of merchants who are not verified : "+merchant.get(i).name+" - "+merchant.get(i).mid);
            }
        }
        if(f==merchant.size()){
            System.out.println("All merchants are verified");
        }
        else{
            System.out.print("Enter your name : ");
            String name=sc.next();
            System.out.print("Enter your id : ");
            String id=sc.next();
            int c=0;
            for(int i=0;i<merchant.size();i++){
                if(name.equals(merchant.get(i).name) && id.equals(merchant.get(i).mid)){
                    System.out.print("Do you want to get verified?[yes/no] : ");
                    String status=sc.next();
                    if(status.equalsIgnoreCase("yes")){
                        merchant.get(i).verifystatus="Verified";
                        System.out.println("Verification status updated!!");
                        System.out.println("Your are verified now");
                    }   
                    else
                        System.out.println("Thank You!! Visit Again");
                    }
                else{
                    c=c+1;
                }
            }
            if(c==merchant.size()){
                System.out.println("Enter a valid name");
            }
        }
       
        
    }
    static void merchant(){  
        boolean r=true;
        while(r){
            System.out.printf("1.New Merchant%n2.Existing Merchant%n3.Exit%n");
            int b=sc.nextInt();
            switch (b){
                case 1:
                    newMerchant();
                    break;
                case 2:
                    existingMerchant();
                    break;
                case 3:
                    exit();
                    r=false;
                    break;
                default:
                System.out.println("Invalid Input");
            }  
        }
    }
    static void newMerchant(){
        System.out.print("Enter your name : ");
        String merchName=sc.next();
        String mid="M0"+mcount;
        System.out.print("Your id : "+mid);
        String verifystatus="Not Verified";
        mcount++;
        merchant.add(new Merchant(merchName,mid,verifystatus));
        System.out.printf("%nDo you want to get verified ?%nYou will be able to post your products if you get verified!![yes/no] : ");
        String verify=sc.next();
        if(verify.equalsIgnoreCase("yes")){
            merchantVerification();
        }
        else{
            System.out.println("Thank You!! Visit Again");
        }
    }
    static void existingMerchant(){
        boolean r=true;
        int c=0;
        System.out.print("Enter your name : ");
        String name=sc.next();
        System.out.print("Enter your id : ");
        String id=sc.next();
        for(int i=0;i<merchant.size();i++){
            if(name.equals(merchant.get(i).name)&&id.equals(merchant.get(i).mid)){
                while(r){
                    System.out.printf("1.Add product%n2.Update product%n3.Remove product%n4.View product%n5.Sold product History%n6.Exit%n");
                    int a=sc.nextInt();
                    switch(a){
                        case 1:
                            addProduct(id,merchant.get(i).verifystatus);
                            break;
                        case 2:
                            updateproduct(id);
                            break;
                        case 3:
                            removeProduct(id);
                            break;
                        case 4:
                            viewProduct(id);
                            break;
                        case 5:
                            soldHistory(id);
                            break;
                        case 6:
                            exit();
                            r=false;
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
            }
            else
            c=c+1;
            if(c==merchant.size())
            System.out.println("Invalid name or id");
        }  
    }
    static void addProduct(String mid,String verify){
        if(verify.equals("Verified")){
            System.out.print("Enter the product name : ");
            String name=sc.next();
            System.out.print("Price : ");
            int price=sc.nextInt();
            System.out.print("Description : ");
            String desc=sc.next();
            System.out.print("Count : ");
            int count=sc.nextInt();
            String pid="P0"+pcount;
            String delivery="5 days";
            product.add(new Product(name,pid,mid,desc,delivery,price,count));
            System.out.println("Product added sucessfully");
            System.out.println("Your product id : "+pid);
            pcount++;
        }
        else{
            System.out.println("You are not verified");
        } 
    }       
    static void updateproduct(String mid){
        int c=0,g=1;
        for(int i=0;i<product.size();i++){
            if(mid.equals(product.get(i).mid)){
                System.out.println(g+". "+product.get(i).name);
                g++;
            }
        }
        System.out.print("Enter the product you want to update : ");
        String name=sc.next();
        for(int i=0;i<product.size();i++){
            if(name.equals(product.get(i).name)){
                System.out.println(product.get(i).name+"  "+product.get(i).pid+"  "+product.get(i).mid+"  "+product.get(i).desc+"  "+product.get(i).delivery+" "+product.get(i).count);
                System.out.printf("What you want to modify(1.name/2.price/3.desc/4.delivery/5.count..)%nEnter the required num : ");
                int update=sc.nextInt();
                System.out.print("Enter the details to update : ");
                String details=sc.next();
                if(update==1){
                    System.out.println("Current name details : "+product.get(i).name);
                    product.get(i).name=details;
                    System.out.println("Name details updated successfully!!");   
                    System.out.println("Updated name details : "+product.get(i).name);
                }
                else if(update==2){
                    System.out.println("Current price details : "+product.get(i).price);
                    product.get(i).price=Integer.parseInt(details);
                    System.out.println("Price details updated successfully!!");
                    System.out.println("Updated price details : "+product.get(i).price);
                }
                else if(update==3){
                    System.out.println("Current desc details : "+product.get(i).desc);
                    product.get(i).desc=details;
                    System.out.println("Desc details updated successfully!!");   
                    System.out.println("Updated desc details : "+product.get(i).desc);
                }
                else if(update==4){
                    System.out.println("Current delivery details : "+product.get(i).delivery);
                    product.get(i).delivery=details;
                    System.out.println("Delivery details updated successfully!!");
                    System.out.println("Updated delivery details : "+product.get(i).delivery);
                }
                else if(update==5){
                    System.out.println("Current delivery details : "+product.get(i).count);
                    product.get(i).count=Integer.parseInt(details);
                    System.out.println("Count details updated successfully!!");
                    System.out.println("Updated count details : "+product.get(i).count);
                }
                else{
                    System.out.println("Enter a valid input");
                }
                
            } 
            else
                c=c+1;
            if(c==product.size())
            System.out.println("Enter valid name");
        }
    }
    static void removeProduct(String id){
        System.out.println("List of your products : ");
        for(int i=0;i<product.size();i++){
            if(id.equals(product.get(i).mid))
            System.out.println(product.get(i).name+"  "+product.get(i).pid+"  "+product.get(i).mid+"  "+product.get(i).desc+"  "+product.get(i).delivery+" "+product.get(i).count);
        }
        System.out.print("Enter the product id : ");
        String pid=sc.next();
        System.out.println("Do you confirm your deletion[yes/no] :  ");
        String delete=sc.next();
        int c=0;
        if(delete.equalsIgnoreCase("yes")){
            for(int i=0;i<product.size();i++){
                if(product.get(i).pid.equals(pid)){
                    System.out.println(product.get(i).name+" deleted sucessfully");
                    product.remove(i);
                    break;
                }
                else
                c=c+1;
                if(c==product.size()){
                    System.out.println("Enter a valid product id");
                }
            }
        }
        else{
            System.out.println("Okie..Thankyou..");
        }
        
        
    }
    static void viewProduct(String id){
        boolean r=true;
        while(r){
            System.out.printf("1.Own Product%n2.All products%n3.Exit%n");
            int a=sc.nextInt();
            switch(a){
                case 1:
                    ownProduct(id);
                    break;
                case 2:
                    productView();
                    break;
                case 3:
                    exit();
                    r=false;
                    break;
                default:
                System.out.println("Invalid Input");
            }
        }
    }
    static void ownProduct(String id){
        int c=0;
        for(int i=0;i<product.size();i++){
            if(id.equals(product.get(i).mid)){
                c=1;
                System.out.println(i+1 +"  "+product.get(i).name+"  "+product.get(i).pid+"  "+product.get(i).mid+"  "+product.get(i).desc+"  Delivery in "+product.get(i).delivery+"  "+product.get(i).price+"  Product count : "+product.get(i).count);
            }
        }
        if(c==0){
            System.out.println("No products has been added..");
        }
    }
    static void soldHistory(String id){
        int c=1;
        for(int i=0;i<sold.size();i++){
            if(id.equals(merchant.get(i).mid)){
                System.out.println(c +"  "+sold.get(i).pname+"  "+sold.get(i).pid+"  "+sold.get(i).price+"  "+sold.get(i).uname);
                c++;
            }
        }
    }
    static void user(){
        boolean r=true;
        while(r){
            System.out.printf("1.New User%n2.Existing User%n3.Forget password%n4.Exit%n");
            int a=sc.nextInt();
            switch(a){
                case 1:
                    newUser();
                    break;
                case 2:
                    existingUser();
                    break;
                case 3:
                    forgetPassword();
                    break;
                case 4:
                    exit();
                    r=false;
                    break;
                default:
                System.out.println("Invalid Input");
            }
        }
    }
    static void newUser(){
        System.out.print("Enter your username : ");
        String name=sc.next();
        System.out.print("Password : ");
        String pass=sc.next();
        System.out.print("Amount you have : ");
        int cash=sc.nextInt();
        int c=0;
        user.add(new User(name,pass,cash));
        for(int i=0;i<user.size();i++){
            if(name.equals(user.get(i).name) && pass.equals(user.get(i).pass)){
                c=c+1;
            }
        }
        if(c==2){
            System.out.println("Acoount already exists...Sign in to existing account");
            user.remove(user.size()-1);
        }
        else
        System.out.println("Account created successfully!!");  
    }
    static void forgetPassword(){
        int c=0;
        System.out.print("Enter your name : "); 
        String name=sc.next();
        for(int i=0;i<user.size();i++){
            if(name.equals(user.get(i).name)){
                System.out.print("Enter the new password : ");
                String pass=sc.next();
                System.out.print("Confirm new password : ");
                String pass1=sc.next();
                if(pass.equals(pass1)){
                    user.get(i).pass=pass;
                    System.out.println("Password updated sucessfully");
                }
                else{
                    System.out.println("Enter the correct passwprd");
                }
            }
            else{
                c=c+1;
            }
        }
        if(c==user.size()){
            System.out.println("Enter a valid user name");
        }
    }
    static void existingUser(){
        int c=0;
        boolean r=true;
        System.out.print("Enter your user name : ");
        String name=sc.next();
        System.out.print("Password : ");
        String pass=sc.next();
        for(int i=0;i<user.size();i++){
            if(name.equals(user.get(i).name) && pass.equals(user.get(i).pass)){
                while(r){
                    System.out.printf("1.Search Product%n2.View Cart%n3.Remove Account%n4.View Orders%n5.Exit%n");
                    int a=sc.nextInt();
                    switch(a){
                        case 1:
                            searchProduct(user.get(i).name);
                            break;
                        case 2:
                            viewCart(user.get(i).name,i);
                            break;
                        case 3:
                            removeAccount(user.get(i).name);
                            break;
                        case 4:
                            viewOrder(i);
                            break;
                        case 5:
                            exit();
                            r=false;
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
            }
            else{
                c=c+1;
            }
            if(c==user.size())
            System.out.println("Invalid user details");
        }
        
    }
    static void searchProduct(String uname){
        System.out.println("Loading...");
        System.out.println("Product to be searched : ");
        String name=sc.next();
        int c=0,p=1;
        for(int i=0;i<product.size();i++){
            if(name.equalsIgnoreCase(product.get(i).name)){
                System.out.println(p+". "+product.get(i).name+"  "+product.get(i).pid+"  "+product.get(i).price+"   Delivery in "+product.get(i).delivery+"  Stock - "+product.get(i).count);
                p++;
            }
            else{
            c=c+1;
            }
            if(c==product.size()){
            System.out.println("No related products!! Surf for more..");
            }
            else{
                if(product.get(i).count>0){
                    System.out.print("Do you want to buy product ? : ");
                    String pro=sc.next();
                    if(pro.equalsIgnoreCase("yes")){
                        System.out.print("Enter the product name: ");
                        String productName=sc.next();
                        System.out.print("Enter the product id : ");
                        String productId=sc.next();
                        int d=0;
                        for(int j=0;j<product.size();j++){
                            if(productName.equals(product.get(j).name) && productId.equals(product.get(j).pid)){
                                System.out.print("Add to cart?[yes/no] : ");
                                String buy=sc.next();
                                if(buy.equalsIgnoreCase("yes"))
                                cart.add(new Cart(productName,productId,product.get(j).delivery,product.get(j).price,uname));
                                else
                                System.out.println("Surf for more..");
                            }
                            else{
                                d=d+1;
                            }
                            if(d==product.size()){
                                System.out.println("Enter a valid product name!!");
                            }
                        }
                    }
                    else{
                        System.out.println("Surf for more..");
                    }
                }
                else{
                    System.out.println("This product is Out of stock");
                }
                
            }
        }
       
    }
    static void viewCart(String uname,int id){
        int c=1;
        for(int i=0;i<cart.size();i++){
            if(uname.equals(user.get(i).name))
            System.out.println(c+"  "+ cart.get(i).name+"  "+cart.get(i).pid+"  "+cart.get(i).price+"  "+cart.get(i).delivery);
            c++;
        }
        System.out.print("Enter the product you want to buy? : ");
        String pname=sc.next();
        System.out.print("Enter the product id : ");
        String pid=sc.next();
        int d=0;
        for(int i=0;i<product.size();i++){
            if(pname.equals(product.get(i).name) && pid.equals(product.get(i).pid)){
                System.out.println("No cancellation policy!!");
                System.out.println("Do you Pay the Amaount[yes/no] : ");
                String money=sc.next();
                if(money.equalsIgnoreCase("yes")){
                    if(user.get(id).cash>=product.get(i).price){
                        user.get(id).cash-=product.get(i).price;
                        sold.add(new SoldHistory(pname, pid,product.get(i).price,product.get(i).mid,user.get(id).name));
                        userorder.add(new UserOrder(pname,pid,product.get(i).price,product.get(i).desc,user.get(id).name,id));
                        System.out.println("Amount debited!!");
                        System.out.println("Order placed sucessfully");
                        System.out.println("Your order will be delivered in : "+product.get(i).delivery);
                        System.out.println("Happy Shopping...Vist Again..");
                        product.get(i).count--;
                    }
                    else{
                        System.out.println("You have low amount");
                    }
                    
                }
                else{
                    System.out.println("Okie..Thank You!!");
                }
            }
            else{
                d=d+1;
            }
            if(d==product.size()){
                System.out.println("Enter a valid name or id");
            }
        }

    }
    static void removeAccount(String uname){
        System.out.printf("Hi.."+uname+" %nEnter your password : ");
        String pass=sc.next();
        int c=0;
        for(int i=0;i<user.size();i++){
            if(uname.equals(user.get(i).name) && pass.equals(user.get(i).pass)){
                user.remove(i);
                System.out.println("Account removed sucessfully");
                break;
            }
            else{
                c=c+1;
            }
            if(c==user.size()){
                System.out.println("Invalid password..");
            }
        }
    }
    static void viewOrder(int id){
        for(int i=0;i<userorder.size();i++){
            if(id==userorder.get(i).userid){
                System.out.println(userorder.get(i).name+" "+userorder.get(i).pid+" "+userorder.get(i).price+"  User id - "+userorder.get(i).userid);
            }
        }
    }
    static void exit(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}

class Merchant{
    String name,verifystatus,mid;
    Merchant(String name,String mid,String verifyStatus){
        this.name=name;
        this.mid=mid;
        this.verifystatus=verifyStatus;
    }
}
class SoldHistory{
    String pname,pid,mid,uname;
    int price;
    SoldHistory(String pname,String pid,int price,String mid,String uname){
        this.pname=pname;
        this.pid=pid;
        this.price=price;
        this.mid=mid;
        this.uname=uname;
    }
}
class Product{
    String name,pid,mid,desc,delivery;
    int price,count;
    Product(String name,String pid,String mid,String desc,String delivery,int price,int count){
        this.name=name;
        this.pid=pid;
        this.price=price;
        this.mid=mid;
        this.desc=desc;
        this.delivery=delivery;
        this.count=count;
    }
}
class User{
    String name,pass;
    int cash;
    User(String name,String pass,int cash){
        this.name=name;
        this.pass=pass;
        this.cash=cash;
    }
}
class UserOrder{
    String name,desc,pid,username;
    int price,userid;
    UserOrder(String name,String pid,int price,String desc,String username,int userid){
        this.name=name;
        this.price=price;
        this.desc=desc;
        this.pid=pid;
        this.username=username;
        this.userid=userid;
    }
}
class Cart{
    String name,pid,delivery,uname;
    int price;
    Cart(String name,String pid,String delivery,int price,String uname){
        this.name=name;
        this.pid=pid;
        this.price=price;
        this.delivery=delivery;
        this.uname=uname;
    }
}