package Conexao;

import java.sql.*;
import javax.swing.*;

public class Conexao {
 final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost:3306/Restaurante?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"; 
    final private String usuario = "root";
    final private String senha = "";

    public Connection conexao;
    public Statement statement;
    public ResultSet resultset;

    public boolean conecta() {
        boolean result = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
            result = false;
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com a fonte de dados: " + Fonte);
            result = false;
        }
        return result;
    }

    public void executaSQL(String sql) {
        try {
            resultset = statement.executeQuery(sql);
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "Erro no comando SQL: " + sqlex);
        }
    }

    public void desconecta() {
        boolean result = true;
        try {
            conexao.close();
            JOptionPane.showMessageDialog(null, "Banco de dados fechado");
        } catch (SQLException fecha) {
            JOptionPane.showMessageDialog(null, "Não foi possível fechar o banco de dados: " + fecha);
            result = false;
        }
    }
}
