package com.diyar.studentapi;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping({"/", "/api/info"})
    public Map<String, String> info() {
        return Map.of(
                "application", "Student API",
                "student", "Diyar Bakhyt",
                "group", "IT2-2301",
                "studentId", "38310");
    }
}
