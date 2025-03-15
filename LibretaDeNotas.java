import java.util.*;
public class LibretaDeNotas {
    private Map<String, List<Integer>> calificaciones;
    private int cantidadNotas;

    public LibretaDeNotas(int cantidadNotas) {
        this.calificaciones = new HashMap<>();
        this.cantidadNotas = cantidadNotas;
    }

    public void agregarEstudiante(String nombre, List<Integer> notas) {
        if (notas.size() == cantidadNotas) {
            calificaciones.put(nombre, notas);
        } else {
            System.out.println("Número incorrecto de notas para " + nombre);
        }
    }

    public void mostrarPromedios() {
        for (String estudiante : calificaciones.keySet()) {
            List<Integer> notas = calificaciones.get(estudiante);
            double promedio = notas.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            System.out.println(estudiante + " - Promedio: " + promedio);
        }
    }

    public void evaluarNota(String estudiante, int nota) {
        if (nota >= 60) {
            System.out.println(estudiante + " ha aprobado con " + nota);
        } else {
            System.out.println(estudiante + " ha reprobado con " + nota);
        }
    }

    public void compararConPromedioCurso(String estudiante, int nota) {
        double promedioCurso = calificaciones.values().stream()
                .flatMapToInt(l -> l.stream().mapToInt(Integer::intValue))
                .average().orElse(0.0);

        if (nota >= promedioCurso) {
            System.out.println(estudiante + " tiene una nota por encima o igual al promedio del curso.");
        } else {
            System.out.println(estudiante + " tiene una nota por debajo del promedio del curso.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();
        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int cantidadNotas = scanner.nextInt();
        scanner.nextLine();

        LibretaDeNotas libreta = new LibretaDeNotas(cantidadNotas);

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre del estudiante: ");
            String nombre = scanner.nextLine();
            List<Integer> notas = new ArrayList<>();
            System.out.println("Ingrese las " + cantidadNotas + " notas:");
            for (int j = 0; j < cantidadNotas; j++) {
                notas.add(scanner.nextInt());
            }
            scanner.nextLine();
            libreta.agregarEstudiante(nombre, notas);
        }

        int opcion;
        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Mostrar promedio de notas por estudiante");
            System.out.println("2. Evaluar si una nota es aprobatoria o reprobatoria");
            System.out.println("3. Comparar una nota con el promedio del curso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    libreta.mostrarPromedios();
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String estudiante = scanner.nextLine();
                    System.out.print("Ingrese la nota a evaluar: ");
                    int nota = scanner.nextInt();
                    scanner.nextLine();
                    libreta.evaluarNota(estudiante, nota);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    estudiante = scanner.nextLine();
                    System.out.print("Ingrese la nota a comparar: ");
                    nota = scanner.nextInt();
                    scanner.nextLine();
                    libreta.compararConPromedioCurso(estudiante, nota);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}

