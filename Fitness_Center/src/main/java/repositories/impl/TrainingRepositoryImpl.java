package repositories.impl;

import models.Training;
import repositories.TrainingRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainingRepositoryImpl implements TrainingRepository {
    private final Connection connection;

    public TrainingRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Training fitnessClass) {
        String query = "INSERT INTO trainings (name, description, schedule, trainerid) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fitnessClass.getName());
            stmt.setString(2, fitnessClass.getDescription());
            stmt.setString(3, fitnessClass.getSchedule());
            stmt.setInt(4, fitnessClass.getTrainerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Training> getAll() {
        List<Training> classes = new ArrayList<>();
        String query = "SELECT * FROM trainings";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                classes.add(new Training(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("schedule"),
                        rs.getInt("trainerid")
                ));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return classes;
    }

    @Override
    public Training getById(int id) {
        String query = "SELECT * FROM trainings WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Training(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("schedule"),
                            rs.getInt("trainerid")
                    );
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM trainings WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }
}
