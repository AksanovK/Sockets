import com.beust.jcommander.*;
import java.util.ArrayList;
import java.util.List;

@Parameters(separators = "=")
public class Args {

    @Parameter(names = "--port")
    public int mode = 7777;

    @Parameter(names = "--server-port")
    public int count = 7777;

    @Parameter(names = "--server-ip")
    public String files = "localhost";

}
