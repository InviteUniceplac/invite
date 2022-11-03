package br.com.invite.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.invite.R;
import br.com.invite.controller.UsuarioControler;
import br.com.invite.model.Evento;
import br.com.invite.model.MyAdapter;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("Eventos");
    private MyAdapter adapter;

    private ArrayList list;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preparaBotoesMenuInferior();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new MyAdapter(this, list);

        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Evento evento = dataSnapshot.getValue(Evento.class);
                    list.add(evento);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(HomeActivity.this, GerarConviteActivity.class);
                startActivity(in);
            }
        });

    }

    private void preparaBotoesMenuInferior() {

        View eventosBtn = findViewById(R.id.btn_eventos);
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("KgRVqmbx6pQmBmi68PoGc8wcPof1")){

            eventosBtn.setVisibility(View.VISIBLE);

        }else{

            eventosBtn.setVisibility(View.GONE);

        }

        View perfilBtn = findViewById(R.id.btn_perfil);
        View logoutBtn = findViewById(R.id.btn_logout);

        eventosBtn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, EventosActivity.class);
            startActivity(intent);
        });

        perfilBtn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        logoutBtn.setOnClickListener(view -> {
            Intent logout = new Intent(HomeActivity.this, LoginActivity.class);
            UsuarioControler controler = new UsuarioControler();
            controler.logout();

            startActivity(logout);
            finish();
        });

    }
}