package input;

import algorithms.Algorithm;
import cz.gyarab.util.light.Matrix;
import lib.Animation;

import java.util.Arrays;

public class CliInput {
    public static void handle(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].toLowerCase();
        }

        String action = args[0];


        switch (action) {
            case "animate" -> {
                if (args.length < 2) {
                    System.out.println("Missing algorithm argument");
                    System.exit(1);
                }

                String algorithmArg = args[1];

                Algorithm algorithm = getAlgorithm(algorithmArg);

                if (algorithm == null) {
                    System.out.println("Invalid algorithm: " + algorithmArg);
                    System.exit(1);
                }

                int matrixSize = 50;

                for (String arg : args) {
                    if (arg.matches("--matrix-size=\\d+")) {
                        matrixSize = Integer.parseInt(arg.split("=")[1]);
                    }
                }

                Matrix matrix = Matrix.createMatrix(matrixSize, matrixSize);
                Animation.animate(matrix, algorithm);
                System.exit(0);
            }
            case "info" -> {
                if (args.length < 2) {
                    System.out.println("Missing algorithm argument");
                    System.exit(1);
                }

                String algorithmArg = args[1];

                Algorithm algorithm = getAlgorithm(algorithmArg);

                if (algorithm == null) {
                    System.out.println("Invalid algorithm: " + algorithmArg);
                    System.exit(1);
                }

                System.out.println(algorithm);
                System.exit(0);
            }
            case "help" -> {
                String help = """

                        Actions:
                            animate <algorithm> [options] - animates the given algorithm
                            info <algorithm> - prints information about the given algorithm
                            help - prints this help message
                        Options:
                            --matrix-size=<size> - sets the size of the matrix
                        Algorithms:
                        """;

                for (int i = 0; i < Algorithm.getAlgorithms().length; i++) {
                    Algorithm algorithm = Algorithm.getAlgorithms()[i];
                    help += "   " + (i + 1) + ". " + algorithm.prettyName + "\n";
                }

                help += "To specify an algorithm you can use either the id of the algorithm or its name without spaces (bubblesort, shellsort, ...)";
                System.out.println(help);

                System.exit(0);
            }
            default -> {
                System.out.println("Invalid action: " + action + " use help for help");
                System.exit(1);
            }
        }
    }
    private static Algorithm getAlgorithm(String arg) {
        if (arg.matches("\\d+")) {
            int algorithmIndex = Integer.parseInt(arg);
            return Algorithm.getById(algorithmIndex - 1);

        } else {
            return Algorithm.getByName(arg);
        }
    }
}
