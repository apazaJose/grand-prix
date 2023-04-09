
import java.util.*;
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

}
