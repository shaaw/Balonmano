package Modelo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class Jugador {
	public float x;
	public float y;
	public String numero;
	public boolean equipo;
	
	
	public Jugador(float x, float y, String numero, boolean equipo){
		this.x = x;
		this.y = y;
		this.numero = numero;
		this.equipo = equipo;
	}
	
	public Jugador(Jugador jugador)
	{
		x = jugador.x;
		y = jugador.y;
		numero = jugador.numero;
		equipo = jugador.equipo;
	}
	
	public void dibujar(Canvas canvas)
	{
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        
        canvas.drawCircle(this.x, this.y, 20, paint);
        paint.setStyle(Paint.Style.FILL);
        if(equipo == true)
        {
        	paint.setColor(Color.RED);
        }else
        	paint.setColor(Color.CYAN);
        canvas.drawCircle(this.x, this.y, 20, paint);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Align.CENTER);
        paint.setTextSize(20);
        canvas.drawText(this.numero, this.x-1, -1+this.y - paint.ascent()  /2, paint);
	}
	
	

}
