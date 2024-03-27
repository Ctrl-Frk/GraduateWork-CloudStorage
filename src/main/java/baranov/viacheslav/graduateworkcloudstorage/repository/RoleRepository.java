package baranov.viacheslav.graduateworkcloudstorage.repository;

import baranov.viacheslav.graduateworkcloudstorage.model.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}