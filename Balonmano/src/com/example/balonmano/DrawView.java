package com.example.balonmano;

import java.util.ArrayList;

import Modelo.Almacen;
import Modelo.Jugador;
import Modelo.Linea;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    public ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    public ArrayList<Linea> lineas = new ArrayList<Linea>();
    public ArrayList<Almacen> listaAlmacen = new ArrayList<Almacen>();
    
	int listaCont = 0;
	Path path = new Path();
    float pelotax;
    float pelotay;
    Jugador a = null;
	Linea linea = null;
	Almacen almacen = null;
    boolean encontrado = false;
    boolean pelota = false;
    String herramienta = "Mover";
    float x,y;
	String accion, estiloLinea = "";
	
	 
    @SuppressWarnings("deprecation")
	public DrawView(Context context) {
        super(context);      
        
        jugadores.add(new Jugador(50, 140, "1", true));
        jugadores.add(new Jugador(110, 220, "2", true));
        jugadores.add(new Jugador(195, 240, "3", true));
        jugadores.add(new Jugador(280, 240, "4", true));
        jugadores.add(new Jugador(365, 220, "5", true));
        jugadores.add(new Jugador(425, 140, "6", true));
        jugadores.add(new Jugador(235, 40, "7", true));
        
        jugadores.add(new Jugador(30, 60, "1", false));
        jugadores.add(new Jugador(237, 240, "2", false));
        jugadores.add(new Jugador(440, 60, "3", false));
        jugadores.add(new Jugador(50, 350 , "4", false));
        jugadores.add(new Jugador(240, 400, "5", false));
        jugadores.add(new Jugador(425, 350, "6", false));
        pelotax = 250;
        pelotay = 250;
    }

    @Override
    public void onDraw(Canvas canvas) {
    	Resources res = getResources();
    	
    	//COMENTADO EL CAMPO POR RAZONES DE EFICIENCIA A LA HORA DE LAS PRUEBAS
    	
    	/*Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.campo);
    	Bitmap sbitmap = Bitmap.createScaledBitmap(bitmap, canvas.getWidth(),canvas.getHeight(), false);
    	canvas.drawBitmap(sbitmap, 0,0 , paint);
    	bitmap.recycle();
    	sbitmap.recycle();
    	*/
		int i;
  	
    	for(i = 0; i < jugadores.size(); i++)
    	{
    		jugadores.get(i).dibujar(canvas);
    	}

        
		if(accion == "moverFlecha")
		{
			linea.pintarLineaMovimiento(canvas, x, y);
		}
		
		if(accion == "levantar")
		{
			almacen = new Almacen(lineas,jugadores);
			
			listaAlmacen.add(almacen);
			listaCont++;
		}
		
				
		for(int j=0; j < lineas.size();j++)
		{
			lineas.get(j).dibujar(canvas);
		}
		
    	Bitmap bitmap1 = BitmapFactory.decodeResource(res, R.drawable.ball);
    	Bitmap sbitmap1 = Bitmap.createScaledBitmap(bitmap1, 30,30, false);
    	
    	canvas.drawBitmap(sbitmap1,pelotax ,pelotay , paint);
    	bitmap1.recycle();
    	sbitmap1.recycle();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
    	if(herramienta.equals("Mover"))
    	{
	    	switch (event.getAction()) {
	    	case MotionEvent.ACTION_DOWN:
				int i;
				encontrado = false;
				pelota = false;
				for(i = 0; !encontrado && i < jugadores.size(); i++)
				{
					if(Math.sqrt(Math.pow((event.getX()-jugadores.get(i).x),2)+Math.pow((event.getY()-jugadores.get(i).y),2))<= 20)
					{
						encontrado=true;
						a = jugadores.get(i);
					}
				}
				if(!encontrado)
				{
					if(event.getX()-pelotax < 30 && event.getX()-pelotax >= 0 && event.getY()-pelotay < 30 && event.getY()-pelotay >= 0 )
					{
						pelota=true;
					}
				}
				break;
	    	case MotionEvent.ACTION_MOVE:
		   		 if(encontrado)
		   		 {
		   			a.x = event.getX();
		   		 	a.y = event.getY();
		   		 	invalidate();
		   		 }else
		   		 {
		   			 if(pelota)
		   			 {
		   				 pelotax = event.getX() - 15;
		   				 pelotay = event.getY() - 15;
		   				invalidate();
		   			 }
		   		 }
		   		 break;
	    	 case MotionEvent.ACTION_UP:
	    		 if(encontrado)
	    		 {
	    			a.x = event.getX();
	    		 	a.y = event.getY();
	    		 	invalidate();
	    		 }else
		   		 {
		   			 if(pelota)
		   			 {
		   				 pelotax = event.getX() - 15;
		   				 pelotay = event.getY() - 15;
		   				invalidate();
		   			 }
		   		 }
	    		 
	    		 break;
			}

    	}
    	else if(herramienta.equals("Flecha"))
    	{
	    	if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
	    		linea = new Linea(estiloLinea);
	    		linea.x0 = event.getX();
				linea.y0 = event.getY();

				//Parche para arreglar error extranyo al pulsar despues de pintar una flecha
				x = linea.x0;
				y = linea.y0;
			}
			
			if(event.getAction() == MotionEvent.ACTION_UP)
			{
				accion = "levantar";
				linea.x1 = event.getX();
				linea.y1 = event.getY();
				lineas.add(linea);
			}
			
			if(event.getAction() == MotionEvent.ACTION_MOVE)
			{
				accion = "moverFlecha";
				x = event.getX();
				y = event.getY();
						
			}
			invalidate();
    	}
    	
    	
    	
    	
    	return true;
    }
    public void CargarListaAlmacen(int pos)
    {
    	if(pos < listaAlmacen.size() && pos >= 0)
    	{
    		lineas.clear();
    		jugadores.clear();
    	
    		lineas = listaAlmacen.get(pos).lineas;
    		jugadores = listaAlmacen.get(pos).jugadores;
    	}
    }
    
    public void Undo()
    {
    	listaCont--;
    	CargarListaAlmacen(listaCont);
    }
    public void Redo()
    {
    	listaCont++;
    	CargarListaAlmacen(listaCont);
    }

	public void CargarJugada(String jugada) {
		//Aqu’ hay que colocar a los jugadores segœn jugada, que
		//nos llega por su nombre en el string jugada. 6-0,5-1...
	}



}
