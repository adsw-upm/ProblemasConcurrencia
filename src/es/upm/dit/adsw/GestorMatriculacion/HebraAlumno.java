package es.upm.dit.adsw.GestorMatriculacion;

/**
 * aalonso 
 * @since 13/6/2017
 */


import java.util.Random;

public class HebraAlumno extends Thread{
    private int retardoAleatorio = 10;
    private Gestor gestor;
    private Random random;
    private int curso;
    private int id;
    private boolean matricula;

    public HebraAlumno (Gestor gestor, int curso, int id) {
        this.gestor = gestor;
        this.curso  = curso;
        this.id     = id;
        random      = new Random(id);
    }

    public void run() {

        int calificacion = random.nextInt(retardoAleatorio);

        try {
            Thread.sleep(random.nextInt(retardoAleatorio)*1000);
            matricula = gestor.solictarMatricula(calificacion, curso);
            System.out.println("Alumno " + id + " calificacion: " + calificacion + " matricula: " + matricula);
        } catch (InterruptedException e) {
            System.out.println("Unexpected exception");
        }
    }
}
