package com.example.balonmano;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.Menu;
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
        drawView.setBackgroundColor(Color.WHITE);
        Button mover = (Button)findViewById(R.id.saveButton);
        Button flecha = (Button)findViewById(R.id.cancelButton);
        mover.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.herramienta="Mover";
				
			}
		});
        
        flecha.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				drawView.herramienta = "Flecha";
				
			}
		});
        
        
    }
    
}
