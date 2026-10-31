package com.diyar.studentapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class StudentApiTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    StudentRepository repo;

    @Test
    void contextLoads() {
    }

    @Test
    void infoShowsStudentName() throws Exception {
        mvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.student").value("Wrong Name"));
    }

    @Test
    void createAndListStudents() throws Exception {
        String body = "{\"fullName\":\"Diyar Bakhyt\",\"email\":\"diyar.bakhyt@example.com\","
                + "\"studentId\":\"38310\",\"groupName\":\"IT2-2301\"}";
        mvc.perform(post("/api/students").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value("Diyar Bakhyt"));
        mvc.perform(get("/api/students")).andExpect(status().isOk());
    }

    @Test
    void unknownStudentReturns404() throws Exception {
        mvc.perform(get("/api/students/99999")).andExpect(status().isNotFound());
    }

    @Test
    void updateStudent() throws Exception {
        Student s = repo.save(new Student("Test Update", "t@example.com", "1", "IT2-2301"));
        String body = "{\"fullName\":\"Diyar Bakhyt Updated\",\"email\":\"t@example.com\","
                + "\"studentId\":\"38310\",\"groupName\":\"IT2-2301\"}";
        mvc.perform(put("/api/students/" + s.getId()).contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Diyar Bakhyt Updated"));
    }

    @Test
    void deleteStudent() throws Exception {
        Student s = repo.save(new Student("Test Delete", "d@example.com", "2", "IT2-2301"));
        mvc.perform(delete("/api/students/" + s.getId())).andExpect(status().isNoContent());
        mvc.perform(get("/api/students/" + s.getId())).andExpect(status().isNotFound());
    }
}
