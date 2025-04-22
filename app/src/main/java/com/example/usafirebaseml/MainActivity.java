package com.example.usafirebaseml;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1001;
    private ImageView imageView;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSelect = findViewById(R.id.btnSelectImage);
        imageView = findViewById(R.id.imageView);
        textViewResult = findViewById(R.id.textViewResult);

        btnSelect.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_IMAGE_PICK);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            try {
                // Carregar imagem da galeria como Bitmap
                Bitmap originalBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                // Redimensionar imagem para largura máxima de 1024 px
                Bitmap scaledBitmap = scaleBitmapDown(originalBitmap, 1024);

                // Mostrar no ImageView
                imageView.setImageBitmap(scaledBitmap);

                // Criar InputImage a partir do bitmap redimensionado
                InputImage image = InputImage.fromBitmap(scaledBitmap, 0);

                // Realizar OCR
                recognizeText(image);

            } catch (IOException e) {
                e.printStackTrace();
                textViewResult.setText("Erro ao carregar a imagem.");
            }
        }
    }

    private void recognizeText(InputImage image) {
        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        recognizer.process(image)
                .addOnSuccessListener(result -> {
                    String resultText = result.getText();
                    textViewResult.setText(resultText.isEmpty() ? "Nenhum texto encontrado." : resultText);
                })
                .addOnFailureListener(e -> textViewResult.setText("Erro: " + e.getMessage()));
    }

    // Utilitário para redimensionar imagem mantendo proporção
    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        }

        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, true);
    }
}