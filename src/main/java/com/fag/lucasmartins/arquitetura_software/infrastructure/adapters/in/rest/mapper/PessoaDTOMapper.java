package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PessoaRequestDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PessoaResponseDTO;


public class PessoaDTOMapper {

    private PessoaDTOMapper() {
    }

    public static PessoaBO toBo(PessoaRequestDTO dto) {
        return PessoaBO.criar(
                dto.getNomeCompleto(),
                dto.getCpf(),
                dto.getDataNascimento(),
                dto.getEmail(),
                dto.getTelefone()
        );
    }

    public static PessoaResponseDTO toResponseDto(PessoaBO bo) {
        PessoaResponseDTO dto = new PessoaResponseDTO();
        dto.setId(bo.getId());
        dto.setNomeCompleto(bo.getNomeCompleto());
        dto.setDataNascimento(bo.getDataNascimento());
        dto.setEmail(bo.getEmail());
        dto.setTelefone(bo.getTelefone());
        return dto;
    }
}
