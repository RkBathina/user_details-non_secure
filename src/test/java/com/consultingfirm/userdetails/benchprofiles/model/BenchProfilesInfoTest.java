package com.consultingfirm.userdetails.benchprofiles.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class BenchProfilesInfoTest {

    @Test
    public void shouldGetBenchProfilesInfoGettersAndSetters() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        BenchProfilesInfo benchProfilesInfo = BenchProfilesInfo.builder()
                .id(1L)
                .recruiterName("John Doe")
                .consultantName("Jane Smith")
                .allocatedStatus("Allocated")
                .status("Active")
                .turboCheck(5)
                .priority("High")
                .technology("Java")
                .organization("TechCorp")
                .experience("8+")
                .location("New York")
                .relocation("Yes")
                .modeOfStaying("Remote")
                .newOrExisting("New")
                .sourcedBy("Referral")
                .visaStatus("H1B")
                .marketingVisaStatus("Active")
                .contactNumber("1234567890")
                .emailId("example@example.com")
                .originalDob(date)
                .marketingDob(date)
                .whatsappNumber("0987654321")
                .marketingStartDate(date)
                .marketingEndDate(date)
                .comments("No comments")
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        // Act & Assert
        assertThat(benchProfilesInfo.getId()).isEqualTo(1L);
        assertThat(benchProfilesInfo.getRecruiterName()).isEqualTo("John Doe");
        assertThat(benchProfilesInfo.getConsultantName()).isEqualTo("Jane Smith");
        assertThat(benchProfilesInfo.getAllocatedStatus()).isEqualTo("Allocated");
        assertThat(benchProfilesInfo.getStatus()).isEqualTo("Active");
        assertThat(benchProfilesInfo.getTurboCheck()).isEqualTo(5);
        assertThat(benchProfilesInfo.getPriority()).isEqualTo("High");
        assertThat(benchProfilesInfo.getTechnology()).isEqualTo("Java");
        assertThat(benchProfilesInfo.getOrganization()).isEqualTo("TechCorp");
        assertThat(benchProfilesInfo.getExperience()).isEqualTo("8+");
        assertThat(benchProfilesInfo.getLocation()).isEqualTo("New York");
        assertThat(benchProfilesInfo.getRelocation()).isEqualTo("Yes");
        assertThat(benchProfilesInfo.getModeOfStaying()).isEqualTo("Remote");
        assertThat(benchProfilesInfo.getNewOrExisting()).isEqualTo("New");
        assertThat(benchProfilesInfo.getSourcedBy()).isEqualTo("Referral");
        assertThat(benchProfilesInfo.getVisaStatus()).isEqualTo("H1B");
        assertThat(benchProfilesInfo.getMarketingVisaStatus()).isEqualTo("Active");
        assertThat(benchProfilesInfo.getContactNumber()).isEqualTo("1234567890");
        assertThat(benchProfilesInfo.getEmailId()).isEqualTo("example@example.com");
        assertThat(benchProfilesInfo.getOriginalDob()).isEqualTo(date);
        assertThat(benchProfilesInfo.getMarketingDob()).isEqualTo(date);
        assertThat(benchProfilesInfo.getWhatsappNumber()).isEqualTo("0987654321");
        assertThat(benchProfilesInfo.getMarketingStartDate()).isEqualTo(date);
        assertThat(benchProfilesInfo.getMarketingEndDate()).isEqualTo(date);
        assertThat(benchProfilesInfo.getComments()).isEqualTo("No comments");
        assertThat(benchProfilesInfo.getDateCreated()).isEqualTo(dateTime);
        assertThat(benchProfilesInfo.getLastUpdated()).isEqualTo(dateTime);
    }

    @Test
    public void shouldCreateBenchProfilesInfoUsingNoArgsConstructor() {
        // Act
        BenchProfilesInfo benchProfilesInfo = new BenchProfilesInfo();

        // Assert
        assertThat(benchProfilesInfo).isNotNull();
    }

    @Test
    public void shouldCreateBenchProfilesInfoUsingAllArgsConstructor() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        // Act
        BenchProfilesInfo benchProfilesInfo = new BenchProfilesInfo(
                1L, "John Doe", "Jane Smith", "Allocated", "Active", 5, "High",
                "Java", "TechCorp", "8+", "New York", "Yes", "Remote", "New",
                "Referral", "H1B", "Active", "1234567890", "example@example.com",
                date, date, "0987654321", date, date, "No comments",
                dateTime, dateTime
        );

        // Assert
        assertThat(benchProfilesInfo.getId()).isEqualTo(1L);
        assertThat(benchProfilesInfo.getRecruiterName()).isEqualTo("John Doe");
        assertThat(benchProfilesInfo.getConsultantName()).isEqualTo("Jane Smith");
        assertThat(benchProfilesInfo.getAllocatedStatus()).isEqualTo("Allocated");
        assertThat(benchProfilesInfo.getStatus()).isEqualTo("Active");
        assertThat(benchProfilesInfo.getTurboCheck()).isEqualTo(5);
        assertThat(benchProfilesInfo.getPriority()).isEqualTo("High");
        assertThat(benchProfilesInfo.getTechnology()).isEqualTo("Java");
        assertThat(benchProfilesInfo.getOrganization()).isEqualTo("TechCorp");
        assertThat(benchProfilesInfo.getExperience()).isEqualTo("8+");
        assertThat(benchProfilesInfo.getLocation()).isEqualTo("New York");
        assertThat(benchProfilesInfo.getRelocation()).isEqualTo("Yes");
        assertThat(benchProfilesInfo.getModeOfStaying()).isEqualTo("Remote");
        assertThat(benchProfilesInfo.getNewOrExisting()).isEqualTo("New");
        assertThat(benchProfilesInfo.getSourcedBy()).isEqualTo("Referral");
        assertThat(benchProfilesInfo.getVisaStatus()).isEqualTo("H1B");
        assertThat(benchProfilesInfo.getMarketingVisaStatus()).isEqualTo("Active");
        assertThat(benchProfilesInfo.getContactNumber()).isEqualTo("1234567890");
        assertThat(benchProfilesInfo.getEmailId()).isEqualTo("example@example.com");
        assertThat(benchProfilesInfo.getOriginalDob()).isEqualTo(date);
        assertThat(benchProfilesInfo.getMarketingDob()).isEqualTo(date);
        assertThat(benchProfilesInfo.getWhatsappNumber()).isEqualTo("0987654321");
        assertThat(benchProfilesInfo.getMarketingStartDate()).isEqualTo(date);
        assertThat(benchProfilesInfo.getMarketingEndDate()).isEqualTo(date);
        assertThat(benchProfilesInfo.getComments()).isEqualTo("No comments");
        assertThat(benchProfilesInfo.getDateCreated()).isEqualTo(dateTime);
        assertThat(benchProfilesInfo.getLastUpdated()).isEqualTo(dateTime);
    }

    @Test
    public void shouldTestBenchProfilesInfoEqualsAndHashCode() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        BenchProfilesInfo benchProfilesInfo1 = BenchProfilesInfo.builder()
                .id(1L)
                .recruiterName("John Doe")
                .consultantName("Jane Smith")
                .allocatedStatus("Allocated")
                .status("Active")
                .turboCheck(5)
                .priority("High")
                .technology("Java")
                .organization("TechCorp")
                .experience("8+")
                .location("New York")
                .relocation("Yes")
                .modeOfStaying("Remote")
                .newOrExisting("New")
                .sourcedBy("Referral")
                .visaStatus("H1B")
                .marketingVisaStatus("Active")
                .contactNumber("1234567890")
                .emailId("example@example.com")
                .originalDob(date)
                .marketingDob(date)
                .whatsappNumber("0987654321")
                .marketingStartDate(date)
                .marketingEndDate(date)
                .comments("No comments")
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        BenchProfilesInfo benchProfilesInfo2 = BenchProfilesInfo.builder()
                .id(1L)
                .recruiterName("John Doe")
                .consultantName("Jane Smith")
                .allocatedStatus("Allocated")
                .status("Active")
                .turboCheck(5)
                .priority("High")
                .technology("Java")
                .organization("TechCorp")
                .experience("8+")
                .location("New York")
                .relocation("Yes")
                .modeOfStaying("Remote")
                .newOrExisting("New")
                .sourcedBy("Referral")
                .visaStatus("H1B")
                .marketingVisaStatus("Active")
                .contactNumber("1234567890")
                .emailId("example@example.com")
                .originalDob(date)
                .marketingDob(date)
                .whatsappNumber("0987654321")
                .marketingStartDate(date)
                .marketingEndDate(date)
                .comments("No comments")
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        // Act & Assert
        assertThat(benchProfilesInfo1).isEqualTo(benchProfilesInfo2);
        assertThat(benchProfilesInfo1.hashCode()).isEqualTo(benchProfilesInfo2.hashCode());
    }

    @Test
    public void shouldTestBenchProfilesInfoToString() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        BenchProfilesInfo benchProfilesInfo = BenchProfilesInfo.builder()
                .id(1L)
                .recruiterName("John Doe")
                .consultantName("Jane Smith")
                .allocatedStatus("Allocated")
                .status("Active")
                .turboCheck(5)
                .priority("High")
                .technology("Java")
                .organization("TechCorp")
                .experience("8+")
                .location("New York")
                .relocation("Yes")
                .modeOfStaying("Remote")
                .newOrExisting("New")
                .sourcedBy("Referral")
                .visaStatus("H1B")
                .marketingVisaStatus("Active")
                .contactNumber("1234567890")
                .emailId("example@example.com")
                .originalDob(date)
                .marketingDob(date)
                .whatsappNumber("0987654321")
                .marketingStartDate(date)
                .marketingEndDate(date)
                .comments("No comments")
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        // Act
        String benchProfilesInfoString = benchProfilesInfo.toString();

        // Assert
        assertThat(benchProfilesInfoString).isEqualTo("BenchProfilesInfo(id=1, recruiterName=John Doe, consultantName=Jane Smith, allocatedStatus=Allocated, status=Active, turboCheck=5, priority=High, technology=Java, organization=TechCorp, experience=8+, location=New York, relocation=Yes, modeOfStaying=Remote, newOrExisting=New, sourcedBy=Referral, visaStatus=H1B, marketingVisaStatus=Active, contactNumber=1234567890, emailId=example@example.com, originalDob=" + date + ", marketingDob=" + date + ", whatsappNumber=0987654321, marketingStartDate=" + date + ", marketingEndDate=" + date + ", comments=No comments, dateCreated=" + dateTime + ", lastUpdated=" + dateTime + ")");
    }
}
