/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

/**
 *
 * @author zezos
 */
public class Start {
    
    public static void main(String[] args) {
        SplashScreen SP = new SplashScreen();
        Main_page MP = new Main_page();
        SP.Loading.setLocation(0, 80);
        SP.show();
        try{
            for(int i = 0; i<= 100; i+=2){
               Thread.sleep(40);
               SP.Loading.setLocation((i+i)*60/100, 80);
               SP.LoadingBar.setValue(i);
            }
            SP.dispose();
            MP.show();
        }
        catch(InterruptedException e){
        }
    }
    
}
