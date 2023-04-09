

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Equipo
{
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

    private String rutaOptimaVolante( char [][] matrizVolante, int i, int j, int x, int y, ){
        String ruta = "";
        if((matrizVolante[y][x] != '#') && (x < matrizVolante[0].length) && (y < matrizVolante.length)  && (x >= 0) && (y >= 0)){
             return ruta = rutaOptimaVolante(matrizVolante, i, j, x+1, y);
        }else if(j==0){
            ruta = rutaOptimaVolante(matrizVolante, i, j+1, x, y+1);

        }else if(j==1){
            ruta = rutaOptimaVolante(matrizVolante, i, j+1, x, y-2);
        }else if(j==2){
            ruta = rutaOptimaVolante(matrizVolante, i, 0, x-1, y+1);
        }

        return ruta;

    }
}
