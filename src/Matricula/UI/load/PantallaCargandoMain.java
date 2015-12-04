
package Matricula.UI.load;

import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class PantallaCargandoMain {

  PantallaCargando screen;

  public PantallaCargandoMain() {
    inicioPantalla();
	screen.velocidadDeCarga();
  }

  private void inicioPantalla() {
//    ImageIcon myImage = new ImageIcon(new ImageIcon(getClass().getResource("Resourses/univalle500x697.jpg")).getImage());
    ImageIcon myImage = new ImageIcon("univalle500x697.gif");
    screen = new PantallaCargando(myImage);
    screen.setLocationRelativeTo(null);
    screen.setProgresoMax(100);
    screen.setVisible(true);
  }

//  public static void main(String[] args)
//  {
//    new PantallaCargandoMain();
//  }
}