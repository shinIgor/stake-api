package single.project.stakeapi.configuration.database.hibernate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import single.project.stakeapi.configuration.database.BaseDataSourceProperties;
import single.project.stakeapi.configuration.database.NamingStrategyType;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HibernateProperties extends BaseDataSourceProperties {
    private boolean showSql = true;
    private boolean generateDdl = false;
    private String useSqlComments = "true";
    private String formatSql = "true";
    private String enableLazyLoadNoTrans = "false";
    private NamingStrategyType namingStrategy = NamingStrategyType.CAMELCASE;
    private String[] packagesToScans = {
            "single.project.stakeapi",
    };
}
