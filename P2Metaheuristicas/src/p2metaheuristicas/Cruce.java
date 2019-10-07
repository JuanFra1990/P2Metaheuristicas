/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2metaheuristicas;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Cruce {
    private Integer tamano;
    ArrayList<Integer> padreUno = new ArrayList<>();
    ArrayList<Integer> padreDos = new ArrayList<>();
    ArrayList<Integer> hijoUno = new ArrayList<>();
    ArrayList<Integer> hijoDos = new ArrayList<>();
    
    /**
     * @description Constructor de la clase Cruce
     * @param _tamano tamano para crear el cruce
     */
    public void setTamano(Integer _tamano){
        tamano=_tamano;
    }
    
    /**
     * @description función para ver el array hijoUno
     * @return hijoUno contenido del array
     */
    public ArrayList<Integer> hijoUno(){
        return hijoUno;
    }
    
    /**
     * @description función para ver el array hijoDos
     * @return hijoDos contenido del array
     */
    public ArrayList<Integer> hijoDos(){
        return hijoDos;
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
    public void OX (ArrayList<Integer> _padreUno, ArrayList<Integer> _padreDos){
        padreUno = _padreUno;
        padreDos = _padreDos;
        
        for (int i = 0; i < tamano; i++){
            hijoUno.add(0);
            hijoDos.add(0);
        }
        
        Random random = new Random();
        
        Integer rangoUno = random.nextInt(tamano-1);
        Integer rangoDos = random.nextInt(tamano-1);
        
        while(Objects.equals(rangoUno, rangoDos=random.nextInt(tamano-1)));
        if (rangoUno > rangoDos){
            swap(rangoUno,rangoDos);
        }
        
        hijoUno = new ArrayList<>(tamano);
        hijoDos = new ArrayList<>(tamano);
        
        for(int i=0; i<tamano; i++){
            if(i>=rangoUno && i<=rangoDos){
                hijoUno.set(i,padreUno.get(i));
                hijoDos.set(i,padreDos.get(i));  
            }
        }
        
        Integer contador1 = (rangoDos+1)%tamano;
        Integer contador2 = (rangoDos+1)%tamano;
        int i = rangoDos+1;
        Integer contador = 0;
        Integer posicion = 0;
        
         while(contador < tamano){
            boolean estaUno=busca(padreUno,padreDos.get(i%tamano),rangoUno,rangoDos,posicion);
            boolean estaDos=busca(padreDos,padreUno.get(i%tamano),rangoUno,rangoDos,posicion);
            
            if(estaUno == false){
                hijoUno.set(contador1%tamano, padreDos.get(i%tamano));
                contador1++;
            }
            
            if(estaDos == false){
                hijoDos.set(hijoDos.get(contador2%tamano), padreUno.get(i%tamano));
                contador2++;
            }
            
            i++;
            contador++;
        }
    }
    
    /**
     * @description Función para buscar si esta o no está en la lista el elemento que nosotros queremos buscar
     * @param lista lista donde buscaremos el dato
     * @param elemento elemento que estamos buscando
     * @param rango1 tamaño mínimo del rango
     * @param rango2 tamaño máximo del rango
     * @param pos variable donde almacenamos la posicion del elemento buscado
     * @return true o false según si el dato ha sido encontrado o no
     */
    public boolean busca(ArrayList<Integer> lista, Integer elemento, Integer rango1, Integer rango2, Integer pos){
        for (int i=rango1; i<=rango2; i++){
            if(Objects.equals(elemento, lista.get(i))){
                pos=i;
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings("empty-statement")
    public void PMX(ArrayList<Integer> _padreUno, ArrayList<Integer> _padreDos){
        padreUno = _padreUno;
        padreDos = _padreDos;
        
         for (int i = 0; i < tamano; i++){
            hijoUno.add(0);
            hijoDos.add(0);
        }
         
        Random random = new Random();
        
        Integer rangoUno = random.nextInt(tamano-1);
        Integer rangoDos = random.nextInt(tamano-1);
        
        while(Objects.equals(rangoUno, rangoDos=random.nextInt(tamano-1)));
        if (rangoUno > rangoDos){
            swap(rangoUno,rangoDos);
        }
        
        hijoUno = new ArrayList<>(tamano);
        hijoDos = new ArrayList<>(tamano);
        
        for(int i=0;i<tamano;i++){
            if(i>=rangoUno && i<=rangoDos){
                hijoUno.set(i, padreDos.get(i));
                hijoDos.set(i, padreUno.get(i));
            }
        }
       
        Integer contador1 = (rangoDos+1)%tamano;
        Integer contador2 = (rangoDos+1)%tamano;
        Integer contador = 0;
        Integer posicion = 0;
        
        // Parte de reparación anterior
        for(int i=0;i<rangoUno; i++){
            int elemento=padreUno.get(i);
            while(busca(hijoUno,elemento,rangoUno,rangoDos,posicion)){
                elemento=hijoDos.get(posicion);
            }
            
            hijoUno.set(i,elemento);
            elemento = padreDos.get(i);
            
            while(busca(hijoDos,elemento,rangoUno,rangoDos,posicion)){
                elemento=hijoUno.get(posicion);
            }
            
            hijoDos.set(i,elemento);
        }
        
        // Parte de reparación posterior
        for(int i=rangoDos+1; i<tamano; i++){
            int elemento = padreUno.get(i);
            while(busca(hijoUno,elemento,rangoUno,rangoDos,posicion)){
                elemento=hijoDos.get(posicion);
            }
            hijoUno.set(i, elemento);
            
            elemento = padreDos.get(i);
            while(busca(hijoDos, elemento, rangoUno, rangoDos, posicion)){
                elemento = hijoUno.get(posicion);
            }
            
            hijoDos.set(i, elemento);
        }
    }
}
