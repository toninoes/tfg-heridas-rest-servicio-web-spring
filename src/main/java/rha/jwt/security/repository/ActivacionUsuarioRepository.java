package rha.jwt.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rha.jwt.model.security.ActivacionUsuario;

public interface ActivacionUsuarioRepository extends JpaRepository<ActivacionUsuario, Long> {

	ActivacionUsuario findByTokenActivacion(String tokenActivacion);
}
