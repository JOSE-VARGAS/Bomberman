import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class MiRobot extends Thread implements Serializable{
	
	Robot robot;
	private ArrayList<String> explora = new ArrayList();
	
	
	public MiRobot(){
		try{
			robot = new Robot();
			}catch (AWTException e){}


		//lectura();	
	}
	public void baja(Robot robot){
			int evt = KeyEvent.VK_DOWN;
			robot.keyPress(evt);
			robot.keyRelease(evt);
			//evt = KeyEvent.VK_UP;
			//this.keyPressed(evt);
			//this.keyReleased(evt);
	}
	public void sube(Robot robot){
		int evt = KeyEvent.VK_UP;
		robot.keyPress(evt);
		robot.keyRelease(evt);
	}

	public void run(){
		int pausa = 1;
		System.out.println("Me llamo");
		
		do{
			try{
				
			baja(robot);
			baja(robot);
			baja(robot);
			sleep(pausa*100);
			sube(robot);
			System.out.println("Hola");
			}catch (InterruptedException e){

			;

			}	

			
		}while (true);

			}




	public void saluda(){
		
		System.out.println(explora);
		//escrive();
	}
	public void escrive(){
		try{
		ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream("media.obj"));
      	salida.writeObject(explora);
      	salida.close();
      	}catch(Exception e){}
	}

	public void lectura(){
		try{
	  ObjectInputStream entrada=new ObjectInputStream(new FileInputStream("media.obj"));
      explora = (ArrayList)entrada.readObject();
      entrada.close();
      }catch(Exception e){}
	}

}