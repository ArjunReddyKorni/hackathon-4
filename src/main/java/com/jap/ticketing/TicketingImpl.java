package com.jap.ticketing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TicketingImpl {

    public List<Ticketing> readFile(String fileName){

        FileReader fileReader = null;
        BufferedReader bufferedReader= null;

        List<Ticketing> ticketingList = new ArrayList<>();

        try {

            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            String line = null;

            while ((line = bufferedReader.readLine()) != null){
                String[] split = line.split(",");

                ticketingList.add(new Ticketing(split[0],split[1],Integer.parseInt(split[2]),
                        Integer.parseInt(split[3]),Integer.parseInt(split[4]),Integer.parseInt(split[5]),
                        split[6],split[7],Integer.parseInt(split[8]),Double.parseDouble(split[9])) );


            }

        }catch (FileNotFoundException e){
            System.out.println("e = " + e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("e = " + e);
            e.printStackTrace();

        }
        return ticketingList;
    }

    public List<Ticketing> sortBasedOnTheDistance(List<Ticketing> ticketingList){
        ticketingList.sort((o1,o2) -> {
            if(o1.getTravelledKM() == o2.getTravelledKM()){
                return 0;
            }
            else if(o1.getTravelledKM() > o2.getTravelledKM()){
                return -1;
            }
            else{
                return 1;
            }
        });
        
        return ticketingList;
    }
    public double collectionsFromSaleOfTickets(List<Ticketing> ticketingList){

        CollectionFromSaleOfTicket calculator = (ticketingList1) -> {
            double sum = 0;
            for (Ticketing ticketing : ticketingList1) {
                sum += ticketing.getTotalTicketAmount();
            }
            return sum;
        };

        return calculator.totalCollection(ticketingList);
    }

    public static void main(String[] args) {
        TicketingImpl ticketing = new TicketingImpl();

        List<Ticketing> ticketingList = ticketing.readFile("src/main/resources/sample.csv");
        for (Ticketing element : ticketingList) {
            System.out.println("element = " + element);
        }
        List<Ticketing> ticketingList1 = ticketing.sortBasedOnTheDistance(ticketingList);
        for (Ticketing element : ticketingList) {
            System.out.println("element = " + element);
        }
        double totalCollection = ticketing.collectionsFromSaleOfTickets(ticketingList);
        System.out.println("totalCollection = " + totalCollection);
    }
}
