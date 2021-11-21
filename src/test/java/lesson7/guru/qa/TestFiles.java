package lesson7.guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestFiles {
    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    @DisplayName("Скачивание файла doc")
    void DownloadFIleFormatDocTest() throws FileNotFoundException {
        Configuration.proxyEnabled = true;
        Configuration.fileDownload = FileDownloadMode.PROXY;

        open("https://e-kontur.ru/Enquiry/DocumentTemplates");

        $(byText("Договор об оказании услуг")).click();
        File download = $(byText("Скачать шаблон")).download();
        System.out.println(download.getAbsolutePath());
    }
}
