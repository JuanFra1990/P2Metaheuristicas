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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanf
 */
public class P2Metaheuristicas {

     private static Integer tamano;
    
    private static final ArrayList<ArrayList<Integer>> matrizDistancias = new ArrayList<>();
    private static final ArrayList<ArrayList<Integer>> matrizFlujos = new ArrayList<>();
    
    private static ArrayList<Integer> semillas = new ArrayList<>();
    
     @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException {
         LogText.init("Practica2Metaheuristica");
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
                   LogText.LogWriter("Has seleccionado la opción de cargar datos");
                   LogText.LogWriter("\r\n");
                   System.out.println("Has seleccionado la opción de cargar datos");
                   String fichero = seleccionFichero();
                   LogText.LogWriter("Has seleccionado el fichero " + fichero);
                   LogText.LogWriter("\r\n");
                   System.out.println("Has seleccionado el fichero " + fichero);
                   cargaDatos(fichero);
                   break;
                case '2':
                   LogText.LogWriter("Has seleccionado la opción de seleccionar semillas");
                   LogText.LogWriter("\r\n");
                   InputStreamReader isr = new InputStreamReader(System.in);
                   BufferedReader bf = new BufferedReader (isr);
                   Integer semilla;
                   System.out.println("Has seleccionado la opción de seleccionar semillas");
                   System.out.println("¿Cuantas semillas desea introducir?");
                   
                    String lineaTeclado = bf.readLine();
                    semilla = Integer.parseInt(lineaTeclado);
                   
                   Integer tamanoSemilla = semilla;
                   Integer contador = 1;
                   while (tamanoSemilla > 0){
                        System.out.println("Introduzca la semilla numero " + contador);
                        String semillaNueva = bf.readLine();
                        semilla = Integer.parseInt(semillaNueva);
                        semillas.add(semilla);
                        tamanoSemilla--;
                        contador++;
                   }
                   
                   LogText.LogWriter("Las semillas seleccionadas son: ");
                   LogText.LogWriter("\r\n");
                   for (int i = 0; i<semillas.size(); i++){
                       LogText.LogWriter(semillas.get(i).toString());
                       LogText.LogWriter("\r\n");
                   }
                   
                   break;
                case '3':
                    if(matrizDistancias.isEmpty() || matrizFlujos.isEmpty()){
                        System.out.println("Los datos no estan cargados aún, ¿Desea cargarlos? (Responde con S o N)");
                        Reader entradaIn=new InputStreamReader(System.in);
                        opcion=(char)entradaIn.read();
                        if (opcion == 'S'){
                            fichero = seleccionFichero();
                            LogText.LogWriter("Has seleccionado el fichero " + fichero);
                            LogText.LogWriter("\r\n");
                            System.out.println("Has seleccionado el fichero " + fichero);
                            cargaDatos(fichero);
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
                        } else {
                            LogText.LogWriter("Las semillas seleccionadas son: ");
                            LogText.LogWriter("\r\n");
                            for (int i = 0; i<semillas.size(); i++){
                                LogText.LogWriter(semillas.get(i).toString());
                                LogText.LogWriter("\r\n");
                            }
                        }
                    }
                    LogText.LogWriter("Has seleccionado la opción del Algoritmo Genético Estacionario OX");
                    LogText.LogWriter("\r\n");
                    System.out.println("Has seleccionado la opción del Algoritmo Genético Estacionario OX");
                    startTime = System.currentTimeMillis();
                    AlgoritmoGeneticoEstacionario algoritmoEstacionario = new AlgoritmoGeneticoEstacionario();
                    HerramientasAuxiliares herramientasAuxiliares = new HerramientasAuxiliares();
                    herramientasAuxiliares.setMatrizDistancias(matrizDistancias);
                    herramientasAuxiliares.setMatrizFlujos(matrizFlujos);
                    herramientasAuxiliares.setTamano(tamano);
                    herramientasAuxiliares.setNumeroCromosomas(10);
                    herramientasAuxiliares.setEvaluaciones(30);
                    herramientasAuxiliares.setProbabilidadMutacion(Float.POSITIVE_INFINITY);
                    herramientasAuxiliares.setProbabilidadCruce(Float.MAX_VALUE);
                    algoritmoEstacionario.setHerramientasAuxiliares(herramientasAuxiliares);
                    algoritmoEstacionario.evolucion(true);
                    algoritmoEstacionario.mostrarSolucion();
                    endTime = System.currentTimeMillis() - startTime;
                    LogText.LogWriter("Ha tardado " + endTime + " ms");
                    LogText.LogWriter("\r\n");
                    System.out.println("Ha tardado " + endTime + " ms");
                                     
                   break;
                 case '4':
                    if(matrizDistancias.isEmpty() || matrizFlujos.isEmpty()){
                        System.out.println("Los datos no estan cargados aún, ¿Desea cargarlos? (Responde con S o N)");
                        Reader entradaIn=new InputStreamReader(System.in);
                        opcion=(char)entradaIn.read();
                        if (opcion == 'S'){
                             fichero = seleccionFichero();
                            LogText.LogWriter("Has seleccionado el fichero " + fichero);
                            LogText.LogWriter("\r\n");
                            System.out.println("Has seleccionado el fichero " + fichero);
                            cargaDatos(fichero);
                        } else {
                            System.out.println("Sin datos no podemos lanzar el algoritmo genetico estacionario OX");
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
                        } else {
                            LogText.LogWriter("Las semillas seleccionadas son: ");
                            LogText.LogWriter("\r\n");
                            for (int i = 0; i<semillas.size(); i++){
                                LogText.LogWriter(semillas.get(i).toString());
                                LogText.LogWriter("\r\n");
                            }
                        }
                    }
                    
                    LogText.LogWriter("Has seleccionado la opción del Algoritmo Genético Estacionario PMX");
                    LogText.LogWriter("\r\n");
                    System.out.println("Has seleccionado la opción del Algoritmo Genético Estacionario PMX");
                    startTime = System.currentTimeMillis();
                    AlgoritmoGeneticoEstacionario algoritmoEstacionarioPMX = new AlgoritmoGeneticoEstacionario();
                    HerramientasAuxiliares herramientasAuxiliaresGE = new HerramientasAuxiliares();
                    herramientasAuxiliaresGE.setMatrizDistancias(matrizDistancias);
                    herramientasAuxiliaresGE.setMatrizFlujos(matrizFlujos);
                    herramientasAuxiliaresGE.setTamano(tamano);
                    herramientasAuxiliaresGE.setNumeroCromosomas(10);
                    herramientasAuxiliaresGE.setEvaluaciones(30);
                    herramientasAuxiliaresGE.setProbabilidadMutacion(Float.POSITIVE_INFINITY);
                    herramientasAuxiliaresGE.setProbabilidadCruce(Float.MAX_VALUE);
                    algoritmoEstacionarioPMX.setHerramientasAuxiliares(herramientasAuxiliaresGE);
                    algoritmoEstacionarioPMX.evolucion(false);
                    algoritmoEstacionarioPMX.mostrarSolucion();
                    endTime = System.currentTimeMillis() - startTime;
                    LogText.LogWriter("Ha tardado " + endTime + " ms");
                    LogText.LogWriter("\r\n");
                    System.out.println("Ha tardado " + endTime + " ms");
                   break;
                case '5':
                    if(matrizDistancias.isEmpty() || matrizFlujos.isEmpty()){
                        System.out.println("Los datos no estan cargados aún, ¿Desea cargarlos? (Responde con S o N)");
                        Reader entradaIn=new InputStreamReader(System.in);
                        opcion=(char)entradaIn.read();
                        if (opcion == 'S'){
                             fichero = seleccionFichero();
                            LogText.LogWriter("Has seleccionado el fichero " + fichero);
                            LogText.LogWriter("\r\n");
                            System.out.println("Has seleccionado el fichero " + fichero);
                            cargaDatos(fichero);
                        } else {
                            System.out.println("Sin datos no podemos lanzar el algoritmo genetico estacionario OX");
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
                        } else {
                            LogText.LogWriter("Las semillas seleccionadas son: ");
                            LogText.LogWriter("\r\n");
                            for (int i = 0; i<semillas.size(); i++){
                                LogText.LogWriter(semillas.get(i).toString());
                                LogText.LogWriter("\r\n");
                            }
                        }
                    }
                    
                   System.out.println("Has seleccionado la opción del Algoritmo Genético Generacional OX");
                    startTime = System.currentTimeMillis();
                    AlgoritmoGeneticoGeneracional algoritmoGeneracionalOX = new AlgoritmoGeneticoGeneracional();
                    HerramientasAuxiliares herramientasAuxiliaresGGO = new HerramientasAuxiliares();
                    herramientasAuxiliaresGGO.setMatrizDistancias(matrizDistancias);
                    herramientasAuxiliaresGGO.setMatrizFlujos(matrizFlujos);
                    herramientasAuxiliaresGGO.setTamano(tamano);
                    herramientasAuxiliaresGGO.setNumeroCromosomas(10);
                    herramientasAuxiliaresGGO.setEvaluaciones(30);
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
                             fichero = seleccionFichero();
                            LogText.LogWriter("Has seleccionado el fichero " + fichero);
                            LogText.LogWriter("\r\n");
                            System.out.println("Has seleccionado el fichero " + fichero);
                            cargaDatos(fichero);
                        }  else {
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
                        } else {
                            LogText.LogWriter("Las semillas seleccionadas son: ");
                            LogText.LogWriter("\r\n");
                            for (int i = 0; i<semillas.size(); i++){
                                LogText.LogWriter(semillas.get(i).toString());
                                LogText.LogWriter("\r\n");
                            }
                        }
                    }
                    
                    LogText.LogWriter("Has seleccionado la opción del Algoritmo Genético Generacional PMX");
                    LogText.LogWriter("\r\n");
                    System.out.println("Has seleccionado la opción del Algoritmo Genético Generacional PMX");
                    startTime = System.currentTimeMillis();
                    AlgoritmoGeneticoGeneracional algoritmoGeneracionalPMX = new AlgoritmoGeneticoGeneracional();
                    HerramientasAuxiliares herramientasAuxiliaresGGP = new HerramientasAuxiliares();
                    herramientasAuxiliaresGGP.setMatrizDistancias(matrizDistancias);
                    herramientasAuxiliaresGGP.setMatrizFlujos(matrizFlujos);
                    herramientasAuxiliaresGGP.setTamano(tamano);
                    herramientasAuxiliaresGGP.setNumeroCromosomas(10);
                    herramientasAuxiliaresGGP.setEvaluaciones(30);
                    herramientasAuxiliaresGGP.setProbabilidadMutacion(Float.POSITIVE_INFINITY);
                    herramientasAuxiliaresGGP.setProbabilidadCruce(Float.MAX_VALUE);
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
    
    public static String seleccionFichero(){
        System.out.println("¿Que fichero desea seleccionar? (Seleccione un numero del 1 - 10)");
        String ruta = "";
        int opcion = -1;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader (isr);
        try {
             while (opcion < 0 || opcion >10){
                String lineaTeclado = bf.readLine();
                opcion = Integer.parseInt(lineaTeclado);
                System.out.println(opcion);
                if (opcion > 0 && opcion < 4 || opcion > 5 && opcion < 10){
                   ruta = "./archivos/cnf0" + opcion + ".dat";
                } else if (opcion >= 4 && opcion <= 5){
                   ruta = "./archivos/cnf0" + opcion + "dat.sec";
                } else if(opcion == 10){
                     ruta = "./archivos/cnf" + opcion + ".dat";
                } else {
                    ruta = "No es posible leer esta opcion, seleccione un numero valido";
                }
             }   
         } catch (IOException ex) {
             Logger.getLogger(P2Metaheuristicas.class.getName()).log(Level.SEVERE, null, ex);
         }
        return ruta;
    }
    
}
