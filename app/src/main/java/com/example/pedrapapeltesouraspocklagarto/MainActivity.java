package com.example.pedrapapeltesouraspocklagarto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public enum Jogada {
        PEDRA(0), PAPEL(1), TESOURA(2), SPOCK(3), LAGARTO(4);
        private final int valor;
        Jogada(int valor) {
            this.valor = valor;
        }
    }

    public enum Resultado {
        DERROTA(-1), EMPATE(0), VITORIA(1);
        private final int valor;
        Resultado(int valor){
            this.valor = valor;
        }
    }

    public static final Resultado TABELA[][] = {
            {Resultado.EMPATE, Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA, Resultado.VITORIA},
            {Resultado.VITORIA, Resultado.EMPATE, Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA},
            {Resultado.DERROTA, Resultado.VITORIA, Resultado.EMPATE, Resultado.DERROTA, Resultado.VITORIA},
            {Resultado.VITORIA, Resultado.DERROTA, Resultado.VITORIA, Resultado.EMPATE, Resultado.DERROTA},
            {Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA, Resultado.VITORIA, Resultado.EMPATE},
    };

    private Integer pontosComputador = 0;
    private Integer pontosHumano = 0;

    private Button tesouraButton;
    private Button pedraButton;
    private Button papelButton;
    private Button spockButton;
    private Button lagartoButton;
    private ProgressBar progressBarComputador;
    private ProgressBar progressBarHumano;
    private TextView optionTextView;

    private Random dado = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lagartoButton = findViewById(R.id.lagartoButton);
        tesouraButton = findViewById(R.id.tesouraButton);
        papelButton = findViewById(R.id.papelButton);
        spockButton = findViewById(R.id.spockButton);
        pedraButton = findViewById(R.id.pedraButton);

        progressBarComputador = findViewById(R.id.progressBarComputador);
        progressBarHumano = findViewById(R.id.progressBarHumano);
        optionTextView = findViewById(R.id.optionTextView);
    }

    public void buttonPedraClick(View view){
        rodada(Jogada.PEDRA);
    }

    public void buttonPapelClick(View view){
        rodada(Jogada.PAPEL);
    }

    public void buttonTesouraClick(View view){
        rodada(Jogada.TESOURA);
    }

    public void buttonSpockClick(View view){
        rodada(Jogada.SPOCK);
    }

    public void buttonLagartoClick(View view){
        rodada(Jogada.LAGARTO);
    }

    public void rodada(Jogada jogada){
        Jogada jogadaComputador = Jogada.values()[dado.nextInt(4)];
    }
}