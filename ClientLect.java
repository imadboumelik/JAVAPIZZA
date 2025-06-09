package com.pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientLect {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/pizzadb";
        String login = "root"; // 
        String passwd = ""; 

        Connection cn = null; 
        Statement st = null; 
        ResultSet rs = null; 

        try {
            Class.forName("com.mysql.jdbc.Driver");

            cn = DriverManager.getConnection(url, login, passwd);

            st = cn.createStatement();

            String sql = "SELECT * FROM client"; 

            rs = st.executeQuery(sql);

            // Parcourir les enregistrements (rows) du ResultSet
            while (rs.next()) {
                // Extraire les informations du client depuis le ResultSet
                String nroClie = rs.getString(1);
                String nomClie = rs.getString(2);
                String prenomClie = rs.getString(3);
                String adresseClie = rs.getString(4);
                String villeClie = rs.getString(5);
                String nroTelClie = rs.getString(6);
                String titreClie = rs.getString(7);
                String passwdClient = rs.getString(8);

                // Créer un objet Client avec les informations extraites
                Client client = new Client(nroClie, nomClie, prenomClie, adresseClie, villeClie, nroTelClie, titreClie, passwdClient);

                // Afficher l'objet Client créé
                System.out.println(client);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources (ResultSet, Statement, Connection)
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
