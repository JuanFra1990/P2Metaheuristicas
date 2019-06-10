/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2metaheuristicas;

import java.util.ArrayList;


public class AlgoritmoGeneticoEstacionario {
    ArrayList<ArrayList<Integer>> poblacion;
    ArrayList<Integer> costePoblacion;
    ArrayList<Integer> evaluacionCoste;
    Integer posicionPrimeroMejor;
    Integer posicionSegundoMejor;
    Integer posicionPrimeroPeor;
    Integer posicionSegundoPeor;
    HerramientasAuxiliares herramientasAux;
    
    /**
     * @description Función para mostrar los datos de la población que estamos estudiando
     * y cual es el primero mejor y el primero y segundo peores
     */
    public void mostrarPoblacion(){
        for(int i=0; i<herramientasAux.getNumeroCromosomas();i++){
            System.out.println(""+i+"---");
            for (int j=0; j<herramientasAux.getTamano(); j++){
                System.out.println("" + poblacion.get(i).get(j) + " ");
            }
            
            System.out.println(" ---" + costePoblacion.get(i));
        }
        
        System.out.println("El mejor es: " + posicionPrimeroMejor + " con un coste de: " + costePoblacion.get(posicionPrimeroMejor));
        System.out.println("El mejor es: " + posicionPrimeroPeor + " con un coste de: " + costePoblacion.get(posicionPrimeroPeor));
        System.out.println("El mejor es: " + posicionSegundoPeor + " con un coste de: " + costePoblacion.get(posicionSegundoPeor));
    
    }
    
    public void evolucion (boolean tipoCruce){
        Integer evaluaciones = 100;
        Integer tamano = herramientasAux.getTamano();
        Integer numeroCromosomas = herramientasAux.getNumeroCromosomas();
        Float probabilidadMutacion = herramientasAux.getProbabilidadMutacion();
        ArrayList<Integer> padreUno, padreDos;
        ArrayList<Integer> hijoUno, hijoDos;
        Integer costeHUno, costeHDos;
        
        poblacion.add(numeroCromosomas, ArrayList<Integer>(tamano));
        costePoblacion.add(numeroCromosomas);
        for (int i=0; i < numeroCromosomas; i++){
            herramientasAux.cargarVector(poblacion.get(i));
            costePoblacion.get(i) = herramientasAux.costeTotal(poblacion.get(i));
            if (i == 0){
                posicionPrimeroMejor = i;
                posicionPrimeroPeor = i;
            }else{
                if(costePoblacion.get(i) < costePoblacion.get(posicionPrimeroMejor)){
                    posicionPrimeroMejor = i;
                }
                if(costePoblacion.get(i) > costePoblacion.get(posicionPrimeroPeor)){
                    posicionSegundoPeor = posicionPrimeroPeor;
                    posicionPrimeroPeor = i;
                }else{
                    if(costePoblacion.get(i) > costePoblacion.get(posicionSegundoPeor)){
                        posicionSegundoPeor = i;
                    }
                }
            }
        }
        
        Integer p1, p2, p3, p4;
        Cruces cru(tamano);
        if (tipoCruce){
            
        }
    }
}
