package parser;

import Parser.MyArduino;

public class Parser {
    private static Parser parser;

    private Parser(){

    }

    public static Parser getParser() {
        if(parser == null){
            parser = new Parser();
        }
        return parser;
    }

    public String getData(){
        // COM52
        // /dev/ttyACM0
        MyArduino arduino = new MyArduino("/dev/ttyACM0", 9600);
        StringBuilder bld = new StringBuilder();

        System.out.println("Соединение установлено: " + arduino.openConnection());

        arduino.serialWrite('5');
        bld.append(arduino.serialRead(0));

        arduino.closeConnection();

        return bld.toString();
    }
}
