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

public class MiRobot extends Thread implements Serializable{
	
	Robot robot;
	private ArrayList<String> explora = new ArrayList();
	int [] keys;
	final int UP = 0;
	final int RI = 1;
	final int DO = 2;
	final int LE = 3;
	final int BO = 4;
	 
	public MiRobot(int jugador){
		try{
			robot = new Robot();
			}catch (AWTException e){}
		keys = cargaTeclas(jugador);

		//lectura();	
	}
	public void mover(int dir){

		robot.keyPress(dir);
		robot.keyRelease(dir);
	}
	
	public void run(){
		int pausa = 1;
		
		Random rnd = new Random();
		do{
			try{
				int i = rnd.nextInt(5);
				
			mover(keys[i]);
			sleep(pausa);
			}catch (InterruptedException e){

		
			}	
		}while (true);

		}




	public int[] cargaTeclas(int jugador){
		int [] keys = new int[5];
		switch(jugador){
		case 1:
		/** player 1 default key configurations */
        keys[0] = KeyEvent.VK_UP;
        keys[1] = KeyEvent.VK_RIGHT;
        keys[2] = KeyEvent.VK_DOWN;
        keys[3] = KeyEvent.VK_LEFT;
        keys[4] = KeyEvent.VK_NUMPAD0;
        break;
        case 2: 

        /** player 2 default key configurations */
        keys[0] = KeyEvent.VK_W;
        keys[1] = KeyEvent.VK_D;
        keys[2] = KeyEvent.VK_S;
        keys[3] = KeyEvent.VK_A;
        keys[4] = KeyEvent.VK_SPACE;
		break;
		case 3:
        /** player 3 default key configurations */
        keys[0] = KeyEvent.VK_I;
        keys[1] = KeyEvent.VK_L;
        keys[2] = KeyEvent.VK_K;
        keys[3] = KeyEvent.VK_J;
        keys[4] = KeyEvent.VK_BACK_SLASH;
		break;
		case 4:
        /** player 4 default key configurations */
        keys[0] = KeyEvent.VK_NUMPAD8;
        keys[1] = KeyEvent.VK_NUMPAD6;
        keys[2] = KeyEvent.VK_NUMPAD5;
        keys[3] = KeyEvent.VK_NUMPAD4;
        keys[4] = KeyEvent.VK_NUMPAD9;
    	break;
   	 }
   	 return keys;
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