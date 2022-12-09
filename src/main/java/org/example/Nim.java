package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
//import java.util.InputMismatchException;

public class Nim {
    
    private ArrayList<Stack<String>> map = new ArrayList<Stack<String>>();

    public Nim(){
        for (int k = 1; k <= 5; k++){
            map.add(new Stack<String>());
        }
        for (int k = 1; k <= 5; k++){
            for (int i = 0; i < k; i++){
                map.get(k-1).push("[X]");
            }
        }
    }
    
    private void pop(int key, int range) {
        if (map.get(key-1).size() >= range) {
            for (int k = 0; k < range; k++) {
                map.get(key-1).pop();
            }
        }
    }

    private void print(){
        for (int k = 4; k >= 0; k--) {
            System.out.println();
            for (int i = 0; i <= 4; i++) {
                if (map.get(i).size() > k) {
                    System.out.print(map.get(i).get(k) +"\t");
                }else{
                    System.out.print("   \t");
                }
            }
        }
    }
    
    private static void printCenter(String str) {
        int size = 166;
        int left = (size - str.length()) / 2;
        int right = size - left - str.length();
        String repeatedChar = " ";
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < left; i++) {
            buff.append(repeatedChar);
        }
        buff.append(str);
        for (int i = 0; i < right; i++) {
            buff.append(repeatedChar);
        }
        // to see the end (and debug) if using spaces as repeatedChar
        //buff.append("$");  
        System.out.println("| " + buff.toString() + " |");
    }
    
    private static void printSep() {
        System.out.print("\n");
        for (int k = 0; k < 17; k++) {
            System.out.print("----------");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    private static void iniciar() {
        Scanner sc =  new Scanner(System.in);
        String [] names = new String[2];
        System.out.print("\033\143");
        System.out.print("\nIngrese el nombre del Jugador 1: ");
        names[0] = sc.nextLine();
        System.out.print("\nIngrese el nombre del Jugador 2: ");
        names[1] = sc.nextLine();
        System.out.print("\033\143");

        int player = 2, key, range;
        Nim nim = new Nim();
        do {
            player = (player == 1) ? 2 : 1;
            do{
                System.out.println("\n");
                nim.print();
                System.out.print("\n\n\tTURNO DEL JUGADOR NUMERO " + player);
                System.out.print("\nSeleccione una columna: ");
                key = sc.nextInt();
                System.out.print("\nSeleccione la cantidad: ");
                range = sc.nextInt();
                System.out.println("\n");                
            }while(key < 1 || key > 5 || nim.map.get(key-1).size() < range);
            nim.pop(key, range);
            System.out.print("\033\143");
        } while (nim.map.get(0).size() > 0 || nim.map.get(1).size() > 0
                    || nim.map.get(2).size() > 0 || nim.map.get(3).size() > 0
                        || nim.map.get(4).size() > 0);

        sc.close();
        String winnerText = "GANADOR: JUGADOR NUMERO " + player;
        String nameText = "NOMBRE: " + names[player-1];
        printSep();
        printCenter(winnerText);
        printSep();
        printCenter(nameText);
        printSep();
    }
    
    public static void main(String[] args) {
        iniciar();
    }
}
