package com.pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommandeLect {

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

            String sql = "SELECT * FROM commande"; 

            rs = st.executeQuery(sql);

            // Parcourir les enregistrements (rows) du ResultSet
            while (rs.next()) {
                // Extraire les informations de la commande depuis le ResultSet
                String nroCmde = rs.getString(1);
                String dateCmde = rs.getString(2);
                String heureCmde = rs.getString(3);
                String nroClieCmde = rs.getString(4);
                String nroLivrCmde = rs.getString(5);

                // Créer un objet Commande avec les informations extraites
                Commande commande = new Commande(nroCmde, dateCmde, heureCmde, nroClieCmde, nroLivrCmde);

                // Afficher l'objet Commande créé
                System.out.println(commande);
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
