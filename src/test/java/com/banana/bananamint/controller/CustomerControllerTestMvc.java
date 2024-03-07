package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.services.IncomeExpenseService;
import com.banana.bananamint.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTestMvc {

    @BeforeEach
    public void setUp() {
        Income aIncome = new Income(null, null, 500.00, LocalDate.now(), null, "active");
        Mockito.when(service.addIncome(Mockito.any(), Mockito.any()))
                .thenReturn(aIncome);
    }

    @Autowired
    private CustomerController incomeController;

    @MockBean
    private IncomeExpenseService service;

    @Autowired
    private MockMvc mvc;


    @Test
    void addIncome_OK() {
        // given
        Long cid= 1L;
        Long aid= 1L;

        Income aIncome = new Income(null, null, 500.00, LocalDate.now(), null, "active");

        // when + then
        ResponseEntity<Income> responseEntity = incomeController.addIncome(cid, aid, aIncome);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void addIncome_OK2() throws Exception {

        // given
        Long cid= 1L;
        Long aid= 1L;
        Income aIncome = new Income(null, null, 500.00, LocalDate.now(), null, "active");

        // when + then
        mvc.perform(post("/customer"+cid+"/account/"+aid+"/income")
                        .content(JsonUtil.asJsonString(aIncome))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
        ;
    }

}