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
	
	BomberMap mapa;
	int[][] grid;
	public Monitor(BomberMap mapa){
		this.mapa = mapa;
		this.grid = mapa.grid;
		System.out.println(" X = "+this.grid.length+" Y = "+this.grid[0].length);
		getDatos();
		//lectura();	
	}


	/*
	Metodo que regresa True o False si puede avanzar
	A la dirección indicada
	0 = Arriba
	1 = derecha
	2 = abajo 
	3 = izquiera
	*/
	public int hayPaso(int x,int y, int dir){

		if(!verificaArea(x,y,dir))
			return -2;
		//Tomando la dir Arriba
		switch(dir){
			case 0:
				return grid[x][y+1];
			case 1:	
				return grid[x+1][y];
			case 2:
				return grid[x][y-1];
			case 3:	
				return grid[x-1][y];
		}
		return -2;
	}

	public boolean verificaArea(int x,int y,int dir){
		//Verificamos que no salgamos del mapa
		if(x==1&&dir==3) //Izquierda
			return false;
		if(x==grid.length&&dir==1) //Derecha
			return false;
		if(y==grid[0].length&&dir==2) //Abajo
			return false;
		if(y==1&&dir==0) //Arriba
			return false;
		else
			return true;

	}

	public void getDatos(){
		for(int i=0;i<mapa.grid.length;i++){
			for(int j=0;j<mapa.grid[i].length;j++)
				System.out.print(mapa.grid[i][j]);
				System.out.print("\n");
			}
		}


	

}