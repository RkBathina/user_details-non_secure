package com.consultingfirm.userdetails.interviews.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class InterviewInfoTest {

    @Test
    public void shouldTestInterviewInfoGettersAndSetters() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        InterviewInfo interviewInfo = InterviewInfo.builder()
                .id(1L)
                .recruiterName("Tanuja Chokkapu")
                .round("Coding Test")
                .interviewDate(date)
                .interviewTime(dateTime.toString())
                .consultantName("Batreddi Pavan")
                .ownSupport("")
                .technology("Java")
                .clientType("Direct Client - (MSV)")
                .clientName("American Airlines")
                .location("Texas")
                .rate("85$/hr")
                .vendor("Hire Talent")
                .feedback(null)
                .comments(null)
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        // Act & Assert
        assertThat(interviewInfo.getId()).isEqualTo(1L);
        assertThat(interviewInfo.getRecruiterName()).isEqualTo("Tanuja Chokkapu");
        assertThat(interviewInfo.getRound()).isEqualTo("Coding Test");
        assertThat(interviewInfo.getInterviewDate()).isEqualTo(date);
        assertThat(interviewInfo.getInterviewTime()).isEqualTo(dateTime.toString());
        assertThat(interviewInfo.getConsultantName()).isEqualTo("Batreddi Pavan");
        assertThat(interviewInfo.getOwnSupport()).isEqualTo("");
        assertThat(interviewInfo.getTechnology()).isEqualTo("Java");
        assertThat(interviewInfo.getClientType()).isEqualTo("Direct Client - (MSV)");
        assertThat(interviewInfo.getClientName()).isEqualTo("American Airlines");
        assertThat(interviewInfo.getLocation()).isEqualTo("Texas");
        assertThat(interviewInfo.getRate()).isEqualTo("85$/hr");
        assertThat(interviewInfo.getVendor()).isEqualTo("Hire Talent");
        assertThat(interviewInfo.getFeedback()).isNull();
        assertThat(interviewInfo.getComments()).isNull();
        assertThat(interviewInfo.getDateCreated()).isEqualTo(dateTime);
        assertThat(interviewInfo.getLastUpdated()).isEqualTo(dateTime);
    }

    @Test
    public void shouldTestInterviewsInfoNoArgsConstructor() {
        // Act
        InterviewInfo interviewInfo = new InterviewInfo();

        // Assert
        assertThat(interviewInfo).isNotNull();
    }

    @Test
    public void shouldTestInterviewsInfoAllArgsConstructor() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        // Act
        InterviewInfo interviewInfo = new InterviewInfo(
                1L, "Tanuja Chokkapu", "Coding Test", date, dateTime.toString(),
                "Batreddi Pavan", "", "Java", "Direct Client - (MSV)",
                "American Airlines", "Texas", "85$/hr", "Hire Talent", null,
                null, dateTime, dateTime
        );

        // Assert
        assertThat(interviewInfo.getId()).isEqualTo(1L);
        assertThat(interviewInfo.getRecruiterName()).isEqualTo("Tanuja Chokkapu");
        assertThat(interviewInfo.getRound()).isEqualTo("Coding Test");
        assertThat(interviewInfo.getInterviewDate()).isEqualTo(date);
        assertThat(interviewInfo.getInterviewTime()).isEqualTo(dateTime.toString());
        assertThat(interviewInfo.getConsultantName()).isEqualTo("Batreddi Pavan");
        assertThat(interviewInfo.getOwnSupport()).isEqualTo("");
        assertThat(interviewInfo.getTechnology()).isEqualTo("Java");
        assertThat(interviewInfo.getClientType()).isEqualTo("Direct Client - (MSV)");
        assertThat(interviewInfo.getClientName()).isEqualTo("American Airlines");
        assertThat(interviewInfo.getLocation()).isEqualTo("Texas");
        assertThat(interviewInfo.getRate()).isEqualTo("85$/hr");
        assertThat(interviewInfo.getVendor()).isEqualTo("Hire Talent");
        assertThat(interviewInfo.getFeedback()).isNull();
        assertThat(interviewInfo.getComments()).isNull();
        assertThat(interviewInfo.getDateCreated()).isEqualTo(dateTime);
        assertThat(interviewInfo.getLastUpdated()).isEqualTo(dateTime);
    }

    @Test
    public void shouldTestInterviewsInfoEqualsAndHashCode() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        InterviewInfo interviewsInfo1 = InterviewInfo.builder()
                .id(1L)
                .recruiterName("Tanuja Chokkapu")
                .round("Coding Test")
                .interviewDate(date)
                .interviewTime(dateTime.toString())
                .consultantName("Batreddi Pavan")
                .ownSupport("")
                .technology("Java")
                .clientType("Direct Client - (MSV)")
                .clientName("American Airlines")
                .location("Texas")
                .rate("85$/hr")
                .vendor("Hire Talent")
                .feedback(null)
                .comments(null)
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        InterviewInfo interviewInfo2 = InterviewInfo.builder()
                .id(1L)
                .recruiterName("Tanuja Chokkapu")
                .round("Coding Test")
                .interviewDate(date)
                .interviewTime(dateTime.toString())
                .consultantName("Batreddi Pavan")
                .ownSupport("")
                .technology("Java")
                .clientType("Direct Client - (MSV)")
                .clientName("American Airlines")
                .location("Texas")
                .rate("85$/hr")
                .vendor("Hire Talent")
                .feedback(null)
                .comments(null)
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();

        // Act & Assert
        assertThat(interviewsInfo1).isEqualTo(interviewInfo2);
        assertThat(interviewsInfo1.hashCode()).isEqualTo(interviewInfo2.hashCode());
    }

    @Test
    public void shouldTestinterviewInfoToString() {
        // Arrange
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        InterviewInfo interviewInfo = InterviewInfo.builder()
                .id(1L)
                .recruiterName("Tanuja Chokkapu")
                .round("Coding Test")
                .interviewDate(date)
                .interviewTime(dateTime.toString())
                .consultantName("Batreddi Pavan")
                .ownSupport("")
                .technology("Java")
                .clientType("Direct Client - (MSV)")
                .clientName("American Airlines")
                .location("Texas")
                .rate("85$/hr")
                .vendor("Hire Talent")
                .feedback(null)
                .comments(null)
                .dateCreated(dateTime)
                .lastUpdated(dateTime)
                .build();
        // Act
        String result = interviewInfo.toString();

        // Assert
        assertThat(result).contains("InterviewInfo");
        assertThat(result).contains("id=1");
        assertThat(result).contains("recruiterName=Tanuja Chokkapu");
        assertThat(result).contains("round=Coding Test");
        assertThat(result).contains("interviewDate=" + date.toString());
        assertThat(result).contains("interviewTime=" + dateTime.toString());
        assertThat(result).contains("consultantName=Batreddi Pavan");
        assertThat(result).contains("ownSupport=");
        assertThat(result).contains("technology=Java");
        assertThat(result).contains("clientType=Direct Client - (MSV)");
        assertThat(result).contains("clientName=American Airlines");
        assertThat(result).contains("location=Texas");
        assertThat(result).contains("rate=85$/hr");
        assertThat(result).contains("vendor=Hire Talent");
        assertThat(result).contains("feedback=null");
        assertThat(result).contains("comments=null");
        assertThat(result).contains("dateCreated=" + dateTime.toString());
        assertThat(result).contains("lastUpdated=" + dateTime.toString());
    }
}
