/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control;

import java.util.List;
import model.Jogo;
import model.Status;

/**
 *
 * @author toazz
 */
public interface InterfaceBD {
    public void persist(Object o) throws Exception;
    public void remover(Object o) throws Exception;
    public Object find(Object o, Object id) throws Exception;
    
    public Jogo buscarJogoPorId(int id) throws Exception;
    public List<Jogo> listarJogos();
    public List<Jogo> filtrarJogos(Status status, String nome);

    public int getTotalJogosFinalizados();
    public int getTotalJogosEmAndamento();
    public Double getMediaNotas();

    public Boolean conexaoAberta();
    public void fecharConexao();
}
