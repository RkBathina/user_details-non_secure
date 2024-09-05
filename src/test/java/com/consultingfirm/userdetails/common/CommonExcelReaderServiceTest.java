//package com.learnspring.userdetailsapi.common;
//
//import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.InputStream;
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class CommonExcelReaderServiceTest {
//
//    @InjectMocks
//    private CommonExcelReaderService commonExcelReaderService;
//
//    @Mock
//    private MultipartFile mockFile;
//
//    @Mock
//    private XSSFWorkbook mockWorkbook;
//
//    @Mock
//    private XSSFSheet mockSheet;
//
//    @Mock
//    private Row mockRow;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//
//        // Set the private fields using reflection
//        setPrivateField(commonExcelReaderService, "benchProfilesSheetIndex", 0);
//        setPrivateField(commonExcelReaderService, "dailySubmissionsSheetIndex", 1);
//        setPrivateField(commonExcelReaderService, "interviewsSheetIndex", 2);
//    }
//
//    @Test
//    public void testReadBenchProfilesExcelFile_ValidData() throws Exception {
//        // Arrange
//        MultipartFile mockMultipartFile = mock(MultipartFile.class);
//        InputStream mockInputStream = mock(InputStream.class);
//        when(mockMultipartFile.getInputStream()).thenReturn(mockInputStream);
//        XSSFWorkbook mockWorkbook = mock(XSSFWorkbook.class);
//        XSSFSheet mockSheet = mock(XSSFSheet.class);
//        Row mockRow1 = mock(Row.class);
//        Row mockRow2 = mock(Row.class);
//
//        when(mockWorkbook.getSheetAt(0)).thenReturn(mockSheet);
//
//        when(mockSheet.iterator()).thenReturn(Arrays.asList(mockRow1, mockRow2).iterator());
//        // Mock cell values for data row
//        when(mockRow2.getCell(0)).thenReturn(mockCell("Recruiter Name"));
//        when(mockRow2.getCell(1)).thenReturn(mockCell("Consultant Name"));
//        when(mockRow2.getCell(19)).thenReturn(mockDateCell(LocalDate.of(2023, 8, 9)));
//        // Act
//        CommonExcelReaderService service = new CommonExcelReaderService();
//        List<BenchProfilesInfo> result = service.readBenchProfilesExcelFile(mockMultipartFile);
//        // Assert
//        assertEquals(1, result.size());
//        assertEquals("Recruiter Name", result.get(0).getRecruiterName());
//        assertEquals("Consultant Name", result.get(0).getConsultantName());
//        assertEquals(LocalDate.of(2023, 8, 9), result.get(0).getOriginalDob());
//    }
//
//
////    @Test
////    void testReadBenchProfilesExcelFile() throws Exception {
////        // Arrange
////        InputStream mockInputStream = mock(InputStream.class);
////        when(mockFile.getInputStream()).thenReturn(mockInputStream);
////        when(mockWorkbook.getSheetAt(0)).thenReturn(mockSheet);
////        // Mock first row (header) - you might not need this depending on logic
////        when(mockSheet.iterator()).thenReturn(Collections.singletonList(mockRow).iterator());
////        when(mockRow.getCell(0)).thenReturn(mockCell("Recruiter", CellType.STRING));
////        when(mockRow.getCell(1)).thenReturn(mockCell("Consultant", CellType.STRING));
////        when(mockRow.getCell(19)).thenReturn(mockCell(LocalDate.of(1990, 1, 1), CellType.NUMERIC));
////        // Mock second row (data)
////        List<Row> rows = new ArrayList<>();
////        rows.add(mockRow);
////        when(mockSheet.iterator()).thenReturn(rows.iterator());
////        when(mockRow.getCell(0)).thenReturn(mockCell("Test Recruiter", CellType.STRING));
////        when(mockRow.getCell(1)).thenReturn(mockCell("Test Consultant", CellType.STRING));
////        when(mockRow.getCell(19)).thenReturn(mockCell(LocalDate.of(2000, 1, 1), CellType.NUMERIC));
////        // Act
////        List<BenchProfilesInfo> result = commonExcelReaderService.readBenchProfilesExcelFile(mockFile);
////        // Assert
////        assertEquals(1, result.size());
////        // Adjust assertion if expecting multiple rows
////        assertEquals("Test Recruiter", result.get(0).getRecruiterName());
////        assertEquals("Test Consultant", result.get(0).getConsultantName());
////        assertEquals(LocalDate.of(2000, 1, 1), result.get(0).getOriginalDob());
////    }
//
////    @Test
////    void testReadBenchProfilesExcelFile() throws Exception {
////        // Arrange
////        InputStream mockInputStream = mock(InputStream.class);
////        when(mockFile.getInputStream()).thenReturn(mockInputStream);
////        when(mockWorkbook.getSheetAt(0)).thenReturn(mockSheet);
////
////        // Mocking the behavior of the sheet and row
////        when(mockSheet.iterator()).thenReturn(Collections.singletonList(mockRow).iterator());
////        when(mockRow.getCell(0)).thenReturn(mockCell("Recruiter"));
////        when(mockRow.getCell(1)).thenReturn(mockCell("Consultant"));
////        when(mockRow.getCell(19)).thenReturn(mockDateCell(LocalDate.of(1990, 1, 1)));
////
////        // Act
////        List<BenchProfilesInfo> result = commonExcelReaderService.readBenchProfilesExcelFile(mockFile);
////
////        // Assert
////        assertEquals(1, result.size());
////        assertEquals("Recruiter", result.get(0).getRecruiterName());
////        assertEquals("Consultant", result.get(0).getConsultantName());
////        assertEquals(LocalDate.of(1990, 1, 1), result.get(0).getOriginalDob());
////    }
//
////    @Test
////    void testReadDailySubmissionsExcelFile() throws Exception {
////        // Arrange
////        InputStream mockInputStream = mock(InputStream.class);
////        when(mockFile.getInputStream()).thenReturn(mockInputStream);
////        when(mockWorkbook.getSheetAt(1)).thenReturn(mockSheet);
////
////        // Mocking the behavior of the sheet and row
////        when(mockSheet.iterator()).thenReturn(Collections.singletonList(mockRow).iterator());
////        when(mockRow.getCell(1)).thenReturn(mockCell("Recruiter"));
////        when(mockRow.getCell(2)).thenReturn(mockCell("Consultant"));
////        when(mockRow.getCell(0)).thenReturn(mockDateCell(LocalDate.of(2023, 8, 9)));
////
////        // Act
////        List<DailySubmissionsInfo> result = commonExcelReaderService.readDailySubmissionsExcelFile(mockFile);
////
////        // Assert
////        assertEquals(1, result.size());
////        assertEquals("Recruiter", result.get(0).getRecruiterName());
////        assertEquals("Consultant", result.get(0).getConsultantName());
////        assertEquals(LocalDate.of(2023, 8, 9), result.get(0).getDateOfEntry());
////    }
//
//
////    @Test
////    void testReadDailySubmissionsExcelFile() throws Exception {
////        // Arrange
////        InputStream mockInputStream = mock(InputStream.class);
////        when(mockFile.getInputStream()).thenReturn(mockInputStream);
////        when(mockWorkbook.getSheetAt(1)).thenReturn(mockSheet);
////
////        // Mocking the behavior of the sheet and row
////        when(mockSheet.iterator()).thenReturn(Collections.singletonList(mockRow).iterator());
////        when(mockRow.getCell(1)).thenReturn(mockCell("Recruiter"));
////        when(mockRow.getCell(2)).thenReturn(mockCell("Consultant"));
////        when(mockRow.getCell(0)).thenReturn(mockDateCell(LocalDate.of(2023, 8, 9)));
////
////        // Act
////        List<DailySubmissionsInfo> result = commonExcelReaderService.readDailySubmissionsExcelFile(mockFile);
////
////        // Assert
////        assertEquals(1, result.size());
////        assertEquals("Recruiter", result.get(0).getRecruiterName());
////        assertEquals("Consultant", result.get(0).getConsultantName());
////        assertEquals(LocalDate.of(2023, 8, 9), result.get(0).getDateOfEntry());
////    }
//
////    @Test
////    void testReadBenchProfilesExcelFile() throws Exception {
////        // Arrange
////        InputStream mockInputStream = mock(InputStream.class);
////        when(mockFile.getInputStream()).thenReturn(mockInputStream);
////        when(mockWorkbook.getSheetAt(0)).thenReturn(mockSheet);
////
////        // Mocking the behavior of the sheet and row
////        when(mockSheet.iterator()).thenReturn(Collections.singletonList(mockRow).iterator());
////        when(mockRow.getCell(0)).thenReturn(mockCell("Recruiter", CellType.STRING));
////        when(mockRow.getCell(1)).thenReturn(mockCell("Consultant", CellType.STRING));
////        when(mockRow.getCell(19)).thenReturn(mockDateCell(LocalDate.of(1990, 1, 1)));
////
////        // Act
////        List<BenchProfilesInfo> result = commonExcelReaderService.readBenchProfilesExcelFile(mockFile);
////
////        // Assert
////        assertEquals(1, result.size());
////        assertEquals("Recruiter", result.get(0).getRecruiterName());
////        assertEquals("Consultant", result.get(0).getConsultantName());
////        assertEquals(LocalDate.of(1990, 1, 1), result.get(0).getOriginalDob());
////    }
//
//
////    @Test
////    void testReadInterviewsExcelFile() throws Exception {
////        // Arrange
////        InputStream mockInputStream = mock(InputStream.class);
////        when(mockFile.getInputStream()).thenReturn(mockInputStream);
////        when(mockWorkbook.getSheetAt(2)).thenReturn(mockSheet);
////
////        // Mocking the behavior of the sheet and row
////        when(mockSheet.iterator()).thenReturn(Collections.singletonList(mockRow).iterator());
////        when(mockRow.getCell(0)).thenReturn(mockCell("Recruiter"));
////        when(mockRow.getCell(2)).thenReturn(mockDateCell(LocalDate.of(2024, 8, 9)));
////        when(mockRow.getCell(3)).thenReturn(mockCell("10:00 AM"));
////
////        // Act
////        List<InterviewInfo> result = commonExcelReaderService.readInterviewsExcelFile(mockFile);
////
////        // Assert
////        assertEquals(1, result.size());
////        assertEquals("Recruiter", result.get(0).getRecruiterName());
////        assertEquals(LocalDate.of(2024, 8, 9), result.get(0).getInterviewDate());
////        assertEquals("10:00 AM", result.get(0).getInterviewTime());
////    }
//
//    //     Helper method to mock cell behavior for string values
//    private Cell mockCell(String value) {
//        Cell cell = mock(Cell.class);
//        when(cell.getCellType()).thenReturn(org.apache.poi.ss.usermodel.CellType.STRING);
//        when(cell.getStringCellValue()).thenReturn(value);
//        return cell;
//    }
//
//    // Helper method to mock cell behavior for date values
//    private Cell mockDateCell(LocalDate date) {
//        Cell cell = mock(Cell.class);
//        when(cell.getCellType()).thenReturn(org.apache.poi.ss.usermodel.CellType.NUMERIC);
//        when(cell.getLocalDateTimeCellValue()).thenReturn(date.atStartOfDay());
//        return cell;
//    }
//
//    // Reflection helper method to set private fields
//    private void setPrivateField(Object targetObject, String fieldName, Object value) throws Exception {
//        Field field = targetObject.getClass().getDeclaredField(fieldName);
//        field.setAccessible(true);
//        field.set(targetObject, value);
//    }
//
////    private Cell mockCell(Object value, CellType cellType) {
////        Cell cell = mock(Cell.class);
////        when(cell.getCellType()).thenReturn(cellType);
////        if (cellType == CellType.STRING) {
////            when(cell.getStringCellValue()).thenReturn((String) value);
////        } else if (cellType == CellType.NUMERIC) {
////            when(cell.getLocalDateTimeCellValue()).thenReturn(LocalDateTime.from((LocalDate) value));
////        }
////        return cell;
////    }
//}
