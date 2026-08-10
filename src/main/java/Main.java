import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.Arrays;
public class Main {
    private static Screen screen;
    private static TerminalSize size;
    private static String[] MENU_ITEMS = {"1.)Add","2.)Multiply","3.)Subract","4.)Division","Exit"};
    private static int selectedItem = 0;
    private static int x;
    private static int y;
    private static int width;
    private static int height;
    private static boolean isRunning = true;

    public static void main(String[]args) throws IOException{
        try{
            createScreen();
            startingPoint();
            screen.stopScreen();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private static void startingPoint()throws IOException {
        while(isRunning) {
            screen.clear();
            drawHeading();
            drawMenu();
            screen.refresh();
            getKeyStroke();

        }
    }

    private static void createScreen() throws IOException{
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        size = screen.getTerminalSize();
        width = getWidth();
        height = getHeight();
        x = calculateHeadingCoordinatesRow();
        y =  calculateYCoordinate();
        screen.setCursorPosition(null);
    }

    private static void drawHeading() {
        TextGraphics heading = screen.newTextGraphics();
        for(int i = 0; i < Headers.ASCII_HEADING.length;i++) {
            heading.putString(x,y + i,Headers.ASCII_HEADING[i]);
        }
    }

    private static void drawOperationHeading(String[]heading) {
        TextGraphics header = screen.newTextGraphics();
        for(int i = 0; i < heading.length; i++) {
            header.putString(x,y + i,heading[i]);
        }
    }

    private static int calculateHeadingCoordinatesRow() {
        int x = (width - Headers.ASCII_HEADING[0].length()) / 2;
        return Math.max(0, x);
    }

    private static int calculateYCoordinate() {
        int y = height/2-11;
        return Math.max(0, y);
    }

    private static int getWidth() {
        return size.getColumns();
    }

    private static int getHeight() {
        return size.getRows();
    }

    private static void getKeyStroke()throws IOException{
        KeyStroke key = screen.readInput();
        if(key != null) {
            switch(key.getKeyType()) {
                case ArrowUp ->{
                    selectedItem = (selectedItem -1 + MENU_ITEMS.length) % MENU_ITEMS.length;
                }
                case ArrowDown ->{
                    selectedItem = (selectedItem + 1 ) % MENU_ITEMS.length;
                }
                case Enter ->{
                    checkForExit();
                }
                case Escape -> isRunning = false;
            }
        }
    }

    private static void checkForExit()throws IOException{
        if(MENU_ITEMS[selectedItem].equals("Exit")) {
            isRunning = false;
            return;
        }
        handleMenuSelection(selectedItem);
    }

    private static void handleMenuSelection(int index) throws IOException {
        screen.clear();
        headerOptions();
        TextGraphics tg = screen.newTextGraphics();
        //String message = "You selected: " + MENU_ITEMS[index];
        int numOne = getNumOne();
        int numTwo = getNumTwo();
        int result = calculationOperations(numOne,numTwo);
        String stringResult = String.valueOf(result);
        int x = (screen.getTerminalSize().getColumns() - stringResult.length()) / 2;
        int y = screen.getTerminalSize().getRows() / 2;
        tg.putString(x, y, stringResult, SGR.BOLD);
        screen.refresh();
        screen.readInput();
    }

    private static void drawMenu()throws IOException{
        TextGraphics tg = screen.newTextGraphics();

        int startY = y + Headers.ASCII_HEADING.length + 2;

        for (int i = 0; i < MENU_ITEMS.length; i++) {
            String item = MENU_ITEMS[i];
            int menuX = (width - item.length()) / 2;
            int menuY = startY + i;

            if (i == selectedItem) {
                tg.setForegroundColor(TextColor.ANSI.BLACK);
                tg.setBackgroundColor(TextColor.ANSI.WHITE);
                tg.putString(menuX, menuY, item);
                tg.setForegroundColor(TextColor.ANSI.DEFAULT);
                tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
            } else {
                tg.putString(menuX, menuY, item);
            }
        }
    }

    private static void headerOptions(){
        switch(selectedItem) {
            case 0: drawOperationHeading(Headers.ADD_HEADING);
                break;
            case 1: drawOperationHeading(Headers.MULTIPLY_HEADING);
                break;
            case 2: drawOperationHeading(Headers.SUBTRACTION_HEADING);
                break;
            case 3: drawOperationHeading(Headers.DIVISION_HEADING);
                break;
            default:
                System.out.println("SOMETHING WENT WRONG!");
        }
    }

    private static int calculationOperations(int numOne,int numTwo){
        switch(selectedItem) {
            case 0 :
                return add(numOne,numTwo);

            case 1:
                return multiply(numOne,numTwo);

            case 2:
                return subract(numOne,numTwo);


            case 3:
                return division(numOne,numTwo);

            default:
                return 0;


        }
    }

    private static int add(int numOne,int numTwo){
        return numOne + numTwo;
    }

    private static int division(int numOne,int numTwo){
        return numOne / numTwo;
    }

    private static int multiply(int numOne,int numTwo){
        return numOne * numTwo;
    }

    private static int subract(int numOne,int numTwo){
        return numOne - numTwo;
    }

    private static int getNumOne()throws IOException{
        TextGraphics tg = screen.newTextGraphics();
        int numOne = getInput(screen,tg,"Enter First Number: ");
        return numOne;
    }

    private static int getNumTwo()throws IOException{
        TextGraphics tg = screen.newTextGraphics();
        int numTwo = getInput(screen,tg,"Enter Second Number: ");
        return numTwo;
    }

    private static int getInput(Screen screen,TextGraphics tg,String prompt)throws IOException{
        int num = 0;
        boolean isInput = true;
        while(isInput) {
            tg.putString(2,2,"          ");
            tg.putString(2,3,"          ");
            tg.putString(2, 2, prompt, SGR.BOLD);
            tg.putString(2, 3, String.valueOf(num), SGR.BOLD);
            screen.refresh();
            KeyStroke key = screen.readInput();
            if(key.getKeyType() == KeyType.Character && Character.isDigit(key.getCharacter())) {
                int digit = Character.getNumericValue(key.getCharacter());
                num = num * 10 + digit;
            }else if(key.getKeyType() == KeyType.Backspace) {
                num = num / 10;
            }else if(key.getKeyType() == KeyType.Enter) {
                isInput = false;
            }
        }
        return num;
    }


























}
