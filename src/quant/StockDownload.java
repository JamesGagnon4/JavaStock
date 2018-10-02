/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quant;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author James Gagnon
 * This class will connect to google finance and download one year worth of historical data for the Stock Ticker provided.
 */
public class StockDownload extends Quant{
    public static final int Date = 0;
    public static final int Open = 1;
    public static final int High = 2;
    public static final int Low = 3;
    public static final int Close = 4;
    public static final int Volume = 5;
    public String symbol;
   
    
    private ArrayList<String> dates;
    private ArrayList<Double> opens;
    private ArrayList<Double> highs;
    private ArrayList<Double> lows;
    private ArrayList<Double> close;
    private ArrayList<Integer> volume;
    

    public StockDownload(String symbol){
    this.symbol = symbol;
    dates = new ArrayList<String>();
    opens = new ArrayList<Double>();
    highs = new ArrayList<Double>();
    lows = new ArrayList<Double>();
    close = new ArrayList<Double> ();
    volume = new ArrayList<Integer> ();
   int countLines = 0;
   
  String url =  "https://query1.finance.yahoo.com/v7/finance/download/"+symbol+"?period1=1523284161&period2=1525876161&interval=1d&events=history&crumb=6FGc3aZAYM6";
  try
  {
        URL googlefin = new URL(url);
        URLConnection data = googlefin.openConnection();
        Scanner input = new Scanner(data.getInputStream());
        if(input.hasNext())//skip line
            input.nextLine();
        
        //start reading data
        while (input.hasNextLine()){
            
            String line = input.nextLine();
            String[] split = line.split(",");
            //Split each line into matching parts, parse into various lists.
            dates.add(split[0]);
            double d = Double.parseDouble(split[1]);
            opens.add(d);
          
            d = Double.parseDouble(split[2]);
            highs.add(d);

            d = Double.parseDouble(split[3]);
            lows.add(d);
            
            d = Double.parseDouble(split[4]);
            close.add(d);
            
            int n = Integer.parseInt(split[5]);
            volume.add(n);
          
            countLines++;
            

        }
  }
  catch(Exception e){
 
  }
  
}
    public double movingAverage(int days){
        double sum = 0;
        double average;
        try{
        for(int i = 0; i < days; i++){
            sum= sum + close.get(i);
        }}
        catch(Exception e){
            
        }
        return Math.round((sum/days)*100.0)/100.0;
    }
    public String getSymbol(){
        String symbol = this.symbol;
        return symbol;
    }
}
