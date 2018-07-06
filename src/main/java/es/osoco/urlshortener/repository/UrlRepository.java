package es.osoco.urlshortener.repository;

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
}
