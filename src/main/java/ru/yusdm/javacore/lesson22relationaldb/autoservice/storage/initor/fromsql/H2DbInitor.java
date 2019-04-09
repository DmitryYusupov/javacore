package ru.yusdm.javacore.lesson22relationaldb.autoservice.storage.initor.fromsql;

import org.apache.commons.collections4.CollectionUtils;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.common.business.database.datasource.HikariCpDataSource;
import ru.yusdm.javacore.lesson22relationaldb.autoservice.storage.initor.Initor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class H2DbInitor implements Initor {
    private static final String DATABASE_CONFIG_PATH = "/ru/yusdm/javacore/lesson22relationaldb/autoservice/database/config/config.properties";
    private static final String DDL_SCRIPT_PATH = "/ru/yusdm/javacore/lesson22relationaldb/autoservice/database/ddl/crerate-schema.sql";
    private static final String DML_SCRIPT_PATH = "/ru/yusdm/javacore/lesson22relationaldb/autoservice/database/dml/init-data.sql";


    @Override
    public void init() {
        try {
            prepareDataSourceConfig();
            createDataBaseStructure();
            fillDataBaseWithData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareDataSourceConfig() throws Exception {
        HikariCpDataSource.HikariCpDataSourceBuilder hikariCpDataSourceBuilder = new HikariCpDataSource.HikariCpDataSourceBuilder();
        Map<DatabaseConfig, String> dbConfigs = readDbConfigFromResources();

        dbConfigs.forEach((param, value) -> {
            switch (param) {

                case URL: {
                    hikariCpDataSourceBuilder.appendUrl(value);
                    break;
                }
                case USER: {
                    hikariCpDataSourceBuilder.appendUserName(value);
                    break;
                }
                case PASSWORD: {
                    hikariCpDataSourceBuilder.appendPassword(value);
                    break;
                }

                case DRIVER: {
                    hikariCpDataSourceBuilder.appendDriver(value);
                    break;
                }
            }
        });
        HikariCpDataSource.init(hikariCpDataSourceBuilder);
    }

    private Map<DatabaseConfig, String> readDbConfigFromResources() throws Exception {
        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream(DATABASE_CONFIG_PATH));

        Map<DatabaseConfig, String> result = new HashMap<>();
        Arrays.stream(DatabaseConfig.values()).forEach(dbConfig ->
                result.put(dbConfig, props.getProperty(dbConfig.getPropName())));

        return result;
    }

    private void createDataBaseStructure() throws Exception {
        List<String> ddl = Files.readAllLines(Paths.get(this.getClass().getResource(DDL_SCRIPT_PATH).toURI()));

        try (Connection con = HikariCpDataSource.getInstance().getConnection();
             Statement statement = con.createStatement()) {
            statement.execute(String.join("", ddl));
        }
    }

    private void fillDataBaseWithData() throws Exception {
        List<String> sqls = prepareSqlsToInitData();
        if (CollectionUtils.isNotEmpty(sqls)) {
            for (String sql : sqls) {
                execSql(sql);
            }
        }
    }

    private List<String> prepareSqlsToInitData() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(this.getClass().getResource(DML_SCRIPT_PATH).toURI()))) {
            return stream.map(line -> {
                stringBuilder.append(line);
                if (line.endsWith(";")) {
                    String sql = stringBuilder.toString();
                    stringBuilder.setLength(0);
                    return sql;
                } else if (line.startsWith("-")) {
                    stringBuilder.setLength(0);
                    return null;
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }
    }

    private void execSql(String sql) throws Exception {
        try (Connection con = HikariCpDataSource.getInstance().getConnection();
             Statement statement = con.createStatement()) {
            statement.execute(sql);
        }
    }

}
