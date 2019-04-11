package ru.yusdm.javacore.lesson22relationaldb.lesson;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.ModelDiscriminator;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.PassengerModel;
import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.TruckModel;
import ru.yusdm.javacore.lesson22relationaldb.lesson.connect.HikariConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 4/10/2019.
 */
public class DataBaseDemo {

    public static void main(String[] args) throws Exception {

        HikariConnectionPool hikariConnectionPool = new HikariConnectionPool();
        try (Connection connection = hikariConnectionPool.getConnection()) {
            //selectModels_A(connection);
            // selectMarks_B(connection);
            //selectMarks_C(connection, "Ford");
            insertAndReturnId(connection);
        }

    }


    private static void selectModels_A(Connection con) throws SQLException {
        List<Model> models = new ArrayList<>();

        String sql = "SELECT * FROM MODEL";
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {


            while (rs.next()) {
                String discriminator = rs.getString("DISCRIMINATOR");
                Optional<ModelDiscriminator> discriminatorByName = ModelDiscriminator.getDiscriminatorByName(discriminator);

                if (discriminatorByName.isPresent()) {
                    ModelDiscriminator disc = discriminatorByName.get();
                    Model model = null;
                    switch (disc) {

                        case PASSENGER: {
                            model = new PassengerModel();
                            ((PassengerModel) model).setNumberOfSeats(rs.getInt("NUMBER_OF_SEATS"));
                            break;
                        }
                        case TRUCK: {
                            model = new TruckModel();
                            break;
                        }
                    }

                    model.setName(rs.getString("NAME"));
                    model.setId(rs.getLong("ID"));
                    models.add(model);
                }

            }

        }

        System.out.println();
    }


    private static void selectMarks_B(Connection con) throws SQLException {
        List<Mark> marks = new ArrayList<>();

        String sql = "SELECT * FROM MARK WHERE NAME = 'Ford'";
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Mark mark = new Mark();
                mark.setId(rs.getLong("ID"));
                mark.setName(rs.getString("NAME"));
                marks.add(mark);
            }

        }

        System.out.println();
    }

    private static void selectMarks_C(Connection con, String markName) throws SQLException {

        List<Mark> marks = new ArrayList<>();

        String sql = "SELECT * FROM MARK WHERE NAME = ?";
        try (PreparedStatement ps = prepare(con, sql,
                p -> p.setString(1, markName));
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mark mark = new Mark();
                mark.setId(rs.getLong("ID"));
                mark.setName(rs.getString("NAME"));
                marks.add(mark);
            }
        }
        System.out.println();
    }


    private static void insertAndReturnId(Connection con) throws SQLException {
        Mark mark = new Mark("Porsche", "Germany");
        String sql = "INSERT INTO MARK (NAME, COUNTRY) VALUES (?, ?)";

        PsProvider psProvider = (connection, sql1) -> {
            try {
                PreparedStatement ps = connection.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
                int index = 0;

                ps.setString(++index, mark.getName());
                ps.setString(++index, mark.getCountry());

                return ps;
            }catch (Exception e){
                throw new RuntimeException("asdasdas");
            }
        };

        try(PreparedStatement ps = psProvider.from(con, sql)){
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()){
                Long generatedId = generatedKeys.getLong("ID");
                mark.setId(generatedId);
            }
        }

        System.out.println();
    }


    @FunctionalInterface
    private interface PsConsumer {
        void apply(PreparedStatement ps) throws SQLException;
    }

    private static PreparedStatement prepare(Connection connection,
                                             String sql,
                                             PsConsumer consumer) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            consumer.apply(ps);
            return ps;
        } catch (Exception e) {
            throw new RuntimeException("Error");
        }
    }


    private interface PsProvider {
        PreparedStatement from(Connection connection, String sql) throws SQLException;
    }



}