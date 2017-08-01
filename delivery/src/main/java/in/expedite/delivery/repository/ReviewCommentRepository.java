package in.expedite.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import in.expedite.delivery.entity.ReviewComment;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

	List<ReviewComment> findByDeliveryIdOrderByCreatedDateDesc(Long deliveryId);
}
