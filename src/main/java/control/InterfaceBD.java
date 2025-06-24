/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control;

import java.util.List;
import model.Jogo;

/**
 *
 * @author toazz
 */
public interface InterfaceBD {
    public Boolean conexaoAberta();
    public void fecharConexao();
    public Object find(Class c, Object id) throws Exception;
    public void persist(Object o) throws Exception;
    public void remover(Object o) throws Exception;
    
    public Jogo buscarJogoPorId(int id) throws Exception;
    public List<Jogo> listarJogos();
}
