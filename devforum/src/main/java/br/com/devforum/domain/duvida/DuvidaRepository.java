package br.com.devforum.domain.duvida;

import br.com.devforum.domain.StatusDuvida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuvidaRepository extends JpaRepository<Duvida, Long> {
    // Busca paginada de dúvidas por status
    Page<Duvida> findByStatus(StatusDuvida status, Pageable pageable);
}