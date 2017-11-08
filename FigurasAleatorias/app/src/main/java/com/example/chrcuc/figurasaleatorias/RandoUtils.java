package com.example.chrcuc.figurasaleatorias;

import java.util.Random;

/**
 * Created by chrcuc on 7/11/17.
 */

public class RandoUtils {
    private static Random r = new Random();

     public static int randomInt(int range) {
     return(r.nextInt(range));
     }

     /** Devuelve un indice aleatorio en el intervalo [0 array.length-1] */
    public static int randomIndex(Object[] array) {
        return(randomInt(array.length));
    }


    public static <T> T randomElement(T[] array) {
        return(array[randomIndex(array)]);
    }


    public static float randomFloat(int n) {
        return((float)Math.random()*n);
    }
}
