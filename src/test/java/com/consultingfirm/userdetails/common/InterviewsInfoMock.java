package com.consultingfirm.userdetails.common;

import com.consultingfirm.userdetails.interviews.dto.Interview;
import com.consultingfirm.userdetails.interviews.model.InterviewInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InterviewsInfoMock {

    private InterviewsInfoMock() {
    }

    //    {
//        "id": 1,
//            "recruiterName": "Tanuja Chokkapu",
//            "round": "Coding Test",
//            "interviewDate": "2024-01-04",
//            "interviewTime": "2:32 PM EST",
//            "consultantName": "Batreddi Pavan",
//            "ownSupport": "",
//            "technology": "Java",
//            "clientType": "Direct Client - (MSV)",
//            "clientName": "American Airlines",
//            "location": "Texas",
//            "rate": "85$/hr",
//            "vendor": "Hire Talent",
//            "feedback": null,
//            "comments": null,
//            "dateCreated": "2024-08-08T12:15:31.393962",
//            "lastUpdated": "2024-08-08T12:15:31.394079"
//    }


    public static InterviewInfo shouldGetInterviewInfo() {

        return InterviewInfo.builder()
                .id(1L)
                .recruiterName("Tanuja Chokkapu")
                .round("Coding Test")
                .interviewDate(LocalDate.parse("2024-01-04"))
                .interviewTime(String.valueOf(LocalDateTime.parse("2024-01-04T14:32:00")))
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
                .dateCreated(LocalDate.parse("2024-08-07").atStartOfDay())
                .lastUpdated(LocalDate.parse("2024-08-07").atStartOfDay())
                .build();
    }

    public static Interview shouldCreateInterviewDetails() {
        return new Interview(
                "Tanuja Chokkapu",
                "Coding Test",
                LocalDate.parse("2024-01-04"),
                "2:32 PM EST",
                "Batreddi Pavan",
                "",
                "Java",
                "Direct Client - (MSV)",
                "American Airlines",
                "Texas",
                "85$/hr",
                "Hire Talent",
                null,
                null,
                LocalDateTime.parse("2024-08-07T12:15:31.393962"),
                LocalDateTime.parse("2024-08-07T12:15:31.394079")
        );
    }

    public static InterviewInfo shouldCreateInterviewProfile() {
        InterviewInfo profile = new InterviewInfo();
        profile.setId(1L);
        profile.setRecruiterName("Tanuja Chokkapu");
        profile.setRound("Coding Test");
        profile.setInterviewDate(LocalDate.parse("2024-01-04"));
        profile.setInterviewTime("2:32 PM EST");
        profile.setConsultantName("Batreddi Pavan");
        profile.setOwnSupport("");
        profile.setTechnology("Java");
        profile.setClientType("Direct Client - (MSV)");
        profile.setClientName("American Airlines");
        profile.setLocation("Texas");
        profile.setRate("85$/hr");
        profile.setVendor("Hire Talent");
        profile.setFeedback(null);
        profile.setComments(null);
        profile.setDateCreated(LocalDateTime.of(2024, 7, 11, 20, 57, 31));
        profile.setLastUpdated(LocalDateTime.of(2024, 7, 11, 20, 57, 31));

        return profile;
    }

    public static InterviewInfo shouldSetInterviewInfo(Interview dto) {
        InterviewInfo profile = new InterviewInfo();
        profile.setRecruiterName(dto.recruiterName());
        profile.setRound(dto.round());
        profile.setInterviewDate(dto.interviewDate());
        profile.setInterviewTime(String.valueOf(dto.interviewTime()));
        profile.setConsultantName(dto.consultantName());
        profile.setOwnSupport(dto.ownSupport());
        profile.setTechnology(dto.technology());
        profile.setClientType(dto.clientType());
        profile.setClientName(dto.clientName());
        profile.setLocation(dto.location());
        profile.setRate(dto.rate());
        profile.setVendor(dto.vendor());
        profile.setFeedback(dto.feedback());
        profile.setComments(dto.comments());
        profile.setDateCreated(dto.dateCreated());
        profile.setLastUpdated(dto.lastUpdated());
        return profile;
    }
}
