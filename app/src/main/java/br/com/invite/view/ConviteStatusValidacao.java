package br.com.invite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.invite.R;

public class ConviteStatusValidacao extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convite_status_validacao);

        TextView messagemStatusValidacao = findViewById(R.id.messagemValidacao);
        ImageView imagemStatusValidacao = findViewById(R.id.imagemStatusValidacao);

//        Bundle data = getIntent().getExtras();
        int httpStatus = 200;
        String httpMessage = "Convite validado com sucesso!";

        if (httpStatus == 400) {
            messagemStatusValidacao.setTextColor(getResources().getColor(R.color.red_failed));
            imagemStatusValidacao.setImageResource(R.drawable.error);
        } else if (httpStatus == 503) {
            messagemStatusValidacao.setTextColor(getResources().getColor(R.color.black));
            imagemStatusValidacao.setImageResource(R.drawable.warning);
        }
        messagemStatusValidacao.setText(httpMessage);
    }
}