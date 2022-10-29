package br.com.invite.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.invite.R;
import br.com.invite.model.Convite;

public class ComprovanteService extends Service {
    int pageHeight = 1120;
    int pageWidth = 792;

    Bitmap bmp, scaledBmp;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void gerarComprovante(Convite convite) {
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        scaledBmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);

        PdfDocument pdf = new PdfDocument();

        Paint paint = new Paint();
        Paint title = new Paint();

        PdfDocument.PageInfo comprovante = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();

        PdfDocument.Page pagina = pdf.startPage(comprovante);

        Canvas canvas = pagina.getCanvas();

        canvas.drawBitmap(scaledBmp, 56, 40, paint);

        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        title.setTextSize(32);

        title.setColor(ContextCompat.getColor(getBaseContext(), R.color.purple_200));

        canvas.drawText("Invite", 209, 100, title);

        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(getBaseContext(), R.color.black));
        title.setTextAlign(Paint.Align.CENTER);
        //canvas.drawText(convite.getUsuario().nome.toUpperCase() + " fez a sua inscrição para o evento: " + convite.getEvento().getNomeEvento() + ", que ocorrerá das " + convite.getEvento().getInicioEvento() + " às " + convite.getEvento().getFimEvento() + " horas, no local: " + convite.getEvento().getLocal(), 396, 560, title);

        pdf.finishPage(pagina);

        File file = new File(Environment.getExternalStorageDirectory(), convite.getEvento().getNomeEvento() + "_" + convite.getUsuario().getNome() + ".pdf");

        try {
            pdf.writeTo(new FileOutputStream(file));
            Toast.makeText(getBaseContext(), "Comprovante gerado com sucesso.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Falha ao tentar gerar o comprovante", Toast.LENGTH_SHORT).show();
        }
        pdf.close();
    }
}