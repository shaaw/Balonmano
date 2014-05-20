package com.example.balonmano;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);            
    }

    @Override
    public void onDraw(Canvas canvas) {
    	Resources res = getResources();
    	Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.campo);
    	Bitmap sbitmap = Bitmap.createScaledBitmap(bitmap, canvas.getWidth(),canvas.getHeight()-200, false);
    	canvas.drawBitmap(sbitmap, 0,0 , paint);
    	
    	
    	
    	/*
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        
        canvas.drawCircle(30, 50, 20, paint);
        paint.setTextAlign(Align.CENTER);
        canvas.drawText("1", 30, 50 - paint.ascent() /2, paint);
       */
    }

}