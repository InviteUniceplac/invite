package br.com.invite.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import br.com.invite.model.Convite;

public class QrCodeService extends Service {
    private final String dominio = "https://chart.googleapis.com/chart?";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void mostrarQrCode(String convite, Context context, ImageView imagemView) {
        Glide.with(context).load(gerarQrCode(convite, null)).into(imagemView);
    }

    public void mostrarQrCode(String convite, Context context, ImageView imagemView, int tamanho) {
        Glide.with(context).load(gerarQrCode(convite, tamanho)).into(imagemView);
    }

    public String gerarQrCode(String data, Integer tamanho) {
        tamanho = (tamanho == null ? 200 : tamanho);

        String dimensoes = "chs=" + tamanho + "x" + tamanho;
        String tipo = "&cht=qr";
        String texto = "&chl=" + data;

        return dominio + dimensoes + tipo + texto;
    }
}