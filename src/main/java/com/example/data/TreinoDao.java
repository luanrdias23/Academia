package com.example.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Treino;

public class TreinoDao {

    private Connection conexao;
    
    public TreinoDao() throws SQLException{
        conexao = ConnectionFactory.getConnection();
    }

    public void inserirTreino(Treino novoTreino) throws SQLException{
        var sql = "INSERT INTO treino (nome_treino) VALUES (?) ";
        var comando = conexao.prepareStatement(sql);
        comando.setString(1,novoTreino.getNomeTreino());

        comando.executeUpdate();
        conexao.close();
    }

    public void apagarTreino(Integer id) throws SQLException {
        buscarTudo();
        var comando = conexao.prepareStatement("DELETE FROM treino WHERE id=?");
        comando.setInt(1, id);
        comando.executeUpdate();
    }

    public void atualizarTreino(Treino treino) throws SQLException{
        var comando = conexao.prepareStatement("UPDATE treino SET nome_treino =? WHERE id=?");
        comando.setString(1,treino.getNomeTreino());
        comando.setInt(2,treino.getId());
        comando.executeUpdate();
    }

    public List<Treino> buscarTudo() throws SQLException{

        var lista = new ArrayList<Treino>();
        var comando = conexao.prepareStatement("SELECT * FROM treino");
        var resultado = comando.executeQuery();


        while(resultado.next()){
            lista.add(new Treino(
                resultado.getInt("id"),
                resultado.getString("nome_treino")
            ));
        }
        return lista;

    }


}
