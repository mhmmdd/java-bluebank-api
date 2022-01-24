package com.bluebank.api.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void save() throws Exception {
        CustomerDTO initialDto = CustomerDTO.builder().build();
        CustomerDTO finalDto = CustomerDTO.builder().name("test").build();
        when(customerService.save(initialDto)).thenReturn(finalDto);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(initialDto)))
                .andExpect(content().json(mapper.writeValueAsString(finalDto)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getById() throws Exception {
        Long id = 1L;
        CustomerDTO finalDto = CustomerDTO.builder().id(id).name("test").build();
        when(customerService.getById(id)).thenReturn(finalDto);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(get("/api/v1/customer/" + id))
                .andExpect(content().json(mapper.writeValueAsString(finalDto)))
                .andExpect(status().is2xxSuccessful());
    }
}