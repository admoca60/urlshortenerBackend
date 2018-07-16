package es.osoco.urlshortener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import es.osoco.urlshortener.entity.Url;

/**
 * @author amolinca
 *
 */
@Component
public interface UrlRepository extends JpaRepository<Url, Long>{
	public Url findByHashCode(String hashCode);
	public List<Url> findByAllocated(boolean allocated);
}
