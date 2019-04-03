
package aprendiendojava14;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AprendiendoJava14 implements Runnable {
    // DECLARACION DE CONDICIONES INICIALES
    JFrame frame;                                                               // Creacion de un marco sobre el que trabajar
    Canvas canvas;                                                              // Creacion de un canvas
    BufferStrategy bufferStrategy;
    private BufferedImage imagenmanzana;
    int posx = 256;
    int posy = 256;
    
    // METODO CONSTRUCTOR, QUE COGE LAS CONDICIONES INICIALES Y LES DA VALORES
    public AprendiendoJava14(){

        try {
      
            imagenmanzana=ImageIO.read(new File("C://imagenes//manzana.png"));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        frame = new JFrame("Manzanajuego");                                   // Creamos una ventana

        JPanel panel = (JPanel) frame.getContentPane();                         // Creamos un nuevo panel dentro del marco
        panel.setPreferredSize(new Dimension(512, 512));                        // Introducimos propiedades del panel
        panel.setLayout(null);                                                  // Inicializamos el layout
        panel.setFocusable(true);                                               // Permitimos que podamos hacer focus
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                   // Cerrar el proceso al salir del programa
        frame.pack();                                                           // Empaquetamos
        frame.setResizable(true);                                              // No permitimos el reescalado
        frame.setVisible(true);                                                 // Hacemos que sea visible
        
       //Crear Canvas
        canvas = new Canvas();                                                  // Creamos un nuevo canvas
        canvas.setBounds(0, 0, 512, 512);                                  // Especificamos las proporciones del canvas
        canvas.setIgnoreRepaint(true);                                        // Ignoramos la repintura
        panel.add(canvas);                                                      // Añadimos el canvas al panel
        
        canvas.createBufferStrategy(2);                                         // Creamos una estrategia de buffer
        bufferStrategy = canvas.getBufferStrategy();                            // Asignamos al canvas
        canvas.requestFocus();        

    }
    // RALMENTE RECORDAMOS QUE EN JAVA EL QUE REALMENTE EJECUTA LAS COSAS ES EL MAIN
    public static void main(String[] args) {
        AprendiendoJava14 ex = new AprendiendoJava14();                                 // Instancia del metodo principal
        // EN JAVA, SI QUIERES QUE ALGO SE REPITA CONTINUAMENTE, ES MUY RECOMENDABLE QUE USES LA LIBRERÍA THREAD
        new Thread(ex).start();                                                 // Arranque de una uneva tarea   
    }
    @Override
    public void run() {
        while(true){                                                         // Mientras sea cierto que se esta ejecutando
            // Estos son los comandos para actualizar cosas del juego
             posx += (Math.random()-0.4)*5;
             posy += (Math.random()-0.4)*5;
             // Estos son los comandos para dibujar cosas
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();           // Crea un nuevo grafico 2D
            g.clearRect(0, 0, 512, 512);
           g.drawImage(imagenmanzana, posx,posy, null);
            g.dispose();                                                            // Vacia la memoria
        bufferStrategy.show();                                                  // Muestra el buffer                                                        // Llamada el metodo de render         
            try {
                Thread.sleep(3);   // Para la ejecicion (como el SetTimeout)
            } catch (InterruptedException ex) {
                Logger.getLogger(AprendiendoJava14.class.getName()).log(Level.SEVERE, null, ex);
            }   
        } 
    }
}
