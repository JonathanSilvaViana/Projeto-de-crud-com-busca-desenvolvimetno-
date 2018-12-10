package com.blabla.cadastrodeprodutos.cadastrodeprodutos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import static com.blabla.cadastrodeprodutos.cadastrodeprodutos.DatabaseHelper.CAMPO_ID_PRODUTO;

public class Produto_Activity extends AppCompatActivity {

    public static final String ID_PRODUTO = "ID_PRODUTO";
    public static final String TITLE_PRODUTO = "TitleProduto";
    public static final String QTD_PRODUTO = "QTD_PRODUTO";

    EditText campo_nome_produto;

    EditText campo_qtd_produto;

    DatabaseHelper DB_methods;

    FloatingActionButton bt_atualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        //botão que cancela a visualização e edição na activity de produto
        FloatingActionButton bt_cancela_edicao = (FloatingActionButton)findViewById(R.id.bt_retornar);


        //nomeia a activity de acordo com o que selecionou na ativity anterior
//        Intent herdaNomeDaListViewItemSelecionadaNaActivityAnterior = getIntent();
//        final String stringTitle = herdaNomeDaListViewItemSelecionadaNaActivityAnterior.getStringExtra(TITLE_PRODUTO);
//        final String stringQTD = herdaNomeDaListViewItemSelecionadaNaActivityAnterior.getStringExtra(QTD_PRODUTO);
//        final int ID = herdaNomeDaListViewItemSelecionadaNaActivityAnterior.getIntExtra(ID_PRODUTO, 0);
//this.setTitle(stringTitle);

        //nomeia a activity de acordo com o que selecionou na ativity anterior

        Intent herdaACTAnterior = getIntent();
        final String stringdoTitle = herdaACTAnterior.getStringExtra(TITLE_PRODUTO);
        final String stringQTD = herdaACTAnterior.getStringExtra(QTD_PRODUTO);
        final Integer iddoproduto = herdaACTAnterior.getIntExtra(ID_PRODUTO, 0);
        //String.valueOf(iddoproduto)
        String activityName = "Produto: ";
        setTitle(activityName + stringdoTitle);

        //colhe o id do produto de acordo com a lista selecionada


        //método de volta a página
        bt_cancela_edicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_cancelar_edicao = new Intent(Produto_Activity.this , MainActivity.class);
                startActivity(intent_cancelar_edicao);
            }
        });

        //insere titulo no campo título de produto

        campo_nome_produto = (EditText)findViewById(R.id.titulo_produto_selecionado);
        campo_nome_produto.setText(stringdoTitle);

        campo_qtd_produto = (EditText)findViewById(R.id.qtd_produto_selecionado);
        campo_qtd_produto.setText(stringQTD);

        Toast.makeText(this, "ID: " + String.valueOf(iddoproduto), Toast.LENGTH_SHORT).show();


        //Toast.makeText(this, String.valueOf(ID), Toast.LENGTH_SHORT).show();

        //adição de alterações no banco começo nesse trecho abaixo.

        DB_methods = new DatabaseHelper(this);

        bt_atualizar = (FloatingActionButton)findViewById(R.id.bt_atualizar);

        bt_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome_produto_atualizado = campo_nome_produto.getText().toString();
                String qtd_produto_atualizado = campo_qtd_produto.getText().toString();
                String referencia_id = String.valueOf(iddoproduto);

                if(!nome_produto_atualizado.equals("") && !qtd_produto_atualizado.equals(""))
                {

                    DB_methods.AtualizaRegistro(nome_produto_atualizado, qtd_produto_atualizado, iddoproduto, stringdoTitle, stringQTD);
                    Toast.makeText(Produto_Activity.this, "Dados atualizados: " + nome_produto_atualizado + ", \n Quantidade: " + qtd_produto_atualizado, Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(Produto_Activity.this, "Não houve alterações no banco", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }
}
