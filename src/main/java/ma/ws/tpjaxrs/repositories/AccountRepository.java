package ma.ws.tpjaxrs.repositories;

import ma.ws.tpjaxrs.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
