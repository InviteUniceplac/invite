package br.com.invite.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.invite.R;
import br.com.invite.controller.EventoController;

public class EventosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);


        Button enviar = findViewById(R.id.idEnviarEvento);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nomeEventotxt = findViewById(R.id.idNomeEvento);
                EditText localtxt = findViewById(R.id.idLocal);
                EditText datatxt = findViewById(R.id.idData);
                EditText patrocinadortxt = findViewById(R.id.idPatrocinador);
                EditText descricaotxt = findViewById(R.id.idDescricao);
                EditText horariotxt = findViewById(R.id.idHora);

                String nomeEvento = nomeEventotxt.getText().toString();
                String local = localtxt.getText().toString();
                String data = datatxt.getText().toString();
                String patrocinador = patrocinadortxt.getText().toString();
                String descricao = descricaotxt.getText().toString();
                String horario = horariotxt.getText().toString();

                String dataHora = data + horario;

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'as' HH:mm");


                try {

                    Date date = formatter.parse(dataHora);

                    Toast.makeText(EventosActivity.this, "A data é" + date, Toast.LENGTH_SHORT).show();

                    EventoController controler = new EventoController(local, nomeEvento, date, patrocinador, descricao);
                    controler.criarEvento(local, nomeEvento, date, patrocinador, descricao);

                    Intent voltaHome = new Intent(EventosActivity.this, HomeActivity.class);
                    startActivity(voltaHome);
                    finish();

                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(EventosActivity.this, "Erro na exercução: " + e.toString(), Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}