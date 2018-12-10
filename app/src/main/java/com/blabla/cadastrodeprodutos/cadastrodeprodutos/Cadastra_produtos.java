package com.blabla.cadastrodeprodutos.cadastrodeprodutos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cadastra_produtos extends AppCompatActivity {


    DatabaseHelper DB_methods;

    //declaração intermediária entre botões
    Button bt_exibir;
    Button bt_salvar;
    Button bt_limpar;
    Button bt_voltar;

    //declaração internmediária entre campos de edição de texto
    EditText titulo_produto;
    EditText qtde_produto;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produtos);

        DB_methods = new DatabaseHelper(this);

        //botões declarados
        bt_exibir = (Button)findViewById(R.id.bt_exibir_ultimo_produto_cadastrado);
        bt_salvar = (Button)findViewById(R.id.bt_salvar);
        bt_limpar = (Button)findViewById(R.id.bt_limpar);

        //barras de texto declaradas
        titulo_produto = (EditText) findViewById(R.id.TituloCampo);
        qtde_produto = (EditText) findViewById(R.id.editText2);

        //Log.d("oi","texto");


        //limpa os campos
        bt_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                titulo_produto.setText("");
                qtde_produto.setText("");

            }
        });

        //salvar dados no banco de dados

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome_produto_pego = titulo_produto.getText().toString();
                String qtd_produto_pego = qtde_produto.getText().toString();

                if(!TextUtils.isEmpty(nome_produto_pego) && !TextUtils.isEmpty(qtd_produto_pego))
                {
                    titulo_produto.setText("");
                    qtde_produto.setText("");

                    //insere os dados
                    boolean insereNoBanco = DB_methods.inserirDados(nome_produto_pego, qtd_produto_pego);

                    Toast.makeText(Cadastra_produtos.this, "Produto inserido: " + nome_produto_pego, Toast.LENGTH_SHORT).show();


                }

                else if (!TextUtils.isEmpty(nome_produto_pego))
                {

                    String valorNulo = "-";

                    titulo_produto.setText("");
                    qtde_produto.setText("");
                    boolean insereNobanco = DB_methods.inserirDados(nome_produto_pego, valorNulo);
                    Toast.makeText(Cadastra_produtos.this, "Produto cadastrado sem quantidade", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    titulo_produto.setText("");
                    qtde_produto.setText("");
                    Toast.makeText(Cadastra_produtos.this, "Sem alterações de dados", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //recupera a ultima linha inserida

        bt_exibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Cursor cursor = DB_methods.recupera_dados_simples();

                if (DB_methods.DATABASE_TABLE_NAME != null)
                {
                    //posiciona o cursor
                    cursor.moveToFirst();

                    //exibe o resultado da função pelo clique do botão para exibir os dados

                    String nome_resultado_minimo = cursor.getString(cursor.getColumnIndex(DB_methods.
                    CAMPO_NOME_PRODUTO));

                    Toast.makeText(Cadastra_produtos.this, "Exibindo: "
                            + nome_resultado_minimo,
                            Toast.LENGTH_LONG).show();
                }

                else {
                    Toast.makeText(Cadastra_produtos.this, "algo errado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //voltar a activity principal, abaixo o mesmo está instanciado
        bt_voltar = (Button)findViewById(R.id.bt_voltar);

        //aciona o botão pelo clique
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarAMain = new Intent(Cadastra_produtos.this , MainActivity.class);
                startActivity(voltarAMain);
            }
        });

    }

    //define o botão voltar do android como falso nessa activity, ao inves de pressionar, volta a activity por meio de um intent, pois assim atualiza o banco de dados da aplicação.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            //intent que retorna para activity principal
            Intent voltarAMain = new Intent(Cadastra_produtos.this, MainActivity.class);
            startActivity(voltarAMain);
        }
        return false;
        // retorna false quando o botão de voltar é pressionado
    }


}
