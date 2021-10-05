import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.lang.XComponent;
import com.sun.star.uno.Exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.durnov.ControlRemoteOffice;

public class TestOffice {

    @Test
    void testThatXComponentNotNull() throws BootstrapException, Exception {
        XComponent currentComponent = ControlRemoteOffice.getCurrentComponent();
        Assertions.assertTrue(currentComponent != null);
    }
}
