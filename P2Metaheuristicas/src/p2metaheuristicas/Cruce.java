/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2metaheuristicas;

import java.util.ArrayList;
import java.util.Random;

public class Cruce {
    private Integer tamano;
    ArrayList<Integer> padreUno;
    ArrayList<Integer> padreDos;
    ArrayList<Integer> hijoUno;
    ArrayList<Integer> hijoDos;
    
    private static int RandomEnRango(int min, int max) {
        if (min >= max) {
                throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void OX (ArrayList<Integer> _padreUno, ArrayList<Integer> _padreDos){
        padreUno = _padreUno;
        padreDos = _padreDos;
        
        Integer rangoUno = RandomEnRango(0, tamano-1);
        Integer rangoDos = RandomEnRango(0, tamano-1);
        
        
    }
}
