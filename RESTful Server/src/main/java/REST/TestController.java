package REST;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final String _template = "Hello, %s!";
    private final AtomicLong _counter = new AtomicLong();

    @RequestMapping("/Test")
    public TestModel test(@RequestParam(value="name", defaultValue = "None") String name) {
        return new TestModel(_counter.incrementAndGet(), String.format(_template, name));
    }
}
