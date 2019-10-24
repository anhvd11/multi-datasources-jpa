package config;

import com.zaxxer.hikari.HikariDataSource;

public class MySqlDatasource extends HikariDataSource {
    public MySqlDatasource(){
        super.addDataSourceProperty("useSSL", "false");
        super.addDataSourceProperty("cachePrepStmts", "true");
        super.addDataSourceProperty("prepStmtCacheSize", "256");
        super.addDataSourceProperty("allowMultiQueries", "true");
        super.addDataSourceProperty("useServerPrepStmts", "false");
        super.addDataSourceProperty("useLocalSessionState", "true");
        super.addDataSourceProperty("prepStmtCacheSqlLimit", "81920");
        super.addDataSourceProperty("nullCatalogMeansCurrent", "true");
        super.addDataSourceProperty("rewriteBatchedStatements", "false");
        super.addDataSourceProperty("useOldAliasMetadataBehavior", "true");
        this.setIdleTimeout(600000); this.setConnectionTimeout(18000);  this.setMinimumIdle(0);
        this.setMaxLifetime(1800000); this.setInitializationFailTimeout(0);
    }
}
