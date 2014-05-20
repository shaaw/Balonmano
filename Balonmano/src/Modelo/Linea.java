package Modelo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;

public class Linea {
	public float x0, y0, x1, y1;
	public Path path;
	public String estilo;
	public Paint paint= new Paint();
	public Linea(String estilo)
	{
		paint.setStyle(Paint.Style.STROKE);
    	paint.setStrokeWidth(1);
		paint.setColor(Color.BLACK);
		if(estilo == "D")
		paint.setPathEffect(new DashPathEffect(new float[] {5,10}, 0));
	}
	
	public void dibujar(Canvas canvas)
	{
		path = new Path();
		paint.setStyle(Paint.Style.STROKE);
		path.moveTo(x0, y0);
		path.lineTo(x1, y1);
		path.close();
		canvas.drawPath(path, paint);
		fillArrow(canvas);
	}
	public void pintarLineaMovimiento(Canvas canvas, float x, float y)
	{
		path = new Path();
		path.moveTo(x0, y0);
		path.lineTo(x, y);
		path.close();
		canvas.drawPath(path, paint);
	}
	public void fillArrow(Canvas canvas) {

	    paint.setStyle(Paint.Style.FILL);

	    float deltaX = x1 - x0;
	    float deltaY = y1 - y0;
	    int ARROWHEAD_LENGTH=5; 
	    float sideZ= (float) Math.sqrt(deltaX *deltaX + deltaY*deltaY); 
	    float frac = ARROWHEAD_LENGTH < sideZ ? ARROWHEAD_LENGTH/sideZ : 1.0f;
	    float point_x_1 = x0 + (float) ((1 - frac) * deltaX + frac * deltaY);
	    float point_y_1 = y0 + (float) ((1 - frac) * deltaY - frac * deltaX);

	    float point_x_2 = x1;
	    float point_y_2 = y1;

	    float point_x_3 = x0 + (float) ((1 - frac) * deltaX - frac * deltaY);
	    float point_y_3 = y0 + (float) ((1 - frac) * deltaY + frac * deltaX);

	    path = new Path();
	    path.setFillType(Path.FillType.EVEN_ODD);

	    path.moveTo(point_x_1, point_y_1);
	    path.lineTo(point_x_2, point_y_2);
	    path.lineTo(point_x_3, point_y_3);
	    path.lineTo(point_x_1, point_y_1);
	    path.lineTo(point_x_1, point_y_1);
	    path.close();

	    canvas.drawPath(path, paint);
	}

}
