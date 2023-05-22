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
import android.widget.ExpandableListAdapter;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.invite.R;
import br.com.invite.model.Convite;

public class ConviteService extends Service {
    private final DatabaseReference conviteReference = FirebaseDatabase.getInstance().getReference("Convites");
    private final DateFormat formatadorData = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    private final DateFormat formatadorHora = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    Bitmap logo, escalaLogo;

    // QR-Code no PDF
//    Bitmap qrCode, escalaQrCode;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String gerarConvite(Convite convite) {
        String uuid = UUID.randomUUID().toString();
        conviteReference.child(uuid).setValue(convite);

        return uuid;
    }

    public void gerarPdfConvite(Convite convite, Context context, Resources resources) {
        logo = BitmapFactory.decodeResource(resources, R.drawable.logo);
        escalaLogo = Bitmap.createScaledBitmap(logo, 140, 140, false);

        // QR-Code no PDF
//        qrCode = BitmapFactory.decodeResource(resources, R.drawable.comprovante);
//        escalaQrCode = Bitmap.createScaledBitmap(qrCode, 500, 500, false);

        PdfDocument pdf = new PdfDocument();
        PdfDocument.PageInfo convitePdf = new PdfDocument.PageInfo.Builder(792, 1120, 1).create();
        PdfDocument.Page pagina = pdf.startPage(convitePdf);

        Paint paint = new Paint();
        Paint title = new Paint();

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
        canvas.drawText("O convidado " + convite.getUsuario().nome.toUpperCase() + " fez sua inscrição para", 396, 400, title);
        canvas.drawText("o evento " + convite.getEvento().getNomeEvento() + ", que ocorrerá no dia ", 396, 440, title);
        canvas.drawText(formatadorData.format(convite.getEvento().getData()) + " às " + formatadorHora.format(convite.getEvento().getData()) + " horas", 396, 480, title);
        canvas.drawText("no local: " + convite.getEvento().getLocal(), 396, 520, title);

        // QR-Code no PDF
//        canvas.drawBitmap(escalaQrCode, 146, 400, paint);

        pdf.finishPage(pagina);

        try {
            File file = new File(Environment.getExternalStorageDirectory(), "/Documents/convite" + convite.getEvento().getNomeEvento() + "_" + convite.getUsuario().getNome() + ".pdf");

            pdf.writeTo(new FileOutputStream(file));
            Toast.makeText(context, "Convite gerado com sucesso.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Falha ao tentar gerar o convite", Toast.LENGTH_SHORT).show();
        }

        pdf.close();
    }

    public AtomicInteger procurarConvite(String uid) {
        AtomicInteger status = new AtomicInteger(200);
        try {
            conviteReference.child(uid).get().addOnCompleteListener(task -> {
                if (!task.isSuccessful())
                    status.set(503);
            });
        } catch (DatabaseException e) {
            status.set(404);
        } catch (Exception e) {
            status.set(500);
        }

        return status;
    }
}