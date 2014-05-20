package com.example.balonmano;

import java.util.ArrayList;
import java.math.*;

import Modelo.Jugador;
import Modelo.Linea;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class DrawView extends View {
    Paint paint = new Paint();
    ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	ArrayList<Linea> lineas = new ArrayList<Linea>();
    Jugador a = null;
	Linea linea = null;
    boolean encontrado = false;
    String herramienta = "Mover";
    float x,y;
	String accion;
	
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
    }

    @Override
    public void onDraw(Canvas canvas) {
    	Resources res = getResources();
    	Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.campo);
    	Bitmap sbitmap = Bitmap.createScaledBitmap(bitmap, canvas.getWidth(),canvas.getHeight()-200, false);
    	canvas.drawBitmap(sbitmap, 0,0 , paint);
    	bitmap.recycle();
    	//Estilo
    	paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1);
		paint.setColor(Color.BLACK);
		
		int i;
    	for(i = 0; i < jugadores.size(); i++)
    	{
    		jugadores.get(i).dibujar(canvas);
    	}

		
		if(accion == "moverDedo")
		{
			canvas.drawLine(linea.x0, linea.y0, x, y, paint);
		}
				
		for(int j=0; j < lineas.size();j++)
		{
			lineas.get(j).dibujar(canvas, paint);
		}
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
				
				for(i = 0; !encontrado && i < jugadores.size(); i++)
				{
					if(Math.sqrt(Math.pow((event.getX()-jugadores.get(i).x),2)+Math.pow((event.getY()-jugadores.get(i).y),2))<= 20)
					{
						encontrado=true;
						a = jugadores.get(i);
					}
				}
				break;
	    	case MotionEvent.ACTION_MOVE:
		   		 if(encontrado)
		   		 {
		   			a.x = event.getX();
		   		 	a.y = event.getY();
		   		 	invalidate();
		   		 }
		   		 break;
	    	 case MotionEvent.ACTION_UP:
	    		 if(encontrado)
	    		 {
	    			a.x = event.getX();
	    		 	a.y = event.getY();
	    		 	invalidate();
	    		 }
	    		 break;
			}

    	}
    	else if(herramienta.equals("Flecha"))
    	{
	    	if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
	    		linea = new Linea();
				linea.x0 = event.getX();
				linea.y0 = event.getY();
			}
			
			if(event.getAction() == MotionEvent.ACTION_UP)
			{
				linea.x1 = event.getX();
				linea.y1 = event.getY();
				lineas.add(linea);
			}
			
			if(event.getAction() == MotionEvent.ACTION_MOVE)
			{
				accion = "moverDedo";
				x = event.getX();
				y = event.getY();
						
			}
			invalidate();
    	}
    	
    	
    	
    	
    	return true;
    }



}