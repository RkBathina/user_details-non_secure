package com.consultingfirm.userdetails.common;

import com.consultingfirm.userdetails.dailysubmissions.dto.DailySubmissions;
import com.consultingfirm.userdetails.dailysubmissions.model.DailySubmissionsInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailySubmissionsInfoMock {

    private DailySubmissionsInfoMock() {
    }

    //    {
//        "id": 2,
//            "dateOfEntry": "2024-01-02",
//            "recruiterName": "Sai Ravulapalli",
//            "consultantName": "Batreddi Pavan",
//            "technology": "Java",
//            "priority": "Remote",
//            "skill": "Java Developer",
//            "allocatedStatus": "Assigned",
//            "clientType": "Direct Client - (MSV)",
//            "clientName": "American Airlines",
//            "requirementSource": "Linkedin",
//            "feedback": null,
//            "comments": null,
//            "dateCreated": "2024-08-07T14:24:44.622703",
//            "lastUpdated": "2024-08-07T14:24:44.622746"
//    },
    public static DailySubmissionsInfo shouldGetDailySubmissionsInfo() {

        // Initialize sample data
        return DailySubmissionsInfo.builder()
                .id(1L)
                .dateOfEntry(LocalDate.parse("2024-01-02"))
                .recruiterName("Sai Ravulapalli")
                .consultantName("Batreddi Pavan")
                .technology("Java")
                .priority("Remote")
                .skill("Java Developer")
                .allocatedStatus("Assigned")
                .clientType("Direct Client - (MSV)")
                .clientName("American Airlines")
                .requirementSource("Linkedin")
                .feedback(null)
                .comments(null)
                .dateCreated(LocalDate.parse("2024-08-07").atStartOfDay())
                .lastUpdated(LocalDate.parse("2024-08-07").atStartOfDay())
                .build();
    }

    public static DailySubmissions shouldCreateDailySubmissionsDetails() {
        return new DailySubmissions(
                LocalDate.parse("2024-01-02"),
                "Sai Ravulapalli",
                "Batreddi Pavan",
                "Java",
                "Remote",
                "Java Developer",
                "Assigned",
                "Direct Client - (MSV)",
                "American Airlines",
                "Linkedin",
                null,
                null,
                LocalDateTime.of(2024, 7, 11, 20, 57, 31),
                LocalDateTime.of(2024, 7, 11, 20, 57, 31)
        );
    }

    public static DailySubmissionsInfo shouldCreateDailySubmissionsProfile() {
        DailySubmissionsInfo profile = new DailySubmissionsInfo();
        profile.setId(1L);
        profile.setDateOfEntry(LocalDate.parse("2024-01-02"));
        profile.setRecruiterName("Sai Ravulapalli");
        profile.setConsultantName("Batreddi Pavan");
        profile.setTechnology("Java");
        profile.setPriority("Remote");
        profile.setSkill("Java Developer");
        profile.setAllocatedStatus("Assigned");
        profile.setClientType("Direct Client - (MSV)");
        profile.setClientName("American Airlines");
        profile.setRequirementSource("Linkedin");
        profile.setFeedback(null);
        profile.setComments(null);
        profile.setDateCreated(LocalDateTime.of(2024, 7, 11, 20, 57, 31));
        profile.setLastUpdated(LocalDateTime.of(2024, 7, 11, 20, 57, 31));

        return profile;
    }

    public static DailySubmissionsInfo shouldSetDailySubmissionsInfo(DailySubmissions dto) {
        DailySubmissionsInfo profile = new DailySubmissionsInfo();
        profile.setDateOfEntry(dto.dateOfEntry());
        profile.setRecruiterName(dto.recruiterName());
        profile.setConsultantName(dto.consultantName());
        profile.setTechnology(dto.technology());
        profile.setPriority(dto.priority());
        profile.setSkill(dto.skill());
        profile.setAllocatedStatus(dto.allocatedStatus());
        profile.setClientType(dto.clientType());
        profile.setClientName(dto.clientName());
        profile.setRequirementSource(dto.requirementSource());
        profile.setFeedback(dto.feedback());
        profile.setComments(dto.comments());
        profile.setDateCreated(dto.dateCreated());
        profile.setLastUpdated(dto.lastUpdated());
        return profile;
    }
}
