package parser;

public class Parser {
    private String logs = "logs";
    private static Parser parser;
    private MyArduino arduino = null;

    private Parser(){

    }

    public boolean setCOMPort(String comPort){
        try {
            arduino.setPortDescription(comPort);
            arduino.setBaudRate(9600);
        }catch(Exception e){
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

    public String getData(){
        // COM52
        // /dev/ttyACM0
        //arduino = new MyArduino("/dev/ttyACM0", 9600);
        StringBuilder bld = new StringBuilder();

        System.out.println("Соединение установлено: " + arduino.openConnection());

        arduino.serialWrite('5');
        bld.append(arduino.serialRead(0));

        arduino.closeConnection();

        return bld.toString();
    }
}
