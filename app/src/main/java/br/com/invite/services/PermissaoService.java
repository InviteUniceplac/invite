package br.com.invite.services;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissaoService extends Service {
    public static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void verificaPermissoes(Activity activity) {
        if (isPermitido()) {
            Toast.makeText(activity, "Permissão concedida", Toast.LENGTH_SHORT).show();
        } else {
            requisitarPermissao(activity);
        }
    }

    private boolean isPermitido() {
        int escritaPermissao = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int leituraPermissao = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return escritaPermissao == PackageManager.PERMISSION_GRANTED && leituraPermissao == PackageManager.PERMISSION_GRANTED;
    }

    private void requisitarPermissao(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }
}