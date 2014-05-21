package com.example.balonmano;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	DrawView drawView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        drawView = new DrawView(this);
        LinearLayout layout= (LinearLayout)findViewById(R.id.canvasLayout);
        layout.addView(drawView);
        drawView.setBackgroundResource(R.drawable.campo);
        final Button mover = (Button)findViewById(R.id.saveButton);
        final Button flecha = (Button)findViewById(R.id.cancelButton);
        final Button lContinua = (Button)findViewById(R.id.L_Cont);
        final Button lDiscontinua = (Button)findViewById(R.id.L_Disc);
        final Button Borrar = (Button)findViewById(R.id.B_Del);
        
        mover.setEnabled(false);
        lContinua.setEnabled(false);
        lDiscontinua.setEnabled(false);
        
        mover.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.herramienta="Mover";
				mover.setEnabled(false);
				flecha.setEnabled(true);
			}
		});
        
        flecha.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.herramienta = "Flecha";
				flecha.setEnabled(false);
				mover.setEnabled(true);
				lDiscontinua.setEnabled(true);
			}
		});
        
        lContinua.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.estiloLinea = "C";
		        lDiscontinua.setEnabled(true);
		        lContinua.setEnabled(false);			
			}
		});
        
        lDiscontinua.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.estiloLinea = "D";
		        lDiscontinua.setEnabled(false);
		        lContinua.setEnabled(true);
			}
		});
        
        Borrar.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			//drawView.lineas.clear();
			drawView.Undo();
			drawView.invalidate();
			
		}
        });
        
        
    }
    
    
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.main, menu);
		menu.add("6-0");
		menu.add("5-1");
		menu.add("4-2");
		menu.add("3-2-1");
		return true;
	}
    
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	 //seleccion de cada opcion de los menus.
	    switch (item.getItemId()) {
	        case 0:
	            drawView.CargarJugada(item.getTitle().toString());
	            return true;
	        case 1:
	        	drawView.CargarJugada(item.getTitle().toString());
	            return true;
	        case 2:
	        	drawView.CargarJugada(item.getTitle().toString());
	            return true;
	        case 3:
	        	drawView.CargarJugada(item.getTitle().toString());
	            return true;
	       default:
	    	   return true; 
	    }
	}
    
    
}
