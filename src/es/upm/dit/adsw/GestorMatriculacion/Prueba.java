package es.upm.dit.adsw.GestorMatriculacion;

/**
 * aalonso 
 * @since 13/6/2017
 */


/**
 * Created by aalonso on 13/6/17.
 */
public class Prueba {

    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        int nVeces    = 10;
        int nPlazas   =  5;
        int curso     =  5;
        Thread alumno;

        gestor.inicioMatricula(curso, nPlazas);
        for (int i = 0; i < nVeces; i++) {
            alumno = new HebraAlumno(gestor, curso, i);
            alumno.start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
        gestor.finMatricula(curso);

    }
}
