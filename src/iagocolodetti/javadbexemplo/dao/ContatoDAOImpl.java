package iagocolodetti.javadbexemplo.dao;

import iagocolodetti.javadbexemplo.exception.ContatoNaoExisteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iagocolodetti
 */
public class ContatoDAOImpl implements ContatoDAO {

    private ConnectionFactory cf;

    public ContatoDAOImpl() {
        cf = new ConnectionFactory();
    }

    // <editor-fold defaultstate="expand" desc="Comando(s) de Criação/Escrita (CREATE)">
    @Override
    public void add(Contato contato)
            throws SQLException {

        Connection con = cf.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("INSERT INTO contato(nome, telefone, email) VALUES(?, ?, ?)");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Não foi possível adicionar o contato.");
        } finally {
            cf.closeConnection(con, ps);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="expand" desc="Comando(s) de Leitura/Busca (READ)">
    @Override
    public Contato getContato(int id)
            throws ContatoNaoExisteException, SQLException {

        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Contato contato = null;

        try {
            ps = con.prepareStatement("SELECT * FROM contato WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                contato = new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"));
            }

            if (contato == null) {
                throw new ContatoNaoExisteException("Não existe contato com esse ID.");
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar o contato.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }

        return contato;
    }

    @Override
    public List<Contato> getContatosPorNome(String nome)
            throws ContatoNaoExisteException, SQLException {

        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Contato> contatos = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM contato WHERE nome LIKE ?");
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Contato contato = new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"));
                contatos.add(contato);
            }

            if (contatos.isEmpty()) {
                throw new ContatoNaoExisteException("Não existem contatos com esse nome ou parte dele.");
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar os contatos.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }

        return contatos;
    }

    @Override
    public List<Contato> getContatosPorTelefone(String telefone)
            throws ContatoNaoExisteException, SQLException {

        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Contato> contatos = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM contato WHERE telefone LIKE ?");
            ps.setString(1, "%" + telefone + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Contato contato = new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"));
                contatos.add(contato);
            }

            if (contatos.isEmpty()) {
                throw new ContatoNaoExisteException("Não existem contatos com esse telefone ou parte dele.");
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar os contatos.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }

        return contatos;
    }
    
    @Override
    public List<Contato> getContatosPorEmail(String email)
            throws ContatoNaoExisteException, SQLException {

        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Contato> contatos = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM contato WHERE email LIKE ?");
            ps.setString(1, "%" + email + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Contato contato = new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"));
                contatos.add(contato);
            }

            if (contatos.isEmpty()) {
                throw new ContatoNaoExisteException("Não existem contatos com esse e-mail ou parte dele.");
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar os contatos.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }

        return contatos;
    }

    @Override
    public List<Contato> getContatos()
            throws ContatoNaoExisteException, SQLException {

        Connection con = cf.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Contato> contatos = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM contato");
            rs = ps.executeQuery();

            while (rs.next()) {
                Contato contato = new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"));
                contatos.add(contato);
            }

            if (contatos.isEmpty()) {
                throw new ContatoNaoExisteException("Não existem contatos.");
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível buscar os contatos.");
        } finally {
            cf.closeConnection(con, ps, rs);
        }

        return contatos;
    }
    // </editor-fold>

    // <editor-fold defaultstate="expand" desc="Comando(s) de Atualização/Alteração (UPDATE)">
    @Override
    public void update(Contato contato)
            throws ContatoNaoExisteException, SQLException {

        Connection con = cf.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("UPDATE contato SET nome = ?, telefone = ?, email = ? WHERE id = ?");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.setInt(4, contato.getId());

            if (ps.executeUpdate() == 0) {
                throw new ContatoNaoExisteException("Não existe contato com esse ID.");
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível alterar/atualizar o contato.");
        } finally {
            cf.closeConnection(con, ps);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="expand" desc="Comando(s) de Exclusão/Remoção (DELETE)">
    @Override
    public void delete(int id)
            throws ContatoNaoExisteException, SQLException {

        Connection con = cf.getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM contato WHERE id = ?");
            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new ContatoNaoExisteException();
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível excluir/remover o contato.");
        } finally {
            cf.closeConnection(con, ps);
        }
    }
    // </editor-fold>
}
