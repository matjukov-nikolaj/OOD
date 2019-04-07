package com.ood;

import com.ood.state.service.MenuController;

public class App
{

    public static void main( String[] args )
    {
        MenuController menuController = new MenuController();
        menuController.start();
    }
}