public class Main {
    public static void main(String[] args) throws Exception {

        Equipo ferrari = new Equipo();
        String desifrado = ferrari.desifrarPlan("HOy es el " +
                "día del Peatón, solo quiero salir a comer unat empanadas " +
                "y pasear con mis 5 mejores amigos");
        System.out.println(desifrado);
    }
}
