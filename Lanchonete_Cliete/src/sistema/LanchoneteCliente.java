
package sistema;

/**
 *
 * @author Eduardo
 */
public class LanchoneteCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            Cardapio cardapio = new Cardapio();
            cardapio.setLocationRelativeTo(null); // Opcional: Centraliza a tela
            cardapio.setVisible(true);
        }
       });
   }
}
