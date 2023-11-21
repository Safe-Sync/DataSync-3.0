package entidades;

import conexao.Conexao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class Funcionario {
    Conexao conectar = new Conexao();
    JdbcTemplate con = conectar.getConexao();
    Sistema sistema = new Sistema();

    private Integer idFuncionario;
    private String nome;
    private String email;
    private Integer fkEmpresa;

    public Funcionario(String email, String senha) {
         try {
             String sql = "SELECT idFuncionario, nomeFuncionario, email, fkEmpresa FROM funcionarios WHERE email = ? AND senha = ?";
             con.queryForObject(sql, new Object[]{email, senha}, (rs,rowN) -> {
                 this.idFuncionario = rs.getInt(1);
                 this.nome = rs.getString(2);
                 this.email = rs.getString(3);
                 this.fkEmpresa = rs.getInt(4);
                 return null;
             });

             sistema.setFuncAtual(this.email, this.fkEmpresa, this.idFuncionario);
             sistema.mensagemLoginValido(this.nome);
         } catch (EmptyResultDataAccessException e) {
             sistema.mensagemLoginInvalido();
         }
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return String.format("""
                Funcionário: {
                idFuncionario: %d
                fkEmpresa: %d
                Nome: %s
                email: %s
                }
                """, idFuncionario, fkEmpresa, nome, email);
    }
}
