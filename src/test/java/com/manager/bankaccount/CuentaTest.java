package com.manager.bankaccount;

import com.manager.bankaccount.entity.Cliente;
import com.manager.bankaccount.entity.Cuenta;
import com.manager.bankaccount.response.CuentaResponse;
import com.manager.bankaccount.service.ClienteService;
import com.manager.bankaccount.service.CuentaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CuentaTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CuentaService cuentaService;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void guardarCuentaTest() throws IOException, Exception {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        cliente.setContrasenia("123");
        cliente.setDireccion("Direccion");
        cliente.setEdad(25);
        cliente.setEstado("True");
        cliente.setGenero("Masculino");
        cliente.setIdentificacion("1717558904");
        cliente.setNombre("Hugo Mora");
        cliente.setTelefono("022587045");

        given(this.clienteService.getClienteByIdentificacion(Mockito.anyString())).willReturn(cliente);

        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(cliente);
        cuenta.setEstado("True");
        cuenta.setNumeroCuenta("12345");
        cuenta.setSaldo(new BigDecimal(1000));
        cuenta.setSaldoInicial(new BigDecimal(1000));
        cuenta.setTipoCuenta("Corriente");

        given(this.cuentaService.guardarCuenta(Mockito.any())).willReturn(cuenta);

        CuentaResponse cuentaResponse = new CuentaResponse();
        cuentaResponse.setEstado("True");
        cuentaResponse.setIdentificacionCliente("1717558904");
        cuentaResponse.setNumeroCuenta("12345");
        cuentaResponse.setSaldoInicial(new BigDecimal(1000));
        cuentaResponse.setTipoCuenta("Ahorro");

        this.mvc.perform(
                        post("/cuentas/save").contentType(MediaType.APPLICATION_JSON).content(JsonTest.toJson(cuentaResponse)))
                .andExpect(status().isOk());

        verify(this.cuentaService, VerificationModeFactory.times(1)).guardarCuenta(Mockito.any());
        reset(this.cuentaService);
    }

    @Test
    public void eliminarCuenta() throws Exception {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("12345");
        cuenta.setTipoCuenta("Ahorro");

        mvc.perform(
                        delete("/cuentas/delete/1").contentType(MediaType.APPLICATION_JSON).content(JsonTest.toJson(cuenta)))
                .andExpect(status().isOk());

        verify(this.cuentaService, VerificationModeFactory.times(1)).eliminarCuenta(Mockito.anyLong());
        reset(this.cuentaService);
    }

    @Test
    public void actualizarCuenta() throws Exception {
        Cuenta cuenta = new Cuenta();

        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        cliente.setContrasenia("123");
        cliente.setDireccion("Direccion");
        cliente.setEdad(25);
        cliente.setEstado("True");
        cliente.setGenero("Masculino");
        cliente.setIdentificacion("1717558904");
        cliente.setNombre("Hugo Mora");
        cliente.setTelefono("022587045");

        cuenta.setTipoCuenta("Corriente");
        cuenta.setCliente(cliente);
        cuenta.setCuentaId(1L);
        cuenta.setEstado("True");
        cuenta.setNumeroCuenta("12345");
        cuenta.setSaldo(new BigDecimal(100));
        cuenta.setSaldoInicial(new BigDecimal(100));

        given(this.cuentaService.getCuentaById(Mockito.anyLong())).willReturn(cuenta);

        given(this.clienteService.getClienteByIdentificacion(Mockito.anyString())).willReturn(cliente);

        CuentaResponse cuentaTo = new CuentaResponse();
        cuentaTo.setTipoCuenta("Ahorro");

        this.mvc.perform(
                        delete("/cuentas/delete/1").contentType(MediaType.APPLICATION_JSON).content(JsonTest.toJson(cuentaTo)))
                .andExpect(status().isOk());

        verify(this.cuentaService, VerificationModeFactory.times(1)).eliminarCuenta(Mockito.anyLong());
        reset(this.cuentaService);
    }
}
