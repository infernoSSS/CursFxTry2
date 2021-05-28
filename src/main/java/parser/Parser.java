package parser;

import arduino.Arduino;

public class Parser {
    private String logs = "logs\n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " \n" +
            " lods";
    private static Parser parser;

    private Parser(){
        Arduino arduino = new Arduino("COM52", 9600);
    }

    public static Parser getParser() {
        if(parser == null){
            parser = new Parser();
        }
        return parser;
    }

    public String getData(){
        /*

        Scanner scanner = new Scanner(System.in);
        Arduino arduino = new Arduino("COM52", 9600);

        boolean connected = arduino.openConnection();
        System.out.println("Соединение установлено: " + connected);

        arduino.serialWrite("Some request");
        String ans = arduino.serialRead(1);
        if (ans != "1") return null;

        String data = arduino.serialRead();

        String[] result = data.split("\n");

        // String[] to ArrayList<String>

        arduino.closeConnection();

        }

         */
        return logs;
    }
}
