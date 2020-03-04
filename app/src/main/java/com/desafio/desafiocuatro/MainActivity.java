package com.desafio.desafiocuatro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imvSmile, imvCarga;
    private TextView textViewInfo;
    private Button buttonCarga;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imvCarga    = findViewById(R.id.imvCarga);
        buttonCarga = findViewById(R.id.buttonCargaImagen);

        // Boton
        buttonCarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        setClick();
    }


         private void cargaImagen(View v) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,0);

        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           super.onActivityResult(requestCode, resultCode, data);
           if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
               Bundle extras = data.getExtras();
               Bitmap imageBitmap = (Bitmap) extras.get("data");
               imvCarga.setImageBitmap(imageBitmap);
           }
       }



        public void setClick() {
            Button nextMain = findViewById(R.id.nextMain);
            nextMain.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // @SuppressLint("WrongViewCast") ImageView imagen = findViewById(R.id.nextMain);
            if(imvCarga.getDrawable() != null) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("BOOLEAN", true);
                intent.putExtra("String", "http://www.desafiolatam.com/");
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.sinfoto), Toast.LENGTH_SHORT).show();
            }
        }
    });
}


}



