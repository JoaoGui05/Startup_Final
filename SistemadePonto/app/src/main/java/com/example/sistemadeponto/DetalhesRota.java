package com.example.sistemadeponto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalhesRota extends AppCompatActivity {

    private Button btnFinalizar, btnAlertaVerde, btnAlertaAmarelo, btnAlertaVermelho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_rota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAlertaVerde = findViewById(R.id.btnAlertaVerde);
        btnAlertaAmarelo = findViewById(R.id.btnAlertaAmarelo);
        btnAlertaVermelho = findViewById(R.id.btnAlertaVermelho);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnAlertaVerde.setOnClickListener(v -> enviarAlerta("verde"));
        btnAlertaAmarelo.setOnClickListener(v -> enviarAlerta("amarelo"));
        btnAlertaVermelho.setOnClickListener(v -> enviarAlerta("vermelho"));

        // Finalizar rota
        btnFinalizar.setOnClickListener(v -> {
            Toast.makeText(this, "Rota finalizada com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); // ou voltar para a tela de rotas
        });
    }

    private void enviarAlerta(String nivel) {
        // Aqui você pode implementar a lógica real de envio, como um POST para uma API ou salvar no Firebase, etc.
        String mensagem = "Alerta " + nivel.toUpperCase() + " enviado ao supervisor!";
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();

        // Simulação de envio de alerta para outro módulo ou sistema:
        // Você pode usar BroadcastReceiver, Firebase, banco local ou Intent com extras.

        // Exemplo: simulando envio de dados com Intent para o painel do gerente (caso estejam usando uma activity específica)
        Intent intent = new Intent("com.example.sistemadeponto.ALERTA_ENVIADO");
        intent.putExtra("nivel_alerta", nivel);
        sendBroadcast(intent);
    }
}