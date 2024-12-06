package repositories.impl;

import models.Trainer;
import repositories.TrainerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerRepositoryImpl implements TrainerRepository {
    private final Connection connection;

    public TrainerRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Trainer trainer) {
        String query = "INSERT INTO trainers (name, qualification, phone) VALUES (?, ?, ?)";
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setString(1, trainer.getName());
            prst.setString(2, trainer.getQualification());
            prst.setString(3, trainer.getPhone());
            prst.executeUpdate();
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Trainer> getAll() {
        List<Trainer> trainers = new ArrayList<>();
        String query = "SELECT * FROM trainers";
        try (Statement prst = connection.createStatement(); ResultSet rs = prst.executeQuery(query)) {
            while (rs.next()) {
                trainers.add(new Trainer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("qualification"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return trainers;
    }

    @Override
    public Trainer getById(int id) {
        String query = "SELECT * FROM trainers WHERE id = ?";
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setInt(1, id);
            try (ResultSet rs = prst.executeQuery()) {
                if (rs.next()) {
                    return new Trainer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("qualification"),
                            rs.getString("phone")
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
        String query = "DELETE FROM trainers WHERE id = ?";
        try (PreparedStatement prst = connection.prepareStatement(query)) {
            prst.setInt(1, id);
            prst.executeUpdate();
        } catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }
}
