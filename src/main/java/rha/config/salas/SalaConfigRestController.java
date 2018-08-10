package rha.config.salas;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salasconfigs")
public class SalaConfigRestController {
	
	@Autowired
	private SalaConfigService salaConfigService;
		
	@PostMapping("/{salaId}")	
	public ResponseEntity<SalaConfig> create(@PathVariable(value = "salaId") Long salaId, @Valid @RequestBody SalaConfig sC) {
		return salaConfigService.create(salaId, sC);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<SalaConfig> update(@PathVariable(value = "id") Long id, @Valid @RequestBody SalaConfig sC) {
		return salaConfigService.update(id, sC);
	}

}
