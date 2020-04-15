package es.upm.dit.adsw.DespegueAviones;

/**
 * Created by aalonso on 29/3/17.
 */

public class Temporizador {

    private GestorDespegue gestor;

    public Temporizador(GestorDespegue gestor) {
        this.gestor = gestor;
    }

    public void iniciarTemporizador(int minutos) {

        // Se simula a esperar el numero de segundos del parametro
        new Thread () {
            public void run()  {
                try {
                    Thread.sleep(minutos*1000);
                    gestor.finTemporizador();
                } catch ( InterruptedException e) {
                    return;
                }
            }
        }.start();

    }
}
