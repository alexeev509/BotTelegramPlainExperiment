import com.home.server.TextTimeParser;
import org.junit.Test;

public class ParserTester {
    TextTimeParser textTimeParser=new TextTimeParser();



    @Test
    public void parseStringTest() throws Exception {
        textTimeParser.parseString("четыре часа две минуты двадцать   две секунды");
    }

    @Test
    public void parseStringTest2() throws Exception {
        textTimeParser.parseString("двадцать   две секунды");
    }

    @Test
    public void parseStringTest3() throws Exception {
        textTimeParser.parseString("ff секунды");
    }

    @Test
    public void parseStringTest4() throws Exception {
        textTimeParser.parseString("1 секунда две минуты");
    }
}
