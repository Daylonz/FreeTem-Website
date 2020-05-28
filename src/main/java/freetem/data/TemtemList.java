package freetem.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daylon
 * created 2/10/2020
 */

public class TemtemList {

    private static List<Temtem> temtemList = new ArrayList<>();
    //return (20 + Math.ceil((level / catchRate) * 300));

    static
    {
        temtemList.add(new Temtem(2, "Oree", 23, 145));
        temtemList.add(new Temtem(3, "Zaobian", 0, 0));
        temtemList.add(new Temtem(7, "Platypet", 23, 125));
        temtemList.add(new Temtem(8, "Platox", 23, 105));
        temtemList.add(new Temtem(9, "Platimous", 24, 85));
        temtemList.add(new Temtem(10, "Swali", 22, 200));
        temtemList.add(new Temtem(10, "Laoli", 22, 140));
    }
}
