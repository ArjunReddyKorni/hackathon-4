package com.jap.ticketing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TicketingTest {
    TicketingImpl ticketingImpl = null;
    Ticketing ticketing = null;
    String fileName = "src/main/resources/sample.csv";
    String fileNameOfIncorrectFormat = "src/main/resources/NumberFormatExceptionFile.csv";
    @Before
    public void setUp(){
        ticketingImpl = new TicketingImpl();
        ticketing = new Ticketing();
    }
    @After
    public void tearDown(){
        ticketingImpl = null; ticketing = null;
    }

    @Test
    public void readingDataAndAddedToList(){
        List<Ticketing> output = ticketingImpl.readFile(fileName);
        assertNotEquals("Ticketing{scheduleNo='KIAS-12/5', routeNo='KIAS-12UP', ticketFromStopId=9387, ticketFromStopSeqNo=1, ticketTillStopId=11359, ticketTillStopSeqNo=39, ticketDate='01/09/2018', ticketTime='02:04:08', totalTicketAmount=562, travelledKM=49.3}",output.get(1));
        List<Ticketing> output1 = ticketingImpl.sortBasedOnTheDistance(output);
        assertEquals(49.3,output1.get(0).getTravelledKM(),0.5);
    }

    @Test
    public void readFile() {
        List<Ticketing> output = ticketingImpl.readFile(fileName);
        assertEquals("Ticket Records objects not returned correctly",49,output.size());
    }

    @Test
    public void totalCollection(){
        List<Ticketing> output = ticketingImpl.readFile(fileName);
        assertEquals(10348,ticketingImpl.collectionsFromSaleOfTickets(output),0.5);
    }
    @Test
    public void sortByDistance(){
        List<Ticketing> output = ticketingImpl.readFile(fileName);
        List<Ticketing> output1 = ticketingImpl.sortBasedOnTheDistance(output);
        assertEquals(49.5,output1.get(0).getTravelledKM(),0.5);

    }



}
