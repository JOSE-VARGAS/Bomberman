import java.awt.Robot;
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
	int [] keys;
	private int playerNo = 0;
	private ArrayList<String> explora = new ArrayList();
	
	
	public MiRobot(int playerNo){
		this.playerNo = playerNo;
		try{
			Robot robot = new Robot();
			}catch (AWTException e){}

		 keys = new int[5];
        /** load the configurations */
        for (int k = BomberKeyConfig.UP; k <= BomberKeyConfig.BOMB; k++)
            keys[k] = BomberKeyConfig.keys[playerNo - 1][k];
		lectura();		
	}




	public void saluda(){
		
		for(int i=0;i<keys.length;i++)
		System.out.println(keys[i]);
		explora.add("F4");
		System.out.println(explora);
		escrive();
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