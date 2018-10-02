/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quant;
//Download stock information and generate 10, 20 and 200 day moving averages
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Quant {
final static String SID = "ACc861fb8ffac7d2811e0190ce6b8131e1";
final static String Token = "2af62e119bb8aee1291176da6782b3f9";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_WHITE = "\u001B[37m";
public static final String ANSI_RESET = "\u001B[0m";


  public static void main(String args[]) throws FileNotFoundException  
  {  
StockDownload[]SP;
Scanner input = new Scanner(System.in);      
Date date = new Date();
System.out.println("Today's date is "+ date);
newDate(date);

File current = new File("C://TechnicalAnalysis/current.ini"); //Initialize current portfolio
Scanner input2 = new Scanner(current);
java.io.File watchlist = new java.io.File("C:/TechnicalAnalysis/Watchlists/"+newDate(date)+".csv");
java.io.File portfolio = new java.io.File("C:/TechnicalAnalysis/Portfolio/"+newDate(date)+".csv");
java.io.PrintWriter output = new java.io.PrintWriter(watchlist);
java.io.PrintWriter output1 = new java.io.PrintWriter(portfolio);

output.print( "Ticker, 10 Day Moving Average, 20 Day Moving Average, 200 Day Moving Average\n");
output1.print( "Ticker, 10 Day Moving Average, 20 Day Moving Average, 200 Day Moving Average\n");


System.out.println("Enter input:\n 1 to Download S&P data\n 2 to search S&P data \n 3 to check current Portfolio \n 0 to quit");
int in = input.nextInt();
StockList list = new StockList();

while(in != 0){
    if (in == 1){
   SP= SP(list); 
       System.out.println("Enter input: 1 to Download S&P data\n 2 to search S&P data \n 3 to check current Portfolio \n 0 to quit");
       in = input.nextInt();
    if (in ==2){
        for(int i =0; i < SP.length;i++){
            Search(SP[i], date, output);
        }
    }}
    if (in ==3 ){
        ArrayList<StockDownload> sp = new ArrayList<StockDownload>();
        
System.out.println("Loading Portfolio");
        while (input2.hasNext()){
            String ticker = input2.nextLine();
            StockDownload next = new StockDownload(ticker);
            sp.add(next);
        }
        System.out.println("Portfolio Loaded, Searching Portfolio");
        for(int i =0; i < sp.size();i++){
         output1.print(sp.get(i).getSymbol() + ","+sp.get(i).movingAverage(10)+","+sp.get(i).movingAverage(20)+ ","+sp.get(i).movingAverage(200));
         output1.println();
         System.out.println(sp.get(i).symbol + " "+sp.get(i).movingAverage(10)+" "+sp.get(i).movingAverage(20)+ " "+sp.get(i).movingAverage(200));
           
        }
    }
    System.out.println("Enter next command.");
    in = input.nextInt();
    }


Twilio.init(SID, Token);
output.close();
output1.close();
System.out.println("Data Recorded.");
//System.out.println(ANSI_WHITE +dl.getSymbol()+ANSI_RESET);


//sendMessage("NLY","Buy");
  }  
  public static StockDownload[]SP(StockList list){
      System.out.println("Retrieving S&P Data");
StockDownload[]SP= new StockDownload[list.getTotal()];
for(int i = 0; i <list.getTotal();i++){
    if(i%50 ==0){
        System.out.print((i/5));
    }
    if(i%16==0){
        System.out.print(".");
    }
    SP[i]= new StockDownload(list.symbols.get(i));
    
    
if(i==504){
    System.out.println();
    System.out.println("Retrieval Complete.");
}
}
return SP;
  }
  public static void Search(StockDownload sp, Date date, java.io.PrintWriter output){
      try{
     if((sp.movingAverage(10)<sp.movingAverage(20))&&(sp.movingAverage(20)>(sp.movingAverage(200)*1.1))){

         output.print(sp.symbol + ","+sp.movingAverage(10)+","+sp.movingAverage(20)+ ","+sp.movingAverage(200));
         output.println();
         System.out.println(sp.symbol + " "+sp.movingAverage(10)+" "+sp.movingAverage(20)+ " "+sp.movingAverage(200));
         
     }}
         catch(Exception e){
             
         
     }

      
  }


   public static String newDate(Date date){
       String date1 = date.toString();
       String[] dates = date1.split(" ");
       date1 = dates[2]+""+dates[1]+""+dates[5];
       return date1;
   }
   public static void Indicator(){
       
   }
}  

