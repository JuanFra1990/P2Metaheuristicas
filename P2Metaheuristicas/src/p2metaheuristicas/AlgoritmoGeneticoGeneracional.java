/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2metaheuristicas;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class AlgoritmoGeneticoGeneracional {
    
    ArrayList<ArrayList<Integer>> poblacion = new ArrayList<>();
    ArrayList<ArrayList<Integer>> poblacionNueva = new ArrayList<>();
    ArrayList<Integer> evolucionCoste = new ArrayList<>();
    ArrayList<Integer> costePoblacion;
    Integer posicionPrimeroMejor;
    ArrayList<Integer> mejor = new ArrayList<>();
    HerramientasAuxiliares herramientasAux;
    
    
    /**
     * @param hA Valor que queremos darle a herramientasAux
     * @description Funcion que permite darle valor a herramientasAux
     */
    public void setHerramientasAuxiliares(HerramientasAuxiliares hA){
        herramientasAux = hA;
    }
    
    
    /**
     * @description Funcion que nos permite mostrar por la salida estandar los miembros de la poblacion
     */
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
    
    @SuppressWarnings("empty-statement")
    public void evolucion (boolean tipoCruce){
        Integer tamano = herramientasAux.getTamano();
        Integer numeroCromosomas = herramientasAux.getNumeroCromosomas();
        poblacion = new ArrayList<>(numeroCromosomas);
        poblacionNueva = new ArrayList<>(numeroCromosomas);
        ArrayList<Integer> pob = new ArrayList<>(tamano);
        for(int i=0; i<numeroCromosomas; i++){
            poblacion.add(pob);
            poblacionNueva.add(pob);
        }
        costePoblacion = new ArrayList<Integer>(numeroCromosomas);
        Float probabilidadCruce = herramientasAux.getProbabilidadCruce();
        Float probabilidadMutacion = herramientasAux.getProbabilidadMutacion()*tamano;
        Integer evaluaciones = 50;
        Integer totalEvaluaciones = herramientasAux.getEvaluaciones();
        ArrayList<Integer> costes = new ArrayList<Integer>(numeroCromosomas);
        
        for(Integer i=0; i<numeroCromosomas; i++){
            herramientasAux.cargarVector(poblacion.get(i));
            costePoblacion.add(i, herramientasAux.costeTotal(poblacion.get(i)));
            if(i==0){
                posicionPrimeroMejor=i;
            }else{
                if (costePoblacion.get(i)<costePoblacion.get(posicionPrimeroMejor)){
                    posicionPrimeroMejor=i;
                }
            }
        }
                        
        Cruce cruce = new Cruce(tamano);
        
        if (tipoCruce) {
            System.out.println("Ha seleccionado el tipo de Algoritmo Genetico generacional OX");
        } else {
            System.out.println("Ha seleccionado el tipo de Algoritmo Genetico generacional PMX");
        }
        
         while(evaluaciones<totalEvaluaciones){
             // INICIO TORNEO
            posicionPrimeroMejor=0;
            for(Integer i=0; i<numeroCromosomas; i++){
                Integer posicionDos;
                while(i==(posicionDos=RandomEnRango(0,numeroCromosomas-1)));
                if(costePoblacion.get(i) < costePoblacion.get(posicionDos)){
                    poblacionNueva.add(i, poblacion.get(i));
                    costes.add(i, costePoblacion.get(i));
                }else{
                    poblacionNueva.add(i, poblacion.get(posicionDos));
                    costes.add(i, costePoblacion.get(posicionDos));
                }
                if(costes.get(i) < costes.get(posicionPrimeroMejor)){
                    posicionPrimeroMejor = i;
                }
            }
            //Almacenamos el mejor coste hasta el momento y la mejor poblacion
            Integer mejorCoste;
            mejorCoste = costes.get(posicionPrimeroMejor);
            mejor=poblacionNueva.get(posicionPrimeroMejor);
            //FIN TORNEO
            
            //CRUCE
            ArrayList<Boolean> flagsPadres = new ArrayList<>(numeroCromosomas); 
            Double p;
            int elemento = 0;
            
            for(Integer i=0; i<numeroCromosomas; i++){
                p = RandomEnRangoDouble(0.0,1.0);
                if(p < probabilidadCruce){
                    while(i==(elemento=RandomEnRango(0,numeroCromosomas-1)));
                    if(tipoCruce){
                        cruce.OX(poblacionNueva.get(i), poblacionNueva.get(elemento));
                    } else {
                        cruce.PMX(poblacionNueva.get(i), poblacionNueva.get(elemento));
                    }
                    flagsPadres.add(i, true);
                    flagsPadres.add(elemento, true);
                    poblacionNueva.add(i, cruce.hijoUno);
                    poblacionNueva.add(elemento, cruce.hijoDos);
                    
                }
                
            }
            
            //MUTACION
            funcionMutacion(flagsPadres, Math.round(probabilidadMutacion));
            //FIN MUTACION
            
            //REEMPLAZO
            //Se inicializa el elitismo a false para indicar que aún no hemos cogido los mejores
            Boolean elitismo = false;
            for(Integer i=0; i < numeroCromosomas; i++){
                if( flagsPadres.get(i) ){
                    if( i == posicionPrimeroMejor){
                        elitismo=true;
                    }
                    costes.add(i, herramientasAux.costeTotal(poblacionNueva.get(i)));
                    evaluaciones++;
                    if(evaluaciones==totalEvaluaciones)break;
                }
            }
            
            Integer posicionPeor=0;
            for(Integer i=1; i < numeroCromosomas; i++){
                if(costes.get(i) > costes.get(posicionPeor)){
                    posicionPeor = i;
                }
            }
            
            if( elitismo ){
                poblacionNueva.add(posicionPeor, mejor);
                costes.add(posicionPeor, mejorCoste);
            }
        
            Integer posicionMejor=0;
            for(Integer i=1; i < numeroCromosomas; i++){
                if(costes.get(i) < costes.get(posicionMejor)){
                    posicionMejor = i;
                }
            }
            
            posicionPrimeroMejor = posicionMejor;
            mejor = poblacionNueva.get(posicionMejor);
            mejorCoste = costes.get(posicionMejor);
            if(evolucionCoste.size()==9){
                Integer i=234;
            }
        
            poblacion = poblacion;
            costePoblacion = costes;

            //FIN REEMPLAZO
            evolucionCoste.add(costePoblacion.get(posicionPrimeroMejor));
            
        }
    }
        
    
    public void funcionMutacion(ArrayList<Boolean> marcaje, int pMutacion){
        Integer tamano = herramientasAux.getTamano();
        Integer numeroCromosomas = herramientasAux.getNumeroCromosomas();
        Double p;
        Integer genetico;
        
        for(int i=0; i<numeroCromosomas; i++){
            for(int j=0; j<tamano; j++){
                if(marcaje.get(i) == true){
                    p = RandomEnRangoDouble(0.0, 1.0);
                    if(p<pMutacion){
                        while(j == (genetico=(int) (Math.random() * numeroCromosomas))){
                            Integer auxiliar = poblacionNueva.get(i).get(genetico);
                            poblacionNueva.get(i).set(genetico, poblacionNueva.get(i).get(j));
                            poblacionNueva.get(i).set(j, auxiliar);
                        }
                    }
                }
            }
        }
    }
    
    public void mostrarSolucion(){
        for(int i=0; i<poblacion.size(); i++){
            System.out.println(" "+poblacion.get(i)+" ");
        }
        /*for (int i=0; i<costePoblacion.size(); i++){
            System.out.println("Coste: " + costePoblacion.get(i));
        }*/
        System.out.println("Coste: " + costePoblacion.get(posicionPrimeroMejor));
    }
}
