package com.blabla.cadastrodeprodutos.cadastrodeprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class ResultadosDaBuscaActivity extends AppCompatActivity {

    String titulo;

    ListView resultadosDaBuscaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_da_busca);

        //define o título da página
        titulo = "Exibindo resultados por: ";

        String anterior = getIntent().getStringExtra("TITLE");

        setTitle(titulo + anterior);

        //dados falsos

        /*String[] resultados_falsos = {"Maçã",
                "Abacaxi",
                "Pera",
                "Manga",
                "Mangostão",
                "Durião",
                "Uva",
                "Pitaya",
                "Torranja",
                "Laranja",
                "Maracujá",
                "Morango"
        };

        //instancia a listview
        resultadosDaBuscaView = (ListView)findViewById(R.id.listaResultadosDaBusca);



        ArrayAdapter adapterFakeResults = new ArrayAdapter<String> (this,
                android.R.layout.simple_list_item_1, R.id.listaResultadosDaBusca, resultados_falsos);

        String erroPublico = "resultadosDaBuscaView.setAdapter(adapterFakeResults)";

        resultadosDaBuscaView.setAdapter(adapterFakeResults);

        Log.e("Erro ao popular dados", "Erro" + erroPublico);*/

    }

    //define o botão voltar do android como falso nessa activity, ao inves de pressionar, volta a activity por meio de um intent, pois assim atualiza o banco de dados da aplicação.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            //intent que retorna para activity principal
            Intent voltarAMain = new Intent(ResultadosDaBuscaActivity.this, MainActivity.class);
            startActivity(voltarAMain);
        }
        return false;
        // retorna false quando o botão de voltar é pressionado
    }

}
