package es.upm.dit.adsw.DespegueAviones;

/**
 * Created by aalonso on 29/3/17.
 */
public class PruebaDespegueAvion {

    public static void main(String[] args) {

        int nAviones    = 10;
        int nAvionesVip =  5;
        int retardo     =  2;
        int retardoVip  =  4;
        GestorDespegue gestor = new GestorDespegue();

        for (int i = 0; i < nAviones; i++) {
            Avion avion = new Avion(gestor, i *  retardo, i);
            avion.start();
        }

        for (int i = 0; i < nAvionesVip; i++) {
            AvionVip avionVip = new AvionVip(gestor, i * retardoVip, 1000 + i);
            avionVip.start();
        }


    }

}
