package repositories.impl;

import models.Subscription;
import repositories.SubscriptionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionRepositoryImpl implements SubscriptionRepository {
    private final Connection connection;

    public SubscriptionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Subscription subscription) {
        String query = "INSERT INTO subscriptions (clientid, type, startdate, enddate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setInt(1, subscription.getClientId());
            prst.setString(2, subscription.getType());
            prst.setString(3, subscription.getStartDate());
            prst.setString(4, subscription.getEndDate());
            prst.executeUpdate();
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Subscription> getAll() {
        List<Subscription> subscriptions = new ArrayList<>();
        String query = "SELECT * FROM subscriptions";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                subscriptions.add(new Subscription(
                        rs.getInt("id"),
                        rs.getInt("clientid"),
                        rs.getString("type"),
                        rs.getString("startdate"),
                        rs.getString("enddate")
                ));
            }
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return subscriptions;
    }

    @Override
    public Subscription getById(int id) {
        String query = "SELECT * FROM subscriptions WHERE id = ?";
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setInt(1, id);
            try (ResultSet rs = prst.executeQuery()) {
                if (rs.next()) {
                    return new Subscription(
                            rs.getInt("id"),
                            rs.getInt("clientid"),
                            rs.getString("type"),
                            rs.getString("startdate"),
                            rs.getString("enddate")
                    );
                }
            }
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM subscriptions WHERE id = ?";
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setInt(1, id);
            prst.executeUpdate();
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }
}
