package org.example.springboot2.web.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloResponseDtoTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void lombok_test() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

    @Test
    public void helloDto_test() throws Exception {
        String name = "hello";
        int amount = 1000;

        ResultActions resultActions = mvc.perform(
                        get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)));

        resultActions.andExpect(status().isOk())
                     .andDo(print())
                     .andExpect(jsonPath("$.name", is(name)))
                     .andExpect(jsonPath("$.amount", is(amount)));
    }
}
