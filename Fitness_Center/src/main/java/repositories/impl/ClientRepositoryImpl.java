package repositories.impl;

import models.Client;
import repositories.ClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {
    private final Connection connection;

    public ClientRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Client client) {
        String query = "INSERT INTO clients (firstName, lastName, email, phone, registrationDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setString(1, client.getFirstName());
            prst.setString(2, client.getLastName());
            prst.setString(3, client.getEmail());
            prst.setString(4, client.getPhone());
            prst.setString(5, client.getRegistrationDate());
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Client> getAll() {

        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("registrationDate")
                ));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return clients;

    }


    @Override
    public Client getById(int id) {
        String query = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                return new Client(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("registrationDate")
                );
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
            return null;

    }


    @Override
    public void delete(int id) {
        String query = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setInt(1, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
