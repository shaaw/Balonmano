package Modelo;

import java.util.ArrayList;


public class Almacen {
	public ArrayList<Linea> lineas;
	public ArrayList<Jugador> jugadores;
	public float pelotax, pelotay;
	
	public Almacen(ArrayList<Linea> lineas,ArrayList<Jugador> jugadores, float pelotax, float pelotay)
	{
		int i = 0;
		
		this.lineas = new ArrayList<Linea>();
		for(i = 0; i < lineas.size(); i++)
			this.lineas.add(new Linea(lineas.get(i)));
		this.jugadores = new ArrayList<Jugador>();
		for(i = 0; i < jugadores.size(); i++)
			this.jugadores.add(new Jugador(jugadores.get(i)));
		this.pelotax = pelotax;
		this.pelotay = pelotay;
	}

	
}
