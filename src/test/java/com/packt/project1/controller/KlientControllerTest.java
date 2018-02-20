package com.packt.project1.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class KlientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private KlientRepository klientRepository;

    @InjectMocks
    private KlientController klientController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(klientController)
                .build();
    }

    @Test
    public void test_get_all_success() throws Exception {
        List<Klient> klienci = Arrays.asList(
                new Klient(1, "Andrzej", "J", "똫ia쿮go 10", "783626262"),
                new Klient(2, "Anita", "J", "똫ia쿮go 10", "534254233"));
        when(klientRepository.findAll()).thenReturn(klienci);
        mockMvc.perform(get("/klient"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].imie", is("Andrzej")))
                .andExpect(jsonPath("$[0].nazwisko", is("J")))
                .andExpect(jsonPath("$[0].adres", is("똫ia쿮go 10")))
                .andExpect(jsonPath("$[0].telefon", is("783626262")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].imie", is("Anita")))
                .andExpect(jsonPath("$[1].nazwisko", is("J")))
                .andExpect(jsonPath("$[1].adres", is("똫ia쿮go 10")))
                .andExpect(jsonPath("$[1].telefon", is("534254233")));
        verify(klientRepository, times(1)).findAll();
        verifyNoMoreInteractions(klientRepository);
    }
    @Test
    public void test_get_by_id_success() throws Exception {
    	   Klient klient1 = new Klient(1, "Andrzej", "J", "똫ia쿮go 10", "783626262");
    	   Optional<Klient> klient1o = Optional.of(klient1);
           when(klientRepository.findById(1)).thenReturn(klient1o);

        mockMvc.perform(get("/klient/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.imie", is("Andrzej")))
                .andExpect(jsonPath("$.nazwisko", is("J")))
                .andExpect(jsonPath("$.adres", is("똫ia쿮go 10")))
                .andExpect(jsonPath("$.telefon", is("783626262")));

        verify(klientRepository, times(1)).findById(1);
        verifyNoMoreInteractions(klientRepository);
    }
    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {

        when(klientRepository.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/klient/{id}", 1))
                .andExpect(status().isNotFound());

        verify(klientRepository, times(1)).findById(1);
        verifyNoMoreInteractions(klientRepository);
    }
}
