package com.jap.ticketing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketingImpl {

    public List<Ticketing> readFile(String fileName){

        FileReader fileReader = null;
        BufferedReader bufferedReader= null;
        Ticketing []ticketings = null;
        List<Ticketing> ticketingList = new ArrayList<>();

        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            int count =0;
            while ((bufferedReader.readLine()) != null) {
                count++;
            }
            ticketings = new Ticketing[count];
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            String line = null;
            int i =0;
            while ((line = bufferedReader.readLine()) != null){
                String[] split = line.split(",");
                // schedule_no,route_no,ticket_from_stop_id,ticket_from_stop_seq_no,ticket_till_stop_id,
                //ticket_till_stop_seq_no,ticket_date,ticket_time,total_ticket_amount,travelled_KM

                ticketings[i] = new Ticketing(split[0],split[1],Integer.parseInt(split[2]),
                        Integer.parseInt(split[3]),Integer.parseInt(split[4]),Integer.parseInt(split[5]),
                        split[6],split[7],Integer.parseInt(split[8]),Double.parseDouble(split[9]) );

                ticketingList.add(ticketings[i]);
                i++;
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
        ticketingList.sort((t1,t2) -> (int) (t2.getTravelledKM() - t1.getTravelledKM()));
        
        return ticketingList;
    }
    public int collectionsFromSaleOfTickets(List<Ticketing> ticketingList){

        int totalCollections = 0;
        for (Ticketing element : ticketingList) {
            
            totalCollections += element.getTotalTicketAmount();
        }
        return totalCollections;
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
        int totalCollection = ticketing.collectionsFromSaleOfTickets(ticketingList);
        System.out.println("totalCollection = " + totalCollection);
    }
}
