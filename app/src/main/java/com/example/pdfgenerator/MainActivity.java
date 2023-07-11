package com.example.pdfgenerator;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.widget.Button;
import android.os.Bundle;
import android.graphics.pdf.PdfDocument;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button generatePdfButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generatePdfButton=findViewById(R.id.create);
        generatePdfButton.setOnClickListener(V->{
            PdfDocument document = new PdfDocument();

            // Create a PageInfo object describing the page dimensions and attributes
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(200, 600, 1).create();

            // Start a new page
            PdfDocument.Page page = document.startPage(pageInfo);

            // Get the Canvas object from the PdfPage, which allows drawing content
            Canvas canvas = page.getCanvas();

            // Perform your drawing operations on the canvas
            // For example:
            Paint paint = new Paint();

            paint.setColor(Color.RED);
            canvas.drawText("Hello, PDF!", 20, 20, paint);

            // Finish the page
            document.finishPage(page);
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "/" + "fname.pdf");
            // Save the document to a file

            try {
                document.writeTo(new FileOutputStream(file));
                // PDF generation successful
                showToast("PDF generated successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                // PDF generation failed
                showToast("Error generating PDF!"+e);

            }

            // Close the document
            document.close();

        });

    }
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}