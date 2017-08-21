package in.expedite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.expedite.entity.ResetPassword;

public interface ResetPasswordRepo extends JpaRepository<ResetPassword, String> {

	ResetPassword findByLinkId(String linkId);
}
