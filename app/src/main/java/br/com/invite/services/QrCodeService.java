package br.com.invite.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

<<<<<<< HEAD
=======
import br.com.invite.model.Convite;

>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493
public class QrCodeService extends Service {
    private final String dominio = "https://chart.googleapis.com/chart?";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void mostrarQrCode(Context context, ImageView imagemView) {
<<<<<<< HEAD
        Glide.with(context).load(gerarQrCode("teste", 400)).into(imagemView);
=======
        Glide.with(context).load(gerarQrCode("teste", null)).into(imagemView);
>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493
    }

    public void mostrarQrCode(Context context, ImageView imagemView, int tamanho) {
        Glide.with(context).load(gerarQrCode("teste", tamanho)).into(imagemView);
    }

<<<<<<< HEAD
=======
    public void mostrarQrCode(Convite convite, Context context, ImageView imagemView) {
        Glide.with(context).load(gerarQrCode(convite.toString(), null)).into(imagemView);
    }

    public void mostrarQrCode(Convite convite, Context context, ImageView imagemView, int tamanho) {
        Glide.with(context).load(gerarQrCode(convite.toString(), tamanho)).into(imagemView);
    }

>>>>>>> afc5f0c61b138bf6fecf2160382f3e97e9f8f493
    public String gerarQrCode(String data, Integer tamanho) {
        tamanho = (tamanho == null ? 200 : tamanho);

        String dimensoes = "chs=" + tamanho + "x" + tamanho;
        String tipo = "&cht=qr";
        String texto = "&chl=" + data;

        return dominio + dimensoes + tipo + texto;
    }
}