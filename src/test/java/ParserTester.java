import com.home.server.TextTimeParser;
import org.junit.Test;

public class ParserTester {
    TextTimeParser textTimeParser=new TextTimeParser();

    @Test
    public void testParseMethod1() throws Exception {
        textTimeParser.parseText("двадцать три часа");

    }

    @Test
    public void testParseMethod2() throws Exception {
        textTimeParser.parseText("двадцать   часа");
    }

    @Test
    public void testParseMethod3() throws Exception {
        System.out.println(textTimeParser.parseText("двадцать   секунд"));
    }
}
