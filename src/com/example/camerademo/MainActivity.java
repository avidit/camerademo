package com.example.camerademo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
	Bitmap bitmap = null;
	
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
		myint.putExtra(MediaStore.EXTRA_OUTPUT, myfileUri);//RG - set this, so camera can capture the picture and store into this file
		startActivityForResult(myint, CAMERA_REQUEST); 
	}

	private File getPhoto()
	{
		//RG
		File directory = Environment.getExternalStorageDirectory(); //you need 	this in the manifest: <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

		directory=new File(directory.getAbsolutePath()+"/.temp/");

		if(directory.exists() == false)
		{
			directory.mkdir();
		}
		Locale current = getResources().getConfiguration().locale;
		SimpleDateFormat myformat = new SimpleDateFormat("yyMMdd_HHmmss", current);
		String date = myformat.format(new Date());
<<<<<<< HEAD
		File image = File(directory.getPath() + File.separator + "IMG_" + date + ".jpg");
		
=======
		try {
			return File.createTempFile("IMG_" + date, ".jpg", directory);//RG - you need to CREATE an empty file, before you send it to the camera
		} catch (IOException e) {
			boolean exists = directory.exists();
			e.printStackTrace();
		}

		return null;
>>>>>>> 07c81430e9bc10e832309683e6617dec4038b4ac
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{  
		if (requestCode == CAMERA_REQUEST)
		{
			if (resultCode == RESULT_OK)
			{  
<<<<<<< HEAD
				//Uri photoUri  = data.getData();
				String filestring = myfileUri.getPath();

				Bitmap photo = (Bitmap) data.getExtras().get("data"); 
				preview.setImageBitmap(photo);

				Bitmap bitmap = BitmapFactory.decodeFile(filestring);
				BitmapDrawable drawable = new BitmapDrawable(this.getResources(),bitmap);
				//whole_photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
				whole_photo.setImageDrawable(drawable);
=======
				String filestring = myfileUri.getPath();
				File dest = new File(filestring);
				FileInputStream fis;
				try {
					fis = new FileInputStream(dest); //
					if(bitmap != null)
					{
						bitmap.recycle();//RG - make sure you recycle to save memory
					}
					bitmap = BitmapFactory.decodeStream(fis);
					preview.setImageBitmap(bitmap);
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
>>>>>>> 07c81430e9bc10e832309683e6617dec4038b4ac
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
