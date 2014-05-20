package Modelo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Linea {
	public float x0, y0, x1, y1;
	public Linea()
	{
		
	}
	public Linea(float x0, float y0, float x1, float y1)
	{
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}
	public void dibujar(Canvas canvas, Paint paint)
	{
		canvas.drawLine(x0,y0,x1,y1, paint);
		fillArrow(canvas, paint);
	}
	public void fillArrow(Canvas canvas, Paint paint) {

	    paint.setStyle(Paint.Style.FILL);

	    float deltaX = x1 - x0;
	    float deltaY = y1 - y0;
	    //float frac = (float) 0.05;
	    int ARROWHEAD_LENGTH=5; 
	    float sideZ= (float) Math.sqrt(deltaX *deltaX + deltaY*deltaY); 
	    float frac = ARROWHEAD_LENGTH < sideZ ? ARROWHEAD_LENGTH/sideZ : 1.0f;
	    float point_x_1 = x0 + (float) ((1 - frac) * deltaX + frac * deltaY);
	    float point_y_1 = y0 + (float) ((1 - frac) * deltaY - frac * deltaX);

	    float point_x_2 = x1;
	    float point_y_2 = y1;

	    float point_x_3 = x0 + (float) ((1 - frac) * deltaX - frac * deltaY);
	    float point_y_3 = y0 + (float) ((1 - frac) * deltaY + frac * deltaX);

	    Path path = new Path();
	    path.setFillType(Path.FillType.EVEN_ODD);

	    path.moveTo(point_x_1, point_y_1);
	    path.lineTo(point_x_2, point_y_2);
	    path.lineTo(point_x_3, point_y_3);
	    path.lineTo(point_x_1, point_y_1);
	    //path.lineTo(point_x_1, point_y_1);
	    path.close();

	    canvas.drawPath(path, paint);
	}

}
