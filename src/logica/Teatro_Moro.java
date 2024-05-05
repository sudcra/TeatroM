/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logica;

import igu.Pantalla;
import javax.swing.JComboBox;




/**
 *
 * @author lgutierrez
 */
public class Teatro_Moro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pantalla panta = new Pantalla();
        panta.setVisible(true);
        panta.setLocationRelativeTo(null);
        JComboBox<String> combobox = panta.getTipoAsiento();
        System.out.println(combobox);
    }
    
}
