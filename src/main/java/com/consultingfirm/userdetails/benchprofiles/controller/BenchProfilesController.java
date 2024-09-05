package com.consultingfirm.userdetails.benchprofiles.controller;

import com.consultingfirm.userdetails.benchprofiles.dto.BenchProfiles;
import com.consultingfirm.userdetails.benchprofiles.model.BenchProfilesInfo;
import com.consultingfirm.userdetails.benchprofiles.service.BenchProfilesService;
import com.consultingfirm.userdetails.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/non-secure/benchprofiles")
public class BenchProfilesController {

    private final BenchProfilesService benchProfilesService;

    public BenchProfilesController(BenchProfilesService benchProfilesService) {
        this.benchProfilesService = benchProfilesService;
    }

    @PostMapping(value = "/upload-bench-profiles-excel", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        benchProfilesService.uploadUserDetails(file);

        return new ResponseEntity<>("Excel data uploaded and inserted into database successfully.", HttpStatus.OK);
    }

    @PostMapping("/create-bench-profiles")
    public ResponseEntity<BenchProfilesInfo> createBenchProfileInfo(@Valid @RequestBody BenchProfiles benchProfiles) {
        var newBenchProfileInfo = benchProfilesService.createUserInfoDetails(benchProfiles);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBenchProfileInfo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Bench profiles User Details")
    public ResponseEntity<String> updateBenchProfileInfo(@PathVariable Long id, @RequestBody BenchProfilesInfo benchProfilesInfo) {
        benchProfilesService.updateUserDetails(id, benchProfilesInfo);
        return new ResponseEntity<>("User details updated successfully.", HttpStatus.OK);
    }

    @GetMapping("/fetch-users")
    @Operation(summary = "Fetch Bench profiles User Details")
    public ResponseEntity<List<BenchProfilesInfo>> fetchBenchProfileDetails() {
        Optional<List<BenchProfilesInfo>> users = benchProfilesService.getUserDetails();

        if (users.isEmpty() || users.get().isEmpty()) {
            throw new UserNotFoundException("No users found.");
        }

        return new ResponseEntity<>(users.get(), HttpStatus.OK);
    }

    @GetMapping("/fetch-users/{id}")
    @Operation(summary = "Fetch Bench profiles User Details by ID")
    public ResponseEntity<Optional<BenchProfilesInfo>> fetchBenchProfileDetailsByID(@PathVariable Long id) {
        Optional<Optional<BenchProfilesInfo>> users = benchProfilesService.getUserDetailsByID(id);

        return users.map(userDetails -> new ResponseEntity<>(userDetails, HttpStatus.OK))
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @DeleteMapping("delete-all-users")
    @Operation(summary = "Delete All Users")
    public ResponseEntity<HttpStatus> deleteAllUserInfo() {
        benchProfilesService.deleteAllUserInfo();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete-users/{id}")
    @Operation(summary = "Delete User By Id")
    public ResponseEntity<HttpStatus> deleteUserInfoById(@PathVariable("id") long id) {
        benchProfilesService.deleteUserInfoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
