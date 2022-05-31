package codeshare.code;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepository extends JpaRepository<Code, UUID> {

    List<Code> findAllByCreatorName(String userName);
    Page<Code> findAllByCreatorName(String userName, Pageable pageable);
}
