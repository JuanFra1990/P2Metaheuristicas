/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2metaheuristicas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class AlgoritmoGeneticoEstacionario {
    ArrayList<ArrayList<Integer>> poblacion = new ArrayList<>();
    ArrayList<Integer> costePoblacion;
    ArrayList<Integer> evolucionCoste;
    Integer posicionPrimeroMejor;
    Integer posicionSegundoMejor;
    Integer posicionPrimeroPeor;
    Integer posicionSegundoPeor;
    HerramientasAuxiliares herramientasAux;
    
     /**
     * @param hA Valor que queremos darle a herramientasAux
     * @description Funcion que permite darle valor a herramientasAux
     */
    public void setHerramientasAuxiliares(HerramientasAuxiliares hA){
        herramientasAux = hA;
    }
    
     /**
     * @param NuevaPoblacion Valor que queremos darle a poblacion
     * @description Funcion que permite darle valor a poblacion
     */
    public void setPoblacion(ArrayList<ArrayList<Integer>> NuevaPoblacion){
        poblacion = NuevaPoblacion;
    }
    
    /**
     * @description Función para mostrar los datos de la población que estamos estudiando
     * y cual es el primero mejor y el primero y segundo peores
     */
    public void mostrarPoblacion() throws IOException{
        for(int i=0; i<herramientasAux.getNumeroCromosomas();i++){
            System.out.println(""+i+"---");
            for (int j=0; j<herramientasAux.getTamano(); j++){
                System.out.println("" + poblacion.get(i).get(j) + " ");
            }
            
            System.out.println(" ---" + costePoblacion.get(i));
        }
        LogText.LogWriter("El mejor es: " + posicionPrimeroMejor + " con un coste de: " + costePoblacion.get(posicionPrimeroMejor));
        LogText.LogWriter("\r\n");
        LogText.LogWriter("El peor es: " + posicionPrimeroPeor + " con un coste de: " + costePoblacion.get(posicionPrimeroPeor));
        LogText.LogWriter("\r\n");
        LogText.LogWriter("El segundo peor es: " + posicionSegundoPeor + " con un coste de: " + costePoblacion.get(posicionSegundoPeor));
        LogText.LogWriter("\r\n");
        System.out.println("El mejor es: " + posicionPrimeroMejor + " con un coste de: " + costePoblacion.get(posicionPrimeroMejor));
        System.out.println("El peor es: " + posicionPrimeroPeor + " con un coste de: " + costePoblacion.get(posicionPrimeroPeor));
        System.out.println("El segundo peor es: " + posicionSegundoPeor + " con un coste de: " + costePoblacion.get(posicionSegundoPeor));
    
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
        Integer evaluaciones = 50;
        Integer tamano = herramientasAux.getTamano();
        Integer numeroCromosomas = herramientasAux.getNumeroCromosomas();
        Float probabilidadMutacion = herramientasAux.getProbabilidadMutacion() * tamano;
        ArrayList<Integer> padreUno, padreDos;
        ArrayList<Integer> hijoUno, hijoDos;
        Integer costeHUno, costeHDos;
        poblacion = new ArrayList<>(numeroCromosomas);
        ArrayList<Integer> pob = new ArrayList<>(tamano);
        for(int i=0; i<numeroCromosomas; i++){
            poblacion.add(pob);
        }
        costePoblacion = new ArrayList<>(numeroCromosomas);
        for (int i=0; i<numeroCromosomas; i++){
            costePoblacion.add(0);
        }
       
        for (int i = 0; i < numeroCromosomas; i++) {
            poblacion.set(i, herramientasAux.cargarVector(poblacion.get(i)));
            System.out.println(herramientasAux.costeTotal(poblacion.get(i)));
            costePoblacion.set(i, herramientasAux.costeTotal(poblacion.get(i)));
            if (i == 0) {
                posicionPrimeroMejor = i;
                posicionPrimeroPeor = i;
                posicionSegundoPeor = i;
            } else {
                if (costePoblacion.get(i) < costePoblacion.get(posicionPrimeroMejor)) {
                    posicionPrimeroMejor = i;
                }
                if (costePoblacion.get(i) > costePoblacion.get(posicionPrimeroPeor)) {
                    posicionSegundoPeor = posicionPrimeroPeor;
                    posicionPrimeroPeor = i;
                } else {
                    if (costePoblacion.get(i) > costePoblacion.get(posicionSegundoPeor)) {
                        posicionSegundoPeor = i;
                    }
                }
            }
        }
        
        Integer p1, p2, p3, p4;
        Cruce cruce = new Cruce();
        cruce.setTamano(tamano);
        
        Random random = new Random();
         while(evaluaciones < herramientasAux.getEvaluaciones()){
            p1=random.nextInt(numeroCromosomas-1);
            p2=random.nextInt(numeroCromosomas-1);
            p3=random.nextInt(numeroCromosomas-1);
            p4=random.nextInt(numeroCromosomas-1);
            
            if (costePoblacion.get(p1) < costePoblacion.get(p2)) {
                padreUno = poblacion.get(p1);
            } else {
                padreUno = poblacion.get(p2);
            }
            
             if (costePoblacion.get(p3) < costePoblacion.get(p4)) {
                padreDos = poblacion.get(p3);
            } else {
                padreDos = poblacion.get(p4);
            }

            if (tipoCruce) {
                cruce.OX(padreUno, padreDos);
            } else {
                cruce.PMX(padreUno, padreDos);
            }
            
            hijoUno = cruce.hijoUno();
            hijoDos = cruce.hijoDos();
            
            funcionMutacion(hijoUno, hijoDos);
            costeHUno = herramientasAux.costeTotal(hijoUno);
            evaluaciones++;
            if (evaluaciones == herramientasAux.getEvaluaciones()) break;
            costeHDos = herramientasAux.costeTotal(hijoDos);
            evaluaciones++;
            if (evaluaciones == herramientasAux.getEvaluaciones())break;
            funcionReemplazo(hijoUno,hijoDos,costeHUno,costeHDos);
            
             
            posicionPrimeroPeor = 0;
            posicionSegundoPeor = 0;
            
            for (int i = 0; i < numeroCromosomas; i++) {
                if (costePoblacion.get(i) < costePoblacion.get(posicionPrimeroMejor)) {
                    posicionPrimeroMejor = i;
                }
                if (costePoblacion.get(i) > costePoblacion.get(posicionPrimeroMejor)) {
                    posicionSegundoPeor = posicionPrimeroMejor;
                    posicionPrimeroPeor = i;
                } else {
                    if (costePoblacion.get(i) > costePoblacion.get(posicionPrimeroMejor)) {
                        posicionSegundoPeor = i;
                    }
                }
            }
            evolucionCoste.add(costePoblacion.get(posicionPrimeroMejor));
         }
    }
    
    @SuppressWarnings("empty-statement")
    public void funcionMutacion(ArrayList<Integer> hijoUno,ArrayList<Integer> hijoDos){
        Integer tamano = herramientasAux.getTamano();
        Double p;
        Double pMutacion = 0.001 * tamano;
        Integer genetico1;
        Random random = new Random();        
        for(int i=0; i<tamano; i++){
            do {
                p = random.nextDouble();
            } while (p > 1.0 || p < 0.0);
            
            if(p < pMutacion){
                genetico1 = random.nextInt(tamano-1);
                while(i == (genetico1 = RandomEnRango(0, tamano-1))){
                    Integer aux = hijoUno.get(i);
                    hijoUno.set(i, hijoUno.get(genetico1));
                    hijoUno.set(genetico1, aux);
                };
            }
        }
        
        for(int i=0; i<tamano; i++){
            do {
                p = random.nextDouble();
            } while (p > 1.0 || p < 0.0);
            
            if(p < pMutacion){
                genetico1 = random.nextInt(tamano-1);
                while(i == (genetico1 = RandomEnRango(0, tamano-1))){
                    Integer aux = hijoDos.get(i);
                    hijoDos.set(i, hijoDos.get(genetico1));
                    hijoDos.set(genetico1, aux);
                };
            }
        }
    }
    
    public void funcionReemplazo(ArrayList<Integer> hijoUno, ArrayList<Integer> hijoDos, Integer costeUno, Integer costeDos){
        Integer peor;
        Integer mejor;
        
        if(costeUno < costeDos){
            mejor = costeUno;
            peor = costeDos;
        }else{
            mejor = costeDos;
            peor = costeUno;
        }
        
        if(peor < costePoblacion.get(posicionSegundoPeor)){ // se cambiarían los dos
            poblacion.set(posicionSegundoPeor, hijoUno);
            costePoblacion.set(posicionSegundoPeor, costeUno);
            poblacion.set(posicionPrimeroPeor, hijoDos);
            costePoblacion.set(posicionPrimeroPeor, costeDos);
        }else if(peor < costePoblacion.get(posicionPrimeroPeor)){ // Se cambiaría el por por el primero peor y el mejor por el segundo peor
            if(peor == costeUno){
                poblacion.set(posicionPrimeroPeor, hijoUno);
                costePoblacion.set(posicionPrimeroPeor, costeUno);
                poblacion.set(posicionSegundoPeor, hijoDos);
                costePoblacion.set(posicionSegundoPeor, costeDos);
            }else{
                poblacion.set(posicionPrimeroPeor, hijoDos);
                costePoblacion.set(posicionPrimeroPeor, costeDos);
                poblacion.set(posicionSegundoPeor, hijoUno);
                costePoblacion.set(posicionSegundoPeor, costeUno);
            }
        }else if(mejor < costePoblacion.get(posicionPrimeroPeor)){ // Solo se cambia el mejor por el peor
            if(mejor == costeUno){
                poblacion.set(posicionPrimeroPeor, hijoUno);
                costePoblacion.set(posicionPrimeroPeor, costeUno);
            }else{
                poblacion.set(posicionPrimeroPeor, hijoDos);
                costePoblacion.set(posicionPrimeroPeor, costeDos);
            }
        }else if(mejor < costePoblacion.get(posicionSegundoPeor)){ // Solo se cambia el segundo peor por el mejor
            if(mejor == costeUno){
                poblacion.set(posicionSegundoPeor, hijoUno);
                costePoblacion.set(posicionSegundoPeor, costeUno);
            }else{
                poblacion.set(posicionSegundoPeor, hijoDos);
                costePoblacion.set(posicionSegundoPeor, costeDos);
            }
        }
    }
    
    public void mostrarSolucion() throws IOException{
        for(int i=0; i<poblacion.size(); i++){
            System.out.println(" "+poblacion.get(i)+" ");
        }
        LogText.LogWriter("Coste: " + costePoblacion.get(posicionPrimeroMejor));
        LogText.LogWriter("\r\n");
        System.out.println("Coste: " + costePoblacion.get(posicionPrimeroMejor));
    }
}
