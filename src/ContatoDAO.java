
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
public class ContatoDAO {

    private Connection con = null;

    public ContatoDAO() {
        con = ConnectionFactory.getConnection();
    }

    // <editor-fold defaultstate="collapsed" desc="Comando(s) de Criação/Escrita (CREATE)">
    public void add(Contato contato) throws SQLException {

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
            ConnectionFactory.closeConnection(ps);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Comando(s) de Leitura/Busca (READ)">
    public Contato getContato(int id) throws ContatoNaoExisteException, SQLException {

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
            ConnectionFactory.closeConnection(ps, rs);
        }

        return contato;
    }

    public List<Contato> getContatosPorNome(String nome) throws ContatoNaoExisteException, SQLException {

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
            ConnectionFactory.closeConnection(ps, rs);
        }

        return contatos;
    }

    public List<Contato> getContatosPorTelefone(String telefone) throws ContatoNaoExisteException, SQLException {

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
            ConnectionFactory.closeConnection(ps, rs);
        }

        return contatos;
    }

    public List<Contato> getContatosPorEmail(String email) throws ContatoNaoExisteException, SQLException {

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
            ConnectionFactory.closeConnection(ps, rs);
        }

        return contatos;
    }

    public List<Contato> getContatos() throws ContatoNaoExisteException, SQLException {

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
            ConnectionFactory.closeConnection(ps, rs);
        }

        return contatos;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Comando(s) de Atualização/Alteração (UPDATE)">
    public void update(Contato contato) throws ContatoNaoExisteException, SQLException {

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("UPDATE contato SET nome = ?, telefone = ?, email = ? WHERE id = ?");
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.setInt(4, contato.getId());

            if (ps.executeUpdate() == 0) throw new ContatoNaoExisteException("Não existe contato com esse ID.");
        } catch (SQLException e) {
            throw new SQLException("Não foi possível alterar/atualizar o contato.");
        } finally {
            ConnectionFactory.closeConnection(ps);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Comando(s) de Exclusão/Remoção (DELETE)">
    public void delete(int id) throws ContatoNaoExisteException, SQLException {

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM contato WHERE id = ?");
            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) throw new ContatoNaoExisteException();
        } catch (SQLException e) {
            throw new SQLException("Não foi possível excluir/remover o contato.");
        } finally {
            ConnectionFactory.closeConnection(ps);
        }
    }
    // </editor-fold>

    public void close() {
        ConnectionFactory.closeConnection(con);
    }
}
