package com.example.camerademo;

import java.io.File;
import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	ImageView preview = null;
	ImageView whole_photo = null;
	ImageButton clicker;
	Bitmap photo;
	Uri myfileUri = null;
	private static final int CAMERA_REQUEST = 0; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		preview = (ImageView)findViewById(R.id.thumb);
		whole_photo = (ImageView)findViewById(R.id.photo);
		clicker = (ImageButton)findViewById(R.id.camera);

		clicker.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent myint = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
		myfileUri = Uri.fromFile(getPhoto());
		startActivityForResult(myint, CAMERA_REQUEST); 
	}

	private File getPhoto()
	{
		File directory = new File(
				Environment.getExternalStoragePublicDirectory(
						Environment.DIRECTORY_PICTURES), getPackageName());

		Locale current = getResources().getConfiguration().locale;
		SimpleDateFormat myformat = new SimpleDateFormat("yyMMdd_HHmmss", current);
		String date = myformat.format(new Date());
		File image = File(directory.getPath() + File.separator + "IMG_" + date + ".jpg");
		
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{  
		if (requestCode == CAMERA_REQUEST)
		{
			if (resultCode == RESULT_OK)
			{  
				//Uri photoUri  = data.getData();
				String filestring = myfileUri.getPath();

				Bitmap photo = (Bitmap) data.getExtras().get("data"); 
				preview.setImageBitmap(photo);

				Bitmap bitmap = BitmapFactory.decodeFile(filestring);
				BitmapDrawable drawable = new BitmapDrawable(this.getResources(),bitmap);
				//whole_photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
				whole_photo.setImageDrawable(drawable);
			}
			else if (resultCode == RESULT_CANCELED) 
			{
				Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
			}
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
