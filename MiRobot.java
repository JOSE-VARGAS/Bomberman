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

public class MiRobot extends BomberPlayer implements Serializable{
	
	Robot robot;
	private ArrayList<String> explora = new ArrayList();
	
	
	public MiRobot(BomberGame game, BomberMap map, int playerNo){
		super(game,map,playerNo);
		
		try{
			Robot robot = new Robot();
			}catch (AWTException e){}

		lectura();	
		ciclin();
	}
	public void ciclin(){
			int evt = KeyEvent.VK_DOWN;
			this.keyPressed(evt);
			this.keyReleased(evt);
			//evt = KeyEvent.VK_UP;
			//this.keyPressed(evt);
			//this.keyReleased(evt);
			System.out.println("Me movi");
			
		
		
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