

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Equipo
{
    private boolean caminoCont = false;
    private ArrayList<Neumatico>neumaticos;
    
    public Equipo(){
        neumaticos=new ArrayList<Neumatico>();
        
    }
    public ArrayList<Neumatico> mejorEstratejia(ArrayList<Neumatico> lista){
    neumaticos=lista;
    return neumaticos;
    }
    
    public String desifrarPlan(String mensajeCodificado){
       String desifrado;
       String mensaje=mensajeCodificado.toUpperCase();
        int pos=desifrar(mensaje,0,"PLAN",0);
       if(pos>0){
          desifrado= "PLAN "+ String.valueOf(mensaje.charAt(pos+1))+desifrarNumero(mensaje,pos+3);
        }
        else{
          desifrado="";
        }
       return desifrado;
    }
    
    private String desifrarNumero(String mensaje,int pos){
        String numero="";
        if(pos<mensaje.length()){
           char caracter =mensaje.charAt(pos);
           int valorASCII = (int) caracter;
           if(valorASCII>=49 && valorASCII<=57){
                 numero=" + "+String.valueOf(caracter);;
            }
            else{
                numero=desifrarNumero(mensaje,pos+1);
            }
           
        }
        return numero;
    }
    
    private int desifrar(String mensaje,int posm,String plan,int posp){
     int pos=0;      
     if(posm<mensaje.length()){
        if(posp < 4){
            char letram=mensaje.charAt(posm);
             char letrap=plan.charAt(posp);
             if(letram==letrap){
                 pos=desifrar(mensaje,posm+1,plan,posp+1);
              }
              else{
                  pos=desifrar(mensaje,posm+1,plan,posp);
              }
        }
        else{
            pos=posm;
        }
    }
    return pos;
    }

    //@Autor: JARO-Hub
    //@Fecha: 2021-05-10;
    // @Descripcion: Metodo que retorna la ruta optima para el volante.
    /*
         #: Espacio ocupado.
         0: Espacio libre.
         ####################
         000####0000000000000
         ##000000000###00000#
         000000000000000###00
         ##00###0#####0##00##
         ########0000000#####
     */
/*
    public String rutaOptimaVolante( char [][] matrizVolante){
        int i=0 ,j=0;
        int x=0 ,y=0;
        if((matrizVolante[0].length & matrizVolante.length) > 0){
            String rutaOptima = "";
            ArrayList<String> ruta = new ArrayList<>();
            ArrayList<Integer> size = new ArrayList<>();
            for(int cont =0; cont< matrizVolante.length; cont++){
                if(matrizVolante[i][j] != '#'){
                    rutaOptima =rutaOptimaVolante(matrizVolante, i, j, x, cont);
                    if(rutaOptima!=null){
                        ruta.add(rutaOptima);
                    }
                }
                i++;
            }
            if(ruta.size() == 0){
                return "No es posible encontrar un camino optimo";
            }else{
                for(int cont =0; cont<ruta.size(); cont++){
                    size.add(ruta.get(cont).length());
                }
                int minimo = Collections.min(size);
                for(int cont =0; cont<ruta.size(); cont++){
                    if(ruta.get(cont).length() == minimo) {
                        return ruta.get(cont);
                    }
                }

            }


        }

        return "No es posible encontrar un camino optimo";
    }
*/
    public String rutaOptimaVolante(char[][] matrizVolante) {
        if (matrizVolante.length == 0 || matrizVolante[0].length == 0) {
            return "No es posible encontrar un camino optimo";
        }

        List<List<String>> rutas = new ArrayList<>();

        for (int i = 0; i < matrizVolante.length; i++) {
            if (matrizVolante[i][0] != '#') {
                List<String> rutaOptima = rutaOpt(matrizVolante, 0, i,
                        new ArrayList<>(), new boolean[matrizVolante.length][matrizVolante[0].length]);
                if (rutaOptima != null) {
                    rutas.add(rutaOptima);
                }
            }
        }

        if (rutas.isEmpty()) {
            return "No es posible encontrar un camino optimo";
        } else {
            // Inicializa el menor valor con el primer elemento de la primera lista.
            int menor = rutas.get(0).size();
            List<String> listaMenor = rutas.get(0);
            // Itera sobre la lista de listas y sus elementos para encontrar el menor valor.
            for (List<String> lista : rutas) {
                if(lista.size()< menor){
                    menor = lista.size();
                    listaMenor =lista;
                }
            }
            return listaMenor.toString();
        }
    }


    private  List<String> ruta(char [][] matrizVolante, int x, int y, boolean min, boolean auxmin,
                         List<String> camino, List<String> caminoAlt, boolean [][] mVisitados){
        List<String> caminoOrg;
        if(caminoCont){
            //Iterar los caminos alternos
            if(x<0){
                return null;
            }else if (x==matrizVolante[0].length){
                if(caminoAlt.size() <= camino.size()){
                    camino = caminoAlt;
                    return camino;
                }else{
                    return camino;
                }
            }else if(!(y<0 || y>= matrizVolante.length) && !esMovNoValido(matrizVolante, x, y, mVisitados)){
                mVisitados[y][x] = true;
                caminoAlt.add("("+y+","+x+")");
                caminoOrg = ruta(matrizVolante, x+1, y, false, false, camino, caminoAlt, mVisitados);
                caminoOrg = ruta(matrizVolante, x, y+1, false, false, camino, caminoAlt, mVisitados);
                caminoOrg = ruta(matrizVolante, x, y-1, false, false, camino, caminoAlt, mVisitados);
                caminoOrg = ruta(matrizVolante, x-1, y, true, true, camino, caminoAlt, mVisitados);

            } else if (auxmin) {
                caminoAlt.remove(caminoAlt.size()-1);
                return caminoAlt;
            }

            return caminoAlt;
        }else{
            //Tenemos que encontrar el camino original para iterar con alternos
            if (x < 0) {
                return null;
            } else if (x==matrizVolante[0].length) {
                //Terminamos el primer camino
                caminoCont=true;
                return camino;
            } else if (!esMovNoValido(matrizVolante,x,y,mVisitados)) {
                mVisitados[y][x] = true;
                camino.add("("+y+","+x+")");  // (0,0)
                caminoAlt=camino;

                caminoOrg = ruta(matrizVolante, x+1, y, false, false, camino, caminoAlt, mVisitados);
                caminoOrg = ruta(matrizVolante, x, y+1, false, false, camino, caminoAlt, mVisitados);
                caminoOrg = ruta(matrizVolante, x, y-1, false, false, camino, caminoAlt, mVisitados);
                caminoOrg = ruta(matrizVolante, x-1, y, true, true, camino, caminoAlt, mVisitados);

            }else if (min){
                camino.remove(camino.size()-1);
                return camino;
            }
            return camino;
        }



    }

    private boolean esMovNoValido(char[][] matrizVolante, int x, int y, boolean[][] mVisitados) {
        return   y < 0 || y >= matrizVolante.length || matrizVolante[y][x] == '#' || mVisitados[y][x];
    }

    private List<String> rutaOpt(char[][] matrizVolante, int x, int y, List<String> camino, boolean[][] mVisitados) {
        if (x < 0 || y < 0 || y >= matrizVolante.length || x >= matrizVolante[0].length || matrizVolante[y][x] == '#' || mVisitados[y][x]) {
            return null;
        }

        if (x == matrizVolante[0].length - 1) {
            camino.add("(" + y + "," + x + ")");
            return camino;
        }

        mVisitados[y][x] = true;
        camino.add("(" + y + "," + x + ")");

        List<String> copiaCamino = new ArrayList<>(camino);

        // Explorar las 4 direcciones posibles
        List<String> rutaDerecha = rutaOpt(matrizVolante, x + 1, y, copiaCamino, mVisitados);
        List<String> rutaAbajo = rutaOpt(matrizVolante, x, y + 1, copiaCamino, mVisitados);
        List<String> rutaArriba = rutaOpt(matrizVolante, x, y - 1, copiaCamino, mVisitados);

        mVisitados[y][x] = false;

        // Seleccionar la ruta más corta
        List<String> rutaCorta = rutaDerecha;
        if (rutaCorta == null || (rutaAbajo != null && rutaAbajo.size() < rutaCorta.size())) {
            rutaCorta = rutaAbajo;
        }
        if (rutaCorta == null || (rutaArriba != null && rutaArriba.size() < rutaCorta.size())) {
            rutaCorta = rutaArriba;
        }

        return rutaCorta;
    }
}
