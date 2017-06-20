package in.expedite.wiki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.wiki.entity.Page;

public interface PageRepository extends JpaRepository<Page, Long>{

	Page findByUrlFriendlyTitle(String urlFriendlyTitle);
	
	List<Page> findByTitleLikeIgnoreCase(String title);
}

