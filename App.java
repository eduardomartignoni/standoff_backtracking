public class App {

    public static void main(String[] args) {
        if (args.length == 3) {

            int n = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);

            Configurador configurador = new Configurador(n, b, c);

            long startTime = System.nanoTime();

            int totalConfiguracoes = configurador.calcularConfiguracoes();

            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / 1_000_000;

            System.out.println("Total de configurações válidas: " + totalConfiguracoes);
            System.out.println("Tempo de execução: " + duration + " ms");
        }
        else {
            System.out.println("Uso correto: java App <n> <b> <c>");
        }
    }
}
