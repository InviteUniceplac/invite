package br.com.invite.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.invite.model.Convite;

public class ConviteService extends Service {
    private final DatabaseReference conviteReference = FirebaseDatabase.getInstance().getReference("Convites");

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void gerarConvite(Convite convite) {
        conviteReference.setValue(convite);
    }
}