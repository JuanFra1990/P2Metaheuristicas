/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2metaheuristicas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 *
 * @author juanf
 */
public class P2Metaheuristicas {

     private static Integer tamano;
    
    private static final ArrayList<ArrayList<Integer>> matrizDistancias = new ArrayList<>();
    private static final ArrayList<ArrayList<Integer>> matrizFlujos = new ArrayList<>();
    
    private static ArrayList<Integer> semillas = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        StringBuilder str=new StringBuilder();
       char opcion = '0';
       long startTime;
      long endTime;
       while (opcion != '7') {
           System.out.println("---------------Menú Practica 2 -----------------------------------------");
           System.out.println("--- 1. Carga de datos --------------------------------------------------");
           System.out.println("--- 2. Seleccion de semilla --------------------------------------------");
           System.out.println("--- 3. Seleccion de Algoritmo Genético Estacionario OX -----------------");
           System.out.println("--- 4. Seleccion de Algoritmo Genético Estacionario PMX-----------------");
           System.out.println("--- 5. Seleccion de Algoritmo Genético Generacional OX -----------------");
           System.out.println("--- 6. Seleccion de Algoritmo Genético Generacional PMX ----------------");
           System.out.println("--- 7. Finalizar Programa ----------------------------------------------");
           System.out.println("------------------------------------------------------------------------");
           System.out.println("Introduce opción: ");
           Reader entrada=new InputStreamReader(System.in);
           opcion=(char)entrada.read();
           
            switch (opcion){
                case '1':
                   System.out.println("Has seleccionado la opción de cargar datos");
                   cargaDatos("./archivos/cnf02.dat");
                   break;
                case '2':
                   System.out.println("Has seleccionado la opción de seleccionar semillas");
                   System.out.println("¿Cuantas semillas desea introducir?");
                   Reader entradaNumeroSemillas=new InputStreamReader(System.in);
                   opcion=(char)entradaNumeroSemillas.read();
                   Integer tamanoSemilla = Character.getNumericValue(opcion);
                   Integer contador = 1;
                   while (tamanoSemilla > 0){
                        System.out.println("Introduzca la semilla numero " + contador);
                        Reader entradaSemillas=new InputStreamReader(System.in);
                        opcion=(char)entradaSemillas.read();
                        semillas.add(Character.getNumericValue(opcion));
                        tamanoSemilla--;
                        contador++;
                   }
                   break;
                case '3':
                    if(matrizDistancias.isEmpty() || matrizFlujos.isEmpty()){
                        System.out.println("Los datos no estan cargados aún, ¿Desea cargarlos? (Responde con S o N)");
                        Reader entradaIn=new InputStreamReader(System.in);
                        opcion=(char)entradaIn.read();
                        if (opcion == 'S'){
                            cargaDatos("./archivos/cnf02.dat");
                        } else {
                            System.out.println("Sin datos no podemos lanzar el algoritmo, greedy.");
                            System.out.println("Lo sentimos");
                            break;
                        }
                    }
                    
                    if (semillas.isEmpty()){
                        System.out.println("¿Cuantas semillas desea introducir?");
                        Reader entradaNumeroSemillasR=new InputStreamReader(System.in);
                        opcion=(char)entradaNumeroSemillasR.read();
                        Integer tamanoSemillaESB =  Character.getNumericValue(opcion);;
                        Integer contadorR = 0;
                        while ((int)tamanoSemillaESB > 0){
                            System.out.println("Introduzca la semilla numero " + contadorR);
                            Reader entradaSemillas=new InputStreamReader(System.in);
                            opcion=(char)entradaSemillas.read();
                            semillas.add(Character.getNumericValue(opcion));
                            tamanoSemillaESB--;
                            contadorR++;
                        }
                        
                        if (opcion == 0){
                            System.out.println("Sin semilla no podemos lanzar el algoritmo de Enfriamiento simulado Boltzmann.");
                            System.out.println("Lo sentimos");
                            break;
                        }
                    }
                    
                    System.out.println("Has seleccionado la opción del Algoritmo Genético Estacionario OX");
                    startTime = System.currentTimeMillis();
                    AlgoritmoGeneticoEstacionario algoritmoEstacionario = new AlgoritmoGeneticoEstacionario();
                    HerramientasAuxiliares herramientasAuxiliares = new HerramientasAuxiliares();
                    herramientasAuxiliares.setMatrizDistancias(matrizDistancias);
                    herramientasAuxiliares.setMatrizFlujos(matrizFlujos);
                    herramientasAuxiliares.setTamano(tamano);
                    herramientasAuxiliares.setNumeroCromosomas(2);
                    herramientasAuxiliares.setEvaluaciones(0);
                    algoritmoEstacionario.setHerramientasAuxiliares(herramientasAuxiliares);
                    algoritmoEstacionario.evolucion(true);
                    algoritmoEstacionario.mostrarSolucion();
                    endTime = System.currentTimeMillis() - startTime;
                    System.out.println("Ha tardado " + endTime + " ms");
                                     
                   break;
                 case '4':
                    if(matrizDistancias.size() == 0 || matrizFlujos.size() == 0){
                        System.out.println("Los datos no estan cargados aún, ¿Desea cargarlos? (Responde con S o N)");
                        Reader entradaIn=new InputStreamReader(System.in);
                        opcion=(char)entradaIn.read();
                        if (opcion == 'S'){
                            cargaDatos("./archivos/cnf02.dat");
                        } else {
                            System.out.println("Sin datos no podemos lanzar el algoritmo de búsqueda local.");
                            System.out.println("Lo sentimos");
                            break;
                        }
                    }
                    
                    if (semillas.isEmpty()){
                        System.out.println("¿Cuantas semillas desea introducir?");
                        Reader entradaNumeroSemillasR=new InputStreamReader(System.in);
                        opcion=(char)entradaNumeroSemillasR.read();
                        Integer tamanoSemillaESB =  Character.getNumericValue(opcion);;
                        Integer contadorR = 0;
                        while ((int)tamanoSemillaESB > 0){
                            System.out.println("Introduzca la semilla numero " + contadorR);
                            Reader entradaSemillas=new InputStreamReader(System.in);
                            opcion=(char)entradaSemillas.read();
                            semillas.add(Character.getNumericValue(opcion));
                            tamanoSemillaESB--;
                            contadorR++;
                        }
                        
                        if (opcion == 0){
                            System.out.println("Sin semilla no podemos lanzar el algoritmo de Enfriamiento simulado Boltzmann.");
                            System.out.println("Lo sentimos");
                            break;
                        }
                    }
                    
                   System.out.println("Has seleccionado la opción del Algoritmo Genético Estacionario OX");
                   startTime = System.currentTimeMillis();
                    AlgoritmoGeneticoEstacionario algoritmoEstacionarioPMX = new AlgoritmoGeneticoEstacionario();
                    HerramientasAuxiliares herramientasAuxiliaresGE = new HerramientasAuxiliares();
                    herramientasAuxiliaresGE.setMatrizDistancias(matrizDistancias);
                    herramientasAuxiliaresGE.setMatrizFlujos(matrizFlujos);
                    herramientasAuxiliaresGE.setTamano(tamano);
                    herramientasAuxiliaresGE.setNumeroCromosomas(2);
                    herramientasAuxiliaresGE.setEvaluaciones(0);
                    algoritmoEstacionarioPMX.setHerramientasAuxiliares(herramientasAuxiliaresGE);
                    algoritmoEstacionarioPMX.evolucion(false);
                    algoritmoEstacionarioPMX.mostrarSolucion();
                    endTime = System.currentTimeMillis() - startTime;
                    System.out.println("Ha tardado " + endTime + " ms");
                   break;
                case '5':
                    if(matrizDistancias.size() == 0 || matrizFlujos.size() == 0){
                        System.out.println("Los datos no estan cargados aún, ¿Desea cargarlos? (Responde con S o N)");
                        Reader entradaIn=new InputStreamReader(System.in);
                        opcion=(char)entradaIn.read();
                        if (opcion == 'S'){
                            cargaDatos("./archivos/cnf02.dat");
                        } else {
                            System.out.println("Sin datos no podemos lanzar el algoritmo de búsqueda local.");
                            System.out.println("Lo sentimos");
                            break;
                        }
                    }
                    
                    if (semillas.isEmpty()){
                        System.out.println("¿Cuantas semillas desea introducir?");
                        Reader entradaNumeroSemillasR=new InputStreamReader(System.in);
                        opcion=(char)entradaNumeroSemillasR.read();
                        Integer tamanoSemillaESB =  Character.getNumericValue(opcion);;
                        Integer contadorR = 0;
                        while ((int)tamanoSemillaESB > 0){
                            System.out.println("Introduzca la semilla numero " + contadorR);
                            Reader entradaSemillas=new InputStreamReader(System.in);
                            opcion=(char)entradaSemillas.read();
                            semillas.add(Character.getNumericValue(opcion));
                            tamanoSemillaESB--;
                            contadorR++;
                        }
                        
                        if (opcion == 0){
                            System.out.println("Sin semilla no podemos lanzar el algoritmo de Enfriamiento simulado Boltzmann.");
                            System.out.println("Lo sentimos");
                            break;
                        }
                    }
                    
                   System.out.println("Has seleccionado la opción del Algoritmo Genético Generacional OX");
                    startTime = System.currentTimeMillis();
                    AlgoritmoGeneticoGeneracional algoritmoGeneracionalOX = new AlgoritmoGeneticoGeneracional();
                    HerramientasAuxiliares herramientasAuxiliaresGGO = new HerramientasAuxiliares();
                    herramientasAuxiliaresGGO.setMatrizDistancias(matrizDistancias);
                    herramientasAuxiliaresGGO.setMatrizFlujos(matrizFlujos);
                    herramientasAuxiliaresGGO.setTamano(tamano);
                    herramientasAuxiliaresGGO.setNumeroCromosomas(2);
                    herramientasAuxiliaresGGO.setEvaluaciones(1);
                    herramientasAuxiliaresGGO.setProbabilidadCruce(Float.MAX_VALUE);
                    herramientasAuxiliaresGGO.setProbabilidadMutacion(Float.MAX_VALUE);
                    algoritmoGeneracionalOX.setHerramientasAuxiliares(herramientasAuxiliaresGGO);
                    algoritmoGeneracionalOX.evolucion(true);
                    algoritmoGeneracionalOX.mostrarSolucion();
                     endTime = System.currentTimeMillis() - startTime;
                    System.out.println("Ha tardado " + endTime + " ms");
                   
                   break;
                case '6':
                    if(matrizDistancias.size() == 0 || matrizFlujos.size() == 0){
                        System.out.println("Los datos no estan cargados aún, ¿Desea cargarlos? (Responde con S o N)");
                        Reader entradaIn=new InputStreamReader(System.in);
                        opcion=(char)entradaIn.read();
                        if (opcion == 'S'){
                            cargaDatos("./archivos/cnf02.dat");
                        } else {
                            System.out.println("Sin datos no podemos lanzar el algoritmo de búsqueda local.");
                            System.out.println("Lo sentimos");
                            break;
                        }
                    }
                    
                    if (semillas.isEmpty()){
                        System.out.println("¿Cuantas semillas desea introducir?");
                        Reader entradaNumeroSemillasR=new InputStreamReader(System.in);
                        opcion=(char)entradaNumeroSemillasR.read();
                        Integer tamanoSemillaESB =  Character.getNumericValue(opcion);;
                        Integer contadorR = 0;
                        while ((int)tamanoSemillaESB > 0){
                            System.out.println("Introduzca la semilla numero " + contadorR);
                            Reader entradaSemillas=new InputStreamReader(System.in);
                            opcion=(char)entradaSemillas.read();
                            semillas.add(Character.getNumericValue(opcion));
                            tamanoSemillaESB--;
                            contadorR++;
                        }
                        
                        if (opcion == 0){
                            System.out.println("Sin semilla no podemos lanzar el algoritmo de Enfriamiento simulado Boltzmann.");
                            System.out.println("Lo sentimos");
                            break;
                        }
                    }
                    
                    System.out.println("Has seleccionado la opción del Algoritmo Genético Generacional PMX");
                    startTime = System.currentTimeMillis();
                    AlgoritmoGeneticoGeneracional algoritmoGeneracionalPMX = new AlgoritmoGeneticoGeneracional();
                    HerramientasAuxiliares herramientasAuxiliaresGGP = new HerramientasAuxiliares();
                    herramientasAuxiliaresGGP.setMatrizDistancias(matrizDistancias);
                    herramientasAuxiliaresGGP.setMatrizFlujos(matrizFlujos);
                    herramientasAuxiliaresGGP.setTamano(tamano);
                    herramientasAuxiliaresGGP.setNumeroCromosomas(2);
                    herramientasAuxiliaresGGP.setEvaluaciones(0);
                    algoritmoGeneracionalPMX.setHerramientasAuxiliares(herramientasAuxiliaresGGP);
                    algoritmoGeneracionalPMX.evolucion(true);
                    algoritmoGeneracionalPMX.mostrarSolucion();
                     endTime = System.currentTimeMillis() - startTime;
                    System.out.println("Ha tardado " + endTime + " ms");
                   break;
                case '7':
                   System.out.println("Ha decidido salir del programa, muchisimas gracias por usarlo.");
                   System.out.println("Hasta pronto!");
                   break;
                default:
                   System.out.println("Esta opción no esta contemplada.");
                   System.out.println("Elija otra. ¡Gracias!.");
                   break;
           }
               
       }
    }
    
    public static void cargaDatos(String archivo) throws FileNotFoundException, IOException {
      String cadena;
      FileReader f = new FileReader(archivo);
      Boolean primeravez=true;
        try (BufferedReader b = new BufferedReader(f)) {
            while((cadena = b.readLine())!=null) {
                // Cogemos el primer caracter para representar el tamaño de la matriz (AXA)
                if (primeravez) {
                    System.out.println("tamaño de la matriz es:" + cadena + "X" + cadena);
                    tamano = new Integer(cadena);
                    primeravez = false;
                } else {
                    //A partir de hay nos queda coger los numeros de cada matriz y meterlos en una EEDD, una matriz es buena
                    //Ya que conociendo el tamaño podemos separar sus filas y sus columnas
                    if (!cadena.isEmpty()){
                        if (matrizDistancias.isEmpty()){
                             matrizDistancias.add(new ArrayList<>(tamano));
                        }
                        
                        if (matrizFlujos.isEmpty()){
                               matrizFlujos.add(new ArrayList<>(tamano));
                        }
                        
                        if (matrizFlujos.size() <= tamano){
                            String[] cadfila = cadena.split(" ");
                            for (String cadfila1 : cadfila) {
                                Integer num = new Integer(cadfila1);
                                matrizFlujos.get(matrizFlujos.size()-1).add(num);
                            }
                            matrizFlujos.add(new ArrayList<>(tamano));
                            
                        } else {
                            String[] cadfila = cadena.split(" ");
                            for (String cadfila1 : cadfila) {
                                Integer num = new Integer(cadfila1);
                                matrizDistancias.get(matrizDistancias.size()-1).add(num);
                            }
                            matrizDistancias.add(new ArrayList<>(tamano));   
                        }
                    }
                }
            }
        } catch(Exception e) {
            System.out.println("Excepcion leyendo fichero "+ archivo + ": " + e);
        }
        System.out.println("Carga realizada de manera correcta");
        int tamDistancias = matrizDistancias.size() -1;
        int tamFlujos = matrizFlujos.size() -1;
        System.out.println("El tamaño de la matriz de flujos es: " + tamFlujos);
        System.out.println("El tamaño de la matriz de distancias es: " + tamDistancias);
    } 
    
}
