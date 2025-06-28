/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jogo;
import model.Status;

/**
 *
 * @author toazz
 */

public class PersistenciaJPA implements InterfaceBD {

    private EntityManager entity;
    private EntityManagerFactory factory;

    public PersistenciaJPA() {
        factory = Persistence.createEntityManagerFactory("pf_lpoo_marjorytoazza");
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        return entity != null && entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        if (entity != null && entity.isOpen()) {
            entity.close();
        }
    }

    @Override
    public void persist(Object o) throws Exception {
        entity = getEntityManager();
        try {
            entity.getTransaction().begin();
            if (!entity.contains(o)) {
                o = entity.merge(o);
            }
            entity.persist(o);
            entity.getTransaction().commit();
        } catch (Exception e) {
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
            Logger.getLogger(PersistenciaJPA.class.getName()).log(Level.SEVERE, "Erro ao persistir: " + o.getClass().getSimpleName(), e);
            throw e;
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        entity = getEntityManager();
        try {
            entity.getTransaction().begin();
            if (!entity.contains(o)) {
                o = entity.merge(o);
            }
            entity.remove(o);
            entity.getTransaction().commit();
        } catch (Exception e) {
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
            Logger.getLogger(PersistenciaJPA.class.getName()).log(Level.SEVERE, "Erro ao remover: " + o.getClass().getSimpleName(), e);
            throw e;
        }
    }

    public EntityManager getEntityManager() {
        if (entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity;
    }

    @Override
    public Jogo buscarJogoPorId(int id) throws Exception {
        return (Jogo) this.find(Jogo.class, id);
    }

    @Override
    public List<Jogo> listarJogos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Jogo> query = em.createQuery("SELECT j FROM Jogo j ORDER BY j.nome ASC", Jogo.class);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // Retorna o total de jogos cadastrados
    @Override
    public int getTotalJogos() {
        List<Jogo> todosOsJogos = this.listarJogos();
        int contador = 0;
        for (Jogo jogo : todosOsJogos) {
            contador++;
        }
        return contador;
    }
    
    // Retorna o total de jogos finalizados
    @Override
    public int getTotalJogosFinalizados() {
        List<Jogo> todosOsJogos = this.listarJogos();
        int contador = 0;
        for (Jogo jogo : todosOsJogos) {
            if (jogo.getStatus() == Status.FINALIZADO) {
                contador++;
            }
        }
        return contador;
    }

    // Retorna o total de jogos em andamento
    @Override
    public int getTotalJogosEmAndamento() {
        List<Jogo> todosOsJogos = this.listarJogos();
        int contador = 0;
        for (Jogo jogo : todosOsJogos) {
            if (jogo.getStatus() == Status.JOGANDO) {
                contador++;
            }
        }
        return contador;
    }

    // Média de todas as notas dos jogos
    @Override
    public Double getMediaNotas() {
        List<Jogo> todosOsJogos = this.listarJogos();
        if (todosOsJogos.isEmpty()) {
            return 0.0; 
        }
        double somaDasNotas = 0.0;
        for (Jogo jogo : todosOsJogos) { 
            somaDasNotas += jogo.getNota(); 
        }
        return somaDasNotas / todosOsJogos.size(); 
    }

    // Filtro para os jogos
    @Override
    public List<Jogo> filtrarJogos(Status status, String nome) {
        List<Jogo> todosOsJogos = this.listarJogos(); 
        List<Jogo> jogosFiltrados = new ArrayList<>();

        for (Jogo jogo : todosOsJogos) { 
            boolean corresponde = true;

            if (status != null && jogo.getStatus() != status) {
                corresponde = false;
            }

            if (corresponde && nome != null && !nome.trim().isEmpty()) {
                if (!jogo.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    corresponde = false;
                }
            }

            if (corresponde) {
                jogosFiltrados.add(jogo);
            }
        }
        return jogosFiltrados;
    }

    @Override
    public Object find(Class o, Object id) throws Exception {
       return this.getEntityManager().find(o, id);
    }
}
