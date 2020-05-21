package com;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//loome kaubad
        Product ps1 = new Product("sokid", 42);
        Product ps2 = new Product("sokid", 42);
        Product pk1 = new Product("kindad", 10);
        Product pk2 = new Product("kindad", 9);
        Product pm = new Product("mantel", 60);
        Product pp = new Product("püksid", 56);

// testime equals() meetodi toimimist:
// objekt iseendaga:
        System.out.println("ps1 ?= ps1: " + ps1.equals(ps1) ); // true
// kaks sama sisuga kaupa:
        System.out.println("ps1 ?= ps2: " + ps1.equals(ps2) ); // true
// sama nime, aga erineva suurusega kaup:
        System.out.println("pk1 ?= pk2: " + pk1.equals(pk2) ); // false
// testime hashCode() meetodi toimimist:
        Set<Product> productTestSet = new HashSet<>();
// Hulka lisati uus element:
        System.out.println("lisan ps1: " + productTestSet.add(ps1) ); // true

// Sellise "sisuga" element juba eksisteerib, ei lisatud topelt:
        System.out.println("lisan ps2: " + productTestSet.add(ps2) ); // false

// Loome uue objekti, mille "sisu" on sama, mis ps1-l. Otsing peab selle leidma
        System.out.println("otsin nr.42 sokke: "+ productTestSet.contains(new Product("sokid", 42)) ); // true

        System.out.println(" ");

// Testime lao toimimist
// lisame kaubad lattu
        Warehouse w = new Warehouse();
        w.receive(ps1, "A");
        w.receive(pk1, "A");
        w.receive(pk2, "B");
        w.receive(ps2, "B");
        w.receive(pm, "B");
        w.receive(pp, "L");

        w.createCSVFromProductList();
        System.out.println( ps1 + " on laos " + w.getItemCount(ps1) + " tk"); //2tk
        System.out.println( pk1 + " on laos " + w.getItemCount(pk1) + " tk"); //1tk

// loome otsitava kauba
        Product po1 = new Product("sokid", 42);

// väljastame laost kauba:
        String shelfId = w.dispatch(po1);
        if (shelfId != null) {
            System.out.println( "Väljastasin toote " + po1 + " asukohalt " + shelfId);
        }
        else {
            System.out.println( "Toodet, " + po1 + ", ei leitud laost");
        }

// kontrolliks prindime välja laoseisu ja vaatame, kas antud kauba kogus on vähenenud
        System.out.println( ps1 + " on laos " + w.getItemCount(ps1) + " tk"); //1tk

        w.createCSVFromProductList("PRODUCT_LIST_2.csv");
    }
}
