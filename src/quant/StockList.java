/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StockList {
    public static final int Symbol = 0;
    public static final int Name = 1;
    public static final int Sector = 2;
    public  static int total = 0;
    
    public static ArrayList<String> symbols;
    public static ArrayList<String> names;
    public static ArrayList<String> sector;
    
    public StockList() throws FileNotFoundException{
    symbols = new ArrayList<String>();
    names = new ArrayList<String>();
    sector = new ArrayList<String>();
    
  System.out.print("Loading S&P 500...");
   File file = new File("C://TechnicalAnalysis/Exchanges/S&P.csv");
  Scanner input = new Scanner(file);
   System.out.println("Done");
   addExchange(input);
   
//    System.out.print("Loading S&P 500...");
//    file = new File("C://TechnicalAnalysis/Exchanges/S&P.csv");
//    input = new Scanner(file);
//    addExchange(input);
//    System.out.println("Done");
    
//    System.out.print("Loading NASDAQ...");
//    file = new File("C://TechnicalAnalysis/Exchanges/NASDAQ.csv");
//    input = new Scanner(file);
//    addExchange(input);
//    System.out.println("Done.");
    }
    public static void addExchange(Scanner input){
        input.useDelimiter(",");
            if (input.hasNext()){
             input.nextLine();
    }
    while(input.hasNext()){
     String line = input.nextLine();
     String[]split = line.split(",");
        symbols.add(split[0]);
        names.add(split[1]);
        sector.add(split[2]);
        total++;
    }
    }
    public  int getTotal(){
        
        return total;
    }
}
