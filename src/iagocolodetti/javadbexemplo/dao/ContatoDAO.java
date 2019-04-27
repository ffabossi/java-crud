package iagocolodetti.javadbexemplo.dao;

import iagocolodetti.javadbexemplo.exception.ContatoNaoExisteException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author iagocolodetti
 */
public interface ContatoDAO {

    public void add(Contato contato)
            throws SQLException;

    public Contato getContato(int id)
            throws ContatoNaoExisteException, SQLException;

    public List<Contato> getContatosPorNome(String nome)
            throws ContatoNaoExisteException, SQLException;

    public List<Contato> getContatosPorTelefone(String telefone)
            throws ContatoNaoExisteException, SQLException;

    public List<Contato> getContatosPorEmail(String email)
            throws ContatoNaoExisteException, SQLException;

    public List<Contato> getContatos()
            throws ContatoNaoExisteException, SQLException;

    public void update(Contato contato)
            throws ContatoNaoExisteException, SQLException;

    public void delete(int id)
            throws ContatoNaoExisteException, SQLException;
}
