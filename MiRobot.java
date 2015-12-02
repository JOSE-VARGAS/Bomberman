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
	int jugador;
	Monitor monitor;
	BomberPlayer player;
	ArrayList<Integer> secuencia;
	int estado; //0 :explora,1 :Huye, 2: Sembrador, 3:Ataca, 4:Atrapa_Bonus

	public MiRobot(int jugador,Monitor monitor,BomberPlayer player){
		try{
			robot = new Robot();
		}catch (AWTException e){}
			keys = cargaTeclas(jugador);
			this.jugador = jugador;
			this.monitor = monitor;
			this.player = player;
			secuencia = new ArrayList();
			estado = 0; //Explora

		//lectura();	
	}

	public void mover(int dir){

		robot.keyPress(dir);
		robot.keyRelease(dir);
	}
	
	/*
	Casillas 
	-1 = espacio libre
	0 = Hay pared
	1 = ladrillo
	3 = Bomba
	*/

	public void run(){
		int pausa = 1;
		int i = 0;
		Random rnd = new Random();
		do{
			try{
			i = rnd.nextInt(4);
			movEnDir(i);
			//mover(keys[1]);
			System.out.println("Aqui esta mi bomberman "+player.getX()+" "+player.getY());
			
			sleep(pausa);
			}catch (InterruptedException e){}	
		}while (true);
	}
	
	/*
	Se mueve en una direccion hasta encontrar una pared
	*/

	public void movEnDir(int dir){
		int x,y,res;
		do{
			x = getX();
			y = getY();
			res = monitor.hayPaso(x,y,dir);
			System.out.println("M : "+res+" D : "+dir+" X : "+x+" Y : "+y);
			if(res==-1)
				mover(keys[dir]);
		}while(res==-1); //!=-2
	}


	/*
	Estas Subrutinas estan pensadas en explorar el mapa
	*/
	

	//------------------------------------------------------------------------------------

	public int getX(){
		return player.getX()/16; //Es por los pixeles 16x16
	}
	public int getY(){
		return player.getY()/16;
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
        keys[4] = KeyEvent.VK_Q; //KeyEvent.VK_NUMPAD0;
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
        keys[0] = KeyEvent.VK_F;//KeyEvent.VK_NUMPAD8;
        keys[1] = KeyEvent.VK_C;//KeyEvent.VK_NUMPAD5;
        keys[2] = KeyEvent.VK_E;//KeyEvent.VK_NUMPAD4;
        keys[3] = KeyEvent.VK_F;//KeyEvent.VK_NUMPAD6;
        keys[4] = KeyEvent.VK_G;//KeyEvent.VK_NUMPAD9;
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