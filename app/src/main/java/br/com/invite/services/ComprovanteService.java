package br.com.invite.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.invite.R;
import br.com.invite.model.Convite;

public class ComprovanteService extends Service {
    private final DateFormat formatadorData = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    private final DateFormat formatadorHora = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);

    int pageHeight = 1120;
    int pageWidth = 792;

    Bitmap logo, escalaLogo;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void gerarPdfComprovante(Convite convite, Context context, Resources resources) {
        logo = BitmapFactory.decodeResource(resources, R.drawable.logo);
        escalaLogo = Bitmap.createScaledBitmap(logo, 140, 140, false);

        PdfDocument pdf = new PdfDocument();

        Paint paint = new Paint();
        Paint title = new Paint();

        PdfDocument.PageInfo comprovantePdf = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();

        PdfDocument.Page pagina = pdf.startPage(comprovantePdf);

        Canvas canvas = pagina.getCanvas();

        canvas.drawBitmap(escalaLogo, 56, 40, paint);

        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextSize(60);
        title.setColor(ContextCompat.getColor(context, R.color.purple_200));
        canvas.drawText("Invite", 210, 160, title);

        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        title.setTextSize(30);
        title.setColor(ContextCompat.getColor(context, R.color.black));
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(
                convite.getUsuario().nome.toUpperCase() + " fez a sua inscrição para o evento: \n\n"
                        + convite.getEvento().getNomeEvento() + ", que ocorrerá das \n\n"
                        + formatadorData.format(convite.getEvento().getData()) + " às \n\n"
                        + formatadorHora.format(convite.getEvento().getData()) + " horas, no local: \n\n"
                        + convite.getEvento().getLocal(), 396, 560, title);

        pdf.finishPage(pagina);

        try {
            File file = new File(Environment.getExternalStorageDirectory(), "/Documents/comprovante" + convite.getEvento().getNomeEvento() + "_" + convite.getUsuario().getNome() + ".pdf");

            pdf.writeTo(new FileOutputStream(file));
            Toast.makeText(context, "Comprovante gerado com sucesso.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Falha ao tentar gerar o comprovante", Toast.LENGTH_SHORT).show();
        }
        pdf.close();
    }
}