package cp.ch07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class PreventDuplicated {
    private final static String LOCK_PATH = "/download/locks";
    private final static String LOCK_FILE = ".lock";
    private final static String PERMISSIONS = "rw------";

    public static void main(String[] args) throws IOException {
        checkRunning();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("the program received kill signal");
            getLockFile().toFile().delete();
        }));

        for (;;){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if(path.toFile().exists()){
            throw new RuntimeException("The program is already running.");
        }
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));
    }

    private static Path getLockFile(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
