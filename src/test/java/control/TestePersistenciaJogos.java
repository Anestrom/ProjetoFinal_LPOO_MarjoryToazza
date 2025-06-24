/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.Genero;
import model.Jogo;
import model.Plataforma;
import model.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toazz
 */
public class TestePersistenciaJogos {

    private PersistenciaJPA jpa;

    @Before
    public void setUp() {
        jpa = new PersistenciaJPA();
        System.out.println("Iniciando teste de conexão...");
    }

    @After
    public void tearDown() {
        if (jpa != null && jpa.conexaoAberta()) {
            jpa.fecharConexao();
            System.out.println("Conexão fechada com sucesso.");
        }
    }

    @Test
    public void testeConexaoAberta() {
        assertNotNull("Instância JPA não pode ser nula", jpa);
        assertTrue("A conexão deveria estar aberta", jpa.conexaoAberta());
        System.out.println("Conexão aberta com sucesso!");
    }

    @Test
    public void testeCadastroEListagemJogos(){
        System.out.println("Executando teste de cadastro e listagem...");
        
        try{
            // Cria um novo objeto Jogo
            Jogo novoJogo = new Jogo();
            novoJogo.setNome("Hollow Knight");
            novoJogo.setPlataforma(Plataforma.NINTENDO);
            novoJogo.setGenero(Genero.AVENTURA);
            novoJogo.setStatus(Status.JOGANDO);
            novoJogo.setNota(10);
            novoJogo.setComentarios("Excelente");
            
            // Persiste o objeto no banco
            jpa.persist(novoJogo);
            System.out.println("Jogo " + novoJogo.getNome() + " persistido com sucesso");
        } catch (Exception ex){
            System.err.println("Erro ao persistir Jogo: " + ex);
        }
        
        List<Jogo> listaJogos = jpa.listarJogos();

        if (listaJogos.isEmpty()) {
            System.out.println("Não há Jogos cadastradas");
        }
        
        System.out.println("Imprimindo jogos encontrados:");
        for(Jogo j: listaJogos){
            System.out.println("-> Nome: " + j.getNome() +" | Plataforma: "+ j.getPlataforma());
        }
    }
}
