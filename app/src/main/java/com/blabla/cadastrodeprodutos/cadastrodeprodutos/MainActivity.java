package com.blabla.cadastrodeprodutos.cadastrodeprodutos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.blabla.cadastrodeprodutos.cadastrodeprodutos.models.Produto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CustomAdapterRecycler.OnProdutoListener {

    private CustomAdapterRecycler mAdapter;

    Button bt_viewBusca;

    private CustomAdapterProduto CS;

    DatabaseHelper DB_methods;

    RecyclerView listaPrincipal;
    String[] listados_fake;

    ArrayAdapter<String> adaptadorFake;

    String[] listado;

    Menu item;

    SearchView BarraDebusca;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passaaocadastrodeprodutos = new Intent(MainActivity.this, Cadastra_produtos.class);
                startActivity(passaaocadastrodeprodutos);
            }
        });


        listaPrincipal = (RecyclerView) findViewById(R.id.recyclerview);

        listados_fake = getResources().getStringArray(R.array.planets_array);

        listado = listados_fake;

        //campos de lista e array usandos como testes comentados abaixo

        //String[] listador_faker = {"Terra " + "1", "Venus " + "2", "Marte " + "3", "Jupiter " + "4", "Saturno " + "5"};



       /* if (listados_fake != null)
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, listados_fake);
            listaPrincipal.setAdapter(adapter);
            Toast.makeText(this, "A lista não está vazia", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.d( "Erro: ","Não foi possível popular a lista");
            Toast.makeText(this, "Não foi possível popular a lista", Toast.LENGTH_SHORT).show();
        }*/


        //clique na lista, passível de substituição
        /*listaPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent produto_selecionado_intent = new Intent(MainActivity.this, Produto_Activity.class);
                startActivity(produto_selecionado_intent);
            }
        });*/

        DB_methods = new DatabaseHelper(this);

        populaLista();


        //instancia a barra de busca

        BarraDebusca = (SearchView) findViewById(R.id.barradebusca);




        //zzz

        BarraDebusca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                xingalista(newText);

                return false;


            }

        });

    }


    public void populaLista()
    {


        Log.d("TAG", "Fazendo deploy");


        List<Produto> produtos = new ArrayList<>();


        Cursor data = DB_methods.recupera_dados_mais_completos();
        //final CS<Integer> listId = new ArrayList<>();
        //final ArrayList<String> listData = new ArrayList<>();
        //final ArrayList<String> listQTD = new ArrayList<>();
        while (data.moveToNext())
        {

            //listId.add(data.getInt(0));
            //listData.add(data.getString(1));
            //listQTD.add(data.getString(2));

            Produto produto = new Produto();
            produto.nome = data.getString(1);
            produto.qtde = data.getString(2);
            produto.id = data.getInt(0);

            produtos.add(produto);


            //listId.toString();

            //teste de valores incubados
           // Toast.makeText(this, "ver: " + listId + " " + listData + " " + listQTD,  Toast.LENGTH_SHORT).show();




        }

        mAdapter = new CustomAdapterRecycler(produtos, this, this);


        listaPrincipal.setAdapter(mAdapter);

//        listaPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
//
//                Integer umaStringParaOutraActivityComId = listId.get(position);
//                String umaStringParaOutraActivity = listData.get(position);
//                String umaStringParaOutraActivityComQTD = listQTD.get(position);
//
//
//                Intent intentProdutoSelecionado = new Intent(MainActivity.this, Produto_Activity.class);
//                //leva os dados para o intent
//                intentProdutoSelecionado.putExtra(Produto_Activity.TITLE_PRODUTO, umaStringParaOutraActivity);
//                intentProdutoSelecionado.putExtra(Produto_Activity.QTD_PRODUTO, umaStringParaOutraActivityComQTD);
//                intentProdutoSelecionado.putExtra(Produto_Activity.ID_PRODUTO, umaStringParaOutraActivityComId);
//
//
//                startActivity(intentProdutoSelecionado);
//
//
//            }
//        });



       // ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);








//        });
//
    }

    public void xingalista(String newText)
    {

        List<Produto> produtos = new ArrayList<>();

        Cursor xinga = DB_methods.QuerySearchNomeDeProduto(newText);


        while (xinga.moveToNext()){


            Produto produto = new Produto();
            produto.nome = xinga.getString(1);
            produto.qtde = xinga.getString(2);
            produto.id = xinga.getInt(0);

            produtos.add(produto);

            Log.d("XingaMethod" , produto.nome);

        }


        mAdapter.update(produtos);














    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId())
        {
            case R.id.action_sair:
                Toast.makeText(this, "encerrado", Toast.LENGTH_LONG).show();
                //System.exit(0);
                finish();
        }



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
            return true;

        }


        return super.onOptionsItemSelected(item);




    }

    @Override
    public void onProdutoItemClick(Produto produto) {

        Integer iddoproduto = produto.id;
        String TITLE_PRODUTO1 = produto.nome;
        String qtd = produto.qtde;

        Intent passaaoProduto = new Intent(MainActivity.this , Produto_Activity.class);
        passaaoProduto.putExtra(Produto_Activity.ID_PRODUTO, iddoproduto);
        passaaoProduto.putExtra(Produto_Activity.TITLE_PRODUTO, TITLE_PRODUTO1);
        passaaoProduto.putExtra(Produto_Activity.QTD_PRODUTO, qtd);
        startActivity(passaaoProduto);
    }
}
