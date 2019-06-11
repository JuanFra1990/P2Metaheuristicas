/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2metaheuristicas;

import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGeneticoGeneracional {
    ArrayList<ArrayList<Integer>> poblacion;
    ArrayList<ArrayList<Integer>> poblacionNueva;
    ArrayList<Integer> evolucionCoste;
    ArrayList<Integer> costePoblacion;
    Integer posicionPrimeroMejor;
    ArrayList<Integer> mejor;
    HerramientasAuxiliares herramientasAux;
    
    public void mostrarPoblacion(){
        for(int i=0; i<herramientasAux.getNumeroCromosomas();i++){
            System.out.println(" "+i+"---");
            for(int j=0; j<herramientasAux.getTamano(); j++){
                System.out.println(" "+poblacion.get(i).get(j)+" ");
            }
            System.out.println(" ---"+ costePoblacion.get(i) + " ");
        }
        
        System.out.println("El mejor es: " + posicionPrimeroMejor + " con un coste de: " + costePoblacion.get(posicionPrimeroMejor)+ ".");
    }
    
    /**
     * @description Función para crear numeros aleatorios en un rango introducido por dos números en la función
     * @param min el número mínimo del rango
     * @param max el número máximo del rango
     * @return Devuelve los números creado aleatoriamente
     */
    private static int RandomEnRango(int min, int max) {
        if (min >= max) {
                throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    /**
     * @description Función para crear numeros aleatorios en un rango introducido por dos números en la función
     * @param min el número mínimo del rango
     * @param max el número máximo del rango
     * @return Devuelve los números creado aleatoriamente
     */
    private static Double RandomEnRangoDouble(Double min, Double max) {
        if (min >= max) {
                throw new IllegalArgumentException("max must be greater than min");
        }

        Double r = new Random().nextDouble();
        Double resultado = min + (r * (max - min));
        return resultado;
    }
    
    /**
     * @description Funcion para intercambiar dos valores entre ellos
     * @param uno valor uno introducido
     * @param dos valor dos introducido
     */
    private void swap(Integer uno, Integer dos){
        Integer aux;
        aux = uno;
        uno = dos;
        dos = aux;
    }
    
    public void evolucion (boolean tipoCruce){
        
    }
    
    public void funcionMutacion(ArrayList<boolean> marcaje, int pMutacion){
        Integer tamano = herramientasAux.getTamano();
        Integer numeroCromosomas = herramientasAux.getNumeroCromosomas();
        Double p;
        Integer genetico;
        
        for(int i=0; i<numeroCromosomas; i++){
            for(int j=0; j<tamano; j++){
                if(marcaje.get(i) == true){
                    p = RandomEnRangoDouble(0.0, 1.0);
                    if(p<pMutacion){
                        while(j == (genetico=RandomEnRango(0, tamano-1))){
                            swap(poblacionNueva.get(i).get(j),poblacionNueva.get(i).get(genetico));
                        }
                    }
                }
            }
        }
    }
    
    public void mostrarSolucion(){
        int tamano = herramientasAux.getTamano();
        for(int i=0; i<tamano; i++){
            System.out.println(" " + poblacion.get(i) + " ");
        }
        
        System.out.println("Coste: "+costePoblacion.get(posicionPrimeroMejor));
    }
}
