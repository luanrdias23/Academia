package com.example;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.data.ExercicioDao;
import com.example.data.TreinoDao;
import com.example.model.Exercicio;
import com.example.model.Treino;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class PrimaryController implements Initializable{

    @FXML TextField txtNomeExercicio;
    @FXML TextField txtRepeticoes;
    @FXML TextField txtPeso;
    @FXML TextField txtEquipamento;
    @FXML ComboBox<Treino> cbTreino;

    @FXML TableView<Exercicio> tabelaExercicios;

    @FXML TableColumn<Exercicio, String> colNomeExercicio;
    @FXML TableColumn<Exercicio, String> colRepeticoes;
    @FXML TableColumn<Exercicio, String> colPeso;
    @FXML TableColumn<Exercicio, String> colEquipamento;
    @FXML TableColumn<Treino, String> colTreino;

    @FXML TextField txtNomeTreino;

    @FXML TableView<Treino> tabelaTreinos;

    @FXML TableColumn<Treino, String> colNomeTreino;

    TreinoDao treinoDao;
    ExercicioDao exercicioDao;

    public void adicionarExercicio(){
        var novoExercicio = criaExercicio();
        try {
            exercicioDao.inserirExercicio(novoExercicio);
            tabelaExercicios.getItems().add(novoExercicio);
            carregarExercicio();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarTreino(){
        var novoTreino = criaTreino();
        try {
            treinoDao.inserirTreino(novoTreino);
            tabelaTreinos.getItems().add(novoTreino);
            carregarTreino();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void apagarExercicio(){
        var exercicio = tabelaExercicios.getSelectionModel().getSelectedItem();
        try {
            exercicioDao.apagarExercicio(exercicio.getId());
            tabelaExercicios.getItems().remove(exercicio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void apagarTreino(){
        var treino = tabelaTreinos.getSelectionModel().getSelectedItem();
        try {
            treinoDao.apagarTreino(treino.getId());
            tabelaTreinos.getItems().remove(treino);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarExercicio(Exercicio exercicio){
        try {
            exercicioDao.atualizarExercicio(exercicio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarTreino(Treino treino){
        try {
            treinoDao.atualizarTreino(treino);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarExercicio(){
        tabelaExercicios.getItems().clear();
        try {
            limparCamposExercicio();
            var exercicios = exercicioDao.buscarTudo();
            exercicios.forEach(exercicio -> tabelaExercicios.getItems().add(exercicio));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarTreino(){
        tabelaTreinos.getItems().clear();
        try {
            txtNomeTreino.clear();
            var treinos = treinoDao.buscarTudo();
            treinos.forEach(treino -> tabelaTreinos.getItems().add(treino));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void limparCamposExercicio() {
        txtNomeExercicio.clear();
        txtRepeticoes.clear();
        txtPeso.clear();
        txtEquipamento.clear();
    }

    private Exercicio criaExercicio() {
        return new Exercicio(
            null,
            txtNomeExercicio.getText(), 
            txtRepeticoes.getText(), 
            txtPeso.getText(), 
            txtEquipamento.getText(), 
            cbTreino.getSelectionModel().getSelectedItem()
        );
    }

    private Treino criaTreino() {
        return new Treino(
            null, 
            txtNomeTreino.getText()
        );
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colNomeExercicio.setCellValueFactory(new PropertyValueFactory<>("nomeExercicio"));
        colNomeExercicio.setCellFactory(TextFieldTableCell.forTableColumn());
        colNomeExercicio.setOnEditCommit(c -> atualizarExercicio(c.getRowValue().nome(c.getNewValue())));

        colRepeticoes.setCellValueFactory(new PropertyValueFactory<>("repeticoes"));
        colRepeticoes.setCellFactory(TextFieldTableCell.forTableColumn());
        colRepeticoes.setOnEditCommit(c -> atualizarExercicio(c.getRowValue().repeticoes(c.getNewValue())));

        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colPeso.setCellFactory(TextFieldTableCell.forTableColumn());
        colPeso.setOnEditCommit(c -> atualizarExercicio(c.getRowValue().peso(c.getNewValue())));

        colEquipamento.setCellValueFactory(new PropertyValueFactory<>("equipamento"));
        colEquipamento.setCellFactory(TextFieldTableCell.forTableColumn());
        colEquipamento.setOnEditCommit(c -> atualizarExercicio(c.getRowValue().equipamento(c.getNewValue())));

        colTreino.setCellValueFactory(new PropertyValueFactory<>("treino"));

        colNomeTreino.setCellValueFactory(new PropertyValueFactory<>("nomeTreino"));
        colNomeTreino.setCellFactory(TextFieldTableCell.forTableColumn());
        colNomeTreino.setOnEditCommit(c -> atualizarTreino(c.getRowValue().nome(c.getNewValue())));

        try {
            treinoDao = new TreinoDao();
            exercicioDao = new ExercicioDao();
            cbTreino.getItems().addAll(treinoDao.buscarTudo());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        carregarExercicio();
        carregarTreino();

        tabelaExercicios.setEditable(true);
        tabelaTreinos.setEditable(true);
    }

    
}