package br.com.conexa.hospital.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CredenciaisDTO implements Serializable {

    private String usuario;
    private String senha;

    public CredenciaisDTO() {
    }
}
