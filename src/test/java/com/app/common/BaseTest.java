package com.app.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
public class BaseTest {

    protected final MockMvc mockMvc;

    protected final ObjectMapper objectMapper;

    protected final ModelMapper modelMapper;

    public BaseTest(MockMvc mockMvc, ObjectMapper objectMapper, ModelMapper modelMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }
}
