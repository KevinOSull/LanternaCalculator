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

    private static void startingPoint()throws IOException{

    }

    private static void createScreen()throws IOException{

    }

    private static void drawHeading(){

    }

    private static void drawOperationHeading(String[]heading){

    }

    private static int calculateHeadingCoordinatesRow(){
        return 0;
    }

    private static int calculateYCoordinate(){
        return 0;
    }

    private static int getWidth(){
        return 0;
    }

    private static int getHeight(){
        return 0;
    }

    private static void getKeyStroke()throws IOException{

    }

    private static void checkForExit()throws IOException{

    }

    private static void handleMenuSelection(int index){

    }

    private static void drawMenu()throws IOException{

    }

    private static void headerOptions(){

    }

    private static int calculationOperations(int numOne,int numTwo){
        return 0;
    }

    private static int add(int numOne,int numTwo){
        return 0;
    }

    private static int division(int numOne,int numTwo){
        return 0;
    }

    private static int multiply(int numOne,int numTwo){
        return 0;
    }

    private static int subract(int numOne,int numTwo){
        return 0;
    }

    private static int getNumOne()throws IOException{
        return 0;
    }

    private static int getNumTwo()throws IOException{
        return 0;
    }

    private static int getInput(Screen screen,TextGraphics tg,String prompt)throws IOException{
        return 0;
    }


























}
