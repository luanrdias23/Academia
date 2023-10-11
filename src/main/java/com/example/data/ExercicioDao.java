package com.example.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Exercicio;
import com.example.model.Treino;

public class ExercicioDao {

    private Connection conexao;

    public ExercicioDao() throws SQLException{
        conexao = ConnectionFactory.getConnection();
    }

    public void inserirExercicio(Exercicio exercicio) throws SQLException{

        var sql = "INSERT INTO exercicio (nome_exercicio, repeticoes, peso, equipamento, treino_id) VALUES (?, ?, ?, ?, ?) ";
        var comando = conexao.prepareStatement(sql);
        comando.setString(1,exercicio.getNomeExercicio());
        comando.setString(2,exercicio.getRepeticoes());
        comando.setString(3,exercicio.getPeso());
        comando.setString(4,exercicio.getEquipamento());
        comando.setInt(5, exercicio.getTreino().getId());

        comando.executeUpdate();

        conexao.close();
    }


    public List<Exercicio> buscarTudo() throws SQLException{

        var lista = new ArrayList<Exercicio>();
        var comando = conexao.prepareStatement("SELECT exercicio.*, treino.nome_treino FROM exercicio INNER JOIN treino ON exercicio.treino_id = treino.id");
        var resultado = comando.executeQuery();


        while(resultado.next()){
            lista.add(new Exercicio(
                resultado.getInt("id"),
                resultado.getString("nome_exercicio"),
                resultado.getString("repeticoes"),
                resultado.getString("peso"),
                resultado.getString("equipamento"),
                new Treino(
                    resultado.getInt("treino_id"),
                    resultado.getString("nome_treino"))
            ));
        }
        return lista;
    }


    public void apagarExercicio(Integer id) throws SQLException {
        buscarTudo();
        var comando = conexao.prepareStatement("DELETE FROM exercicio WHERE id=?");
        comando.setInt(1, id);
        comando.executeUpdate();
    }


    public void atualizarExercicio(Exercicio exercicio) throws SQLException{
        var comando = conexao.prepareStatement("UPDATE exercicio SET nome_exercicio =?,repeticoes=?,peso=?,equipamento=? WHERE id=?");
        comando.setString(1,exercicio.getNomeExercicio());
        comando.setString(2,exercicio.getRepeticoes());
        comando.setString(3,exercicio.getPeso());
        comando.setString(4,exercicio.getEquipamento());
        comando.setInt(5,exercicio.getId());
        comando.executeUpdate();
    }
    
}
