package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    private static final String TIMESTAMP = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    private static final String FOLDER_PATH = "src/test/resources/Evidencias/" + TIMESTAMP + "/";

    public static void takeScreenshot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File destFile = new File(FOLDER_PATH + fileName);
            destFile.getParentFile().mkdirs(); // crea la carpeta una sola vez
            Files.copy(screenshot.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("INFO: Screenshot guardado en: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeFullPageScreenshot(WebDriver driver, String fileName) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100)) // hace scroll y pega
                .takeScreenshot(driver);
        try {
            File destFile = new File("src/test/resources/Evidencias/"+ fileName);
            destFile.getParentFile().mkdirs();
            ImageIO.write(screenshot.getImage(), "PNG", destFile);
            System.out.println("INFO: Full screenshot guardado en: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
