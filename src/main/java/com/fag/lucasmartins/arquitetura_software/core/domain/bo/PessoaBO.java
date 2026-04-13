package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class PessoaBO {

    private UUID id;
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;

    private PessoaBO() {
    }

    public static PessoaBO criar(
            String nomeCompleto,
            String cpf,
            LocalDate dataNascimento,
            String email,
            String telefone
    ) {
        PessoaBO pessoa = new PessoaBO();
        pessoa.id = UUID.randomUUID();

        pessoa.setNomeCompleto(nomeCompleto);
        pessoa.setCpf(cpf);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setEmail(email);
        pessoa.setTelefone(telefone);

        pessoa.validarMaioridade();
        pessoa.validarCpf();
        pessoa.validarEmail();
        pessoa.validarTelefone();

        return pessoa;
    }

    private void validarMaioridade() {
        if (this.dataNascimento == null) {
            throw new DomainException("A data de nascimento é obrigatória.");
        }
        int idade = Period.between(this.dataNascimento, LocalDate.now()).getYears();
        if (idade < 18) {
            throw new DomainException(
                    "Cadastro não permitido: o cliente deve ter no mínimo 18 anos. " +
                    "Idade informada: " + idade + " ano(s)."
            );
        }
    }

    private void validarCpf() {
        if (this.cpf == null || this.cpf.trim().isEmpty()) {
            throw new DomainException("O CPF é obrigatório.");
        }
        if (this.cpf.trim().length() != 11) {
            throw new DomainException(
                    "CPF inválido: deve conter exatamente 11 caracteres (sem pontos ou traços). " +
                    "Tamanho informado: " + this.cpf.trim().length() + "."
            );
        }
    }

    private void validarEmail() {
        if (this.email == null || this.email.trim().isEmpty()) {
            throw new DomainException("O e-mail é obrigatório.");
        }
        if (!this.email.contains("@")) {
            throw new DomainException(
                    "E-mail inválido: o endereço informado não contém '@'. Valor: " + this.email
            );
        }
    }

    private void validarTelefone() {
        if (this.telefone == null || this.telefone.trim().isEmpty()) {
            throw new DomainException("O telefone é obrigatório.");
        }
        if (this.telefone.trim().length() != 11) {
            throw new DomainException(
                    "Telefone inválido: deve conter exatamente 11 caracteres (sem parênteses ou traços). " +
                    "Tamanho informado: " + this.telefone.trim().length() + "."
            );
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new DomainException("O nome completo é obrigatório.");
        }
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
