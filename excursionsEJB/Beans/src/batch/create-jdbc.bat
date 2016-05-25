:: run <GLASSFISH_HOME>/glassfish/bin/asadmin
:: then run following commands:

create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property portNumber=3306:password=_Password1900:user=testAdmin:serverName=localhost:databaseName=test:connectionAttributes=;create=true testPool
create-jdbc-resource --connectionpoolid testPool --enabled=true jdbc/testdb