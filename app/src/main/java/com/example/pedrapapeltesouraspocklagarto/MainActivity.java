package com.example.pedrapapeltesouraspocklagarto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

        Resultado(int valor) {
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

    public void buttonPedraClick(View view) {
        rodada(Jogada.PEDRA);
    }

    public void buttonPapelClick(View view) {
        rodada(Jogada.PAPEL);
    }

    public void buttonTesouraClick(View view) {
        rodada(Jogada.TESOURA);
    }

    public void buttonSpockClick(View view) {
        rodada(Jogada.SPOCK);
    }

    public void buttonLagartoClick(View view) {
        rodada(Jogada.LAGARTO);
    }

    public void rodada(Jogada jogada) {
        Jogada jogadaComputador = Jogada.values()[dado.nextInt(4)];
        switch (TABELA[jogada.valor][jogadaComputador.valor]) {
            case VITORIA:
                pontosHumano += 3;
                Toast.makeText(this, "Humano: "+jogada.toString()+", Computador: "+jogadaComputador.toString()+": Ponto para o humano." , Toast.LENGTH_SHORT).show();
                break;
            case DERROTA:
                Toast.makeText(this, "Humano: "+jogada.toString()+", Computador: "+jogadaComputador.toString()+": Ponto o computador." , Toast.LENGTH_SHORT).show();
                pontosComputador += 3;
                break;
            case EMPATE:
                Toast.makeText(this, "Humano: "+jogada.toString()+", Computador: "+jogadaComputador.toString()+": Empate." , Toast.LENGTH_SHORT).show();
                pontosHumano++;
                pontosComputador++;
                break;
        }
        atualizaStatus();
    }

    private void iniciaTorneio(){
        pontosComputador = 0;
        pontosHumano = 0;
    }

    private void atualizaStatus() {
        progressBarComputador.setProgress(pontosComputador);
        progressBarHumano.setProgress(pontosHumano);
        if (pontosHumano < 15 && pontosComputador < 15) {
            optionTextView.setText("Escolha uma opção.");
        } else if (pontosHumano >= 15 && pontosComputador < 15) {
            optionTextView.setText("O humano venceu o torneio.");
            iniciaTorneio();
        } else if (pontosHumano < 15 && pontosComputador >= 15) {
            optionTextView.setText("O computador venceu o torneio.");
            iniciaTorneio();
        } else {
            optionTextView.setText("O torneio terminou empatado.");
            iniciaTorneio();
        }
    }

    public void textViewStatusClick(View v){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Reiniciar o torneio?");
        dialogBuilder.setMessage("Deseja reiniciar o torneio zerando o estado atual?");
        dialogBuilder.setPositiveButton("Reiniciar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        iniciaTorneio();
                        atualizaStatus();
                    }
                });
        dialogBuilder.create();
        dialogBuilder.show();
    }
}
