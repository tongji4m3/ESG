import ch.qos.logback.classic.net.SyslogAppender;
import org.junit.Test;

import java.util.UUID;

public class MainTest
{
    @Test
    public void test()
    {
        UUID uuid = UUID.randomUUID();
        String x = uuid.toString();
        System.out.println(x.length());
    }
}
