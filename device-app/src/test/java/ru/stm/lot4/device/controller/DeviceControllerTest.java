package ru.stm.lot4.device.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.stm.lot4.device.service.DeviceService;
import ru.stm.lot4.dto.model.PhoneDTO;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DeviceService deviceService;

    @MockBean
    private EntityManagerFactory entityManagerFactory;

    ObjectMapper objectMapper = new ObjectMapper();

    private final String DEVICE_REST_PATH = "/device";

    @Test
    public void invalidPhoneCreate() throws Exception {
        PhoneDTO dto = new PhoneDTO();
        dto.setActive(true);
        dto.setNumber("+79877543498");
        //dto.setApplication(applicationRepository.getById(1L));
        this.mockMvc.perform(post(DEVICE_REST_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void invalidDeletePhone() throws Exception {
        this.mockMvc.perform(delete(DEVICE_REST_PATH + "/148"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void createPhoneNullApplication() throws Exception {
        PhoneDTO dto = new PhoneDTO();
        dto.setActive(true);
        dto.setNumber("+79898524597");
        dto.setApplication(null);
        this.mockMvc.perform(post(DEVICE_REST_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}