package com.pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pizza.Pizza;

public class PizzaLect {

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

            String sql = "SELECT * FROM pizza"; 

            rs = st.executeQuery(sql);

            // Parcourir les enregistrements (rows) du ResultSet
            while (rs.next()) {
                // Extraire les informations de la pizza depuis le ResultSet
                String nroPizza = rs.getString(1);
                String designPizza = rs.getString(2);
                String tarifPizza = rs.getString(3);

                // Créer un objet Pizza avec les informations extraites
                Pizza itemPizza = new Pizza(nroPizza, designPizza, tarifPizza);

                // Afficher l'objet Pizza créé
                System.out.println(itemPizza);
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
