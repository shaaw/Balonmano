package Modelo;

import java.util.ArrayList;


public class Almacen {
	public ArrayList<Linea> lineas;
	public ArrayList<Jugador> jugadores;
	
	public Almacen(ArrayList<Linea> lineas,ArrayList<Jugador> jugadores)
	{
		this.lineas = new ArrayList<Linea>(lineas);
		this.jugadores = new ArrayList<Jugador>(jugadores);
	}

	
}
