package lesson7.guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import com.codeborne.selenide.FileDownloadMode;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class TestFiles {
    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    @DisplayName("Скачивание файла xls")
    void DownloadFIleXlsTest() throws IOException {
        open("https://kub-24.ru/prajs-list-shablon-prajs-lista-2020-v-excel-word-pdf/");

        File xlsxFile = $(byText("шаблон прайс-листа в Excel")).download();
        XLS parsedXlsx = new XLS(xlsxFile);
        boolean checkFile = ((XSSFWorkbook) parsedXlsx.excel)
                .getSheetAt(0)
                .getRow(12)
                .getCell(1)
                .getStringCellValue()
                .contains("Кирпич строительный двойной щелевой М150 ( 250х120х130мм)");
    }

    @Test
    @DisplayName("Скачивание файла readme")
    @Disabled
    void DownloadFileReadmeTest() throws IOException {
        open("https://github.com/dtuchs/epam-hackathon/blob/master/README.md");

        File download = $("#raw-url").download();
        String fileContent = IOUtils.toString(new FileReader(download));
        Assertions.assertTrue(fileContent.contains("Сборка в JENKINS http://178.250.156.47:8081/job/Project_23"));
    }

    @Test
    @DisplayName("Скачивание файла pdf")
    void DownloadFIlePdfTest() throws IOException {
        open("https://kub-24.ru/prajs-list-shablon-prajs-lista-2020-v-excel-word-pdf/");

        File pdfFile = $(byText("шаблон прайс-листа в PDF")).download();
        PDF parsedPdf = new PDF(pdfFile);
        Assertions.assertEquals(1, parsedPdf.numberOfPages);
    }


}
