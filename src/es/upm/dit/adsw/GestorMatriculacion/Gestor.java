package es.upm.dit.adsw.GestorMatriculacion;

/**
 * aalonso 
 * @since 13/6/17
 */
public class Gestor {

    private int     nPlazas         = 0;
    private int     curso           = 0;
    private int     nMatriculas     = 0;
    private int     minCalificacion = 0;
    private boolean hayCurso        = false;
    private boolean finCurso        = false;
    private boolean calcular        = false;
    private int     nCuentas        = 0;

    public synchronized boolean inicioMatricula (int curso, int nPlazas) {

        if (hayCurso) return false;

        hayCurso        = true;
        this.curso      = curso;
        this.nPlazas    = nPlazas;
        nMatriculas     = 0;
        minCalificacion = Integer.MAX_VALUE;
        calcular        = false;

        return true;
    }

    public synchronized boolean finMatricula (int curso) {

        if (this.curso == curso && hayCurso) {
            hayCurso = false;
            notifyAll();
            return true;
        }

        return false;
    }

    public synchronized boolean solictarMatricula(int calificacion, int curso) throws InterruptedException{

        if (!hayCurso || this.curso != curso) return false;

        if (calificacion < minCalificacion) return false;

        if (nPlazas > nMatriculas) nMatriculas ++;

        while (hayCurso) {
            this.calcular = true;
            this.minCalificacion = Integer.MAX_VALUE;
            this.nCuentas = 0;
            notifyAll();

            while (!calcular) {
                wait();
            }

            if (!hayCurso) return true;

            if (minCalificacion > calificacion) {
                minCalificacion = calificacion;
            }

            nCuentas ++;

            if (nCuentas > nMatriculas) {
                notifyAll();
                System.out.println(nMatriculas);
                calcular = false;

                if (nPlazas == nMatriculas)

                while (calcular) {
                    wait();
                }

                if (calificacion < minCalificacion) return false;
            }
        }

        return true;
    }

}
