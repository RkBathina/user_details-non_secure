package com.consultingfirm.userdetails.dailysubmissions.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DailySubmissionsInfoTest {

    @Test
    public void shouldGetDailySubmissionsInfoGettersAndSetters() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        DailySubmissionsInfo dailySubmissionsInfo = DailySubmissionsInfo.builder()
                .id(1L)
                .dateOfEntry(date)
                .recruiterName("John Doe")
                .consultantName("Jane Smith")
                .technology("Java")
                .priority("High")
                .skill("Java Developer")
                .allocatedStatus("Assigned")
                .clientType("Direct Client - (MSV)")
                .clientName("American Airlines")
                .requirementSource("Linkedin")
                .feedback(null)
                .comments(null)
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        // Act & Assert
        assertThat(dailySubmissionsInfo.getId()).isEqualTo(1L);
        assertThat(dailySubmissionsInfo.getDateOfEntry()).isEqualTo(date);
        assertThat(dailySubmissionsInfo.getRecruiterName()).isEqualTo("John Doe");
        assertThat(dailySubmissionsInfo.getConsultantName()).isEqualTo("Jane Smith");
        assertThat(dailySubmissionsInfo.getTechnology()).isEqualTo("Java");
        assertThat(dailySubmissionsInfo.getPriority()).isEqualTo("High");
        assertThat(dailySubmissionsInfo.getSkill()).isEqualTo("Java Developer");
        assertThat(dailySubmissionsInfo.getAllocatedStatus()).isEqualTo("Assigned");
        assertThat(dailySubmissionsInfo.getClientType()).isEqualTo("Direct Client - (MSV)");
        assertThat(dailySubmissionsInfo.getClientName()).isEqualTo("American Airlines");
        assertThat(dailySubmissionsInfo.getRequirementSource()).isEqualTo("Linkedin");
        assertThat(dailySubmissionsInfo.getFeedback()).isNull();
        assertThat(dailySubmissionsInfo.getComments()).isNull();
        assertThat(dailySubmissionsInfo.getDateCreated()).isEqualTo(dateTime);
        assertThat(dailySubmissionsInfo.getLastUpdated()).isEqualTo(dateTime);
    }

    @Test
    public void shouldCreateDailySubmissionsInfoUsingNoArgsConstructor() {
        // Act
        DailySubmissionsInfo dailySubmissionsInfo = new DailySubmissionsInfo();

        // Assert
        assertThat(dailySubmissionsInfo).isNotNull();
    }

    @Test
    public void shouldCreateDailySubmissionsInfoUsingAllArgsConstructor() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        // Act
        DailySubmissionsInfo dailySubmissionsInfo = new DailySubmissionsInfo(
                1L, date, "John Doe", "Jane Smith", "Java", "High",
                "Java Developer", "Assigned", "Direct Client - (MSV)",
                "American Airlines", "Linkedin", null, null, dateTime, dateTime
        );

        // Assert
        assertThat(dailySubmissionsInfo.getId()).isEqualTo(1L);
        assertThat(dailySubmissionsInfo.getDateOfEntry()).isEqualTo(date);
        assertThat(dailySubmissionsInfo.getRecruiterName()).isEqualTo("John Doe");
        assertThat(dailySubmissionsInfo.getConsultantName()).isEqualTo("Jane Smith");
        assertThat(dailySubmissionsInfo.getTechnology()).isEqualTo("Java");
        assertThat(dailySubmissionsInfo.getPriority()).isEqualTo("High");
        assertThat(dailySubmissionsInfo.getSkill()).isEqualTo("Java Developer");
        assertThat(dailySubmissionsInfo.getAllocatedStatus()).isEqualTo("Assigned");
        assertThat(dailySubmissionsInfo.getClientType()).isEqualTo("Direct Client - (MSV)");
        assertThat(dailySubmissionsInfo.getClientName()).isEqualTo("American Airlines");
        assertThat(dailySubmissionsInfo.getRequirementSource()).isEqualTo("Linkedin");
        assertThat(dailySubmissionsInfo.getFeedback()).isNull();
        assertThat(dailySubmissionsInfo.getComments()).isNull();
        assertThat(dailySubmissionsInfo.getDateCreated()).isEqualTo(dateTime);
        assertThat(dailySubmissionsInfo.getLastUpdated()).isEqualTo(dateTime);
    }

    @Test
    public void shouldTestDailySubmissionsInfoEqualsAndHashCode() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        DailySubmissionsInfo dailySubmissionsInfo1 = DailySubmissionsInfo.builder()
                .id(1L)
                .dateOfEntry(date)
                .recruiterName("John Doe")
                .consultantName("Jane Smith")
                .technology("Java")
                .priority("High")
                .skill("Java Developer")
                .allocatedStatus("Allocated")
                .clientType("Direct Client - (MSV)")
                .clientName("American Airlines")
                .requirementSource("Linkedin")
                .feedback(null)
                .comments(null)
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        DailySubmissionsInfo dailySubmissionsInfo2 = DailySubmissionsInfo.builder()
                .id(1L)
                .dateOfEntry(date)
                .recruiterName("John Doe")
                .consultantName("Jane Smith")
                .technology("Java")
                .priority("High")
                .skill("Java Developer")
                .allocatedStatus("Allocated")
                .clientType("Direct Client - (MSV)")
                .clientName("American Airlines")
                .requirementSource("Linkedin")
                .feedback(null)
                .comments(null)
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        // Act & Assert
        assertThat(dailySubmissionsInfo1).isEqualTo(dailySubmissionsInfo2);
        assertThat(dailySubmissionsInfo1.hashCode()).isEqualTo(dailySubmissionsInfo2.hashCode());
    }

    @Test
    public void shouldTestDailySubmissionsInfoToString() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        DailySubmissionsInfo dailySubmissionsInfo = DailySubmissionsInfo.builder()
                .id(1L)
                .dateOfEntry(date)
                .recruiterName("John Doe")
                .consultantName("Jane Smith")
                .technology("Java")
                .priority("High")
                .skill("Java Developer")
                .allocatedStatus("Allocated")
                .clientType("Direct Client - (MSV)")
                .clientName("American Airlines")
                .requirementSource("Linkedin")
                .feedback(null)
                .comments(null)
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();
        // Act
        String result = dailySubmissionsInfo.toString();

        // Assert
        assertThat(result).contains("DailySubmissionsInfo");
        assertThat(result).contains("id=1");
        assertThat(result).contains("dateOfEntry=" + date.toString());
        assertThat(result).contains("recruiterName=John Doe");
        assertThat(result).contains("consultantName=Jane Smith");
        assertThat(result).contains("technology=Java");
        assertThat(result).contains("priority=High");
        assertThat(result).contains("skill=Java Developer");
        assertThat(result).contains("allocatedStatus=Allocated");
        assertThat(result).contains("clientType=Direct Client - (MSV)");
        assertThat(result).contains("clientName=American Airlines");
        assertThat(result).contains("requirementSource=Linkedin");
        assertThat(result).contains("feedback=null");
        assertThat(result).contains("comments=null");
        assertThat(result).contains("dateCreated=" + dateTime.toString());
        assertThat(result).contains("lastUpdated=" + dateTime.toString());
    }
}
