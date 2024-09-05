package com.consultingfirm.userdetails.common;

import com.consultingfirm.userdetails.benchprofiles.dto.BenchProfiles;
import com.consultingfirm.userdetails.benchprofiles.model.BenchProfilesInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BenchProfilesInfoMock {

    private BenchProfilesInfoMock() {
    }

    public static BenchProfilesInfo shouldGetBenchProfilesInfo() {

        // Initialize sample data
        return BenchProfilesInfo.builder()
                .id(1L)
                .recruiterName("Mastan shaik")
                .consultantName("Muni Dinesh")
                .allocatedStatus("Assigned")
                .status("New")
                .turboCheck(80)
                .priority("Newly added")
                .technology("DevOps")
                .organization("Tek Ninjas")
                .experience("11")
                .location("TX")
                .relocation("Yes")
                .modeOfStaying("GuestHouse")
                .newOrExisting("New")
                .sourcedBy("Swaroop")
                .visaStatus("H1B Transfer")
                .marketingVisaStatus("H1B")
                .contactNumber("940-8437-379")
                .emailId("dineshmallipeddu09@gmail.com")
                .originalDob(LocalDate.parse("1989-06-21"))
                .marketingDob(LocalDate.parse("1989-06-21"))
                .whatsappNumber("9.391479789E9")
                .marketingStartDate(LocalDate.parse("2024-02-05"))
                .marketingEndDate(null)
                .comments(null)
                .dateCreated(LocalDateTime.parse("2024-07-11T20:57:31.974543"))
                .lastUpdated(LocalDateTime.parse("2024-07-11T20:57:31.974684"))
                .build();
    }

    public static BenchProfiles shouldCreateBenchProfilesDetails() {
        return new BenchProfiles(
                "Mastan shaik",
                "Muni Dinesh",
                "Assigned",
                "New",
                80,
                "Newly added",
                "DevOps",
                "Tek Ninjas",
                "11",
                "TX",
                "Yes",
                "GuestHouse",
                "New",
                "Swaroop",
                "H1B Transfer",
                "H1B",
                "940-8437-379",
                "dineshmallipeddu09@gmail.com",
                LocalDate.of(1989, 6, 21),
                LocalDate.of(1989, 6, 21),
                "9.391479789E9",
                LocalDate.of(2024, 2, 5),
                null,
                null,
                LocalDateTime.of(2024, 7, 11, 20, 57, 31),
                LocalDateTime.of(2024, 7, 11, 20, 57, 31)
        );
    }

    public static BenchProfilesInfo shouldCreateProfile() {
        BenchProfilesInfo profile = new BenchProfilesInfo();
        profile.setId(1L);
        profile.setRecruiterName("Mastan shaik");
        profile.setConsultantName("Muni Dinesh");
        profile.setAllocatedStatus("Assigned");
        profile.setStatus("New");
        profile.setTurboCheck(80);
        profile.setPriority("Newly added");
        profile.setTechnology("DevOps");
        profile.setOrganization("Tek Ninjas");
        profile.setExperience("11");
        profile.setLocation("TX");
        profile.setRelocation("Yes");
        profile.setModeOfStaying("GuestHouse");
        profile.setNewOrExisting("New");
        profile.setSourcedBy("Swaroop");
        profile.setVisaStatus("H1B Transfer");
        profile.setMarketingVisaStatus("H1B");
        profile.setContactNumber("940-8437-379");
        profile.setEmailId("dineshmallipeddu09@gmail.com");
        profile.setOriginalDob(LocalDate.of(1989, 6, 21));
        profile.setMarketingDob(LocalDate.of(1989, 6, 21));
        profile.setWhatsappNumber("9.391479789E9");
        profile.setMarketingStartDate(LocalDate.of(2024, 2, 5));
        profile.setDateCreated(LocalDateTime.of(2024, 7, 11, 20, 57, 31));
        profile.setLastUpdated(LocalDateTime.of(2024, 7, 11, 20, 57, 31));
        return profile;
    }

    public static BenchProfilesInfo shouldSetBenchProfilesInfo(BenchProfiles dto) {
        BenchProfilesInfo profile = new BenchProfilesInfo();
        profile.setRecruiterName(dto.recruiterName());
        profile.setConsultantName(dto.consultantName());
        profile.setAllocatedStatus(dto.allocatedStatus());
        profile.setStatus(dto.status());
        profile.setTurboCheck(dto.turboCheck());
        profile.setPriority(dto.priority());
        profile.setTechnology(dto.technology());
        profile.setOrganization(dto.organization());
        profile.setExperience(dto.experience());
        profile.setLocation(dto.location());
        profile.setRelocation(dto.relocation());
        profile.setModeOfStaying(dto.modeOfStaying());
        profile.setNewOrExisting(dto.newOrExisting());
        profile.setSourcedBy(dto.sourcedBy());
        profile.setVisaStatus(dto.visaStatus());
        profile.setMarketingVisaStatus(dto.marketingVisaStatus());
        profile.setContactNumber(dto.contactNumber());
        profile.setEmailId(dto.emailId());
        profile.setOriginalDob(dto.originalDob());
        profile.setMarketingDob(dto.marketingDob());
        profile.setWhatsappNumber(dto.whatsappNumber());
        profile.setMarketingStartDate(dto.marketingStartDate());
        profile.setDateCreated(dto.dateCreated());
        profile.setLastUpdated(dto.lastUpdated());
        return profile;
    }
}
