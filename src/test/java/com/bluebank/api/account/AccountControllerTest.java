package com.bluebank.api.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void createAccount() throws Exception {
        AccountDTO initialDto = AccountDTO.builder().build();
        AccountDTO finalDto = AccountDTO.builder().customerId(1L).build();
        when(accountService.createAccount(initialDto)).thenReturn(finalDto);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(initialDto)))
                .andExpect(content().json(mapper.writeValueAsString(finalDto)))
                .andExpect(status().is2xxSuccessful());
    }
}