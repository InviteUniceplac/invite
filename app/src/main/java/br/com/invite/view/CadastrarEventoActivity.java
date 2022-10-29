package br.com.invite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import br.com.invite.R;
import br.com.invite.controller.EventoController;

public class CadastrarEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);


        Button enviar = findViewById(R.id.idEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nomeEventotxt = findViewById(R.id.idEvento);
                EditText localtxt = findViewById(R.id.idLocal);
                EditText datatxt = findViewById(R.id.idData);
                EditText patrocinadortxt = findViewById(R.id.idPatrocinador);
                EditText descricaotxt = findViewById(R.id.idDescricao);
                EditText horariotxt = findViewById(R.id.idHorario);

                String nomeEvento = nomeEventotxt.getText().toString();
                String local = localtxt.getText().toString();
                String data = datatxt.getText().toString();
                String patrocinador  = patrocinadortxt.getText().toString();
                String descricao = descricaotxt.getText().toString();
                String horario = horariotxt.getText().toString();

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");


                try {

                    String juncaoData = data + " " + horario;

                    Date date = formatter.parse(juncaoData);

                    Toast.makeText(CadastrarEventoActivity.this, "A data é" + date, Toast.LENGTH_SHORT).show();
                    EventoController controler = new EventoController(local,nomeEvento,date,patrocinador,descricao);

                    controler.adicionarEvento(local,nomeEvento,date,patrocinador,descricao);

                    Intent voltaHome = new Intent(CadastrarEventoActivity.this, HomeActivity.class);
                    startActivity(voltaHome);
                    finish();

                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(CadastrarEventoActivity.this, "Erro na exercução: " + e.toString(), Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}