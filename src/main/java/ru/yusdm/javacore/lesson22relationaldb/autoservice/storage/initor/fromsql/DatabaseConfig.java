package ru.yusdm.javacore.lesson22relationaldb.autoservice.storage.initor.fromsql;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum DatabaseConfig {
    URL("custom.database.url"),
    USER("custom.database.user"),
    PASSWORD("custom.database.password"),
    DRIVER("custom.database.driver");

    private String propName;
    private static Map<String, DatabaseConfig> propNameDbConfigMap = new HashMap<>();

    static {
        for (DatabaseConfig conf : DatabaseConfig.values()) {
            propNameDbConfigMap.put(conf.propName, conf);
        }
    }

    DatabaseConfig(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }


    public static Optional<DatabaseConfig> getConfigByPropName(String propName) {
        return Optional.ofNullable(propNameDbConfigMap.get(propName));
    }
}
