package Manager;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.Reader;

public class BatisManager {
    private static SqlSessionFactory _factory;
    static {
        String resource = "spring-mybatis.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException except) {
            System.out.println(except.getMessage());
        }
        _factory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() { return _factory; }
}
