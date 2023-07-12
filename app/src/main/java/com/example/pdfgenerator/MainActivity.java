package com.example.pdfgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.widget.Button;
import android.os.Bundle;
import android.graphics.pdf.PdfDocument;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button generatePdfButton;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.inputValue1);
        generatePdfButton = findViewById(R.id.create);
        generatePdfButton.setOnClickListener(V -> {
            generatePdf(editText.getText().toString());
        });

    }

    private void generatePdf(String inputVal) {
        PdfDocument document = new PdfDocument();
        int pdfWidth = getResources().getInteger(R.integer.pdf_width);
        int pdfHeight = getResources().getInteger(R.integer.pdf_height);

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pdfWidth, pdfHeight, 1).create();

        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(150f);
        float x = 150f;
        float y = 250f;
        canvas.drawText(inputVal, x, y, paint);


        document.finishPage(page);

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "/" + "hello.pdf");

        try {
            document.writeTo(new FileOutputStream(file));
            // PDF generation successful
            showToast("PDF generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            // PDF generation failed
            showToast("Error generating PDF!" + e);

        } finally {            // Close the document
            document.close();


        }


    }


    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}