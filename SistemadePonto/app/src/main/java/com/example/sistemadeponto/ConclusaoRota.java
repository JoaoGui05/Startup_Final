package com.example.sistemadeponto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConclusaoRota extends AppCompatActivity {

    private Button btnVoltarInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conclusao_rota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnVoltarInicio = findViewById(R.id.btnVoltarInicio);

        btnVoltarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Funcionario funcionario = FuncionarioStorage.getFuncionarioLogado();
                DatabaseHelper dbHelper = new DatabaseHelper(ConclusaoRota.this);
                dbHelper.inserirStatus(funcionario.getNome(), "online");
                // Volta para a tela de início, que está dentro da MainActivity com bottom navigation
                Intent intent = new Intent(ConclusaoRota.this, MainActivity2.class);
                // Define que deve abrir na aba "Início" (índice 0, por exemplo)
                intent.putExtra("fragment_to_load", "inicio");
                startActivity(intent);
                finish(); // Fecha essa Activity para não voltar nela com o botão de voltar
            }
        });
    }}