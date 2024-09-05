package com.consultingfirm.userdetails.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExcelUtilTest {

    @Mock
    private Row mockRow;

    @Mock
    private Cell mockCell;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnStringValueWhenCellIsString() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("Test String");

        String result = ExcelUtil.getStringCellValue(mockRow, 0);

        assertEquals("Test String", result);
    }

    @Test
    void shouldReturnTrimmedStringValueWhenCellIsStringWithWhitespace() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("  Test String  ");

        String result = ExcelUtil.getStringCellValue(mockRow, 0);

        assertEquals("Test String", result);
    }

    @Test
    void shouldReturnNumericValueAsStringWhenCellIsNumeric() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(123.0);

        String result = ExcelUtil.getStringCellValue(mockRow, 0);

        assertEquals("123", result);
    }

    @Test
    void shouldReturnNullWhenStringCellIsNull() {
        when(mockRow.getCell(0)).thenReturn(null);

        String result = ExcelUtil.getStringCellValue(mockRow, 0);

        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenCellTypeIsNotStringOrNumeric() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.BOOLEAN);

        String result = ExcelUtil.getStringCellValue(mockRow, 0);

        assertNull(result);
    }

    @Test
    void shouldReturnIntegerValueWhenCellIsNumeric() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(123.0);

        Integer result = ExcelUtil.getIntegerCellValue(mockRow, 0);

        assertEquals(123, result);
    }

    @Test
    void shouldReturnNullWhenCellIsNull() {
        when(mockRow.getCell(0)).thenReturn(null);

        Integer result = ExcelUtil.getIntegerCellValue(mockRow, 0);

        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenIntegerCellIsBlank() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.BLANK);

        Integer result = ExcelUtil.getIntegerCellValue(mockRow, 0);

        assertNull(result);
    }

    @Test
    void shouldReturnZeroWhenCellIsStringAndNotParsable() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("Not a number");

        Integer result = ExcelUtil.getIntegerCellValue(mockRow, 0);

        assertEquals(0, result);
    }

    @Test
    void shouldReturnDateWhenCellIsNumeric() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.NUMERIC);
        LocalDateTime mockDate = LocalDateTime.of(2023, 8, 9, 0, 0);
        when(mockCell.getLocalDateTimeCellValue()).thenReturn(mockDate);

        LocalDate result = ExcelUtil.getDateCellValue(mockRow, 0);

        assertEquals(LocalDate.of(2023, 8, 9), result);
    }

    @Test
    void shouldReturnNullWhenDateCellIsNull() {
        when(mockRow.getCell(0)).thenReturn(null);

        LocalDate result = ExcelUtil.getDateCellValue(mockRow, 0);

        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenDateCellIsBlank() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.BLANK);

        LocalDate result = ExcelUtil.getDateCellValue(mockRow, 0);

        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenCellContainsInvalidString() {
        when(mockRow.getCell(0)).thenReturn(mockCell);
        when(mockCell.getCellType()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("Invalid Date");

        LocalDate result = ExcelUtil.getDateCellValue(mockRow, 0);

        assertNull(result);
    }

    @Test
    void shouldReturnTrueWhenAllCellsAreBlank() {
        Row mockRow = mock(Row.class);
        when(mockRow.getLastCellNum()).thenReturn((short) 3);

        for (int i = 0; i < 3; i++) {
            Cell mockCell = mock(Cell.class);
            when(mockRow.getCell(i)).thenReturn(mockCell);
            when(mockCell.getCellType()).thenReturn(CellType.BLANK);
        }

        boolean result = ExcelUtil.isEmptyRow(mockRow);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenAtLeastOneCellIsNotBlank() {
        Row mockRow = mock(Row.class);
        when(mockRow.getLastCellNum()).thenReturn((short) 3);

        Cell mockCell1 = mock(Cell.class);
        when(mockRow.getCell(0)).thenReturn(mockCell1);
        when(mockCell1.getCellType()).thenReturn(CellType.STRING);
        when(mockCell1.getStringCellValue()).thenReturn("Test");

        Cell mockCell2 = mock(Cell.class);
        when(mockRow.getCell(1)).thenReturn(mockCell2);
        when(mockCell2.getCellType()).thenReturn(CellType.BLANK);

        Cell mockCell3 = mock(Cell.class);
        when(mockRow.getCell(2)).thenReturn(mockCell3);
        when(mockCell3.getCellType()).thenReturn(CellType.BLANK);

        boolean result = ExcelUtil.isEmptyRow(mockRow);

        assertFalse(result);
    }
}
