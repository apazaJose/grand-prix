public class Main {
    public static void main(String[] args) throws Exception {

        Equipo ferrari = new Equipo();
        String desifrado = ferrari.desifrarPlan("HOy es el " +
                "día del Peatón, solo quiero salir a comer unat empanadas " +
                "y pasear con mis 5 mejores amigos");
        System.out.println(desifrado);

        char[][] matrizVolante = {
                "####################".toCharArray(),
                "000####0000000000000".toCharArray(),
                "##000000000###00000#".toCharArray(),
                "000000000000000###00".toCharArray(),
                "##00###0#####0##00##".toCharArray(),
                "########0000000#####".toCharArray()
        };
        String rutaOptima = ferrari.rutaOptimaVolante(matrizVolante);
        System.out.println(rutaOptima);
    }
}
