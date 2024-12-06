package repositories;


import models.Client;
import models.Trainer;
import models.Subscription;
import models.Training;
import repositories.impl.ClientRepositoryImpl;
import repositories.impl.TrainerRepositoryImpl;
import repositories.impl.TrainingRepositoryImpl;
import repositories.impl.SubscriptionRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/fitness_site";
        String username = "postgres";
        String password = "rootwert";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("коннект успешен");


            ClientRepositoryImpl clientRepo = new ClientRepositoryImpl(connection);
            TrainerRepositoryImpl trainerRepo = new TrainerRepositoryImpl(connection);
            TrainingRepositoryImpl trainingrepo = new TrainingRepositoryImpl(connection);
            SubscriptionRepositoryImpl subscriptionRepo = new SubscriptionRepositoryImpl(connection);


            testClients(clientRepo);
            testTrainers(trainerRepo);
            testClasses(trainingrepo);
            testSubscriptions(subscriptionRepo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testClients(ClientRepositoryImpl clientRepo) throws SQLException {
        System.out.println("Клиенты");


        Client newClient = new Client(0, "Bulat", "Gizzatullin", "kdsf@gmail", "550055", "today");
        clientRepo.add(newClient);
        System.out.println("добавлелние клиента: " + newClient);


        List<Client> clients = clientRepo.getAll();
        System.out.println("все клиенты " + clients);


        Client clientById = clientRepo.getById(9);
        System.out.println("клиент по определенному айди: " + clientById);


        clientRepo.delete(0);
        System.out.println("клиент с опр айди удален");
    }

    private static void testTrainers(TrainerRepositoryImpl trainerRepo) throws SQLException {
        System.out.println("Тренеры");


        Trainer newTrainer = new Trainer(0, "Islam", "Swimming", "880550111");
        trainerRepo.add(newTrainer);
        System.out.println("новый тренер добавлен: " + newTrainer);


        List<Trainer> trainers = trainerRepo.getAll();
        System.out.println("все тренеры: " + trainers);


        Trainer trainerById = trainerRepo.getById(0);
        System.out.println("тренер с опр айди: " + trainerById);


        trainerRepo.delete(0);
        System.out.println("тренер с опр айди удален");
    }

    private static void testClasses(TrainingRepositoryImpl classRepo) throws SQLException {
        System.out.println("Упражнения");


        Training newClass = new Training(0, "cardio", "basic cardio", "yesterday", 8);
        classRepo.add(newClass);
        System.out.println(" новое упр добавлено " + newClass);


        List<Training> classes = classRepo.getAll();
        System.out.println("все упражнения: " + classes);


        Training classById = classRepo.getById(0);
        System.out.println("упр с опр айди: " + classById);


        classRepo.delete(0);
        System.out.println("упр с опр айди удален");
    }

    private static void testSubscriptions(SubscriptionRepositoryImpl subscriptionRepo) throws SQLException {
        System.out.println("Абонементы");


        Subscription newSubscription = new Subscription(0, 7, "годовой", "2024-01-01", "2025-01-01");
        subscriptionRepo.add(newSubscription);
        System.out.println("новый абон добавлен: " + newSubscription);


        List<Subscription> subscriptions = subscriptionRepo.getAll();
        System.out.println("все абоны: " + subscriptions);


        Subscription subscriptionById = subscriptionRepo.getById(0);
        System.out.println("абон с опр айди найден: " + subscriptionById);


        subscriptionRepo.delete(0);
        System.out.println("абон с опр айди удален");
    }
}
