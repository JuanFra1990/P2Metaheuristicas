/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2metaheuristicas;

import java.util.ArrayList;

public class AlgoritmoGreedy {
    int Semilla;
    Integer tamano;
    
    ArrayList<Integer> final1 = new ArrayList<>();
    ArrayList<Integer> final2 = new ArrayList<>();
    
    ArrayList<Integer> solucion = new ArrayList<>();
    
    /**
     * @return ArrayList<Integer> devuelve el vector solucion
     */
    public ArrayList<Integer> getSolucion(){
        return solucion;
    }
    /**
     * @param t Integer que permite modificar el tamaño de nuestros Arrays
     * @description Esta función es un set de nuestra propiedad tamaño.
     */
    
    public void setTamano(Integer t){
        tamano = t;
    }
    
    /**
     * @param MD matriz distancias de nuestro problema
     * @param MF matriz flujo de nuestro problema
     * @description Permite cambiar el valor de las dos matrices de nuestro problema.
     */

    public void setArrays(ArrayList<Integer>  MD, ArrayList<Integer> MF){
        final2 = MD;
        final1 = MF;
    }
    
    /**
     * @param valoresDistancia ArrayList de entrada y salida para obtener la suma de las filas de la matriz Distancia
     * @param valoresFlujo ArrayList de entrada y salida para obtener la suma de las filas de la matriz Flujo
     * @description En esta funcion tan solo hacemos el calculo de las filas de las diferentes matrices, este es un apoyo
     * para nuestro algoritmo Greedy.
     */
    
    public void calculoFilasGreedy(ArrayList<ArrayList<Integer>> matriz1, ArrayList<ArrayList<Integer>> matriz2 ){
        Integer resultadoFilas = 0;
        
        for (int i=0; i < tamano; i++){
            for (int j=0; j < tamano; j++){
                resultadoFilas += matriz1.get(i).get(j);
            }
            final1.set(i, resultadoFilas);
            resultadoFilas = 0;
        }
        
        for (int i=0; i < tamano; i++){
            for (int j=0; j < tamano; j++){
                resultadoFilas += matriz2.get(i).get(j);
            }
            final2.set(i, resultadoFilas);
            resultadoFilas = 0;
        }
    }
    
    /**
     * @param valoresDistancia ArrayList de entrada para obtener la suma de las filas de la matriz Distancia
     * @param valoresFlujo ArrayList de entrada para obtener la suma de las filas de la matriz Flujo
     * @description En esta funcion el objetivo es obtener la solución greedy mediante nuestros parametros de entrada, calculados previamente
     * y con distintas variables apoyo como son vectorIndice1, vectorIndice2, posicion, flujoMaximo y distanciaMaxima, el calculo consiste en
     * recorrer cada Array y comparar tanto la distancia minima como el flujo maximo, de superar los umbrales marcados se actualiza el valor y
     * la posicion del Array y se almacena en nuestros vectoresIndice, que más tarde la unión de estos saldra nuestro vectorSolucion.
     */
    public ArrayList<Integer> AlgoritmoGreedy(){
        ArrayList<Integer> vectorSolucion = new ArrayList<>(tamano);
        ArrayList<Integer> vectorIndice1 = new ArrayList<>(tamano);
        ArrayList<Integer> vectorIndice2 = new ArrayList<>(tamano);
        Integer posicion = 0;
        Integer flujoMaximo = -10;
        Integer distanciaMinima = 999999999;
        //Creamos el vector de indices de flujos ordenados de manera ascendente por posicion 
        for (int i = 0; i< tamano; i++){
            for (int j = 0; j< tamano; j++){
                if (flujoMaximo < final1.get(i)){
                    flujoMaximo = final1.get(i);
                    posicion = i;
                }
            }
            flujoMaximo = -10;
            final1.set(i, -10);
            vectorIndice1.add(posicion);
        }
        //Creamos el vector de indices de distancias ordenados de manera ascendente por posicion 
        for (int i = 0; i< tamano; i++){
            for (int j = 0; j< tamano; j++){
                if (distanciaMinima > final2.get(i)){
                    distanciaMinima = final2.get(i);
                    posicion = i;
                }
            }
            distanciaMinima = 999999999;
            final2.set(i, 999999999);
            vectorIndice1.add(posicion);
        }
        //Creacion del vector solución (Mayor flujo con menor distancia)
        for (int i = 0; i < tamano; i++) {
            vectorSolucion.add(vectorIndice1.get(i), vectorIndice2.get(i));
        }
        
        solucion = vectorSolucion;
       
        return vectorSolucion;
    } 
}
