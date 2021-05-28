package parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private static Parser parser;
    private MyArduino arduino = new MyArduino();

    public Parser(){
        //arduino = new MyArduino();
    }

    public boolean setCOMPort(String comPort){
        try {
            // "/dev/ttyACM0"
            arduino.setPortDescription(comPort);
            arduino.setBaudRate(9600);
            arduino.openConnection();
        } catch(Exception e){
            return false;
        }
        return true;
    }

    public static Parser getParser() {
        if(parser == null){
            parser = new Parser();
        }
        return parser;
    }

    public String getData() throws InterruptedException {
        // /dev/ttyACM0
        // System.out.println("Соединение установлено: " + arduino.openConnection());
        if (!arduino.openConnection()) return "No connection";

        arduino.serialWrite('1');
        String result = arduino.serialRead(0);

        StringBuffer buffer = new StringBuffer();

        System.out.println(Arrays.toString(result.replace(".", " ").split(" ")));
        List<String> list = Arrays.stream(result.replace(".", " ").split(" ")).filter(x -> !x.equals("end\n")).map(s -> {
            buffer.append("Card ");
            buffer.append(s.substring(0, 7));
            buffer.append(" was ");
            switch(Integer.parseInt(s.substring(8,9))){
                case 0:
                    buffer.append("Access granted");
                    break;
                case 1:
                    buffer.append("Access denied");
                    break;
                case 2:
                    buffer.append("Add card");
                    break;
                case 3:
                    buffer.append("Delete card");
                    break;
                case 4:
                    buffer.append("Entry program mode");
                    break;
                case 5:
                    buffer.append("Exiting program mode");
                    break;
                case 6:
                    buffer.append("Add mater card");
                    break;
            }
            return buffer.append("\n").toString();
        }).collect(Collectors.toList());

        arduino.closeConnection();

        return buffer.toString();
    }
}
