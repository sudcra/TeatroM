
package logica;


 


import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.List;
public class Venta {
    int id;
    List<Ticket> tickets;
    LocalDateTime marcaTemporal;
    // Constructor
    public Venta(int id, List<Ticket> tickets) {
        this.id = id;
        this.marcaTemporal  = LocalDateTime.now();
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getMarcaTemporal() {
        return marcaTemporal;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
    
    
    public int getTotalticket() {
        int cantidad = 0;
        for (Ticket ticket : tickets) {
            cantidad += 1;
        }
        return cantidad;
    }
    
    
    public double getTotalMonto() {
        double total = 0;
        for (Ticket ticket : tickets) {
            total += ticket.getMonto();
        }
        return total;
    }
    public  int boleta() {
        double total = 0;
        String mensaje = "Detalle de la venta:" + System.lineSeparator()+ System.lineSeparator();
        for (Ticket ticket : tickets) {
           mensaje = mensaje + " 1 asiento " + ticket.getAsiento() + "; tipo:" + ticket.getTipo()+ "; precio:" + ticket.getValor()+ "; Dcto: " + ticket.getDescuento()  + "% ; Final: $" + ticket.getMonto() + System.lineSeparator();
           total += ticket.getMonto();
        }
         mensaje = mensaje + System.lineSeparator() + System.lineSeparator() + "Total:         $" + total + System.lineSeparator() + System.lineSeparator() + "Esperamos que disfrute la función";
        JOptionPane.showMessageDialog(null, mensaje, "Boleta:", JOptionPane.INFORMATION_MESSAGE); 
        return 10;
    }
    public void setMarcaTemporal(LocalDateTime marcaTemporal) {
        this.marcaTemporal = marcaTemporal;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    
}
