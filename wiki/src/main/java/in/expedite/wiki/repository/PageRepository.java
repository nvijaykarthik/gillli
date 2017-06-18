package in.expedite.wiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.wiki.entity.Page;

public interface PageRepository extends JpaRepository<Page, Long>{

	Page findByUrlFriendlyTitle(String urlFriendlyTitle);
}
