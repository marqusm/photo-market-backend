package com.marqus.photomarketbackend.controller;

import com.marqus.photomarketbackend.entity.Role;
import com.marqus.photomarketbackend.util.TestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayInputStream;

import static com.marqus.photomarketbackend.util.StringGenerator.generateRandomString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PhotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestHelper testHelper;

    @Test
    void upload_ShouldReturn201() throws Exception {
        var loginResponse = testHelper.registerAndLogin(Role.SELLER);
        var file = new MockMultipartFile(
                "content",
                "photo.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                new ByteArrayInputStream("fake image content".getBytes())
        );
        mockMvc.perform(multipart("/photos")
                        .file(file)
                        .param("name", generateRandomString(6))
                        .param("hashtag", "hash1")
                        .param("hashtag", "hash2")
                        .param("hashtag", "hash3")
                        .header("Authorization", "Bearer " + loginResponse.token())
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void upload_ShouldReturn403() throws Exception {
        var loginResponse = testHelper.registerAndLogin(Role.BUYER);
        var file = new MockMultipartFile(
                "content",
                "photo.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "fake image content".getBytes()
        );
        mockMvc.perform(multipart("/photos")
                        .file(file)
                        .param("name", generateRandomString(6))
                        .header("Authorization", "Bearer " + loginResponse.token())
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}