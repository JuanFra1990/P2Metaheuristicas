/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2metaheuristicas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HerramientasAuxiliares {
    private static ArrayList<ArrayList<Integer>> matrizFlujos;
    private static ArrayList<ArrayList<Integer>> matrizDistancias;
    private static Integer tamano;
    private static Integer numeroCromosomas;
    private static Float probabilidadCruce;
    private static Float probabilidadMutacion;
    private static Integer evaluaciones;
    private static Integer semilla;
    private static Integer ejecucion;
    
     /**
     * @param Ntamano
     * @description Esta función permite almacenar el valor del tamano.
     */
    
    public void setTamano(Integer Ntamano) {
        tamano = Ntamano;
    }
     /**
     * @description Esta función permite devolver el tamaño de las matrices.
     * @return tamano devuelve un Integer con el tamaño de las matrices de nuestro problema
     */
    
    public Integer getTamano() {
        return tamano;
    }
    
    /**
     * @param NmatrizFlujos nueva matriz de flujos para cambiar valor
     * @description Esta función permite almacenar el valor de la matrizFlujos.
     */
    
    public void setMatrizFlujos(ArrayList<ArrayList<Integer>> NmatrizFlujos) {
        matrizFlujos = NmatrizFlujos;
    }
    
    /**
     * @param NmatrizDistancias nueva matriz de distancias para cambiar valor
     * @description Esta función permite almacenar el valor de la matrizFlujos.
     */
    
    public void setMatrizDistancias(ArrayList<ArrayList<Integer>> NmatrizDistancias) {
        matrizDistancias = NmatrizDistancias;
    }
    
    /**
     * @param numCromosomas Valor que queremos darle a numeroCromosomas
     * @description Esta función permite modificar el valor de numeroCromosomas.
     */
    
    public void setNumeroCromosomas(Integer numCromosomas) {
        numeroCromosomas = numCromosomas;
    }
    
    
    /**
     * @description Esta función nos permite devolver el número de cromosomas
     * @return numeroCromosomas devuelve un Integer con el número de cromosomas que tenemos
     */
    public Integer getNumeroCromosomas(){
        return numeroCromosomas;
    }
    
    /**
     * @param _probabilidadCruce valor que queremos dar a la variable probabilidadCruce
     * @description Esta función nos facilita cambiar el valor de la probabilidad de Cruce
     */
    public void setProbabilidadCruce(Float _probabilidadCruce){
        probabilidadCruce = _probabilidadCruce;
    }
    
    /**
     * @description Esta función es para ver la probabilidad de cruce que hay
     * @return probabilidadCruce devuelve un Float que representa la probabilidad de cruce
     */
    public Float getProbabilidadCruce(){
        return probabilidadCruce;
    }
    
    /**
     * @param _probabilidadMutacion valor que queremos dar a la variable probabilidadMutacion
     * @description Esta función nos facilita cambiar el valor de la probabilidad de Mutacion
     */
    public void setProbabilidadMutacion(Float _probabilidadMutacion){
        probabilidadMutacion = _probabilidadMutacion;
    }
    
    /**
     * @description Esta función nos facilita la probabilidad de mutación que tenemos
     * @return probabilidadMutacion un Float para representar la probabilidad de mutación
     */
    public Float getProbabilidadMutacion(){
        return probabilidadMutacion;
    }
    
    /**
     * @description Esta función nos da el valor de las evaluaciones
     * @return evaluaciones un Integer que nos da un valor de las evaluaciones realizadas
     */
    public Integer getEvaluaciones(){
        return evaluaciones;
    }
    
      /**
     * @param _evaluaciones valor que queremos dar a la variable evaluaciones
     * @description Esta función permite cambiar el valor de la variable evaluaciones.
     */
    
    public void setEvaluaciones(Integer _evaluaciones) {
        evaluaciones = _evaluaciones;
    }
    
    /**
     * @description Esta función permite mostrar el contenido de la matriz de distancias por la salida estandar.
     */
    
    public void mostrarMatrizDistancias() {
        System.out.println("-------------Matriz de distancias ------");
        for (Integer i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                System.out.println(matrizDistancias.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * @description Esta función permite mostrar el contenido de la matriz de flujos por la salida estandar.
     */
    public void mostrarMatrizFlujos() {
        System.out.println("-------------Matriz de flujos ------");
        for (Integer i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                System.out.println(matrizFlujos.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
        /**
     * @param Solucion Array de Integer que contiene la solución
     * @description Esta función permite realizar el calculo del coste de creación de dicha solución.
     * @return coste devuelve un Integer con el coste de la solución
     */
    
  public Integer costeTotal(ArrayList<Integer> Solucion) {
        int coste = 0;
        for (int i = 0; i < tamano-1; i++) {
            for (int j = 0; j < tamano-1; j++) {
                if (i != j) {
                    coste += matrizFlujos.get(i).get(j) * matrizDistancias.get(Solucion.get(i)).get(Solucion.get(j));
                }
            }
        }
        return coste;
    }
    
    
    /**
     * @param Solucion Array de Integer que contiene una solución a nuestro problema 
     * @param posicionA Integer que contiene una posición de la matriz
     * @param posicionB Integer que contiene una posición de la matriz
     * @param coste Integer que almacena el coste de la solucion de nuestro problema
     * @description Esta función recorre el contenido de las matrices de distancia y flujo y junto a la solución permite hallar el coste.
     * @return coste devuelve un Integer con el coste de la solución actual
     */
    public Integer costeFactorial(ArrayList<Integer> Solucion, Integer posicionA, Integer posicionB, Integer coste) {
        for (Integer i = 0; i < tamano; i++) {
            if (i != posicionA && i != posicionB) {
                coste += matrizFlujos.get(posicionA).get(i)*(matrizDistancias.get(Solucion.get(posicionB)).get(Solucion.get(i)) 
                        - matrizDistancias.get(Solucion.get(posicionA)).get(Solucion.get(i)) 
                        + matrizFlujos.get(posicionB).get(i)*(matrizDistancias.get(Solucion.get(posicionA)).get(Solucion.get(i)) 
                        - matrizDistancias.get(Solucion.get(posicionB)).get(Solucion.get(i))))
                        + matrizFlujos.get(i).get(posicionA)*(matrizDistancias.get(Solucion.get(i)).get(Solucion.get(posicionB)) 
                        - matrizDistancias.get(Solucion.get(i)).get(Solucion.get(posicionA))) 
                        + matrizFlujos.get(i).get(posicionB)*(matrizDistancias.get(Solucion.get(i)).get(Solucion.get(posicionA)) 
                        - matrizDistancias.get(Solucion.get(i)).get(Solucion.get(posicionB)));
            }
        }
        return coste;
    }
    
    /**
     * @param array Array de Integer 
     * @return  ArrayList<Integer>
     * @description Esta función rellena de valores auxiliares dentro de un rango nuestro array.
     */
    public ArrayList<Integer> cargarVector(ArrayList<Integer> array) {
        for (int icV = 0; icV < tamano; icV++) {
            array.add(icV);
        }
        Integer auxiliar;
        Random random = new Random();
        for (int icV = tamano - 1; icV > 0; icV--) {
            Integer r = random.nextInt(icV);
            auxiliar = array.get(r);
            array.set(r, array.get(icV));
            array.set(icV, auxiliar);
        }
        return array;
    }
}
