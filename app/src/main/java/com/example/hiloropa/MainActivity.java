package com.example.hiloropa;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imgSombrero;
    private ImageView imgPolera;
    private ImageView imgPantalon;
    private ProgressBar progressBar;

    // Handler para actualizar la UI desde el hilo
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSombrero = findViewById(R.id.imgSombrero);
        imgPolera = findViewById(R.id.imgPolera);
        imgPantalon = findViewById(R.id.imgPantalon);
        progressBar = findViewById(R.id.progressBar);

        Button btnCamiseta = findViewById(R.id.btnCamiseta);
        Button btnSombrero = findViewById(R.id.btnSombrero);
        Button btnPantalon = findViewById(R.id.btnPantalon);

        // Ahora cada botón manda su ImageView y su drawable
        btnCamiseta.setOnClickListener(v -> descargarRopa(R.drawable.polera, imgPolera));
        btnSombrero.setOnClickListener(v -> descargarRopa(R.drawable.sombrero, imgSombrero));
        btnPantalon.setOnClickListener(v -> descargarRopa(R.drawable.pantalon, imgPantalon));
    }

    // Método genérico para "descargar" ropa y aplicarla a la ImageView correcta
    private void descargarRopa(int ropaDrawable, ImageView destino) {
        progressBar.setVisibility(View.VISIBLE);

        new Thread(() -> {
            try {
                Thread.sleep(3000); // simula descarga de 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(() -> {
                destino.setImageResource(ropaDrawable);
                progressBar.setVisibility(View.GONE);
            });
        }).start();
    }
}
