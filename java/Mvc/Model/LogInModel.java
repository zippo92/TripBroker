package Mvc.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Simone on 01/12/2015.
 */
public class LogInModel {

    List<String> names;

    public LogInModel()
    {
        names = new ArrayList<String>();

        names = Arrays.asList("pippo","paperino","pluto","John","antonio");



    }


    public List<String> getNames()
    {
        return names;
    }


}
