package com.blabla.cadastrodeprodutos.cadastrodeprodutos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{



    //nome do banco de dados
    public final static String DATABASE_NAME = "bancodeprodutos.db";
    public final static String DATABASE_VERSION = "1";

    //nome da única tabela do banco de dados
    public final static String DATABASE_TABLE_NAME = "produtos";

    //colunas
    public final static String CAMPO_ID_PRODUTO = "id";
    public final static String CAMPO_NOME_PRODUTO = "produto";
    public final static String CAMPO_QUANTIDADE_PRODUTO = "quantidade";



    //criação do banco
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("erro de alguma coisa 1","deu algum erro no banco");
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(

                "CREATE TABLE " +
                        DATABASE_TABLE_NAME +
                        " (" +
                        CAMPO_ID_PRODUTO +
                        " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        CAMPO_NOME_PRODUTO +
                        " text NOT NULL, " +
                        CAMPO_QUANTIDADE_PRODUTO +
                        " text NOT NULL);"

        );
        Log.e("erro de alguma coisa 2","deu algum erro no banco");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  DATABASE_TABLE_NAME);
        onCreate(db);
        Log.e("erro de alguma coisa 3","deu algum erro no banco");
    }

    public boolean inserirDados(String pdr, String qtd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAMPO_NOME_PRODUTO, pdr);
        contentValues.put(CAMPO_QUANTIDADE_PRODUTO, qtd);
        long resultado = db.insert(DATABASE_TABLE_NAME, null, contentValues);
        if (resultado == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //coleta uma query da tabela

    public Cursor recupera_dados_simples()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
        DATABASE_TABLE_NAME +
                " ORDER BY  " +
                CAMPO_ID_PRODUTO +
                ", " +
                CAMPO_NOME_PRODUTO +
                ", " +
                CAMPO_QUANTIDADE_PRODUTO +
                " DESC LIMIT 1 ",
                null
        );

        return cursor;
    }

    public Cursor recupera_dados_mais_completos()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " +
                        CAMPO_ID_PRODUTO +
                        ", " +
                        CAMPO_NOME_PRODUTO +
                        ", " +
                        CAMPO_QUANTIDADE_PRODUTO +
                        " " +
                        "FROM " +
                        DATABASE_TABLE_NAME +
                        " " +
                        " ORDER BY " +
                        CAMPO_ID_PRODUTO +
                        " DESC ",
                null
        );
        return cursor;
    }


   /*public ArrayList<String> getRow(String CAMPO_ID_PRODUTO, String CAMPO_NOME_PRODUTO, String CAMPO_QUANTIDADE_PRODUTO)
   {
       ArrayList<String> row new = ArrayList<String>();
       String query = "SELECT * FROM produtos where id NOT NULL";
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(query, new String[] { "1" });
   }*/

   public Cursor getProdutoID(String name)
   {
       SQLiteDatabase db = this.getWritableDatabase();
       String queryParaRetornarId = " SELECT " +
               CAMPO_NOME_PRODUTO +
               " FROM " +
               DATABASE_TABLE_NAME +
               " WHERE " +
               CAMPO_ID_PRODUTO +
               " = '" +
               name +
               "'";
       Cursor data = db.rawQuery(queryParaRetornarId , null);
       return data;

   }

   public void AtualizaRegistro(String novoNomeProduto, String novaQuantidadeProduto, int id, String nomeAntigoDoProduto, String qtdAntigaProduto)
   {
       SQLiteDatabase db = this.getWritableDatabase();
       String query = "UPDATE " +
               DATABASE_TABLE_NAME +
               " SET " +
               CAMPO_NOME_PRODUTO +
              " = '" +
               novoNomeProduto +
               "', " +
               CAMPO_QUANTIDADE_PRODUTO +
               " =' " +
               novaQuantidadeProduto +
               " '" +
               " WHERE " +
               CAMPO_ID_PRODUTO +
               " = " +
               id;



       Log.d("UPDATED","Execução_DB: " + query);
       Log.d("Inserido", "Produto atualizado: " + nomeAntigoDoProduto);
       db.execSQL(query);
       //update produtos set produto = 'produto 10', quantidade = '3' where id = 2

   }


   //metodo para busca no widget de busca

   public Cursor QuerySearchNomeDeProduto (String produtoParaBuscar)
   {
       SQLiteDatabase db = this.getWritableDatabase();
               String query = "SELECT " +
                       CAMPO_ID_PRODUTO +
                       " , " +
                       CAMPO_NOME_PRODUTO +
                       " , " +
                       CAMPO_QUANTIDADE_PRODUTO +
                       " FROM " +
                       DATABASE_TABLE_NAME +
                       " WHERE " +
                       CAMPO_NOME_PRODUTO +
                       " LIKE " +
                       " ? ";
               Log.d("Buscado" , ":" + query);
               Log.d("Busca realizada" , " do produto: " + produtoParaBuscar);
               //Log.e("Erro de busca..." , "Erro durante a busca:" + produtoParaBuscar + "falha na query: " + query);
               //db.execSQL(query);
                Cursor data = db.rawQuery(query , new String[] { "%" + produtoParaBuscar + "%" });
                //db.close();
               return data;


   }
}

