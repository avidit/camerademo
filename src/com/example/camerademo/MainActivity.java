package com.example.camerademo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{

	ImageView preview;
	ImageView whole_photo;
	ImageButton clicker;
	private static final int CAMERA_REQUEST = 1; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		preview = (ImageView)this.findViewById(R.id.thumb);
		whole_photo = (ImageView)this.findViewById(R.id.photo);
		clicker = (ImageButton) this.findViewById(R.id.camera);
		clicker.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent myint = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
		startActivityForResult(myint, 1); 
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == 1 && resultCode == RESULT_OK) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
            preview.setImageBitmap(photo);
        }  
    } 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
