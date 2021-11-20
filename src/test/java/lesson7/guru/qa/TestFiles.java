package lesson7.guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.*;

public class TestFiles {
    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    @DisplayName("Скачивание файла doc")
    void DownloadFIleFormatDocTest() throws FileNotFoundException {
        open("https://products.groupdocs.app/ru-ru/editor/total");

        $(".uploadfileinput").uploadFromClasspath("test-doc.docx");
        $("#mat-tab-label-0-0").click();
        File download = $(".fa-file-download").download();
        System.out.println(download.getAbsolutePath());
    }
}
