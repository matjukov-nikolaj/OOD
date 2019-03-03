package com.ood;

import com.ood.coffee.beverage.*;
import com.ood.coffee.condiment.*;
import com.ood.exceptions.WrongAmount;
import org.apache.log4j.Logger;

public class App
{
    private static final Logger LOG = Logger.getLogger(App.class);

    private static void print(Beverage beverage) {
        LOG.info(beverage.getDescription() + " ===> $" + beverage.getCost());
    }

    public static void main( String[] args )
    {
        Beverage coffee = new Coffee("Coffee");
        print(coffee);
        coffee = new Syrup(coffee, SyrupType.CHOCOLATE);
        print(coffee);
        try {
            coffee = new CoconutFlakes(coffee, 10);
            print(coffee);
            coffee = new ChocolateCrumbs(coffee, 20);
            print(coffee);
        } catch (WrongAmount e) {
            LOG.error(e.getMessage());
        }

        Beverage latte1 = new Latte(CoffeeType.STANDARD);
        print(latte1);
        Beverage latte2 = new Latte(CoffeeType.DOUBLE);
        print(latte2);

        Beverage cappuccino1 = new Cappuccino(CoffeeType.STANDARD);
        print(cappuccino1);
        Beverage cappuccino2 = new Cappuccino(CoffeeType.DOUBLE);
        print(cappuccino2);

        Beverage tea1 = new Tea(TeaSort.BLACK);
        print(tea1);
        Beverage tea2 = new Tea(TeaSort.RED);
        print(tea2);
        Beverage tea3 = new Tea(TeaSort.GREEN);
        print(tea3);
        Beverage tea4 = new Tea(TeaSort.WHITE);
        print(tea4);

        Beverage milkshake1 = new Milkshake(MilkshakeType.SMALL);
        print(milkshake1);
        Beverage milkshake2 = new Milkshake(MilkshakeType.MEDIUM);
        print(milkshake2);
        Beverage milkshake3 = new Milkshake(MilkshakeType.BIG);
        print(milkshake3);

        coffee = new Cream(coffee);
        print(coffee);

        try {
            coffee = new Chocolate(coffee, 3);
            print(coffee);
            coffee = new Chocolate(coffee, 6);
        } catch (WrongAmount e) {
            LOG.error(e.getMessage());
        }
        print(coffee);

        coffee = new Liquor(coffee, LiquorType.NUT);
        print(coffee);

    }
}