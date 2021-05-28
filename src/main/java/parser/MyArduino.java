package parser;

import com.fazecast.jSerialComm.SerialPort;
import sun.security.util.IOUtils;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;


public class MyArduino {
    private SerialPort comPort;
    private String portDescription;
    private int baud_rate;

    public MyArduino() {
        //empty constructor if port undecided
    }
    public MyArduino(String portDescription) {
        //make sure to set baud rate after
        this.portDescription = portDescription;
        comPort = SerialPort.getCommPort(this.portDescription);
    }

    public MyArduino(String portDescription, int baud_rate) {
        //preferred constructor
        this.portDescription = portDescription;
        comPort = SerialPort.getCommPort(this.portDescription);
        this.baud_rate = baud_rate;
        comPort.setBaudRate(this.baud_rate);
    }



    public boolean openConnection(){
        if(comPort.openPort()){
            try {Thread.sleep(100);} catch(Exception e){}
            return true;
        }
        else {
            return false;
        }
    }

    public void closeConnection() {
        comPort.closePort();
    }

    public void setPortDescription(String portDescription){
        this.portDescription = portDescription;
        comPort = SerialPort.getCommPort(this.portDescription);
    }
    public void setBaudRate(int baud_rate){
        this.baud_rate = baud_rate;
        comPort.setBaudRate(this.baud_rate);
    }

    public String getPortDescription(){
        return portDescription;
    }

    public SerialPort getSerialPort(){
        return comPort;
    }


    public String serialRead(){
        //will be an infinite loop if incoming data is not bound
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        StringBuilder out= new StringBuilder();
        Scanner in = new Scanner(comPort.getInputStream());
        try
        {
            while(in.hasNext())
                out.append(in.next()).append("\n");
            in.close();
        } catch (Exception e) { e.printStackTrace(); }
        return out.toString();
    }

    public String serialRead(int limit){
        //in case of unlimited incoming data, set a limit for number of readings
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        StringBuilder out= new StringBuilder();
        int count=0;
        Scanner in = new Scanner(comPort.getInputStream());
        try {
            while (true) {
                if (!(count <= limit)) break;
                //if (!in.hasNext()) break;
                out.append(
                        in.next()
                ).append("\n");
                count++;
            }
        } catch (Exception e) {}
        in.close();
        return out.toString();
    }

    public String serialRead2(int limit){
        //in case of unlimited incoming data, set a limit for number of readings
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        StringBuilder out= new StringBuilder();
        int count=0;
        return comPort.getInputStream().toString();
    }

    public void serialWrite(String s){
        //writes the entire string at once.
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        try{Thread.sleep(5);} catch(Exception e){}
        PrintWriter pout = new PrintWriter(comPort.getOutputStream());
        pout.print(s);
        pout.flush();

    }
    public void serialWrite(String s,int noOfChars, int delay){
        //writes the entire string at once.
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        try{Thread.sleep(5);} catch(Exception e){}
        PrintWriter pout = new PrintWriter(comPort.getOutputStream());
        for(int i=0;i<s.length();i+=noOfChars){
            pout.write(s.substring(i,i+noOfChars));
            pout.flush();
            System.out.println(s.substring(i,i+noOfChars));
            try{Thread.sleep(delay);}catch(Exception e){}
        }
        pout.write(noOfChars);
        pout.flush();

    }

    public void serialWrite(char c){
        //writes the entire string at once.
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        try{Thread.sleep(5);} catch(Exception e){}
        PrintWriter pout = new PrintWriter(comPort.getOutputStream());pout.write(c);
        pout.flush();
    }

    public void serialWrite(char c, int delay){
        //writes the entire string at once.
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        try{Thread.sleep(5);} catch(Exception e){}
        PrintWriter pout = new PrintWriter(comPort.getOutputStream());pout.write(c);
        pout.flush();
        try{Thread.sleep(delay);}catch(Exception e){}
    }
}