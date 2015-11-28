import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

public class Monitor extends Thread implements Serializable{
	
	 
	public Monitor(BomberMap mapa){
		System.out.println("Que hay en la 5"+mapa.grid[5][5]);
		//lectura();	
	}
	

}